//v
package app.noah.domain;

import app.noah.domain.glowpickorm.Register;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="wishuserpouchmapping",catalog = "user_glowmee", schema = "user_glowmee")
@Getter @Setter @NoArgsConstructor
public class WishUserPouchMapping
{
    /**
     * 다대일 관계
     * [1] WishUserPouchMapping 과 pouch 는 다대일 관계
     * [2] WishUserPouchMapping 과 register 는 다대일 관계
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="idPouch")
    private Pouch pouch;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="idRegister")
    private Register register;

    @Column(name="create_date")
    private String createDate;

}
