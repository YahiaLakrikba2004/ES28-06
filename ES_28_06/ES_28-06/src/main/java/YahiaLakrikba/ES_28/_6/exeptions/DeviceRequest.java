package YahiaLakrikba.ES_28._6.exeptions;



import YahiaLakrikba.ES_28._6.enums.DeviceStatus;
import YahiaLakrikba.ES_28._6.enums.DeviceType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceRequest {
    @NotNull(message = "Status cannot be null")
    private DeviceStatus status;

    @NotNull(message = "Type cannot be null")
    private DeviceType type;

    private Integer employeeId;
}

