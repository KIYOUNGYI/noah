package app.noah.repository.pouch;

import app.noah.dto.PouchDto;
import app.noah.dto.PouchSearchCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class PouchRepositorySelectTest
{
    @Autowired
    PouchRepository pouchRepository;

    @Autowired
    EntityManager em;

    @Test
    public void di(){}

    @Test
    public void basicSearch()
    {
        List<PouchDto> result = pouchRepository.search(null);
        System.out.println("result:"+result.toString());
    }

    @Test
    public void searchPageSimple_정렬순_10개()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent(null);
        psc.setOffset(0l);
        psc.setLimit(10l);
        psc.setSort(1);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 10개 포함되어 있는지
        assertThat(dummy).extracting("id").contains(1031l,1029l,1030l);
        // 개수가 10개가 맞는지
        assertThat(dummy).size().isEqualTo(10);
    }

    @Test
    public void searchPageSimple_이전등록순_20개()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent(null);
        psc.setOffset(0l);
        psc.setLimit(20l);
        psc.setSort(2);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 20개에 생각하는 아이템이 포함되어 있는지
        assertThat(dummy).extracting("id").contains(13l,19l,27l,110l);
        // 개수가 20개가 맞는지
        assertThat(dummy).size().isEqualTo(20);
    }

    @Test
    public void searchPageSimple_전시순_30개()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent(null);
        psc.setOffset(0l);
        psc.setLimit(30l);
        psc.setSort(3);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 20개에 생각하는 아이템이 포함되어 있는지
        assertThat(dummy).extracting("id").contains(1031l,1009l,1010l,1011l,1012l);
        // 개수가 20개가 맞는지
        assertThat(dummy).size().isEqualTo(30);
    }

    @Test
    public void searchPageSimple_비전시순_10개()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent(null);
        psc.setOffset(0l);
        psc.setLimit(10l);
        psc.setSort(4);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 20개에 생각하는 아이템이 포함되어 있는지
        assertThat(dummy).extracting("id").contains(1030l,829l);
        // 개수가 20개가 맞는지
        assertThat(dummy).size().isEqualTo(10);
    }
    @Test
    public void searchPageSimple_비전시순_그다음_10개()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent(null);
        psc.setOffset(10l);
        psc.setLimit(10l);
        psc.setSort(4);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 20개에 생각하는 아이템이 포함되어 있는지
        assertThat(dummy).extracting("id").contains(805l,651l);
        // 개수가 20개가 맞는지
        assertThat(dummy).size().isEqualTo(10);
    }


    @Test
    public void searchPageSimple_댓글많은순_10개()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent(null);
        psc.setOffset(0l);
        psc.setLimit(10l);
        psc.setSort(9);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 10개에 생각하는 아이템이 포함되어 있는지
        assertThat(dummy).extracting("id").contains(670l,476l);
        // 개수가 10개가 맞는지
        assertThat(dummy).size().isEqualTo(10);
    }

    @Test
    public void searchPouchTitle()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle("안녕");
        psc.setContent(null);
        psc.setOffset(0l);
        psc.setLimit(10l);
        psc.setSort(null);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 10개에 생각하는 아이템이 포함되어 있는지
        assertThat(dummy).extracting("id").contains(1031l,1030l,539l,494l,493l);
        // 개수가 10개가 맞는지
        assertThat(dummy).size().isEqualTo(5);
    }

    @Test
    public void searchContent()
    {
        PouchSearchCondition psc = new PouchSearchCondition();
        psc.setTitle(null);
        psc.setContent("글로우");
        psc.setOffset(0l);
        psc.setLimit(10l);
        psc.setSort(null);
        Map<String, Object> simple = pouchRepository.searchPageSimple(psc);
        System.out.println("=============== result =====================");
        System.out.println("simple:"+simple.toString());
        System.out.println("============================================");
        System.out.println(simple.get("data").getClass());
        ArrayList<PouchDto> dummy = (ArrayList<PouchDto>) simple.get("data");
        // 개수 10개에 생각하는 아이템이 포함되어 있는지
        assertThat(dummy).extracting("id").contains(1027l,1026l,1024l,1018l,1012l);
        // 개수가 10개가 맞는지
        assertThat(dummy).size().isEqualTo(10);
    }

    @Test
    public void searchPouchDetail()
    {
        Map<String, Object> pouchDetail = pouchRepository.getPouchDetail(1031l);
        System.out.println("pouchDetail:"+pouchDetail.toString());
    }

    @Test
    public void summary()
    {
        Map<String,Object> summary = pouchRepository.getSummary();
        System.out.println(">>>> summary : " + summary.toString());
    }


}
