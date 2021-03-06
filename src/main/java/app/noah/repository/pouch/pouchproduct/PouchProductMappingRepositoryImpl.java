package app.noah.repository.pouch.pouchproduct;

import app.noah.domain.PouchProductMapping;
import app.noah.dto.PouchProductDto;
import app.noah.dto.QPouchProductDto;
import com.querydsl.jpa.impl.JPADeleteClause;
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
    public Map<String, Object> findByPouchId(Long idPouch)
    {
        HashMap<String, Object> result = new HashMap<>();
        List<Long> list = queryFactory.select(pouchProductMapping.pouchProduct.idProduct).from(pouchProductMapping)
                .where(pouchProductMapping.pouch.id.eq(idPouch)).fetch();
        result.put("data",list);
        return result;
    }

    @Override
    public Map<String, Object> deletePouchProductByIdPouchAndIdProduct(Long idPouch, Long idProduct)
    {
        HashMap<String,Object> result = new HashMap<>();
        long data = queryFactory.delete(pouchProductMapping)
                .where(pouchProductMapping.pouch.id.eq(idPouch),
                        pouchProductMapping.pouchProduct.idProduct.eq(idProduct)).execute();

        result.put("data",data);
        return result;
    }

}
