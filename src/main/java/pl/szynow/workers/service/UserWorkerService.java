package pl.szynow.workers.service;

import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.exception.UsernameInUseException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.UserWorkerObject;
import pl.szynow.workers.model.object.UserWorkerUpdateObject;

import javax.validation.Valid;
import java.util.List;

public interface UserWorkerService {

    List<UserWorkerObject> searchByFirstLastNameText(String text);
    List<UserWorkerObject> searchByUsernameEmail(String text);
    PageResponse<UserWorkerObject> getUserWorkerPageByUsers(Integer perPage, Integer page);
    UserWorkerObject getUserWorkerObjectByUserId(Long id) throws ObjectNotFoundException;
    void deleteWorker(Long id) throws ObjectNotFoundException;
    UserWorkerObject updateUser(Long id, UserWorkerUpdateObject userUpdate) throws ObjectNotFoundException, UsernameInUseException;
    UserWorkerObject addUser(UserWorkerUpdateObject user);
}
