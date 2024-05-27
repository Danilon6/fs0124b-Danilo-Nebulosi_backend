package it.epicode.DevicesManagment.businesslayer.services.interfaces;

import it.epicode.DevicesManagment.businesslayer.services.dto.BaseDTO;
import it.epicode.DevicesManagment.datalayer.entities.BaseEntity;

public interface Mapper<S extends BaseDTO,D extends BaseEntity> {

    S convertTo(D destination);
    D convertFrom(S source);
}
