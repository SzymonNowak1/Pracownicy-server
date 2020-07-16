package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.configuration.CurrentlyLoggedUser;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.model.object.SalaryTargetObject;
import pl.szynow.workers.model.object.SalaryTargetUpdateObject;

import java.util.List;

@RequestMapping("/${security.endpoint.api.prefix}")
public interface SalaryTargetController {

    @GetMapping("/salarytargets/mysalarytargets")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<SalaryTargetObject>> getAllUserSalaryTargets(@CurrentlyLoggedUser User user);

    @GetMapping("/salarytargets/{salaryTargetId}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<SalaryTargetObject> getSalaryTarget(@CurrentlyLoggedUser User user, @PathVariable Long salaryTargetId);

    @PostMapping("/salarytargets")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<SalaryTargetObject> addSalaryTarget(@CurrentlyLoggedUser User user, @RequestBody SalaryTargetUpdateObject salaryTarget);

    @PutMapping("/salarytargets/{salaryTargetId}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<SalaryTargetObject> updateSalaryTarget(@CurrentlyLoggedUser User user, @PathVariable Long salaryTargetId, @RequestBody SalaryTargetUpdateObject salaryTarget);

    @DeleteMapping("/salarytargets/{salaryTargetId}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity deleteSalaryTarget(@CurrentlyLoggedUser User user, @PathVariable Long salaryTargetId);

    @PutMapping("/salarytargets/{salaryTargetId}/select")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<SalaryTargetObject> selectSalaryTarget(@CurrentlyLoggedUser User user, @PathVariable long salaryTargetId);

}
