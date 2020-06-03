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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Todo
//    private Register register;
    private Long register;

    @Column(name="create_date")
    private String createDate;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="idPouch")
    private Pouch pouch;
}
