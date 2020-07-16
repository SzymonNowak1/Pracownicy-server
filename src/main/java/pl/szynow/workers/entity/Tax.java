package pl.szynow.workers.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table( name = "taxes" )
@Data
public class Tax {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private BigDecimal total;

    private BigDecimal gross;

    private BigDecimal net;

    @OneToMany( mappedBy = "tax" )
    @ToString.Exclude
    private List<Salary> salaries;

}
