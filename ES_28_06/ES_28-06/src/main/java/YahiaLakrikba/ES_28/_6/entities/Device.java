package YahiaLakrikba.ES_28._6.entities;




import YahiaLakrikba.ES_28._6.enums.DeviceStatus;
import YahiaLakrikba.ES_28._6.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", status=" + status +
                ", type=" + type +
                ", employee=" + (employee != null ? employee.getId() : null) +
                '}';
    }
}
