package app.noah.repository.pouch;

import app.noah.domain.glowpickorm.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long>
{
}
