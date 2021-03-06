package app.noah.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

/**
 * TODO 썸네일 이미지
 */
@Data
public class PouchDto
{
    private Long id;
    private String category;
    private Boolean isDisplay;
    private String title;
    private String createDate;
    private String startDate;
    private Integer productCnt;
    private Long readCount;
    private Boolean isEditorPick;
    private Long adminAccount;
    private String adminNickName;
    private Long likeCnt;//추천 개수
    private Integer commentCnt;

    private String fileOrgName;
    private Integer fileSize;
    private String fileSaveName;
    private String fileDir;
    private String fileType;

    @QueryProjection
    public PouchDto(Long id, String category,Boolean isDisplay, String title, String createDate,
                    String startDate, Integer productCnt, Long readCount, Boolean isEditorPick,
                    Long adminAccount, String adminNickName, Long likeCnt, Integer commentCnt,
                    String fileOrgName, Integer fileSize, String fileSaveName, String fileDir, String fileType) {
        this.id = id;
        this.category = category;
        this.isDisplay = isDisplay;
        this.title = title;
        this.createDate = createDate;
        this.startDate = startDate;
        this.productCnt = productCnt;
        this.readCount = readCount;
        this.isEditorPick = isEditorPick;
        this.adminAccount = adminAccount;
        this.adminNickName = adminNickName;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
        this.fileOrgName = fileOrgName;
        this.fileSize = fileSize;
        this.fileSaveName = fileSaveName;
        this.fileDir = fileDir;
        this.fileType = fileType;
    }
}
