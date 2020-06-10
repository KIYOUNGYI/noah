package app.noah.repository.pouch;


import app.noah.dto.ImageContentDto;
import app.noah.dto.PouchRequestDto;
import app.noah.repository.pouch.category.PouchCategoryRepository;
import app.noah.repository.pouch.pouchproduct.PouchProductMappingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        products.add(1234l);
        products.add(1233l);
        ImageContentDto imageContentDto = new ImageContentDto("a", 999,"dummy","/dummy/dummy","image/jpeg");
        PouchRequestDto pouchRequestDto = new PouchRequestDto();
        pouchRequestDto.setIdRegister(636117l);//나 임시
        pouchRequestDto.setIsDisplay(true);
        pouchRequestDto.setIdPouchCategory(7l);//glowpick news
        pouchRequestDto.setTitle("John Snow");
        pouchRequestDto.setContent("wazzup");
        pouchRequestDto.setOpenDate("yyyymmddhhmmss");
        pouchRequestDto.setImageContentDto(imageContentDto);
        pouchRequestDto.setProducts(products);

        Map<String, Object> stringObjectMap = pouchRepository.insertOrUpdatePouch(pouchRequestDto);
        System.out.println(">>>> insertPouch Result : "+stringObjectMap.toString());

        //TODO 숙제
        System.out.println("curious:"+pouchRequestDto.getIdPouch());
//
//        stringObjectMap.get()
        Map<String, Object> byPouchId = pouchProductMappingRepository.findByPouchId(1051l);

//        pouchProductMappingRepository.find

    }

}
