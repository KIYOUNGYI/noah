package app.noah.repository.Pouch;

import app.noah.domain.QPouch;
import app.noah.dto.PouchDto;
import app.noah.dto.PouchSearchCondition;
import app.noah.dto.QPouchDto;
import app.noah.func.ResultFunc;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                pouch.pouchTitle,
                pouch.createDate,
                pouch.startDate,
                pouch.pouchProductMappingsList.size(),
                pouch.readCount,
                pouch.editerPick,
                pouch.adminAccount.idRegister,
                pouch.adminAccount.nickName,
                pouch.recommendCount,
                pouch.pouchCommentList.size()
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
     * @param condition
     * @return
     */
    @Override
    public Map<String, Object> searchPageSimple(PouchSearchCondition condition)
    {

        QueryResults<PouchDto> contents = queryFactory.select(new QPouchDto(
                pouch.id,
                pouch.pouchCategory.pouchCategoryText.as("category"),
                pouch.pouchTitle,
                pouch.createDate,
                pouch.startDate,
                pouch.pouchProductMappingsList.size(),
                pouch.readCount,
                pouch.editerPick,
                pouch.adminAccount.idRegister,
                pouch.adminAccount.nickName,
                pouch.recommendCount,
                pouch.pouchCommentList.size()
        )).from(pouch).
                where(pouchTitleContains(condition.getTitle()), pouchContentContains(condition.getContent()))
                .offset(condition.getOffset())
                .limit(condition.getLimit())
                .orderBy(pouch.id.desc())
                .fetchResults();


        return getResult(contents,true);


    }

    @Override
    public Map<String, Object> searchPageComplex(PouchSearchCondition condition) {
        return null;
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

    private BooleanExpression periodContains(String startDate, String endDate)
    {
        return null;
    }
}
