package pl.szynow.workers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.szynow.workers.entity.Configuration;
import pl.szynow.workers.exception.ObjectNotFoundException;
import pl.szynow.workers.model.PageResponse;
import pl.szynow.workers.model.object.ConfigurationObject;
import pl.szynow.workers.model.object.ConfigurationUpdateObject;
import pl.szynow.workers.repository.ConfigurationRepository;
import pl.szynow.workers.service.ConfigurationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Override
    public PageResponse<ConfigurationObject> getConfigurationPage(Integer perPage, Integer page) {

        Pageable pageable = PageRequest.of(page, perPage, Sort.by(Sort.Order.asc("id")));
        Page<Configuration> allPage = configurationRepository.findAll(pageable);

        return new PageResponse<>(
                allPage,
                allPage.getContent().stream().map(ConfigurationObject::new).collect(Collectors.toList())
        );
    }

    @Override
    public ConfigurationObject getConfiguration(Parameter parameter) throws ObjectNotFoundException {
        return getConfigurationByName(parameter.name());
    }

    @Override
    public ConfigurationObject getConfigurationByName(String name) throws ObjectNotFoundException {
        Optional<Configuration> configuration = configurationRepository.findByName(name);
        if ( !configuration.isPresent() ) throw new ObjectNotFoundException("Not found");

        return new ConfigurationObject( configuration.get() );
    }

    @Override
    public ConfigurationObject setConfiguration(ConfigurationUpdateObject configuration) throws ObjectNotFoundException {
        Optional<Configuration> original = configurationRepository.findById(configuration.getId());
        if ( !original.isPresent() ) throw new ObjectNotFoundException("Not found");

        Configuration entity = original.get();
        entity.setValue( configuration.getValue() );

        configurationRepository.save( entity );
        return new ConfigurationObject( entity );
    }
}
