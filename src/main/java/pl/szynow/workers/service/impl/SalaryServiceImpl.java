package pl.szynow.workers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.szynow.workers.entity.Salary;
import pl.szynow.workers.entity.SalaryTarget;
import pl.szynow.workers.entity.Tax;
import pl.szynow.workers.entity.User;
import pl.szynow.workers.exception.ForbiddenOperationException;
import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.SalaryObject;
import pl.szynow.workers.model.object.SalaryUpdateObject;
import pl.szynow.workers.repository.SalaryRepository;
import pl.szynow.workers.repository.SalaryTargetRepository;
import pl.szynow.workers.repository.TaxRepository;
import pl.szynow.workers.repository.WorkerRepository;
import pl.szynow.workers.service.SalaryService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private SalaryTargetRepository salaryTargetRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Override
    public PageResponse<SalaryObject> getSalaryPage(Integer perPage, Integer page) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Order.asc("id")));
        Page<Salary> salariesPage = salaryRepository.findAll(pageable);

        return new PageResponse<>(
                salariesPage,
                salariesPage.getContent().stream().map(SalaryObject::new).collect(Collectors.toList())
        );
    }

    @Override
    public List<SalaryObject> getAllUserSalaries(User user) {
        List<Salary> byUserId = salaryRepository.findByUserId(user.getId());

        return byUserId.stream().map(SalaryObject::new).collect(Collectors.toList());
    }

    @Override
    public SalaryObject getSalaryObjectById(Long id) throws ObjectNotFoundException {
        Optional<Salary> salary = salaryRepository.findById(id);
        if ( !salary.isPresent() ) throw new ObjectNotFoundException("not found");
        return new SalaryObject( salary.get() );
    }

    @Override
    public void deleteSalary(Long id) throws ObjectNotFoundException, ForbiddenOperationException {
        Optional<Salary> salary = salaryRepository.findById(id);
        if ( !salary.isPresent() ) throw new ObjectNotFoundException("not found");

        if ( salary.get().getStatus().equals(Salary.Status.EXECUTED) ) throw new ForbiddenOperationException("Forbidden operation: salary already executed");

        salary.get().setStatus(Salary.Status.CANCELLED);

        salaryRepository.save(salary.get());
    }

    // TODO: naprawić statusy, na razie tak zostają

    @Override
    public SalaryObject updateSalary(Long id, SalaryUpdateObject salaryUpdate) throws ObjectNotFoundException {
        Optional<Salary> salary = salaryRepository.findById(id);

        if ( !salary.isPresent() ) throw new ObjectNotFoundException("not found");
        Salary entity = salary.get();

        entity.setDate( LocalDate.from( DateTimeFormatter.ISO_DATE.parse( salaryUpdate.getDate() ) ) );
        entity.setBonus( new BigDecimal( salaryUpdate.getBonus() ) );

        salaryRepository.save(entity);
        return new SalaryObject( entity );
    }

    @Override
    public SalaryObject addSalary(SalaryUpdateObject salary) {
        Salary entity = new Salary();

        entity.setDate( LocalDate.from( DateTimeFormatter.ISO_DATE.parse( salary.getDate() ) ) );
        entity.setBonus( new BigDecimal( salary.getBonus() ) );
        entity.setStatus(Salary.Status.ACCEPTED);

        if ( salary.getSalaryTargetId() != null ) {
            Optional<SalaryTarget> byId = salaryTargetRepository.findById(salary.getSalaryTargetId());
            if ( !byId.isPresent() ) throw new ObjectNotFoundException("Salary target #" + salary.getSalaryTargetId() + " not found");

            entity.setSalaryTarget( byId.get() );
        }

        // TODO: nowy tax trzeba poprawnie wygenerować, teraz robimy to po prostu
        Tax tax = new Tax();
        tax.setTotal(new BigDecimal("1200.00"));
        tax.setGross(new BigDecimal("1000.00"));
        tax.setNet(new BigDecimal("800.00"));

        taxRepository.save( tax );

        entity.setTax( tax );
        salaryRepository.save(entity);
        return new SalaryObject( entity );
    }
}
