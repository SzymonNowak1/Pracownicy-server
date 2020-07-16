package pl.szynow.workers.service;

import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.ConfigurationObject;
import pl.szynow.workers.model.object.ConfigurationUpdateObject;

import java.util.List;

public interface ConfigurationService {

    enum Parameter {
        PARAM_1,
        PARAM_2,
        PARAM_3,
        PARAM_4
    }

    ConfigurationObject getConfiguration(Parameter parameter) throws ObjectNotFoundException;
    ConfigurationObject getConfigurationByName(String name) throws ObjectNotFoundException;
    ConfigurationObject setConfiguration(ConfigurationUpdateObject configuration) throws ObjectNotFoundException;
    PageResponse<ConfigurationObject> getConfigurationPage(Integer perPage, Integer page);

}
