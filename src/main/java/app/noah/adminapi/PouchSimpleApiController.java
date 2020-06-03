package app.noah.adminapi;

import app.noah.domain.Pouch;
import app.noah.repository.PouchRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class PouchSimpleApiController
{
    private final PouchRepository pouchRepository;

    @GetMapping("/api/v2/simple-pouch")
    public List<SimplePouchDto> d()
    {
        List<Pouch> pouches = pouchRepository.findAll();
        List<SimplePouchDto> result = pouches.stream().map(p->new SimplePouchDto(p)).collect(toList());
        return result;
    }

    @GetMapping("/api/v2/simple-pouch2")
    public List<SimplePouchDto> d2()
    {
        List<Pouch> all = pouchRepository.findAll();
        System.out.println(">>>> size:" + all.size());
        return null;
    }


    @Data
    static class SimplePouchDto
    {
        private Long id;
        private String category;
//        private String imgUrl;
//        private String pouchTitle;
//        private String createDate;
//        private int productCount;
//        private Long visitCount;
//        private boolean editorPick;
//        private String register;
//        private Long recommendCount;
//        private int commentCount;

        public SimplePouchDto(Pouch pouch)
        {
            this.id = pouch.getId();
            this.category = pouch.getPouchCategory().getPouchCategoryText();
//            this.imgUrl = pouch
//            this.pouchTitle = pouch.getPouchTitle();
//            this.createDate = pouch.getCreateDate();
//            this.productCount = pouch.getPouchProductMappingsList().size();
//            this.visitCount = pouch.getReadCount();
//            this.editorPick = pouch.isEditerPick();
//            this.recommendCount = pouch.getRecommendCount();
//            this.commentCount = pouch.getPouchCommentList().size();
        }
    }
}
