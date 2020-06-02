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


}
