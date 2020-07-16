package pl.szynow.workers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szynow.workers.entity.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
