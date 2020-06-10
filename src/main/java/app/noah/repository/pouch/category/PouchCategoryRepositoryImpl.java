package app.noah.repository.pouch.category;

import app.noah.domain.QPouchCategory;
import app.noah.domain.QPouchProductMapping;
import app.noah.dto.PouchCategoryDto;
import app.noah.dto.QPouchCategoryDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.noah.domain.QPouchCategory.pouchCategory;

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
}
