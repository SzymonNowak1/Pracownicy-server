package pl.szynow.workers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szynow.workers.entity.Position;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p from Position p WHERE p.name LIKE %:text%")
    List<Position> findByNameTextSearch(@Param("text") String text);

}
