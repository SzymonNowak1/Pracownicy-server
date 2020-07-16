package pl.szynow.workers.service;

import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.WorkerObject;
import pl.szynow.workers.model.object.WorkerUpdateObject;

import java.util.List;

public interface WorkerService {

    PageResponse<WorkerObject> getWorkerPage(Integer perPage, Integer page);
    List<WorkerObject> searchByFirstLastName(String text);
    WorkerObject getWorkerObjectById(Long id) throws ObjectNotFoundException;
    void deleteWorker(Long id) throws ObjectNotFoundException;
    WorkerObject updateWorker(Long id, WorkerUpdateObject workerUpdate) throws ObjectNotFoundException;
    WorkerObject addWorker(WorkerUpdateObject worker);

}
