package pl.szynow.workers.service;

import pl.szynow.workers.entity.User;
import pl.szynow.workers.exception.ForbiddenOperationException;
import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.object.SalaryTargetObject;
import pl.szynow.workers.model.object.SalaryTargetUpdateObject;

import java.util.List;

public interface SalaryTargetService {

    List<SalaryTargetObject> getAllUserSalaryTargets(User user);
    SalaryTargetObject getSalaryTargetObjectById(User user, Long id) throws ObjectNotFoundException, ForbiddenOperationException;
    void deleteSalaryTarget(User user, Long id) throws ObjectNotFoundException, ForbiddenOperationException;
    SalaryTargetObject updateSalaryTarget(User user, Long id, SalaryTargetUpdateObject salaryTargetUpdate) throws ObjectNotFoundException, ForbiddenOperationException;
    SalaryTargetObject addSalaryTarget(User user, SalaryTargetUpdateObject salaryTarget) throws ForbiddenOperationException;

    SalaryTargetObject setSelectedSalaryTarget(User user, Long id) throws ForbiddenOperationException;

}