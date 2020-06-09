package app.noah.repository.Pouch;

import app.noah.domain.Pouch;
import app.noah.domain.PouchProductMapping;
import app.noah.domain.QPouchProductMapping;
import app.noah.dto.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.*;

import static app.noah.domain.QPouchProductMapping.pouchProductMapping;
import static app.noah.domain.QPouch.pouch;
import static app.noah.func.ResultFunc.*;

public class PouchRepositoryImpl implements PouchRepositoryCustom{

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
     * 개수->10개,20개,30개,40개,50
     * 정렬순->최신등록순,이전등록순,전시순,비전시순,카테고리순,카테고리역순,추천많은순,추천적은순,댓글많으눗ㄴ,댓글적은순
     * 검색조건
     * [1] 제목 like
     * [2] 내용 like
     * [3] 작성일->시작~끝
     * [4] 정렬조건 11개
     * @param condition
     * @return
     */
    @Override
    public Map<String, Object> searchPageSimple(PouchSearchCondition condition)
    {

        OrderSpecifier[] orderSpecifiersArr = orderByClause(condition.getSort());
        QueryResults<PouchDto> contents = queryFactory.select(new QPouchDto(
                pouch.id, pouch.pouchCategory.pouchCategoryText.as("category"), pouch.isDisplay,
                pouch.pouchTitle, pouch.createDate, pouch.startDate,
                pouch.pouchProductMappingsList.size(), pouch.hitsCount,
                pouch.editerPick, pouch.adminAccount.idRegister, pouch.adminAccount.nickName,
                pouch.recommendCount, pouch.pouchCommentList.size(),
                pouch.fileOrgName,pouch.fileSize,pouch.fileSaveName, pouch.fileDir,pouch.fileType
        )).from(pouch).
                where(
                        pouchTitleContains(condition.getTitle()),
                        pouchContentContains(condition.getContent()),
                        pouchPeriodStart(condition.getStartDate()),
                        pouchPeriodEnd(condition.getEndDate())
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

/**
 *     $list = Yii::$app->db->createCommand("SELECT p.idPouch, p.idPouchCategory, pc.pouchCategoryText, p.pouchImg, p.pouchTitle, p.create_date, p.orderNum, p.hits_count, p.idRegister,
 *             (SELECT nickName FROM adminAccount AS ac WHERE ac.idRegister=p.idRegister) AS nickname, "
 *             . "(SELECT COUNT(m.idProduct) FROM pouchproductmapping m WHERE p.idPouch=m.idPouch) productCount, p.editerPick, p.recommendCount, "
 *             . "(SELECT COUNT(*) FROM pouchcomment c WHERE p.idPouch=c.idPouch) commentCount, p.isDisplay, "
 *             . "p.fileOrgName, p.fileSaveName, p.fileDir, p.fileSize, p.fileType "
 *             . "FROM pouch p, adminAccount r, pouchcategory pc "
 *             . "WHERE p.idRegister=r.idRegister AND p.idPouchCategory=pc.idPouchCategory$where "
 *             . "ORDER BY $order "
 *             . "LIMIT $pages->offset, $pages->limit")->queryAll();
 */