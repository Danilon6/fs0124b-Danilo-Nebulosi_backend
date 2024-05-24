package it.epicode.DevicesManagment.controllers.api;

import it.epicode.DevicesManagment.controllers.exceptions.ApiValidationException;
import it.epicode.DevicesManagment.controllers.models.DeviceRequest;
import it.epicode.DevicesManagment.entities.*;
import it.epicode.DevicesManagment.entities.enums.Status;
import it.epicode.DevicesManagment.services.interfaces.DeviceService;
import it.epicode.DevicesManagment.services.interfaces.LaptopService;
import it.epicode.DevicesManagment.services.interfaces.SmartphoneService;
import it.epicode.DevicesManagment.services.dto.DeviceDTO;
import it.epicode.DevicesManagment.services.interfaces.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/device")
public class DeviceController {

    @Autowired
    DeviceService device;
    @Autowired
    SmartphoneService smartphone;
    @Autowired
    TabletService tablet;
    @Autowired
    LaptopService laptop;


    //RECUPERO ED ELIMINAZIONE DEVICE
    @GetMapping
    public ResponseEntity<Page<Device>> getDevices (Pageable p) {
        var allDevices = device.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allDevices.getTotalElements()));
        return new ResponseEntity<>(allDevices, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice (@PathVariable Long id) {
        var d = device.getById(id);
        return new ResponseEntity<>(d, null, HttpStatus.FOUND);
    }

    //CRUD SMARTPHONE
    @GetMapping("/smartphone")
    public ResponseEntity<Page<Smartphone>> getSmartphones (Pageable p) {
        var allSmartphone = smartphone.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allSmartphone.getTotalElements()));
        return new ResponseEntity<>(allSmartphone, headers, HttpStatus.OK);
    }

    @GetMapping("/smartphone/{id}")
    public ResponseEntity<Smartphone> getSmartphone (@PathVariable Long id) {
        var s = smartphone.getById(id);
        return new ResponseEntity<>(s, null, HttpStatus.FOUND);
    }

    @PostMapping("/smartphone")
    public ResponseEntity<Device> addSmartphone (@RequestBody @Validated DeviceRequest deviceToSave, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var e = smartphone.save(DeviceDTO.builder()
                .withBrand(deviceToSave.brand())
                .withModel(deviceToSave.model())
                .withSerialNumber(deviceToSave.serialNumber())
                .withScreenSize(deviceToSave.screenSize())
                .withStatus(Status.AVAILABLE)
                .build());
        return new ResponseEntity<>(e, null, HttpStatus.CREATED);
    }

    @PutMapping("/smartphone/{id}")
    public ResponseEntity<Smartphone> updateSmartphone (
            @PathVariable Long id,
            @RequestBody Smartphone smartphoneModified
    ) {
        var e = smartphone.update(id, smartphoneModified);
        return new ResponseEntity<>(e, null, HttpStatus.OK);
    }

    @DeleteMapping("/smartphone/{id}")
    public ResponseEntity<Smartphone> deleteSmartphone ( @PathVariable Long id){
        var s = smartphone.delete(id);
        return new ResponseEntity<>(s, null, HttpStatus.OK);
    }

    //CRUD TABLET
    @GetMapping("/tablet")
    public ResponseEntity<Page<Tablet>> getTablet (Pageable p) {
        var allTablet = tablet.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allTablet.getTotalElements()));
        return new ResponseEntity<>(allTablet, headers, HttpStatus.OK);
    }

    @GetMapping("/tablet/{id}")
    public ResponseEntity<Tablet> getTablet (@PathVariable Long id) {
        var t = tablet.getById(id);
        return new ResponseEntity<>(t, null, HttpStatus.FOUND);
    }

    @PostMapping("/tablet")
    public ResponseEntity<Device> addTablet (@RequestBody @Validated DeviceRequest deviceToSave, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var e = tablet.save(DeviceDTO.builder()
                .withBrand(deviceToSave.brand())
                .withModel(deviceToSave.model())
                .withSerialNumber(deviceToSave.serialNumber())
                .withScreenSize(deviceToSave.screenSize())
                .withBrand(deviceToSave.brand())
                .build());
        return new ResponseEntity<>(e, null, HttpStatus.CREATED);
    }

    @PutMapping("/tablet/{id}")
    public ResponseEntity<Tablet> updateTablet (
            @PathVariable Long id,
            @RequestBody Tablet tabletModified
    ) {
        var e = tablet.update(id, tabletModified);
        return new ResponseEntity<>(e, null, HttpStatus.OK);
    }

    @DeleteMapping("/tablet/{id}")
    public ResponseEntity<Tablet> deleteTablet ( @PathVariable Long id){
        var t = tablet.delete(id);
        return new ResponseEntity<>(t, null, HttpStatus.OK);
    }


    //CRUD LAPTOP
    @GetMapping("/laptop")
    public ResponseEntity<Page<Laptop>> getLaptop (Pageable p) {
        var allLaptop = laptop.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allLaptop.getTotalElements()));
        return new ResponseEntity<>(allLaptop, headers, HttpStatus.OK);
    }

    @GetMapping("/laptop/{id}")
    public ResponseEntity<Laptop> getLaptop (@PathVariable Long id) {
        var l = laptop.getById(id);
        return new ResponseEntity<>(l, null, HttpStatus.FOUND);
    }

    @PostMapping("/laptop")
    public ResponseEntity<Device> addLaptop (@RequestBody @Validated DeviceRequest deviceToSave, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var e = laptop.save(DeviceDTO.builder()
                .withBrand(deviceToSave.brand())
                .withModel(deviceToSave.model())
                .withSerialNumber(deviceToSave.serialNumber())
                .withScreenSize(deviceToSave.screenSize())
                .withBrand(deviceToSave.brand())
                .build());
        return new ResponseEntity<>(e, null, HttpStatus.CREATED);
    }

    @PutMapping("/laptop/{id}")
    public ResponseEntity<Laptop> updateLaptop (
            @PathVariable Long id,
            @RequestBody Laptop laptopModified
    ) {
        var e = laptop.update(id, laptopModified);
        return new ResponseEntity<>(e, null, HttpStatus.OK);
    }

    @DeleteMapping("/laptop/{id}")
    public ResponseEntity<Laptop> deleteLaptop ( @PathVariable Long id){
        var l = laptop.delete(id);
        return new ResponseEntity<>(l, null, HttpStatus.OK);
    }

    //ASSEGNAZIONE DISPOSITIVO
    @PutMapping("/assignto/")
    public ResponseEntity<Tablet> assignTo (
            @RequestParam Long employeeId,
            @RequestParam Long deviceId
    ) {
        device.assignTo(employeeId, deviceId);
        return ResponseEntity.ok(null);
    }
}
