package it.epicode.booking.dao;

import it.epicode.booking.entities.Building;
import it.epicode.booking.entities.WorkStation;
import it.epicode.booking.enums.Cities;
import it.epicode.booking.enums.Type;
import it.epicode.booking.repositories.WorkStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkStationService implements CRUDDao<WorkStation>{

    @Autowired
    WorkStationRepository workStation;

    @Override
    public void create(WorkStation e) {
        workStation.save(e);
    }

    @Override
    public void update(WorkStation e) {
        workStation.save(e);
    }

    @Override
    public void delete(Long id) {
        workStation.deleteById(id);
    }

    @Override
    public WorkStation getById(Long id) {
        return workStation.findById(id).orElse(null);
    }

    public List<WorkStation> getByTypeAndCity(Type type, Cities city){
        return workStation.findByTypeAndBuildingCity(type, city);
    }
}
