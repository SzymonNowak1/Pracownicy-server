package pl.szynow.workers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szynow.workers.entity.Salary;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("SELECT s FROM Salary s WHERE s.salaryTarget.worker.user.id = :userId")
    List<Salary> findByUserId(@Param("userId") Long userId);

}
