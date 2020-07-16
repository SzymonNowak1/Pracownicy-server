package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.szynow.workers.controller.WorkerController;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.WorkerObject;
import pl.szynow.workers.model.object.WorkerUpdateObject;
import pl.szynow.workers.service.WorkerService;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class WorkerControllerImpl implements WorkerController {

    @Autowired
    private WorkerService workerService;

    @Override
    public ResponseEntity<PageResponse<WorkerObject>> getAllWorkers(Integer perPage, Integer page) {
        return new ResponseEntity<>( workerService.getWorkerPage(perPage, page), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<WorkerObject>> searchWorkers(@Min(3) String text) {
        return new ResponseEntity<>( workerService.searchByFirstLastName(text), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<WorkerObject> getWorker(Long workerId) {
        return new ResponseEntity<>( workerService.getWorkerObjectById(workerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WorkerObject> addWorker(WorkerUpdateObject worker) {
        return new ResponseEntity<>( workerService.addWorker(worker), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WorkerObject> updateWorker(Long workerId, WorkerUpdateObject worker) {
        return new ResponseEntity<>( workerService.updateWorker(workerId, worker), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteWorker(Long workerId) {
        workerService.deleteWorker(workerId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
