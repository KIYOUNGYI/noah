package app.noah.repository;

import app.noah.domain.glowpickorm.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAccountRepository extends JpaRepository<AdminAccount,Long> {
}
