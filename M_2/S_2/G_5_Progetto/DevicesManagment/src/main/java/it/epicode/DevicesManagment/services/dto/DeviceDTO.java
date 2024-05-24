package it.epicode.DevicesManagment.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class DeviceDTO {
    String model;
    String brand;
    Long serialNumber;
    double screenSize;
}
