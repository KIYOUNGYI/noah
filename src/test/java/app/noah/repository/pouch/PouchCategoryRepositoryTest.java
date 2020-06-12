package app.noah.repository.pouch;

import app.noah.dto.PouchCategorySearchCondition;
import app.noah.repository.pouch.category.PouchCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Map;

@SpringBootTest
@Transactional
public class PouchCategoryRepositoryTest
{
    @Autowired
    PouchCategoryRepository pouchCategoryRepository;

    @Autowired
    EntityManager em;

    @Test
    public void di(){}

    @Test
    public void 캐스트_카테고리_조회()
    {
        Map<String, Object> stringObjectMap = pouchCategoryRepository.simpleSearchPouchCategory();
        System.out.println(">>>> result: "+stringObjectMap.toString());
    }

    @Test
    public void 캐스트_카테고리_조회_페이지용()
    {
        PouchCategorySearchCondition condition = new PouchCategorySearchCondition();
        condition.setOffset(0l);
        condition.setLimit(10l);
        condition.setSort(1);
        Map<String, Object> stringObjectMap = pouchCategoryRepository.complexSearchPouchCategory(condition);
        System.out.println(">>>> result: "+stringObjectMap.toString());
    }

    @Test
    public void 캐스트_상태_조회()
    {
        Map<String,Object> stringObjectMap = pouchCategoryRepository.getSummary();
        System.out.println(">>>> result: "+stringObjectMap.toString());
    }
}
