package app.noah.repository.pouch;


import app.noah.domain.Pouch;
import app.noah.dto.ImageContentDto;
import app.noah.dto.PouchRequestDto;
import app.noah.repository.pouch.category.PouchCategoryRepository;
import app.noah.repository.pouch.pouchproduct.PouchProductMappingRepository;
import app.noah.utils.LocalDateTimeUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PouchCreateUpdateTest
{
    @Autowired
    PouchRepository pouchRepository;

    @Autowired
    PouchCategoryRepository pouchCategoryRepository;

    @Autowired
    PouchProductMappingRepository pouchProductMappingRepository;


    @Autowired
    EntityManager em;

    @Test
    public void di(){}

    @Test
    public void insertPouch()
    {
        List<Long> products = new ArrayList<>();
        products.add(1235l);
        products.add(1233l);
        ImageContentDto imageContentDto = new ImageContentDto("a", 999,"dummy","/dummy/dummy","image/jpeg");
        PouchRequestDto pouchRequestDto = new PouchRequestDto();
        pouchRequestDto.setIdRegister(636117l);//나 임시
        pouchRequestDto.setIsDisplay(true);
        pouchRequestDto.setIdPouchCategory(7l);//glowpick news
        pouchRequestDto.setTitle("John Snow");
        pouchRequestDto.setContent("You know nothing John Snow...");
        String openDate = LocalDateTimeUtil.getLocalDateTimeForFileName(LocalDateTime.now());
        pouchRequestDto.setOpenDate(openDate);
        pouchRequestDto.setImageContentDto(imageContentDto);
        pouchRequestDto.setProducts(products);

        Map<String, Object> stringObjectMap = pouchRepository.insertOrUpdatePouch(pouchRequestDto);
//        Map<String, Object> data = (Map<String, Object>) stringObjectMap.get("data");
        System.out.println(">>>>>> string : "+ stringObjectMap.toString());
        System.out.println(">>>>>> class : "+ stringObjectMap.getClass());

        Object data = stringObjectMap.get("data");
        System.out.println(">>>>> data : "+data);
        System.out.println(">>>>> class :"+data.getClass());
        Long tempId = (Long)data;

        Map<String, Object> byPouchId = pouchProductMappingRepository.findByPouchId(tempId);

        System.out.println(">>>> mapped products : "+ byPouchId.toString());
    }


    @Test
    public void updatePouch()
    {
        List<Long> products = new ArrayList<>();
        //[DB 저장 데이터] [1233,1235]
//        products.add(1238l);//추가
//        products.add(1239l);//추가
//        products.add(1233l);//그대로

        products.add(1233l);
        products.add(1235l);

        ImageContentDto imageContentDto = new ImageContentDto("a", 999,"dummy","/dummy/dummy","image/jpeg");
        PouchRequestDto pouchRequestDto = new PouchRequestDto();
        pouchRequestDto.setIdPouch(1063l);
        pouchRequestDto.setIdRegister(636117l);//나 임시
        pouchRequestDto.setIsDisplay(true);
        pouchRequestDto.setIdPouchCategory(7l);//glowpick news
        pouchRequestDto.setTitle("John Snow");
        pouchRequestDto.setContent("You know nothing John Snow222...");
        String openDate = LocalDateTimeUtil.getLocalDateTimeForFileName(LocalDateTime.now());
        pouchRequestDto.setOpenDate(openDate);
        pouchRequestDto.setImageContentDto(imageContentDto);
        pouchRequestDto.setProducts(products);
        System.out.println(">>>>>> parameter : "+pouchRequestDto.toString());
        Map<String, Object> stringObjectMap = pouchRepository.insertOrUpdatePouch(pouchRequestDto);
        System.out.println(">>>>>> string : "+ stringObjectMap.toString());
        System.out.println(">>>>>> class : "+ stringObjectMap.getClass());

        Object data = stringObjectMap.get("data");
        System.out.println(">>>>> data : "+data);
        System.out.println(">>>>> class :"+data.getClass());
        Long tempId = (Long)data;

        Map<String, Object> byPouchId = pouchProductMappingRepository.findByPouchId(tempId);

        System.out.println(">>>> mapped products : "+ byPouchId.toString());
    }




    @Test
    public void 캐스트_매핑된_제품_수정_로직_골격_스케칭_001()
    {
        //given
        ArrayList<Long> givenList = new ArrayList<>();
        givenList.add(1233l);//그대로 있어야 한다.
        givenList.add(1235l);//insert 되어야 한다.
        givenList.add(1236l);//insert 되어야 한다.
        givenList.add(1240l);//insert 되어야 한다.

        ArrayList<Long> dbList = new ArrayList<>();
        dbList.add(1233l);//그대로 있어야 한다.
        dbList.add(1237l);//삭제되어야한다.
        dbList.add(1238l);//삭제되어야한다.

        ArrayList<Long> insertIdList = new ArrayList<>();
        ArrayList<Long> deleteIdList = new ArrayList<>();

        //when

        for(Long givenId:givenList)
        {
            Long pivot = givenId;
            if(!dbList.contains(pivot))
            {
                insertIdList.add(pivot);
            }
        }

        for(Long givenId:dbList)
        {
            Long pivot = givenId;
            if(!givenList.contains(pivot))
            {
                deleteIdList.add(pivot);
            }
        }

        // then
        System.out.println("insertIdList:"+insertIdList.toString());
        System.out.println("deleteIdList:"+deleteIdList.toString());
        assertThat(insertIdList.toArray()).contains(1235l);
        assertThat(deleteIdList.toArray()).contains(1237l);
    }


    @Test
    public void 캐스트_매핑된_제품_수정_로직_골격_스케칭_002()
    {
        //given
        ArrayList<Long> givenList = new ArrayList<>();
//        givenList.add(1233l);
//        givenList.add(1235l);
//        givenList.add(1236l);
//        givenList.add(1240l);
        ArrayList<Long> dbList = new ArrayList<>();
        dbList.add(1233l);
        dbList.add(1237l);
        dbList.add(1238l);

        ArrayList<Long> insertIdList = new ArrayList<>();
        ArrayList<Long> deleteIdList = new ArrayList<>();

        //when

        for(Long givenId:givenList)
        {
            Long pivot = givenId;
            if(!dbList.contains(pivot))
            {
                insertIdList.add(pivot);
            }
        }

        for(Long givenId:dbList)
        {
            Long pivot = givenId;
            if(!givenList.contains(pivot))
            {
                deleteIdList.add(pivot);
            }
        }
        // then
        System.out.println("=====================================");
        System.out.println("insertIdList:"+insertIdList.toString());
        System.out.println("deleteIdList:"+deleteIdList.toString());
        System.out.println("=====================================");
    }
}
