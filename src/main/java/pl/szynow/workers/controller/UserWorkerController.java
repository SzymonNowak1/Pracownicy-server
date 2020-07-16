package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.UserWorkerObject;
import pl.szynow.workers.model.object.UserWorkerUpdateObject;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RequestMapping( "/${security.endpoint.api.prefix}" )
public interface UserWorkerController {

    @GetMapping("/userworkers")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<PageResponse<UserWorkerObject>> getAllUsers(@RequestParam Integer perPage, @RequestParam Integer page);

    @GetMapping("/userworkers/searchtext")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<UserWorkerObject>> searchUsers(@RequestParam @Size(min = 3) String text);

    @GetMapping("/userworkers/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UserWorkerObject> getUserWorkerByUserId(@PathVariable Long userId);

    @PutMapping("/userworkers/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UserWorkerObject> updateUserWorker(@PathVariable Long userId, @RequestBody @Valid UserWorkerUpdateObject userWorkerUpdateObject);

//    @PostMapping("/userworkers")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserWorkerObject> addUserWorker(@RequestBody UserWorkerObject userWorkerObject);

}
