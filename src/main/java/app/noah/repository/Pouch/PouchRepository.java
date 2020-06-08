package app.noah.repository.Pouch;

import app.noah.domain.Pouch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PouchRepository extends JpaRepository<Pouch,Long>, PouchRepositoryCustom {
}
