package app.noah.repository.pouch;

import app.noah.dto.PouchDto;
import app.noah.dto.PouchSearchCondition;
import app.noah.repository.Pouch.PouchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class PouchRepositoryTest
{
    @Autowired
    PouchRepository pouchRepository;

    @Autowired
    EntityManager em;

    @Test
    public void di(){}

    @Test
    public void baicSearch()
    {
        List<PouchDto> result = pouchRepository.search(null);
        System.out.println("result:"+result.toString());
    }

    @Test
    public void searchPageSimple()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent(null);
        psc.setOffset(0l);
        psc.setLimit(10l);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println(simple.toString());
    }

}
