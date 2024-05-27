package it.epicode.DevicesManagment.businesslayer.services.impl;


import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.duplicated.DuplicateSerialNumberException;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.datalayer.entities.Device;
import it.epicode.DevicesManagment.datalayer.entities.Smartphone;
import it.epicode.DevicesManagment.datalayer.repositories.SmartphoneRepository;
import it.epicode.DevicesManagment.businesslayer.services.dto.DeviceDTO;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    @Autowired
    SmartphoneRepository smartphone;


    @Override
    public Page<Smartphone> getAll(Pageable p) {
        return smartphone.findAll(p);
    }

    @Override
    public Smartphone getById(Long id) {
        return smartphone.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Smartphone save(DeviceDTO e) {
        var serialNumberDuplicated = smartphone.findBySerialNumber(e.getSerialNumber());
        serialNumberDuplicated.ifPresent(
                value -> {throw new DuplicateSerialNumberException(e.getSerialNumber());});

        return smartphone.save(Smartphone.builder()
                .withModel(e.getModel())
                .withBrand(e.getBrand())
                .withSerialNumber(e.getSerialNumber())
                .withScreenSize(e.getScreenSize())
                .withStatus(e.getStatus())
                .build());

    }

    @Override
    public Smartphone update(Long id, Device e) {
        var toModify = this.getById(id);
        toModify.setModel(e.getModel());
        toModify.setBrand(e.getBrand());
        toModify.setSerialNumber(e.getSerialNumber());
        toModify.setScreenSize(e.getScreenSize());
        toModify.setStatus(e.getStatus());
        toModify.setEmployee(e.getEmployee());
        return smartphone.save(toModify);
    }

    @Override
    public Smartphone delete(Long id) {
        var s = this.getById(id);
        smartphone.delete(s);
        return s;
    }
}
