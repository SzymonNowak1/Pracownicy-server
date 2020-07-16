package pl.szynow.workers.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name = "salaries" )
@Data
public class Salary {

    public enum Status {
        ACCEPTED,
        CANCELLED,
        EXECUTED
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalDate date;

    private BigDecimal bonus;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn( name = "taxid" )
    @ToString.Exclude
    private Tax tax;

    @ManyToOne
    @JoinColumn( name = "salarytargetid" )
    @ToString.Exclude
    private SalaryTarget salaryTarget;


}
