package pl.szynow.workers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szynow.workers.entity.Configuration;

import java.util.Optional;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

    Optional<Configuration> findByName(String name);

}
