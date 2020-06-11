package app.noah.repository.pouch;

import app.noah.repository.pouch.pouchproduct.PouchProductMappingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class PouchProductMappingSelectTest
{
    @Autowired
    PouchProductMappingRepository pouchProductMappingRepository;


    @Autowired
    EntityManager em;

    @Test
    public void di(){}

    @Test
    public void 조회테스트()
    {
        Long idPouch = 1063l;
        Map<String, Object> byPouchId = pouchProductMappingRepository.findByPouchId(idPouch);
        System.out.println(">>>byPouchId : " + byPouchId);
        List<Long> given = (List<Long>) byPouchId.get("data");
        System.out.println(">>>given : "+ given.toString());
    }
}
