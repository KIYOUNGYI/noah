package app.noah.repository.pouch.pouchproduct;

import app.noah.domain.PouchProductMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static app.noah.domain.QPouchProductMapping.pouchProductMapping;
public class PouchProductMappingRepositoryImpl implements PouchProductMappingCustom {



    private final JPAQueryFactory queryFactory;

    public PouchProductMappingRepositoryImpl(EntityManager em)
    {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Map<String, Object> findByPouchId(Long pouchId)
    {
        HashMap<String, Object> result = new HashMap<>();
        List<PouchProductMapping> list = queryFactory.selectFrom(pouchProductMapping)
                .where(pouchProductMapping.pouch.id.eq(pouchId)).fetch();

        System.out.println("findByPouchId result : "+list.toString());

        return result;
    }
}
