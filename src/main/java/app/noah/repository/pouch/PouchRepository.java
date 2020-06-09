package app.noah.repository.pouch;

import app.noah.domain.Pouch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PouchRepository extends JpaRepository<Pouch,Long>, PouchRepositoryCustom {
}
