package YahiaLakrikba.ES_28._6.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Data
@Entity
@Getter
@Setter
@ToString
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Name;
    private String SureName;

    @Column(unique = true)
    private String username;

    private String email;
    private String profilePicture;


    @OneToMany(mappedBy = "employee")
    private List<Device> devices;



}
