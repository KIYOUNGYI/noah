package app.noah.repository.pouch;

import app.noah.domain.PouchCategory;
import app.noah.dto.PouchCategoryRequestDto;
import app.noah.dto.PouchCategorySearchCondition;
import app.noah.repository.pouch.category.PouchCategoryRepository;
import app.noah.service.PouchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Transactional
public class PouchCategoryRepositoryTest
{
    @Autowired
    PouchCategoryRepository pouchCategoryRepository;
    @Autowired
    PouchService pouchService;

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

    @Test
    public void 캐스트_카테고리_등록()
    {
        PouchCategoryRequestDto dto = new PouchCategoryRequestDto("tt",11l);

        PouchCategory category = new PouchCategory(dto.getPouchCategoryTitle(),dto.getSortKey());
        PouchCategory save = pouchCategoryRepository.save(category);
        System.out.println(">>>>> save : "+save.toString());

        Map<String, Object> stringObjectMap = pouchCategoryRepository.simpleSearchPouchCategory();
        System.out.println(">>>> result: "+stringObjectMap.toString());
    }

    @Test
    public void 캐스트_카테고리_수정()
    {

        PouchCategory pouchCategory = pouchCategoryRepository.findById(23l).get();
        PouchCategoryRequestDto dto = new PouchCategoryRequestDto("dummy",11l,false);

        Map<String, Object> stringObjectMap = pouchService.updatePouchCategory(23l, dto);
        System.out.println(">>> return value : "+stringObjectMap.toString());

        PouchCategory modified = pouchCategoryRepository.findById(23l).get();
        System.out.println(">>>> modified result :"+ modified.toString());
    }

}
