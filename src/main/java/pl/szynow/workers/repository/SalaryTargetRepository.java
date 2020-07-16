package pl.szynow.workers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szynow.workers.entity.SalaryTarget;

import java.util.List;

public interface SalaryTargetRepository extends JpaRepository<SalaryTarget, Long> {

    @Query("SELECT s FROM SalaryTarget s WHERE s.worker.user.id = :userId")
    List<SalaryTarget> findByUserId(@Param("userId") Long userId);

}
