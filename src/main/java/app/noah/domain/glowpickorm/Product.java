package app.noah.domain.glowpickorm;

import javax.persistence.*;

import app.noah.domain.PouchProductMapping;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product", catalog = "user_glowmee", schema = "user_glowmee")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    //TODO Eager 괜찮은가??
    @JoinColumn(name = "idBrand")
//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    private String productTitle;
    private String volume;
    private Integer price;
    private String productText;
    private String colorType;
    private String keyWord;
    private Long readCount;
    private Double productScore;
    private Double ratingAvg;
    private Long rationCount;
    private Boolean isDisplay;
    private Boolean isNaverApi;
    private Boolean isNaverShopApi;
    private Boolean isProductFeelApi;
    private String rankingInfo;
    private Long productImg;

    @Column(name = "product_idx")
    private Long productIdx;

    @Column(name = "create_date")
    private String createDate;
    private Long primarySecondCategory;

    @Column(name = "modified_date")
    private String modifiedDate;

    private String fileOrgName;
    private String fileSaveName;
    private String fileDir;
    private Integer fileSize;
    private String fileType;
    private Long nrationCount;
    private Long nproductScore;
    private Boolean isDiscontinue;

    @Column(name = "release_date")
    private String releaseDate;

    private String factorsModifiedDate;
    private Long factorsModifiedAdminId;
    private Boolean factorsDisplay;
    private String factorsDisplayStartDate;
    private Integer rating1;
    private Integer rating2;
    private Integer rating3;
    private Integer rating4;
    private Integer rating5;
    private Long insertIdRegister;
    private Long updateIdRegister;
    private Long factorsIdRegister;

    @OneToOne(mappedBy="product")
    private ProductGoods productGoods;

    @OneToMany(mappedBy = "pouchProduct")
    private List<PouchProductMapping> pouchProductMappingList = new ArrayList<>();
}
