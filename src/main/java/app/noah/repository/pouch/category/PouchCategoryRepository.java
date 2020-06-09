package app.noah.repository.pouch.category;

import app.noah.domain.PouchCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PouchCategoryRepository extends JpaRepository<PouchCategory,Long>, PouchCategoryRepositoryCustom
{

}
