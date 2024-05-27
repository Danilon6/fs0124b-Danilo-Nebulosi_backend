package it.epicode.DevicesManagment.businesslayer.services.dto;

import it.epicode.DevicesManagment.datalayer.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class DeviceDTO extends BaseDTO{
    String model;
    String brand;
    Long serialNumber;
    double screenSize;
    Status status;
}
