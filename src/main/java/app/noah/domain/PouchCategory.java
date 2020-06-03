package app.noah.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="pouchcategory", catalog = "user_glowmee", schema="user_glowmee")
public class PouchCategory
{
    /**
     *  Pouch 와 1 대 다 관계
     */

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPouchCategory")
    private Long id;
    private String pouchCategoryText;
    private Long sortKey;
    private boolean isDisplay;
    @Column(name="create_date")
    private String createDate;
    @Column(name="modified_date")
    private String modifiedDate;

    @OneToMany(mappedBy = "pouchCategory")//read 전용
    private List<Pouch> pouches = new ArrayList<>();

}
