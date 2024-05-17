package it.epicode.booking.dao;

import it.epicode.booking.entities.Building;
import it.epicode.booking.repositories.BuildingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BuildingService implements CRUDDao<Building>{

    @Autowired
    BuildingRepository building;

    @Override
    public void create(Building e) {
        building.save(e);
    }

    @Override
    public void update(Building e) {
        building.save(e);
    }

    @Override
    public void delete(Long id) {
        building.deleteById(id);
    }

    @Override
    public Building getById(Long id) {
        return building.findById(id).orElse(null);
    }
}
