package app.noah.dto;

import app.noah.domain.Pouch;
import app.noah.domain.PouchProductMapping;
import app.noah.domain.glowpickorm.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Data
public class PouchDetailResponseDto
{
    private Long pouchId;
    private Boolean isDisplay;

    private String createDate;
    private String startDate;
    private String pouchTitle;
    private Long pouchCategoryId;
    private String pouchContent;


    private String originalImgFileName;
    private Long fileSize;
    private String uploadedFileName;
    private String uploadedFilePath;
    private String fileType;

    //기타정보
    private Long likeCnt;//추천 개수
    private Integer commentCnt;//댓글 개수

    private List<ProductItemDto> productList=new ArrayList<>();

    @QueryProjection
    public PouchDetailResponseDto(Long pouchId, Boolean isDisplay, String createDate, String startDate,
                                  String pouchTitle, Long pouchCategoryId, String pouchContent, String originalImgFileName,
                                  Long fileSize, String uploadedFileName, String uploadedFilePath, String fileType,
                                  Long likeCnt, Integer commentCnt,Pouch pouch) {
        this.pouchId = pouchId;
        this.isDisplay = isDisplay;
        this.createDate = createDate;
        this.startDate = startDate;
        this.pouchTitle = pouchTitle;
        this.pouchCategoryId = pouchCategoryId;
        this.pouchContent = pouchContent;
        this.originalImgFileName = originalImgFileName;
        this.fileSize = fileSize;
        this.uploadedFileName = uploadedFileName;
        this.uploadedFilePath = uploadedFilePath;
        this.fileType = fileType;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
//        List<ProductItemDto> itemDtos = pouch.getPouchProductMappingsList().stream().map(item -> new ProductItemDto(item)).collect(toList());
        this.productList = pouch.getPouchProductMappingsList().stream().map(item -> new ProductItemDto(item)).collect(toList());

//        this.productList = productList;
    }


    @Data
    static class ProductItemDto
    {
        private Long productId;
        private String productTitle;

        @QueryProjection
        public ProductItemDto(PouchProductMapping product)
        {
            productId = product.getPouchProduct().getIdProduct();
            productTitle = product.getPouchProduct().getProductTitle();
        }
    }
}
