package pl.szynow.workers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.szynow.workers.entity.Position;
import pl.szynow.workers.entity.Worker;
import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.WorkerObject;
import pl.szynow.workers.model.object.WorkerUpdateObject;
import pl.szynow.workers.repository.PositionRepository;
import pl.szynow.workers.repository.WorkerRepository;
import pl.szynow.workers.service.WorkerService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public PageResponse<WorkerObject> getWorkerPage(Integer perPage, Integer page) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Order.asc("id")));
        Page<Worker> workersPage = workerRepository.findAll(pageable);

        return new PageResponse<>(
                workersPage,
                workersPage.getContent().stream().map(WorkerObject::new).collect(Collectors.toList())
        );
    }

    @Override
    public List<WorkerObject> searchByFirstLastName(String text) {
        List<Worker> byNameTextSearch = workerRepository.findByFirstNameLastNameTextSearch(text);

        return byNameTextSearch.stream().map(WorkerObject::new).collect(Collectors.toList());
    }

    @Override
    public WorkerObject getWorkerObjectById(Long id) throws ObjectNotFoundException {
        Optional<Worker> worker = workerRepository.findById(id);
        if ( !worker.isPresent() ) throw new ObjectNotFoundException("not found");
        return new WorkerObject( worker.get() );
    }

    @Override
    public void deleteWorker(Long id) throws ObjectNotFoundException {
        Optional<Worker> worker = workerRepository.findById(id);
        if ( !worker.isPresent() ) throw new ObjectNotFoundException("not found");
        workerRepository.delete(worker.get());
    }

    @Override
    public WorkerObject updateWorker(Long id, WorkerUpdateObject workerUpdate) throws ObjectNotFoundException {
        Optional<Worker> worker = workerRepository.findById(id);

        if ( !worker.isPresent() ) throw new ObjectNotFoundException("not found");
        Worker entity = worker.get();

        entity.setFirstName( workerUpdate.getFirstName() );
        entity.setLastName( workerUpdate.getLastName() );
        entity.setBirthday( LocalDate.from(DateTimeFormatter.ISO_DATE.parse(workerUpdate.getBirthday()) ));
        entity.setAddress( workerUpdate.getAddress() );
        entity.setPhoneNumber( workerUpdate.getPhoneNumber() );

        entity.getPositions().clear();
        for (Long positionId : workerUpdate.getPositionIds()) {
            Optional<Position> position = positionRepository.findById( positionId );
            if ( !position.isPresent() ) throw new ObjectNotFoundException("position #" + positionId + " not found");
            entity.getPositions().add(position.get());
        }

        workerRepository.save(entity);
        return new WorkerObject( entity );
    }

    @Override
    public WorkerObject addWorker(WorkerUpdateObject worker) {
        Worker entity = new Worker();

        entity.setFirstName( worker.getFirstName() );
        entity.setLastName( worker.getLastName() );
        entity.setBirthday( LocalDate.from(DateTimeFormatter.ISO_DATE.parse(worker.getBirthday()) ));
        entity.setAddress( worker.getAddress() );
        entity.setPhoneNumber( worker.getPhoneNumber() );

        entity.setPositions( Collections.emptyList() );
        for (Long positionId : worker.getPositionIds()) {
            Optional<Position> position = positionRepository.findById( positionId );
            if ( !position.isPresent() ) throw new ObjectNotFoundException("position #" + positionId + " not found");
            entity.getPositions().add(position.get());
        }

        workerRepository.save(entity);
        return new WorkerObject( entity );

    }
}
