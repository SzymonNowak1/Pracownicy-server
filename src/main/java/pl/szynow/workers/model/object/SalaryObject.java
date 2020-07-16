package pl.szynow.workers.model.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.szynow.workers.entity.Salary;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class SalaryObject {

    @NotNull
    private Long id;
    @NotNull
    private String date;
    private String bonus;
    @NotNull
    private String status;

    private String taxTotal;
    private String taxGross;
    private String taxNet;

    private Long salaryTargetId;
    private String salaryTargetName;

    private Long workerId;
    private String workerFirstName;
    private String workerLastName;

    public SalaryObject(Salary entity) {
        this.id = entity.getId();
        this.date = DateTimeFormatter.ISO_DATE.format( entity.getDate() );
        if ( entity.getBonus() != null ) {
            this.bonus = entity.getBonus().toString();
        } else {
            this.bonus = "0.00";
        }
        this.status = entity.getStatus().name();
        if ( entity.getTax() != null ) {
            this.taxGross = entity.getTax().getGross().toString();
            this.taxNet = entity.getTax().getNet().toString();
            this.taxTotal = entity.getTax().getTotal().toString();
        }
        if ( entity.getSalaryTarget() != null ) {
            this.salaryTargetId = entity.getSalaryTarget().getId();
            this.salaryTargetName = entity.getSalaryTarget().getName();

            if ( entity.getSalaryTarget().getWorker() != null ) {
                this.workerId = entity.getSalaryTarget().getWorker().getId();
                this.workerFirstName = entity.getSalaryTarget().getWorker().getFirstName();
                this.workerLastName = entity.getSalaryTarget().getWorker().getLastName();
            }

        }
    }

}
