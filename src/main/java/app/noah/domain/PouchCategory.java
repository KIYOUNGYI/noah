package app.noah.domain;

import javax.persistence.Entity;

@Entity
public class PouchCategory
{
    /**
     * CREATE TABLE `pouchcategory` (
     *   `idPouchCategory` int(11) NOT NULL AUTO_INCREMENT COMMENT '픽 카테고리 항번',
     *   `pouchCategoryText` varchar(100) NOT NULL COMMENT '픽 카테고리 이름',
     *   `sortKey` int(11) NOT NULL COMMENT '정렬순서',
     *   `isDisplay` int(1) NOT NULL COMMENT '전시여부',
     *   `create_date` char(14) NOT NULL COMMENT '등록일시',
     *   `modified_date` char(14) DEFAULT NULL COMMENT '수정일시',
     *   PRIMARY KEY (`idPouchCategory`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='픽 카테고리';
     */
}
