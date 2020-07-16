package pl.szynow.workers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.entity.Worker;
import pl.szynow.workers.exception.*;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.UserWorkerObject;
import pl.szynow.workers.model.object.UserWorkerUpdateObject;
import pl.szynow.workers.repository.UserRepository;
import pl.szynow.workers.repository.WorkerRepository;
import pl.szynow.workers.service.UserService;
import pl.szynow.workers.service.UserWorkerService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserWorkerServiceImpl implements UserWorkerService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<UserWorkerObject> searchByFirstLastNameText(String text) throws ObjectNotFoundException {
        List<Worker> byFirstNameLastNameTextSearch = workerRepository.findByFirstNameLastNameTextSearch(text);

        return byFirstNameLastNameTextSearch.stream().map( entity -> new UserWorkerObject(entity.getUser(), entity )).collect(Collectors.toList());
    }

    @Override
    public List<UserWorkerObject> searchByUsernameEmail(String text) {
        List<User> byUsernameEmail = userRepository.findByUsernameEmailTextSearch(text);

        return byUsernameEmail.stream().map( entity -> {
            List<Worker> workers = entity.getWorkers();
            Worker workerEntity = null;
            if ( !workers.isEmpty() ) {
                workerEntity = workers.get(0);
            }
            return new UserWorkerObject(entity, workerEntity);
        }).collect(Collectors.toList());
    }

    @Override
    public PageResponse<UserWorkerObject> getUserWorkerPageByUsers(Integer perPage, Integer page) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Order.asc("id")));
        Page<User> usersPage = userRepository.findAll(pageable);

        return new PageResponse<>(
                usersPage,
                usersPage.getContent().stream().map( entity -> {
                    List<Worker> workers = entity.getWorkers();
                    Worker worker = null;
                    if ( !workers.isEmpty() ) {
                        worker = workers.get(0);
                    }
                    return new UserWorkerObject(entity, worker);
                }).collect(Collectors.toList())
        );
    }

    @Override
    public UserWorkerObject getUserWorkerObjectByUserId(Long id) throws ObjectNotFoundException {
        try {
            User userEntity = userService.getById(id);
            List<Worker> workers = userEntity.getWorkers();
            Worker workerEntity = null;
            if ( !workers.isEmpty() ) {
                workerEntity = workers.get(0);
            }
            return new UserWorkerObject(userEntity, workerEntity);
        } catch ( UserNotFoundException e ) {
            throw new ObjectNotFoundException( e );
        }
    }

    @Override
    public void deleteWorker(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserWorkerObject updateUser(Long id, UserWorkerUpdateObject userUpdate) throws ObjectNotFoundException {
        Optional<User> userEntity = userRepository.findById(id);
        if ( !userEntity.isPresent() ) throw new ObjectNotFoundException("Not found");

        boolean usernameFlag = false;
        boolean emailFlag = false;

        User user = userEntity.get();
        // zmiana nazwy uzytkownika
        if ( !userUpdate.getUsername().equals(user.getUsername()) ) {
            if ( userService.isUsernameInUse( userUpdate.getUsername() ) ) throw new UsernameInUseException("Username already exists");
            usernameFlag = true;
        }

        // zmiana maila
        if ( !userUpdate.getEmail().equals(user.getEmail())) {
            if ( userService.isEmailInUse( userUpdate.getEmail() ) ) throw new EmailInUseException("Email already in use");
            emailFlag = true;
        }

        // ustawienie roli
        //TODO: enum z rolami
        if ( userUpdate.getRole().equals( "ADMIN" ) || userUpdate.getRole().equals( "MANAGER" ) || userUpdate.getRole().equals( "USER" ) ) {
            List<String> allRoles = Arrays.asList("ADMIN", "MANAGER", "USER");
            userService.removeRoles( user.getUsername(), allRoles );

            userService.addRoles( user.getUsername(), Collections.singletonList(userUpdate.getRole()) );
        }  else {
            throw new IllegalRoleException("Role should be ADMIN, MANAGER or USER");
        }

        if ( usernameFlag ) user.setUsername( userUpdate.getUsername() );
        if ( emailFlag ) user.setEmail( userUpdate.getEmail() );

        // ustawienie workera
        Optional<Worker> worker;
        if ( userUpdate.getWorkerId() != null ) {
            worker = workerRepository.findById(userUpdate.getWorkerId());
            if ( !worker.isPresent() ) throw new ObjectNotFoundException("Worker not found");
            worker.get().setUser( user );
            workerRepository.save( worker.get() );
        }

        userRepository.save( user );

        List<Worker> workers = user.getWorkers();
        Worker workerEntity = null;
        if ( !workers.isEmpty() ) {
            workerEntity = workers.get(0);
        }
        return new UserWorkerObject(user, workerEntity);
    }

    @Override
    public UserWorkerObject addUser(UserWorkerUpdateObject user) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
