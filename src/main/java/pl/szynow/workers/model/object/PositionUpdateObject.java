package pl.szynow.workers.model.object;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PositionUpdateObject {

    @NotNull
    private String name;
    private String description;
    @NotNull
    private String base;

}
