package app.noah.repository.pouch;

import app.noah.domain.Pouch;
import app.noah.domain.PouchCategory;
import app.noah.domain.PouchProductMapping;
import app.noah.domain.glowpickorm.AdminAccount;
import app.noah.domain.glowpickorm.Product;
import app.noah.dto.*;
import app.noah.repository.AdminAccountRepository;
import app.noah.repository.pouch.category.PouchCategoryRepository;
//import app.noah.repository.pouch.pouchproduct.PouchProductRepository;
import app.noah.repository.pouch.pouchproduct.PouchProductMappingRepository;
import app.noah.utils.LocalDateTimeUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.*;

import static app.noah.domain.QPouchProductMapping.pouchProductMapping;
import static app.noah.domain.QPouch.pouch;
import static app.noah.func.ResultFunc.*;

public class PouchRepositoryImpl implements PouchRepositoryCustom{

    @Autowired private PouchRepository pouchRepository;
    @Autowired private PouchCategoryRepository pouchCategoryRepository;
    @Autowired private AdminAccountRepository adminAccountRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private PouchProductMappingRepository pouchProductMappingRepository;

    private final JPAQueryFactory queryFactory;

    public PouchRepositoryImpl(EntityManager em)
    {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<PouchDto> search(PouchSearchCondition condition)
    {
        List<PouchDto> result = queryFactory.select(new QPouchDto(
                pouch.id,
                pouch.pouchCategory.pouchCategoryText.as("category"),
                pouch.isDisplay,
                pouch.pouchTitle,
                pouch.createDate,
                pouch.startDate,
                pouch.pouchProductMappingsList.size(),
                pouch.readCount,
                pouch.editerPick,
                pouch.adminAccount.idRegister,
                pouch.adminAccount.nickName,
                pouch.recommendCount,
                pouch.pouchCommentList.size(),
                pouch.fileOrgName,pouch.fileSize,pouch.fileSaveName, pouch.fileDir,pouch.fileType
        )).from(pouch).where().fetch();

        return result;
    }

    /**
     *
     * 검색조건
     * [1] 제목 like
     * [2] 내용 like
     * [3] 정렬조건 -> 최신등록순,이전등록순,전시순,비전시순,카테고리순,카테고리역순,추천많은순,추천적은순,댓글많은순,댓글적은순
     * [4] 개수 ->  10,20,30,40,50 개 + 페이징 처리
     * @param condition
     * @return
     */
    @Override
    public Map<String, Object> searchPageSimple(PouchSearchCondition condition)
    {

        OrderSpecifier[] orderSpecifiersArr = orderByClause(condition.getSort());
        QueryResults<PouchDto> contents = queryFactory.select(new QPouchDto(
                pouch.id, pouch.pouchCategory.pouchCategoryText.as("category"), pouch.isDisplay, pouch.pouchTitle,
                pouch.createDate, pouch.startDate, pouch.pouchProductMappingsList.size(), pouch.hitsCount,
                pouch.editerPick, pouch.adminAccount.idRegister, pouch.adminAccount.nickName, pouch.recommendCount,
                pouch.pouchCommentList.size(), pouch.fileOrgName, pouch.fileSize,pouch.fileSaveName, pouch.fileDir,pouch.fileType
        )).from(pouch).
                where(
                        pouchTitleContains(condition.getTitle()),
                        pouchContentContains(condition.getContent()),
                        pouchPeriodStart(condition.getStartDate()),
                        pouchPeriodEnd(condition.getEndDate()),
                        pouch.orderNum.isNotNull()
                )
                .orderBy(orderSpecifiersArr)
                .offset(condition.getOffset())
                .limit(condition.getLimit())
                .fetchResults();
        return getResult(contents,true);
    }

    @Override
    public Map<String, Object> searchPageComplex(PouchSearchCondition condition) {
        return null;
    }

    @Override
    public Map<String, Object> getPouchDetail(Long idPouch)
    {

        Map<String, Object> result = new HashMap<>();

        PouchDto pouchDto = queryFactory.select(new QPouchDto(
                pouch.id, pouch.pouchCategory.pouchCategoryText.as("category"),pouch.isDisplay,
                pouch.pouchTitle, pouch.createDate, pouch.startDate,
                pouch.pouchProductMappingsList.size(), pouch.hitsCount,
                pouch.editerPick, pouch.adminAccount.idRegister, pouch.adminAccount.nickName,
                pouch.recommendCount, pouch.pouchCommentList.size(),
                pouch.fileOrgName,pouch.fileSize,pouch.fileSaveName, pouch.fileDir,pouch.fileType
        )).from(pouch).
                where(pouch.id.eq(idPouch)).fetchFirst();
        result.put("data",pouchDto);

        List<PouchProductDto> products = queryFactory.select(
                new QPouchProductDto(
                        pouchProductMapping.pouchProduct.idProduct, pouchProductMapping.pouchProduct.productTitle,
                        pouchProductMapping.pouchProduct.fileOrgName, pouchProductMapping.pouchProduct.fileSaveName,
                        pouchProductMapping.pouchProduct.fileDir, pouchProductMapping.pouchProduct.fileSize,
                        pouchProductMapping.pouchProduct.fileType
                ))
                .from(pouchProductMapping)
                .where(pouchProductMapping.pouch.id.eq(idPouch)).fetch();


        result.put("products:",products);

        return result;
    }


    /**
     * 캐스트 등록 or 업데이트
     * if idEvent is null:
     *  'insert'
     * else:
     *  'update'
     * @param pouchRequestDto
     * @return
     */
    @Transactional
    public Map<String, Object> insertOrUpdatePouch(PouchRequestDto pouchRequestDto)
    {
        Map<String, Object> result = new HashMap<>();
        Pouch pouch = null;
        Long id = pouchRequestDto.getIdPouch();

        if(id!=null)//update
        {
            pouch =createPouchObj(pouchRequestDto, id);
//            System.out.println(">>>> update : "+ pouch.toString());
            pouchRepository.save(pouch);
            updatePouchProducts(pouchRequestDto,pouch);
        }
        else//insert
        {
            pouch = createPouchObj(pouchRequestDto,id);
            pouchRepository.save(pouch);
            savePouchProducts(pouchRequestDto, pouch);
        }
        result.put("data", pouch.getId());

        return result;
    }

    /**
     * 등록, 활성, 비활성 개수 반환
     * @return
     */
    @Override
    public Map<String, Object> getSummary()
    {
        HashMap<String,Object> result = new HashMap<>();

        List<Tuple> fetch = queryFactory.select(
                pouch.count(),//[1] total
                queryFactory.select(pouch.count()).from(pouch)
                                                  .where(pouch.isDisplay.eq(true)),//[2] active
                queryFactory.select(pouch.count())
                            .from(pouch)
                            .where(pouch.isDisplay.eq(false))//[3] inactive
                )
                .from(pouch).fetch();
        Tuple tuple = fetch.get(0);
        Long total = tuple.get(0,Long.class);
        Long active = tuple.get(1,Long.class);
        Long inactive = tuple.get(2,Long.class);

        result.put("total",total);
        result.put("active",active);
        result.put("inactive",inactive);
        return result;
    }

    /**
     *
     * @param pouchRequestDto
     * @param pouch
     *
     * 로직요약
     *
     */
    private void updatePouchProducts(PouchRequestDto pouchRequestDto, Pouch pouch)
    {
        Long idPouch = pouch.getId();
        List<Long> givenList = pouchRequestDto.getProducts();
        //[3]
        Map<String, Object> productsByPouchId = pouchProductMappingRepository.findByPouchId(idPouch);
        List<Long> dbList = (List<Long>) productsByPouchId.get("data");

        ArrayList<Long> insertIdList = new ArrayList<>();
        ArrayList<Long> deleteIdList = new ArrayList<>();

        for(Long givenId:givenList)
        {
            Long pivot = givenId;
            if(!dbList.contains(pivot))
            {
                insertIdList.add(pivot);
            }
        }
        for(Long givenId:dbList)
        {
            Long pivot = givenId;
            if(!givenList.contains(pivot))
            {
                deleteIdList.add(pivot);
            }
        }
//        System.out.println(">>> insertIdList : "+insertIdList.toString());
//        System.out.println(">>> deleteIdList : "+deleteIdList.toString());

        for(Long id:insertIdList)
        {
            Product pd = productRepository.findById(id).get();
            String now = LocalDateTimeUtil.getLocalDateTimeForFileName(LocalDateTime.now());
            PouchProductMapping pmd = new PouchProductMapping(pouch,pd,now);
            PouchProductMapping save = pouchProductMappingRepository.save(pmd);
//            System.out.println(">>>> saved : "+save.toString());
        }
        for(Long idProduct:deleteIdList)
        {
            pouchProductMappingRepository.deletePouchProductByIdPouchAndIdProduct(idPouch,idProduct);
        }
    }



    private void savePouchProducts(PouchRequestDto pouchRequestDto, Pouch pouch) {
        List<Long> products = pouchRequestDto.getProducts();
        for(Long productId : products)
        {
            Product pd = productRepository.findById(productId).get();
            String now = LocalDateTimeUtil.getLocalDateTimeForFileName(LocalDateTime.now());
            PouchProductMapping pmd = new PouchProductMapping(pouch,pd,now);
            pouchProductMappingRepository.save(pmd);
        }
    }


    private Pouch createPouchObj(PouchRequestDto pouchRequestDto, Long id)
    {
        Pouch pouchObj;
        ImageContentDto imageContentDto = new ImageContentDto
                (
                        pouchRequestDto.getImageContentDto().getOriginalFileName(),
                        pouchRequestDto.getImageContentDto().getFileSize(),
                        pouchRequestDto.getImageContentDto().getUploadFileName(),
                        pouchRequestDto.getImageContentDto().getFilePath(),
                        pouchRequestDto.getImageContentDto().getFileType()
                );
        //[1]
        PouchCategory pouchCategory = pouchCategoryRepository.findById(pouchRequestDto.getIdPouchCategory()).get();
        //[2]
        AdminAccount adminAccount = adminAccountRepository.findById(pouchRequestDto.getIdRegister()).get();
        String createDate = LocalDateTimeUtil.getLocalDateTimeForFileName(LocalDateTime.now());

        //[3]
        Integer orderNum = queryFactory.select(pouch.orderNum).from(pouch).where(pouch.orderNum.isNotNull()).orderBy(pouch.orderNum.desc()).fetchFirst();
//        System.out.println(">>>>>>> orderNum : " + orderNum);

        if(id==null)
        {
//            System.out.println(">>>>>>> create");
            pouchObj = new Pouch(
                                adminAccount,pouchRequestDto.getIsDisplay(),pouchCategory,
                                pouchRequestDto.getTitle(), pouchRequestDto.getContent(),
                                imageContentDto, createDate, pouchRequestDto.getOpenDate(),(orderNum+1)
                              );
        }
        else//UPDATE
        {
//            System.out.println(">>>>>>> update");
            pouchObj = new Pouch(id,adminAccount, pouchRequestDto.getIsDisplay(), pouchCategory,
                               pouchRequestDto.getTitle(),pouchRequestDto.getContent(),
                               imageContentDto, createDate, pouchRequestDto.getOpenDate()
                            );
        }
        return pouchObj;
    }


    private BooleanExpression pouchPeriodStart(String startDate)
    {
        return StringUtils.isEmpty(startDate)? null : pouch.createDate.gt(startDate);
    }

    private BooleanExpression pouchPeriodEnd(String endDate)
    {
        return StringUtils.isEmpty(endDate)? null : pouch.createDate.lt(endDate);
    }

    /**
     * mysql 제목 검색(mysql)
     * @param pouchTitle
     * @return
     */
    private BooleanExpression pouchTitleContains(String pouchTitle)
    {
        return StringUtils.isEmpty(pouchTitle) ? null:pouch.pouchTitle.contains(pouchTitle);
    }

    /**
     * 내용 like 검색(mysql)
     * @param content
     * @return
     */
    private BooleanExpression pouchContentContains(String content)
    {
        return StringUtils.isEmpty(content) ? null:pouch.pouchText.contains(content);
    }


    private OrderSpecifier[] orderByClause(Integer id)
    {

        List<OrderSpecifier> orderBy = new LinkedList<>();
        try
        {
            if (id == 1)
            {
                orderBy.add(pouch.createDate.desc());
            }
            else if (id == 2)//이전등록순
            {
                orderBy.add(pouch.createDate.asc());
            }
            else if (id == 3)//전시순
            {
                orderBy.add(pouch.isDisplay.desc());
                orderBy.add(pouch.createDate.desc());
            }
            else if (id == 4)//비전시순
            {
                orderBy.add(pouch.isDisplay.asc());
                orderBy.add(pouch.createDate.desc());
            }
            else if (id == 5)//카테고리순
            {
                orderBy.add(pouch.pouchCategory.idPouchCategory.asc());
                orderBy.add(pouch.pouchCategory.createDate.desc());
            }
            else if (id == 6)//카테고리역순
            {
                orderBy.add(pouch.pouchCategory.idPouchCategory.desc());
                orderBy.add(pouch.pouchCategory.createDate.desc());
            }
            else if (id == 7)//추천많은순
            {
                orderBy.add(pouch.recommendCount.desc());
                orderBy.add(pouch.createDate.desc());
            }
            else if (id == 8)//추천적은순
            {
                orderBy.add(pouch.recommendCount.asc());
                orderBy.add(pouch.createDate.desc());
            }
            else if (id == 9)//댓글많은순
            {
                orderBy.add(pouch.pouchCommentList.size().desc());
                orderBy.add(pouch.createDate.desc());
            }
            else if (id == 10)//댓글적은순
            {
                orderBy.add(pouch.pouchCommentList.size().asc());
                orderBy.add(pouch.createDate.desc());
            }
            else if (id == 11)//정렬순
            {
                orderBy.add(pouch.orderNum.desc());
            }

            int len = orderBy.size();

            if(len==0)
            {
//                throw new Exception();
                orderBy.add(pouch.orderNum.desc());
            }

            OrderSpecifier[] orders = new OrderSpecifier[len];
            for (int i = 0; i < len; i++)
            {
                orders[i] = orderBy.get(i);
            }
            return orders;
        }
        catch (Exception e)// worst case 예외처리
        {
            System.out.println("orderByClause error : "+ e);
            OrderSpecifier[] orders = new OrderSpecifier[1];
            orders[0] = pouch.createDate.desc();
            return orders;
        }
    }
}
