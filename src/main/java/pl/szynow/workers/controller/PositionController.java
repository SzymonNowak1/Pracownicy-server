package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.PositionObject;
import pl.szynow.workers.model.object.PositionUpdateObject;

import javax.validation.constraints.Size;
import java.util.List;

@RequestMapping("/${security.endpoint.api.prefix}")
public interface PositionController {

    @GetMapping("/positions")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<PageResponse<PositionObject>> getAllPositions(@RequestParam Integer perPage, @RequestParam Integer page);

    @GetMapping("/positions/searchtext")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<List<PositionObject>> searchPositions(@RequestParam @Size(min = 3) String text);

    @GetMapping("/positions/{positionId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<PositionObject> getPosition(@PathVariable Long positionId);

    @PostMapping("/positions")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<PositionObject> addPosition(@RequestBody PositionUpdateObject position);

    @PutMapping("/positions/{positionId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<PositionObject> updatePosition(@PathVariable Long positionId, @RequestBody PositionUpdateObject position);

    @DeleteMapping("/positions/{positionId}")
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity deletePosition(@PathVariable Long positionId);

}
