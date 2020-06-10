package app.noah.repository.pouch.pouchproduct;

import app.noah.domain.PouchProductMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PouchProductMappingRepository extends JpaRepository<PouchProductMapping,Long>,PouchProductMappingCustom
{

}
