package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.szynow.workers.controller.SalaryController;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.SalaryObject;
import pl.szynow.workers.model.object.SalaryUpdateObject;
import pl.szynow.workers.service.SalaryService;

import java.util.List;

@RestController
public class SalaryControllerImpl implements SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Override
    public ResponseEntity<PageResponse<SalaryObject>> getAllSalaries(Integer perPage, Integer page) {
        return new ResponseEntity<>( salaryService.getSalaryPage(perPage, page), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<SalaryObject>> getAllUserSalaries(User user) {
        return new ResponseEntity<>( salaryService.getAllUserSalaries(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalaryObject> getSalary(Long salaryId) {
        return new ResponseEntity<>( salaryService.getSalaryObjectById(salaryId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalaryObject> addSalary(SalaryUpdateObject salary) {
        return new ResponseEntity<>( salaryService.addSalary(salary), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalaryObject> updateSalary(Long salaryId, SalaryUpdateObject salary) {
        return new ResponseEntity<>( salaryService.updateSalary(salaryId, salary), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteSalary(Long salaryId) {
        salaryService.deleteSalary(salaryId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
