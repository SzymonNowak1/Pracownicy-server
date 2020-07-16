package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.controller.PositionController;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.PositionObject;
import pl.szynow.workers.model.object.PositionUpdateObject;
import pl.szynow.workers.service.PositionService;

import java.util.List;

@RestController
public class PositionControllerImpl implements PositionController {

    @Autowired
    private PositionService positionService;

    @Override
    public ResponseEntity<PageResponse<PositionObject>> getAllPositions(Integer perPage, Integer page) {
        return new ResponseEntity<>( positionService.getPositionPage(perPage, page), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<PositionObject>> searchPositions(String text) {
        return new ResponseEntity<>( positionService.searchByName(text), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<PositionObject> getPosition(Long positionId) {
        return new ResponseEntity<>( positionService.getPositionObjectById(positionId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PositionObject> addPosition(PositionUpdateObject position) {
        return new ResponseEntity<>( positionService.addPosition(position), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PositionObject> updatePosition(Long positionId, PositionUpdateObject position) {
        return new ResponseEntity<>( positionService.updatePosition(positionId, position), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deletePosition(Long positionId) {
        positionService.deletePosition(positionId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
