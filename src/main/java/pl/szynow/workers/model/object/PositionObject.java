package pl.szynow.workers.model.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szynow.workers.entity.Position;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PositionObject {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private String base;

    public PositionObject(Position entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.base = entity.getBase().toString();
    }

}
