package it.epicode.DevicesManagment.services.impl;

import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateSerialNumberException;
import it.epicode.DevicesManagment.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.entities.Device;
import it.epicode.DevicesManagment.entities.Laptop;
import it.epicode.DevicesManagment.repositories.LaptopRepository;
import it.epicode.DevicesManagment.services.dto.DeviceDTO;
import it.epicode.DevicesManagment.services.interfaces.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    LaptopRepository laptop;


    @Override
    public Page<Laptop> getAll(Pageable p) {
        return laptop.findAll(p);
    }

    @Override
    public Laptop getById(Long id) {
        return laptop.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Laptop save(DeviceDTO e) {
        var serialNumberDuplicated = laptop.findBySerialNumber(e.getSerialNumber());
        serialNumberDuplicated.ifPresent(
                value -> {throw new DuplicateSerialNumberException(e.getSerialNumber());});

        return laptop.save(Laptop.builder()
                .withModel(e.getModel())
                .withBrand(e.getBrand())
                .withSerialNumber(e.getSerialNumber())
                .withScreenSize(e.getScreenSize())
                .withStatus(e.getStatus())
                .build());
    }

    @Override
    public Laptop update(Long id, Device e) {
        var toModify = this.getById(id);
        toModify.setModel(e.getModel());
        toModify.setBrand(e.getBrand());
        toModify.setSerialNumber(e.getSerialNumber());
        toModify.setScreenSize(e.getScreenSize());
        toModify.setStatus(e.getStatus());
        toModify.setEmployee(e.getEmployee());
        return laptop.save(toModify);
    }

    @Override
    public Laptop delete(Long id) {
        var s = this.getById(id);
        laptop.delete(s);
        return s;
    }
}
