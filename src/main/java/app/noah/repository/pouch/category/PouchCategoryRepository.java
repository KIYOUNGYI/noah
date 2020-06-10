package app.noah.repository.pouch.category;

import app.noah.domain.PouchCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PouchCategoryRepository extends JpaRepository<PouchCategory,Long>,PouchCategoryRepositoryCustom
{

}
