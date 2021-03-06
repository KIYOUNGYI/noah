package app.noah.studyapi;

import app.noah.domain.Pouch;
import app.noah.repository.pouch.PouchRepository_Old;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

//@RestController
@RequiredArgsConstructor
public class StudyApiController
{
    private final PouchRepository_Old pouchOldRepository;

    /**
     * 구현은 간단하나 좋은 성능은 아님,
     * 1+10
     * @return
     */
    @GetMapping("/api/v2/simple-pouch")
    public List<SimplePouchDto> d()
    {
        List<Pouch> pouches = pouchOldRepository.findAll();
        List<SimplePouchDto> result = pouches.stream().map(p->new SimplePouchDto(p)).collect(toList());
        return result;
    }

    /**
     * TOOne 관계는 모두 페치조인한다.
     * (진행중)
     * @return
     */
    @GetMapping("/api/v3/simple-pouch")
    public List<SimplePouchDto> d3()
    {
        List<Pouch> pouches = pouchOldRepository.findAllUsingFetchJoin();
        List<SimplePouchDto> result = pouches.stream().map(p->new SimplePouchDto(p)).collect(toList());
        return result;
    }

    @GetMapping("/api/v3.1/simple-pouch")
    public List<SimplePouchDto> d3
            (
                    @RequestParam(value="offset",defaultValue = "0") int offset,
                    @RequestParam(value="limit",defaultValue = "20") int limit
            )
    {
        List<Pouch> pouches = pouchOldRepository.findAllUsingFetchJoin(offset,limit);
        List<SimplePouchDto> result = pouches.stream().map(p->new SimplePouchDto(p)).collect(toList());
        return result;
    }


    @Data
    static class SimplePouchDto
    {
        private Long id;
        private String categoryName;
        //        private Long categoryId;
//        private String imgUrl;
        private String pouchTitle;
        private String createDate;
        private Integer productCount;
        private Long visitCount;
        private boolean editorPick;
        //        private String register;
        private Long recommendCount;
        private Integer commentCount;

        public SimplePouchDto(Pouch pouch)
        {
            this.id = pouch.getId();
            this.categoryName = pouch.getPouchCategory().getPouchCategoryText();
//            this.categoryId = pouch.getPouchCategory().getIdPouchCategory();
//            this.imgUrl = pouch
            this.pouchTitle = pouch.getPouchTitle();
            this.createDate = pouch.getCreateDate();
            this.productCount = pouch.getPouchProductMappingsList().size();
            this.visitCount = pouch.getReadCount();
            this.editorPick = pouch.isEditerPick();
            this.recommendCount = pouch.getRecommendCount();
            this.commentCount = pouch.getPouchCommentList().size();
        }
    }
}
