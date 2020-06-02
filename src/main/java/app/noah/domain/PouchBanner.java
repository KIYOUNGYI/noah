package app.noah.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pouchbanner",catalog = "user_glowmee", schema = "user_glowmee")
public class PouchBanner
{
    @Id
    private Long id;

    private Long idPouch;

    /**
     * CREATE TABLE `pouchbanner` (
     *   `idPouchBanner` int(11) NOT NULL AUTO_INCREMENT COMMENT '픽배너 항번',
     *   `idPouch` int(11) NOT NULL COMMENT '픽 항번',
     *   `bannerTitle` varchar(255) NOT NULL COMMENT '배너명',
     *   `bannerLinkType` int(4) DEFAULT NULL COMMENT '배너 링크종류',
     *   `bannerLinkCode` varchar(255) DEFAULT NULL COMMENT '배너 링크코드',
     *   `bannerImg` int(11) DEFAULT NULL COMMENT '배너이미지',
     *   `sortKey` int(11) NOT NULL COMMENT '정렬순서',
     *   `isDisplay` int(1) NOT NULL COMMENT '전시여부',
     *   `create_date` char(14) NOT NULL COMMENT '등록일시',
     *   `modified_date` char(14) DEFAULT NULL COMMENT '수정일시',
     *   `fileOrgName` varchar(255) DEFAULT NULL COMMENT '원본 파일명',
     *   `fileSaveName` varchar(128) DEFAULT NULL COMMENT '저장 파일명',
     *   `fileDir` varchar(255) DEFAULT NULL COMMENT '저장 경로',
     *   `fileSize` int(11) DEFAULT NULL COMMENT '파일 크기',
     *   `fileType` varchar(64) DEFAULT NULL COMMENT '파일 형식',
     *   PRIMARY KEY (`idPouchBanner`),
     *   KEY `FK_Pouch_TO_PouchBanner` (`idPouch`),
     *   CONSTRAINT `FK_Pouch_TO_PouchBanner` FOREIGN KEY (`idPouch`) REFERENCES `pouch` (`idPouch`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='픽배너';
     */
}
