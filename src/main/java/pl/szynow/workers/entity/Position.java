package pl.szynow.workers.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table( name = "positions" )
@Data
public class Position {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    private String description;

    private BigDecimal base;

    @ManyToMany( mappedBy = "positions" )
    @ToString.Exclude
    private List<Worker> workers;
}
