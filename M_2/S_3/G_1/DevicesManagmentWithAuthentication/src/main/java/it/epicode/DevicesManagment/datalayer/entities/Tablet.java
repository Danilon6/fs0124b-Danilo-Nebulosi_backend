package it.epicode.DevicesManagment.datalayer.entities;


import it.epicode.DevicesManagment.datalayer.entities.enums.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("tablet")
public class Tablet extends Device{

    @Builder(setterPrefix = "with")
    public Tablet(long id, String model, String brand, Long serialNumber, double screenSize, Status status, Employee employee) {
        super(id, model, brand, serialNumber, screenSize, status, employee);
    }

    public static class TabletBuilder extends DeviceBuilder{
        TabletBuilder() {
            super();
        }
    }
}
