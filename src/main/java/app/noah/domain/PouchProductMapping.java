package app.noah.domain;

public class PouchProductMapping
{
    /**
     * CREATE TABLE `pouchproductmapping` (
     *   `idPouchProduct` int(11) NOT NULL AUTO_INCREMENT COMMENT '맵핑항번',
     *   `idPouch` int(11) NOT NULL COMMENT '픽 항번',
     *   `idProduct` int(11) DEFAULT NULL COMMENT '제품 항번',
     *   `idRegister` int(11) DEFAULT NULL COMMENT '등록한 관리자 항번',
     *   `requestText` text COMMENT '요청내용',
     *   `requestImg` int(11) DEFAULT NULL COMMENT '이미지 항번',
     *   `isConfirm` int(1) DEFAULT NULL COMMENT '승인여부',
     *   `create_date` char(14) NOT NULL COMMENT '등록일시',
     *   `modified_date` char(14) DEFAULT NULL COMMENT '수정일시',
     *   `fileOrgName` varchar(255) DEFAULT NULL COMMENT '원본 파일명',
     *   `fileSaveName` varchar(128) DEFAULT NULL COMMENT '저장 파일명',
     *   `fileDir` varchar(255) DEFAULT NULL COMMENT '저장 경로',
     *   `fileSize` int(11) DEFAULT NULL COMMENT '파일 크기',
     *   `fileType` varchar(64) DEFAULT NULL COMMENT '파일 형식',
     *   `adminIdRegister` int(11) DEFAULT NULL,
     *   PRIMARY KEY (`idPouchProduct`),
     *   KEY `FK_Product_TO_PouchProductMapping` (`idProduct`),
     *   KEY `FK_Pouch_TO_PouchProductMapping` (`idPouch`),
     *   KEY `FK_Register_TO_pouchProductMapping` (`idRegister`),
     *   KEY `pouchproductmapping_create_date_index` (`create_date`),
     *   CONSTRAINT `FK_Pouch_TO_PouchProductMapping` FOREIGN KEY (`idPouch`) REFERENCES `pouch` (`idPouch`),
     *   CONSTRAINT `FK_Product_TO_PouchProductMapping` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`),
     *   CONSTRAINT `FK_Register_TO_PouchProductMapping` FOREIGN KEY (`idRegister`) REFERENCES `register` (`idRegister`) ON DELETE NO ACTION ON UPDATE NO ACTION
     * ) ENGINE=InnoDB AUTO_INCREMENT=99887 DEFAULT CHARSET=utf8 COMMENT='픽과 제품간 맵핑';
     */
}
