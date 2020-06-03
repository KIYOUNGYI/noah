//v
package app.noah.domain;

import app.noah.domain.glowpickorm.Register;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="pouchcomment",catalog = "user_glowmee",schema = "user_glowmee")
public class PouchComment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPouchComment")
    private Long id;

    /**
     * 다대일 관계
     * [1] PouchComment 와 Pouch 는 다대일 관계
     * [2] PouchComment 와 Register 는 다대일 관계
     *
     */
    @ManyToOne(fetch= LAZY) @JoinColumn(name="idPouch")
    private Pouch pouch;

    @ManyToOne(fetch = LAZY) @JoinColumn(name="idRegister")
    private Register register;

    private String pouchComment;

    private boolean isDisplay;

    private boolean isPushConfirm;

    private String ipAddress;

    @Column(name="create_date")
    private String createDate;


    /**
     * CREATE TABLE `pouchcomment` (
     *   `idPouchComment` int(11) NOT NULL AUTO_INCREMENT COMMENT '픽 댓글 항번',
     *   `idRegister` int(11) NOT NULL COMMENT '회원 항번',
     *   `idPouch` int(11) NOT NULL COMMENT '픽 항번',
     *   `pouchComment` varchar(2048) CHARACTER SET utf8mb4 NOT NULL,
     *   `isDisplay` int(1) NOT NULL COMMENT '전시여부',
     *   `isPushConfirm` int(1) NOT NULL COMMENT '푸쉬발송여부(미확인0/확인1)',
     *   `ipAddress` varchar(500) DEFAULT NULL COMMENT '유저 Ip 정보',
     *   `create_date` char(14) NOT NULL COMMENT '등록일시',
     *   PRIMARY KEY (`idPouchComment`),
     *   KEY `FK_Pouch_TO_PouchComment` (`idPouch`),
     *   KEY `FK_Register_TO_PouchComment` (`idRegister`),
     *   CONSTRAINT `FK_Pouch_TO_PouchComment` FOREIGN KEY (`idPouch`) REFERENCES `pouch` (`idPouch`),
     *   CONSTRAINT `FK_Register_TO_PouchComment` FOREIGN KEY (`idRegister`) REFERENCES `register` (`idRegister`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=33483 DEFAULT CHARSET=utf8 COMMENT='픽 댓글';
     */

}
