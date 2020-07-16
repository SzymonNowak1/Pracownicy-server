package pl.szynow.workers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szynow.workers.entity.SalaryTarget;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.entity.Worker;
import pl.szynow.workers.exception.ForbiddenOperationException;
import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.object.SalaryTargetObject;
import pl.szynow.workers.model.object.SalaryTargetUpdateObject;
import pl.szynow.workers.repository.SalaryTargetRepository;
import pl.szynow.workers.service.SalaryTargetService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryTargetServiceImpl implements SalaryTargetService {

    @Autowired
    private SalaryTargetRepository salaryTargetRepository;

    @Override
    public List<SalaryTargetObject> getAllUserSalaryTargets(User user) {
        List<SalaryTarget> byUserId = salaryTargetRepository.findByUserId(user.getId());

        return byUserId.stream().map(SalaryTargetObject::new).collect(Collectors.toList());
    }

    @Override
    public SalaryTargetObject getSalaryTargetObjectById(User user, Long id) throws ObjectNotFoundException, ForbiddenOperationException {
        Optional<SalaryTarget> salaryTarget = salaryTargetRepository.findById(id);
        if ( !salaryTarget.isPresent() ) throw new ObjectNotFoundException("not found");

        if ( salaryTarget.get().getWorker() == null  ) throw new ForbiddenOperationException("Forbidden operation: worker is null");
        if ( salaryTarget.get().getWorker().getUser() == null ) throw new ForbiddenOperationException("Forbidden operation: user is null");
        if ( !salaryTarget.get().getWorker().getUser().getId().equals(user.getId()) ) throw new ForbiddenOperationException("User not match");

        return new SalaryTargetObject( salaryTarget.get() );
    }

    @Override
    public void deleteSalaryTarget(User user, Long id) throws ObjectNotFoundException, ForbiddenOperationException {
        Optional<SalaryTarget> salaryTarget = salaryTargetRepository.findById(id);
        if ( !salaryTarget.isPresent() ) throw new ObjectNotFoundException("not found");

        if ( salaryTarget.get().getWorker() == null  ) throw new ForbiddenOperationException("Forbidden operation: worker is null");
        if ( salaryTarget.get().getWorker().getUser() == null ) throw new ForbiddenOperationException("Forbidden operation: user is null");
        if ( !salaryTarget.get().getWorker().getUser().getId().equals(user.getId()) ) throw new ForbiddenOperationException("User not match");

        if ( salaryTarget.get().getSelected() ) throw new ForbiddenOperationException("Forbidden operation: delete selected");

        salaryTargetRepository.delete(salaryTarget.get());
    }

    @Override
    public SalaryTargetObject updateSalaryTarget(User user, Long id, SalaryTargetUpdateObject salaryTargetUpdate) throws ObjectNotFoundException, ForbiddenOperationException {
        Optional<SalaryTarget> salaryTarget = salaryTargetRepository.findById(id);

        if ( !salaryTarget.isPresent() ) throw new ObjectNotFoundException("not found");

        if ( salaryTarget.get().getWorker() == null  ) throw new ForbiddenOperationException("Forbidden operation: worker is null");
        if ( salaryTarget.get().getWorker().getUser() == null ) throw new ForbiddenOperationException("Forbidden operation: user is null");
        if ( !salaryTarget.get().getWorker().getUser().getId().equals(user.getId()) ) throw new ForbiddenOperationException("User not match");

        SalaryTarget entity = salaryTarget.get();

        entity.setBankAccount( salaryTargetUpdate.getBankAccount() );
        entity.setName( salaryTargetUpdate.getName() );
        entity.setSelected( false );

        salaryTargetRepository.save(entity);
        return new SalaryTargetObject( entity );
    }

    @Override
    public SalaryTargetObject addSalaryTarget(User user, SalaryTargetUpdateObject salaryTarget) throws ForbiddenOperationException {
        SalaryTarget entity = new SalaryTarget();

        entity.setBankAccount( salaryTarget.getBankAccount() );
        entity.setName( salaryTarget.getName() );
        entity.setSelected( false );

        if ( user.getWorkers().size() != 1 ) throw new ForbiddenOperationException("Invalid user-worker state");
        Worker worker = user.getWorkers().get(0);

        entity.setWorker( worker );

        salaryTargetRepository.save(entity);
        return new SalaryTargetObject( entity );
    }

    @Override
    public SalaryTargetObject setSelectedSalaryTarget(User user, Long id) throws ForbiddenOperationException {
        Optional<SalaryTarget> salaryTarget = salaryTargetRepository.findById(id);

        if ( !salaryTarget.isPresent() ) throw new ObjectNotFoundException("not found");

        if ( salaryTarget.get().getWorker() == null  ) throw new ForbiddenOperationException("Forbidden operation: worker is null");
        if ( salaryTarget.get().getWorker().getUser() == null ) throw new ForbiddenOperationException("Forbidden operation: user is null");
        if ( !salaryTarget.get().getWorker().getUser().getId().equals(user.getId()) ) throw new ForbiddenOperationException("User not match");

        List<SalaryTarget> byUserId = salaryTargetRepository.findByUserId(user.getId());
        byUserId.forEach( target -> target.setSelected( false ) );
        salaryTargetRepository.saveAll( byUserId );

        salaryTarget.get().setSelected( true );
        salaryTargetRepository.save( salaryTarget.get() );

        return new SalaryTargetObject( salaryTarget.get() );
    }

}

