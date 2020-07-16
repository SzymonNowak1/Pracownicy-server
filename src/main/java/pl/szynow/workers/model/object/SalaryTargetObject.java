package pl.szynow.workers.model.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szynow.workers.entity.SalaryTarget;

@Data
@NoArgsConstructor
public class SalaryTargetObject {

    private Long id;
    private String name;
    private String bankAccount;
    private boolean selected;

    public SalaryTargetObject(SalaryTarget entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.bankAccount = entity.getBankAccount();
        this.selected = entity.getSelected();
    }


}
