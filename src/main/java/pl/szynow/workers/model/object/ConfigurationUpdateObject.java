package pl.szynow.workers.model.object;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConfigurationUpdateObject {

    @NotNull
    private Long id;
    @NotNull
    private String value;

}
