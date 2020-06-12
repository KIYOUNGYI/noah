package app.noah.repository.pouch.category;

import app.noah.domain.QPouchCategory;
import app.noah.domain.QPouchProductMapping;
import app.noah.dto.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static app.noah.domain.QPouch.pouch;
import static app.noah.domain.QPouchCategory.pouchCategory;
import static app.noah.func.ResultFunc.getResult;

@Service
public class PouchCategoryRepositoryImpl implements PouchCategoryRepositoryCustom
{

    @Autowired
    private PouchCategoryRepository pouchCategoryRepository;

    private final JPAQueryFactory queryFactory;

    public PouchCategoryRepositoryImpl(EntityManager em)
    {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Map<String, Object> simpleSearchPouchCategory()
    {
        HashMap<String,Object> result = new HashMap<>();
        List<PouchCategoryDto> fetch = queryFactory.select(
                new QPouchCategoryDto(pouchCategory.idPouchCategory,
                        pouchCategory.pouchCategoryText)).from(pouchCategory)
                .orderBy(pouchCategory.sortKey.asc()).fetch();
        result.put("data",fetch);
        return result;
    }

    @Override
    public Map<String, Object> complexSearchPouchCategory(PouchCategorySearchCondition condition)
    {
        OrderSpecifier[] orderSpecifiers = orderByClause(condition.getSort());
        QueryResults<PouchCategoryDetailDto> contents = queryFactory.select(new QPouchCategoryDetailDto
                (
                        pouchCategory.sortKey,
                        pouchCategory.pouchCategoryText,
                        pouchCategory.pouches.size(),
                        pouchCategory.isDisplay
                )).from(pouchCategory)
                .orderBy(orderSpecifiers)
                .offset(condition.getOffset())
                .limit(condition.getLimit())
                .fetchResults();

        return getResult(contents,true);
    }

    private OrderSpecifier[] orderByClause(Integer id)
    {
        List<OrderSpecifier> orderBy = new LinkedList<>();
        try {

            if (id == 1)
            {
                orderBy.add(pouchCategory.createDate.desc());//최신등록순
            }
            else if (id == 2)
            {
                orderBy.add(pouchCategory.createDate.asc());//이전등록순
            }
            else if (id == 3)
            {
                orderBy.add(pouchCategory.isDisplay.desc());//전시순
            }
            else if (id == 4)
            {
                orderBy.add(pouchCategory.isDisplay.asc());//비전시순
             }
            else if (id == 5)
            {
                orderBy.add(pouchCategory.sortKey.asc());//sortKey 오름차순
            }
            else if (id == 6)//카테고리역순
            {
                orderBy.add(pouchCategory.sortKey.desc());//sortKey 내림차순
            }
            else if (id == 0)
            {
                orderBy.add(pouchCategory.sortKey.asc());//sortKey 없음
            }
            int len = orderBy.size();

            OrderSpecifier[] orders = new OrderSpecifier[len];

            for (int i = 0; i < len; i++) orders[i] = orderBy.get(i);

            return orders;
        }
        catch (Exception e)// worst case 예외처리
        {
            System.out.println("orderByClause error : "+ e);
            OrderSpecifier[] orders = new OrderSpecifier[1];
            orders[0] = pouchCategory.sortKey.asc();
            return orders;
        }
    }

    @Override
    public Map<String, Object> getSummary()
    {
        HashMap<String,Object> result = new HashMap<>();
        List<Tuple> fetch = queryFactory.select(pouchCategory.count(),
                                                queryFactory.select(pouchCategory.count())
                                                        .from(pouchCategory)
                                                        .where(pouchCategory.isDisplay.eq(true)),
                                                queryFactory.select(pouchCategory.count())
                                                        .from(pouchCategory)
                                                        .where(pouchCategory.isDisplay.eq(false))
                                                )
                                        .from(pouchCategory).fetch();
        Tuple tuple = fetch.get(0);
        Long total = tuple.get(0,Long.class);
        Long active = tuple.get(1,Long.class);
        Long inactive = tuple.get(2,Long.class);
        result.put("total",total);
        result.put("active",active);
        result.put("inactive",inactive);
        return result;

    }


}
