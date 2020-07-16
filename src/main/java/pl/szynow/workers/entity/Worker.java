package pl.szynow.workers.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table( name = "workers" )
@Data
public class Worker {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn( name = "userid" )
    @ToString.Exclude
    private User user;

    @Column( name = "firstname" )
    private String firstName;

    @Column( name = "lastname" )
    private String lastName;


    private LocalDate birthday;

    private String address;

    @Column( name = "phonenumber" )
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "workerpositions",
            joinColumns = @JoinColumn(
                    name = "workerid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "positionid", referencedColumnName = "id" )
    )
    @ToString.Exclude
    private List<Position> positions;

    @OneToMany( mappedBy = "worker" )
    @ToString.Exclude
    private List<SalaryTarget> salaryTargets;

}
