package pl.szynow.workers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.ConfigurationObject;
import pl.szynow.workers.model.object.ConfigurationUpdateObject;

import javax.validation.Valid;

@RequestMapping( "/${security.endpoint.api.prefix}" )
public interface ConfigurationController {

    @GetMapping("/configuration")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<PageResponse<ConfigurationObject>> getAllConfigurations(@RequestParam Integer perPage, @RequestParam Integer page);

    @GetMapping("/configuration/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ConfigurationObject> getConfiguration(@PathVariable String name);

    @PutMapping("/configuration/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ConfigurationObject> updateConfiguration(@PathVariable String name, @RequestBody @Valid ConfigurationUpdateObject updateObject );

}
