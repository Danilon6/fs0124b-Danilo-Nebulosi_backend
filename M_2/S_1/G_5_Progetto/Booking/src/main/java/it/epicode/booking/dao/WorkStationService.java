package it.epicode.booking.dao;

import it.epicode.booking.entities.Building;
import it.epicode.booking.entities.WorkStation;
import it.epicode.booking.enums.Cities;
import it.epicode.booking.enums.Type;
import it.epicode.booking.repositories.WorkStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

    public Optional<List<WorkStation>> getByTypeAndCity(Type type, Cities city){
        List<WorkStation> workStationFounded = workStation.findByTypeAndBuildingCityEqualsTo(type, city);
        return Optional.ofNullable(workStationFounded);
    }
}
