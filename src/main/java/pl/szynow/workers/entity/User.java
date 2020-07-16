package pl.szynow.workers.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "users" )
@Data
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false )
    private String username;

    @Column( length = 60, nullable = false )
    private String password;

    @Column( nullable = false )
    private String email;

    @Column( nullable = false )
    private Boolean active = false;

    private String generated;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "userroles",
            joinColumns = @JoinColumn(
                    name = "userid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roleid", referencedColumnName = "id")
            )
    @ToString.Exclude
    private List<Role> roles;

    @OneToMany( mappedBy = "user" )
    @ToString.Exclude
    private List<Token> tokens;

    @OneToMany( mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Worker> workers;

}
