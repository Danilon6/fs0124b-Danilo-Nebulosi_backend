package it.epicode.DevicesManagment.businesslayer.services.impl;


import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.duplicated.DuplicateSerialNumberException;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.datalayer.entities.Device;
import it.epicode.DevicesManagment.datalayer.entities.Tablet;
import it.epicode.DevicesManagment.datalayer.repositories.TabletRepository;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.TabletService;
import it.epicode.DevicesManagment.businesslayer.services.dto.DeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TabletServiceImpl implements TabletService {

    @Autowired
    TabletRepository tablet;


    @Override
    public Page<Tablet> getAll(Pageable p) {
        return tablet.findAll(p);
    }

    @Override
    public Tablet getById(Long id) {
        return tablet.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Tablet save(DeviceDTO e) {
        var serialNumberDuplicated = tablet.findBySerialNumber(e.getSerialNumber());

        serialNumberDuplicated.ifPresent(
                value -> {throw new DuplicateSerialNumberException(e.getSerialNumber());});

        return tablet.save(Tablet.builder()
                .withBrand(e.getBrand())
                .withModel(e.getModel())
                .withSerialNumber(e.getSerialNumber())
                .withScreenSize(e.getScreenSize())
                .withStatus(e.getStatus())
                .build());
    }

    @Override
    public Tablet update(Long id, Device e) {
        var toModify = this.getById(id);
        toModify.setModel(e.getModel());
        toModify.setBrand(e.getBrand());
        toModify.setSerialNumber(e.getSerialNumber());
        toModify.setScreenSize(e.getScreenSize());
        toModify.setStatus(e.getStatus());
        toModify.setEmployee(e.getEmployee());
        return tablet.save(toModify);
    }

    @Override
    public Tablet delete(Long id) {
        var s = this.getById(id);
        tablet.delete(s);
        return s;
    }
}
