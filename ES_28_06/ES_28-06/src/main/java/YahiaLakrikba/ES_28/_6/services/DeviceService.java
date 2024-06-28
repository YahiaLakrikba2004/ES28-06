package YahiaLakrikba.ES_28._6.services;

import YahiaLakrikba.ES_28._6.entities.Device;
import YahiaLakrikba.ES_28._6.entities.Employee;
import YahiaLakrikba.ES_28._6.enums.DeviceStatus;

import YahiaLakrikba.ES_28._6.exeptions.BadRequestException;
import YahiaLakrikba.ES_28._6.exeptions.NotFoundException;
import YahiaLakrikba.ES_28._6.repositories.DeviceRepository;
import YahiaLakrikba.ES_28._6.requests.DevicePatchRequest;
import YahiaLakrikba.ES_28._6.requests.DeviceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final EmployeeService employeeService;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, EmployeeService employeeService) {
        this.deviceRepository = deviceRepository;
        this.employeeService = employeeService;
    }

    public Device createDevice(DeviceRequest request) throws NotFoundException {
        Device device = new Device();
        return deviceRepository.save(applyRequestToDevice(request, device));
    }

    public Device updateDevice(int id, DeviceRequest request) throws NotFoundException {
        Device device = getDeviceById(id);
        return deviceRepository.save(applyRequestToDevice(request, device));
    }

    public Device patchUpdateDevice(int id, DevicePatchRequest request) throws NotFoundException, BadRequestException {
        Device device = getDeviceById(id);
        if (request.getType() != null) {
            device.setType(request.getType());
        }
        if (request.getStatus() != null) {
            device.setStatus(request.getStatus());
        }
        if (request.getStatus() == DeviceStatus.AVAILABLE) {
            device.setEmployee(null);
        }
        if (request.getEmployeeId() != null) {
            if (device.getStatus() == DeviceStatus.MAINTENANCE) {
                throw new BadRequestException("Device is under maintenance");
            }
            Employee employee = employeeService.getEmployeeById(request.getEmployeeId());
            device.setStatus(DeviceStatus.ASSIGNED);
            device.setEmployee(employee);
        }
        return deviceRepository.save(device);
    }

    public Device getDeviceById(int id) throws NotFoundException {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Device with id=" + id + " not found"));
    }

    public Page<Device> getAllDevices(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    public void deleteDevice(int id) throws NotFoundException {
        Device device = getDeviceById(id);
        deviceRepository.delete(device);
    }

    private Device applyRequestToDevice(DeviceRequest request, Device device) throws NotFoundException {
        device.setType(request.getType());
        device.setStatus(request.getStatus());
        if (request.getStatus() == DeviceStatus.AVAILABLE) {
            device.setEmployee(null);
        }
        if (request.getEmployeeId() != null) {
            if (device.getStatus() == DeviceStatus.MAINTENANCE) {
                throw new BadRequestException("Device is under maintenance");
            }
            Employee employee = employeeService.getEmployeeById(request.getEmployeeId());
            device.setStatus(DeviceStatus.ASSIGNED);
            device.setEmployee(employee);
        }
        return device;
    }
}
