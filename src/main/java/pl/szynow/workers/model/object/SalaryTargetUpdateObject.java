package pl.szynow.workers.model.object;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SalaryTargetUpdateObject {

    @NotNull
    private String name;
    @NotNull
    private String bankAccount;

}
