//v
package app.noah.domain;

import app.noah.utils.LocalDateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="pouchcategory", catalog = "user_glowmee", schema="user_glowmee")
@ToString(of={"idPouchCategory","pouchCategoryText","isDisplay"})
public class PouchCategory
{
    /**
     *  [1] PouchCategory 는 Pouch 와 일대다 관계
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
    private Long idPouchCategory;

    @OneToMany(mappedBy = "pouchCategory")//read 전용
    private List<Pouch> pouches = new ArrayList<>();

    private String pouchCategoryText;
    private Long sortKey;
    private Boolean isDisplay;
    @Column(name="create_date")
    private String createDate;
    @Column(name="modified_date")
    private String modifiedDate;

    //insert
    public PouchCategory(String pouchCategoryText, Long sortKey)
    {
        this.pouchCategoryText = pouchCategoryText;
        this.sortKey = sortKey;
        String now = LocalDateTimeUtil.getLocalDateTimeForFileName(LocalDateTime.now());
        this.createDate = now;
        this.isDisplay = true;
    }

    //update
    public void update(String pouchCategoryText,Long sortKey,Boolean isDisplay)
    {
        this.pouchCategoryText = pouchCategoryText;
        this.sortKey = sortKey;
        String now = LocalDateTimeUtil.getLocalDateTimeForFileName(LocalDateTime.now());
        this.modifiedDate = now;
        this.isDisplay = isDisplay;
    }


}
