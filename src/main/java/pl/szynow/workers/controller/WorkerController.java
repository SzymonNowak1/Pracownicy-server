package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.*;

import javax.validation.constraints.Size;
import java.util.List;

@RequestMapping("/${security.endpoint.api.prefix}")
public interface WorkerController {

    @GetMapping("/workers")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<PageResponse<WorkerObject>> getAllWorkers(@RequestParam Integer perPage, @RequestParam Integer page);

    @GetMapping("/workers/searchworker")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<List<WorkerObject>> searchWorkers(@RequestParam @Size(min = 3) String text);

    @GetMapping("/workers/{workerId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<WorkerObject> getWorker(@PathVariable Long workerId);

    @PostMapping("/workers")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<WorkerObject> addWorker(@RequestBody WorkerUpdateObject worker);

    @PutMapping("/workers/{workerId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<WorkerObject> updateWorker(@PathVariable Long workerId, @RequestBody WorkerUpdateObject worker);

    @DeleteMapping("/workers/{workerId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity deleteWorker(@PathVariable Long workerId);

    // TODO: get salaries for worker

}
