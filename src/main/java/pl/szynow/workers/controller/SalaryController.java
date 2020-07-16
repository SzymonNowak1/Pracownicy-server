package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.configuration.CurrentlyLoggedUser;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.SalaryObject;
import pl.szynow.workers.model.object.SalaryUpdateObject;

import java.util.List;

@RequestMapping("/${security.endpoint.api.prefix}")
public interface SalaryController {

    @GetMapping("/salaries")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<PageResponse<SalaryObject>> getAllSalaries(@RequestParam Integer perPage, @RequestParam Integer page);

    @GetMapping("/salaries/mysalaries")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<SalaryObject>> getAllUserSalaries(@CurrentlyLoggedUser User user);

    @GetMapping("/salaries/{salaryId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<SalaryObject> getSalary(@PathVariable Long salaryId);

    @PostMapping("/salaries")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<SalaryObject> addSalary(@RequestBody SalaryUpdateObject salary);

    @PutMapping("/salaries/{salaryId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<SalaryObject> updateSalary(@PathVariable Long salaryId, @RequestBody SalaryUpdateObject salary);

    @DeleteMapping("/salaries/{salaryId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity deleteSalary(@PathVariable Long salaryId);

}
