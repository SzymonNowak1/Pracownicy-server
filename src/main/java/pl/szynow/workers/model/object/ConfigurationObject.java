package pl.szynow.workers.model.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szynow.workers.entity.Configuration;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ConfigurationObject {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    private String description;
    private String value;

    public ConfigurationObject(Configuration entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.value = entity.getValue();
    }

}
