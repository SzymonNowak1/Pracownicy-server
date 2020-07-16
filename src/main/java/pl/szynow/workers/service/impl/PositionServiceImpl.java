package pl.szynow.workers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.szynow.workers.entity.Position;
import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.PositionObject;
import pl.szynow.workers.model.object.PositionUpdateObject;
import pl.szynow.workers.repository.PositionRepository;
import pl.szynow.workers.service.PositionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public PageResponse<PositionObject> getPositionPage(Integer perPage, Integer page) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Order.asc("id")));
        Page<Position> positionsPage = positionRepository.findAll(pageable);

        return new PageResponse<>(
                positionsPage,
                positionsPage.getContent().stream().map(PositionObject::new).collect(Collectors.toList())
        );
    }

    @Override
    public List<PositionObject> searchByName(String text) {
        List<Position> byNameTextSearch = positionRepository.findByNameTextSearch(text);

        return byNameTextSearch.stream().map(PositionObject::new).collect(Collectors.toList());
    }

    @Override
    public PositionObject getPositionObjectById(Long id) throws ObjectNotFoundException {
        Optional<Position> position = positionRepository.findById(id);
        if ( !position.isPresent() ) throw new ObjectNotFoundException("not found");
        return new PositionObject( position.get() );
    }

    @Override
    public void deletePosition(Long id) throws ObjectNotFoundException {
        Optional<Position> position = positionRepository.findById(id);
        if ( !position.isPresent() ) throw new ObjectNotFoundException("not found");
        positionRepository.delete(position.get());
    }

    @Override
    public PositionObject updatePosition(Long id, PositionUpdateObject positionUpdate) throws ObjectNotFoundException {
        Optional<Position> position = positionRepository.findById(id);
        if ( !position.isPresent() ) throw new ObjectNotFoundException("not found");
        Position entity = position.get();
        entity.setName( positionUpdate.getName() );
        entity.setBase( new BigDecimal( positionUpdate.getBase() ) );
        if ( positionUpdate.getDescription() != null ) {
            entity.setDescription( positionUpdate.getDescription() );
        }

        positionRepository.save(entity);
        return new PositionObject( entity );
    }

    @Override
    public PositionObject addPosition(PositionUpdateObject position) {
        Position entity = new Position();
        entity.setBase( new BigDecimal( position.getBase() ) );
        entity.setDescription( position.getDescription() );
        entity.setName( position.getName() );
        positionRepository.save( entity );
        return new PositionObject( entity );
    }
}
