package it.epicode.booking.repositories;

import it.epicode.booking.entities.Building;
import it.epicode.booking.entities.WorkStation;
import it.epicode.booking.enums.Cities;
import it.epicode.booking.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkStationRepository extends JpaRepository<WorkStation, Long> {

    List<WorkStation> findByTypeAndBuildingCityEqualsTo(Type type, Cities city);

}
