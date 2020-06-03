package app.noah.domain.glowpickorm;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_goods", catalog = "user_glowmee", schema = "user_glowmee")
@Data
public class ProductGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id")
//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "goods_count")
    private Long goodsCount;

    @Column(name = "max_price")
    private Long maxPrice;

    @Column(name = "min_price")
    private Long minPrice;

}
