package pl.szynow.workers.service;

import pl.szynow.workers.entity.User;
import pl.szynow.workers.exception.ForbiddenOperationException;
import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.SalaryObject;
import pl.szynow.workers.model.object.SalaryUpdateObject;

import java.util.List;

public interface SalaryService {

    PageResponse<SalaryObject> getSalaryPage(Integer perPage, Integer page);
    List<SalaryObject> getAllUserSalaries(User user);
    SalaryObject getSalaryObjectById(Long id) throws ObjectNotFoundException;
    void deleteSalary(Long id) throws ObjectNotFoundException, ForbiddenOperationException;
    SalaryObject updateSalary(Long id, SalaryUpdateObject workerUpdate) throws ObjectNotFoundException;
    SalaryObject addSalary(SalaryUpdateObject worker);

}
