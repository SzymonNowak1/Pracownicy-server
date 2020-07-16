package pl.szynow.workers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szynow.workers.controller.ConfigurationController;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.ConfigurationObject;
import pl.szynow.workers.model.object.ConfigurationUpdateObject;
import pl.szynow.workers.service.ConfigurationService;

@RestController
public class ConfigurationControllerImpl implements ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @Override
    public ResponseEntity<PageResponse<ConfigurationObject>> getAllConfigurations(Integer perPage, Integer page) {
        return new ResponseEntity<>( configurationService.getConfigurationPage(perPage, page), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<ConfigurationObject> getConfiguration(String name) {
        ConfigurationService.Parameter parameter = ConfigurationService.Parameter.valueOf( name );
        // TODO: dodać łapanie wyjątku
        return new ResponseEntity<>( configurationService.getConfiguration( parameter ), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<ConfigurationObject> updateConfiguration(String name, ConfigurationUpdateObject updateObject ) {
        return new ResponseEntity<>( configurationService.setConfiguration( updateObject ), HttpStatus.OK );
    }

}
