package pl.szynow.workers.service;

import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.PositionObject;
import pl.szynow.workers.model.object.PositionUpdateObject;

import java.util.List;

public interface PositionService {

    PageResponse<PositionObject> getPositionPage(Integer perPage, Integer page);
    List<PositionObject> searchByName(String text);
    PositionObject getPositionObjectById(Long id) throws ObjectNotFoundException;
    void deletePosition(Long id) throws ObjectNotFoundException;
    PositionObject updatePosition(Long id, PositionUpdateObject positionUpdate) throws ObjectNotFoundException;
    PositionObject addPosition(PositionUpdateObject position);

}
