package it.epicode.DevicesManagment.services.impl;


import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateSerialNumberException;
import it.epicode.DevicesManagment.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.entities.Tablet;
import it.epicode.DevicesManagment.repositories.TabletRepository;
import it.epicode.DevicesManagment.services.interfaces.TabletService;
import it.epicode.DevicesManagment.services.dto.DeviceDTO;
import it.epicode.DevicesManagment.utils.EntitiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TabletServiceImpl implements TabletService {

    @Autowired
    TabletRepository tablet;

    @Autowired
    EntitiesUtils utils;

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
    public Tablet update(Long id, Tablet e) {
        var toModify = this.getById(id);
        utils.copy(e, toModify);
        return tablet.save(toModify);
    }

    @Override
    public Tablet delete(Long id) {
        var s = this.getById(id);
        tablet.delete(s);
        return s;
    }
}
