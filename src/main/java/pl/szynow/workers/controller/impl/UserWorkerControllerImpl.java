package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.controller.UserWorkerController;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.UserWorkerObject;
import pl.szynow.workers.model.object.UserWorkerUpdateObject;
import pl.szynow.workers.service.UserWorkerService;

import java.util.List;

@RestController
public class UserWorkerControllerImpl implements UserWorkerController {

    @Autowired
    private UserWorkerService userWorkerService;

    @Override
    public ResponseEntity<PageResponse<UserWorkerObject>> getAllUsers(Integer perPage, Integer page) {
        return new ResponseEntity<>( userWorkerService.getUserWorkerPageByUsers(perPage, page), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<UserWorkerObject>> searchUsers(String text) {
        return new ResponseEntity<>( userWorkerService.searchByUsernameEmail(text), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserWorkerObject> getUserWorkerByUserId(Long userId) {
        return new ResponseEntity<>( userWorkerService.getUserWorkerObjectByUserId(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserWorkerObject> updateUserWorker(Long userId, UserWorkerUpdateObject userWorkerUpdateObject) {
        return new ResponseEntity<>( userWorkerService.updateUser( userId, userWorkerUpdateObject ), HttpStatus.OK );
    }

//    @Override
//    public ResponseEntity<UserWorkerObject> addUserWorker(@RequestBody UserWorkerObject userWorkerObject) {
//        return null;
//    }
    //TODO: dodać łapanie wyjątków z serwisów

}
