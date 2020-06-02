package app.noah.domain;

/**
 * 복붙
 */

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "register", catalog = "user_glowmee", schema = "user_glowmee")
@Data
public class Register implements Serializable {

    @Id
    @Field(store = Store.YES, name = "register_id")
    @SortableField
    @NumericField
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegister;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String userType;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String eMail;

    private String passWord;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String nickName;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @NumericField
    private Integer location;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @NumericField
    private Integer birthYear;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @NumericField
    private Integer skinType;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @NumericField
    private Integer gender;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String userTel;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String userZipcode;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String userAddress;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String userAddressMore;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String userName;

    @Field(store = Store.YES)
    @SortableField
    @NumericField
    private Integer registerScore;

    @Field(store = Store.YES)
    @NumericField
    private Integer registerRank;

    @Field(store = Store.YES)
    @NumericField
    private Integer eventCount;

    @Field(store = Store.YES)
    @NumericField
    private Integer reviewCount;

    @Field(store = Store.YES)
    @NumericField
    private Long likeCount;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private Boolean isActivity;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private Boolean isBlack;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @NumericField
    private Integer isBlind;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String userImg;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private String uuid;

    private String regId;

    private String apns;

    private String gcmArn;

    private String apnsArn;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private Boolean isPush;

    @Field(store = Store.YES, analyze = Analyze.NO)
    private Boolean isUserPush;

    private Long recommender;

    private Integer adminLevel;

    private Integer department;

    private Integer position;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @Column(name = "create_date")
    private String createDate;

    @Column(name = "modified_date")
    private String modifiedDate;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @Column(name = "last_date")
    private String lastDate;

    private Integer loginCount;

    private String ipAddress;
    private String fileOrgName;
    private String fileSaveName;
    private String fileDir;
    private Integer fileSize;
    private String fileType;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @Column(name = "inactivated_date")
    private String inactivatedDate;

    @Column(name = "app_version")
    private String appVersion;

    @Field(store = Store.YES, analyze = Analyze.NO)
    @Column(name = "closed_date")
    private String closedDate;

    //광고식별자 추가
    @Column(name = "adid")
    private String adid;

//	@ContainedIn
//	@OneToMany(mappedBy = "register")
//	private List<ReviewForFilter> reviewForFilters;

}
