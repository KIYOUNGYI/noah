package app.noah.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="pouch",catalog = "user_glowmee", schema = "user_glowmee")
@Getter @Setter
public class Pouch
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPouch")
    private Long id;

    //idregiter 는 여러개의 Pouch 를 작성할 수 있다.
    //adminAccount와 pouch는 1 대 다 관계
    private AdminAccount adminAccount;

    // Todo
    private Long idPouchCategory;

    // TODO
    private Long idBrand;

    private String pouchTitle;

    private String pouchText;

    private Long readCount;

    private Long pouchScore;

    private Long recommendCount;

//    TODO
    private int isDisplay;

    //TODO
    private boolean todayPouch;

    //TODO
    private boolean editerPick;

    //TODO
    private char[] start_date;

    //TODO
    private char[] end_date;

    private char[] create_date;

    private char[] modified_date;

    private String fileOrgName;

    private String fileSaveName;

    private String fileDir;

    private Long fileSize;

    //TODO
    private String fileType;

    private int orderNum;

    private Long hits_count;

    /**
     * CREATE TABLE `pouch` (
     *   `idPouch` int(11) NOT NULL AUTO_INCREMENT COMMENT '픽 항번',
     *   `idRegister` int(11) NOT NULL COMMENT '회원 항번',
     *   `idPouchCategory` int(11) NOT NULL COMMENT '픽 카테고리 항번',
     *   `idBrand` int(11) DEFAULT NULL COMMENT '브랜드 항번',
     *   `pouchTitle` varchar(255) NOT NULL COMMENT '픽 이름',
     *   `pouchText` text NOT NULL COMMENT '픽 내용',
     *   `readCount` int(11) NOT NULL COMMENT '조회수',
     *   `pouchScore` double NOT NULL COMMENT '인기도',
     *   `recommendCount` int(11) NOT NULL COMMENT '추천수',
     *   `isDisplay` int(1) NOT NULL COMMENT '전시여부',
     *   `todayPouch` int(1) NOT NULL COMMENT '오늘의파우치 노출여부',
     *   `editerPick` int(1) NOT NULL COMMENT '에디터의픽 노출여부',
     *   `pouchImg` int(11) DEFAULT NULL COMMENT '픽 이미지',
     *   `start_date` char(14) DEFAULT NULL COMMENT '노출 시작일시',
     *   `end_date` char(14) DEFAULT NULL COMMENT '노출 종료일시',
     *   `create_date` char(14) NOT NULL COMMENT '등록일시',
     *   `modified_date` char(14) DEFAULT NULL COMMENT '수정일시',
     *   `fileOrgName` varchar(255) DEFAULT NULL COMMENT '원본 파일명',
     *   `fileSaveName` varchar(128) DEFAULT NULL COMMENT '저장 파일명',
     *   `fileDir` varchar(255) DEFAULT NULL COMMENT '저장 경로',
     *   `fileSize` int(11) DEFAULT NULL COMMENT '파일 크기',
     *   `fileType` varchar(64) DEFAULT NULL COMMENT '파일 형식',
     *   `orderNum` int(11) DEFAULT NULL COMMENT '정렬 순서',
     *   `hits_count` int(11) NOT NULL DEFAULT '0',
     *   PRIMARY KEY (`idPouch`),
     *   KEY `FK_PouchCategory_TO_Pouch` (`idPouchCategory`),
     *   KEY `FK_Brand_TO_Pouch` (`idBrand`),
     *   KEY `FK_AdminAccount_TO_Pouch_idx` (`idRegister`),
     *   CONSTRAINT `FK_AdminAccount_TO_Pouch` FOREIGN KEY (`idRegister`) REFERENCES `adminAccount` (`idRegister`),
     *   CONSTRAINT `FK_Brand_TO_Pouch` FOREIGN KEY (`idBrand`) REFERENCES `brand` (`idBrand`),
     *   CONSTRAINT `FK_PouchCategory_TO_Pouch` FOREIGN KEY (`idPouchCategory`) REFERENCES `pouchcategory` (`idPouchCategory`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=1032 DEFAULT CHARSET=utf8 COMMENT='픽';
     */
}


