package app.noah.domain.glowpickorm;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "brand", catalog = "user_glowmee", schema = "user_glowmee")
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;

    private String brandTitle;
    private String brandTitleInitial;
    private String promotionText;
    private Long sortKey;
    private Boolean isDisplay;
    private Boolean isRecommend;
    private String brandUrl;
    private String brandFacebook;
    private String twitter;
    private String kakaotalk;
    private String youTube;
    private String tel;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;
    private Long brandImg;

    @Column(name = "brandinfo_idx")
    private Long brandinfoIdx;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "modified_date")
    private String modifiedDate;

    private String fileOrgName;
    private String fileSaveName;
    private String fileDir;
    private Integer fileSize;
    private String fileType;
    private Long insertIdRegister;
    private Long updateIdRegister;


}
