package YahiaLakrikba.ES_28._6.controller;

import YahiaLakrikba.ES_28._6.entities.Device;

import YahiaLakrikba.ES_28._6.exeptions.BadRequestException;
import YahiaLakrikba.ES_28._6.exeptions.NotFoundException;
import YahiaLakrikba.ES_28._6.exeptions.DeviceRequest;
import YahiaLakrikba.ES_28._6.services.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<Page<Device>> getAllDevices(Pageable pageable) {
        Page<Device> devices = deviceService.getAllDevices(pageable);
        return ResponseEntity.ok(devices);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable int id) throws NotFoundException {
        Device device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody @Valid DeviceRequest deviceRequest,
                                               BindingResult bindingResult) throws BadRequestException, NotFoundException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getAllErrors());
        }
        Device createdDevice = deviceService.createDevice(deviceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDevice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable int id,
                                               @RequestBody @Valid DeviceRequest deviceRequest,
                                               BindingResult bindingResult) throws NotFoundException, BadRequestException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("Validation error: " + bindingResult.getAllErrors());
        }
        Device updatedDevice = deviceService.updateDevice(id, deviceRequest);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable int id) throws NotFoundException {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
