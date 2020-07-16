package pl.szynow.workers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szynow.workers.entity.Worker;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query("SELECT w from Worker w WHERE w.firstName LIKE %:text% OR w.lastName LIKE %:text% ")
    List<Worker> findByFirstNameLastNameTextSearch(@Param("text") String text);

}
