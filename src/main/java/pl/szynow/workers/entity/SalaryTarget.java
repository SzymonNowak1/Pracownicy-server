package pl.szynow.workers.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "salarytargets" )
@Data
public class SalaryTarget {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    @Column( name = "bankaccount" )
    private String bankAccount;

    private Boolean selected;

    @ManyToOne
    @JoinColumn( name = "workerid" )
    @ToString.Exclude
    private Worker worker;

    @OneToMany( mappedBy = "salaryTarget" )
    @ToString.Exclude
    private List<Salary> salaries;

}
