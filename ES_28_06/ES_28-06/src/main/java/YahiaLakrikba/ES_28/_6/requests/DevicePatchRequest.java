package YahiaLakrikba.ES_28._6.requests;

import YahiaLakrikba.ES_28._6.enums.DeviceStatus;
import YahiaLakrikba.ES_28._6.enums.DeviceType;
import lombok.Data;

    @Data
    public class DevicePatchRequest {
        private DeviceStatus status;
        private DeviceType type;
        private Integer employeeId;
    }

