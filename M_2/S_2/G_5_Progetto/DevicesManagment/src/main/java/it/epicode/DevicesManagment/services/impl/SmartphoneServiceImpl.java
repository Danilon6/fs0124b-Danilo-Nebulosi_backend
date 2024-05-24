package it.epicode.DevicesManagment.services.impl;


import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateSerialNumberException;
import it.epicode.DevicesManagment.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.entities.Smartphone;
import it.epicode.DevicesManagment.repositories.SmartphoneRepository;
import it.epicode.DevicesManagment.services.dto.DeviceDTO;
import it.epicode.DevicesManagment.services.interfaces.SmartphoneService;
import it.epicode.DevicesManagment.utils.EntitiesUtils;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    @Autowired
    SmartphoneRepository smartphone;

    @Autowired
    EntitiesUtils utils;

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
    public Smartphone update(Long id, Smartphone e) {
        var toModify = this.getById(id);
        utils.copy(e, toModify);
        return smartphone.save(toModify);
    }

    @Override
    public Smartphone delete(Long id) {
        var s = this.getById(id);
        smartphone.delete(s);
        return s;
    }
}
