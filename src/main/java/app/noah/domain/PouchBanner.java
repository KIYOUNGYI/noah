package app.noah.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Table(name="pouchbanner",catalog = "user_glowmee", schema = "user_glowmee")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class PouchBanner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPouchBanner")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="idPouch")
    private Pouch pouch;

    private String bannerTitle;
    private int bannerLinkType;
    private String bannerLinkCode;
    private Long bannerImg;
    private Long sortKey;
    private boolean isDisplay;
    @Column(name="create_date")
    private String create_date;
    @Column(name="modified_date")
    private String modifiedDate;
    private String fileOrgName;
    private String fileSaveName;
    private String fileDir;
    private Long fileSize;
    private String fileType;

}
