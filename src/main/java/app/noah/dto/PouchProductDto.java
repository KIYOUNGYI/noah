package app.noah.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PouchProductDto
{
    private Long idProduct;
    private String productTitle;
    private String fileOrgName;
    private String fileSaveName;
    private String fileDir;
    private Integer fileSize;
    private String fileType;


    @QueryProjection
    public PouchProductDto(Long idProduct, String productTitle, String fileOrgName, String fileSaveName, String fileDir, Integer fileSize, String fileType) {
        this.idProduct = idProduct;
        this.productTitle = productTitle;
        this.fileOrgName = fileOrgName;
        this.fileSaveName = fileSaveName;
        this.fileDir = fileDir;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }
}
