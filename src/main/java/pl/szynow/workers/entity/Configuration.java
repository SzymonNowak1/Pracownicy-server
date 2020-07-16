package pl.szynow.workers.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "configuration" )
@Data
public class Configuration {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    private String description;

    private String value;

}
