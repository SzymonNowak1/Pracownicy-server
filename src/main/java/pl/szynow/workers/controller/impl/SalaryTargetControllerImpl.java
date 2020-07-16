package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.szynow.workers.controller.SalaryTargetController;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.model.object.SalaryTargetObject;
import pl.szynow.workers.model.object.SalaryTargetUpdateObject;
import pl.szynow.workers.service.SalaryTargetService;

import java.util.List;

@RestController
public class SalaryTargetControllerImpl implements SalaryTargetController {

    @Autowired
    private SalaryTargetService salaryTargetService;

    @Override
    public ResponseEntity<List<SalaryTargetObject>> getAllUserSalaryTargets(User user) {
        return new ResponseEntity<>( salaryTargetService.getAllUserSalaryTargets(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalaryTargetObject> getSalaryTarget(User user, Long salaryTargetId) {
        return new ResponseEntity<>( salaryTargetService.getSalaryTargetObjectById(user, salaryTargetId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalaryTargetObject> addSalaryTarget(User user, SalaryTargetUpdateObject salaryTarget) {
        return new ResponseEntity<>( salaryTargetService.addSalaryTarget(user, salaryTarget), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalaryTargetObject> updateSalaryTarget(User user, Long salaryTargetId, SalaryTargetUpdateObject salaryTarget) {
        return new ResponseEntity<>( salaryTargetService.updateSalaryTarget(user, salaryTargetId, salaryTarget), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteSalaryTarget(User user, Long salaryTargetId) {
        salaryTargetService.deleteSalaryTarget(user, salaryTargetId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SalaryTargetObject> selectSalaryTarget(User user, long salaryTargetId) {
        return new ResponseEntity<>( salaryTargetService.setSelectedSalaryTarget(user, salaryTargetId), HttpStatus.OK );
    }
}
