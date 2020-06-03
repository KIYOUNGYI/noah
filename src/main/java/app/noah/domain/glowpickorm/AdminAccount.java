package app.noah.domain.glowpickorm;

import app.noah.domain.Pouch;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "adminAccount", catalog = "user_glowmee", schema = "user_glowmee")
@Getter @Setter @RequiredArgsConstructor
public class AdminAccount {

    @Id
    private Long idRegister;      //int(11) NOT NULL AUTO_INCREMENT,

    private String eMail;      //varchar(45) NOT NULL,
    private String passWord;      //varchar(150) NOT NULL,
    private String nickName;      //varchar(45) NOT NULL,
    private String birthDay;      //char(8) DEFAULT NULL,
    private String gender;      //enum('m','f') DEFAULT NULL,
    private String userTel;      //varchar(45) DEFAULT NULL,
    private String userZipcode;      //varchar(7) DEFAULT NULL,
    private String userAddress;      //varchar(300) DEFAULT NULL,
    private String userAddressMore;      //varchar(300) DEFAULT NULL,
    private String userName;      //varchar(45) DEFAULT NULL,
    private Boolean isActivity;      //int(1) DEFAULT '1',
    private Integer adminLevel;      //int(4) DEFAULT NULL,
    private Integer department;      //int(11) DEFAULT NULL,
    private Integer position;      //int(11) DEFAULT NULL,

    @Column(name = "create_date")
    private String createDate;      //char(14) NOT NULL,

    @Column(name = "modified_date")
    private String modifiedDate;      //char(14) DEFAULT NULL,

    @Column(name = "last_date")
    private String lastDate;      //char(14) DEFAULT NULL,

    private Long loginCount;      //int(100) DEFAULT '0',
    private String ipAddress;      //varchar(500) DEFAULT NULL,
    private String fileOrgName;      //varchar(255) DEFAULT NULL,
    private String fileSaveName;      //varchar(128) DEFAULT NULL,
    private String fileDir;      //varchar(255) DEFAULT NULL,
    private Integer fileSize;      //int(11) DEFAULT NULL,
    private String fileType;      //varchar(64) DEFAULT NULL,
    private String joinDate;      //char(14) DEFAULT NULL COMMENT '입사일',
    private Double yearOff;      //double DEFAULT '0' COMMENT '연차',
    private Double giveYearOff;      //double DEFAULT '0' COMMENT '추가연차',

    /**
     * 아래부터 추가
     */
    @OneToOne(mappedBy = "adminAccount")
    private Pouch pouch;
}