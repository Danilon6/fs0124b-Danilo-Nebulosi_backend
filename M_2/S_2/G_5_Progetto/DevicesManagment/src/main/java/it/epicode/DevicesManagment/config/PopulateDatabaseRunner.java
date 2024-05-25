package it.epicode.DevicesManagment.config;

import com.github.javafaker.Faker;
import it.epicode.DevicesManagment.entities.enums.Status;
import it.epicode.DevicesManagment.services.dto.DeviceDTO;
import it.epicode.DevicesManagment.services.interfaces.EmployeeService;
import it.epicode.DevicesManagment.services.dto.EmployeeDTO;
import it.epicode.DevicesManagment.services.interfaces.LaptopService;
import it.epicode.DevicesManagment.services.interfaces.SmartphoneService;
import it.epicode.DevicesManagment.services.interfaces.TabletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Slf4j
public class PopulateDatabaseRunner implements CommandLineRunner {

    @Autowired
    EmployeeService employee;

    @Autowired
    SmartphoneService smartphone;

    @Autowired
    TabletService tablet;

    @Autowired
    LaptopService laptop;

    private static final Faker faker = Faker.instance();

    @Override
    public void run(String... args) throws Exception {
            log.info("Popoulating database");
        IntStream.range(0,10)
                .mapToObj(
                        n-> EmployeeDTO.builder()
                                .withFirstName(faker.name().firstName())
                                .withLastName(faker.name().lastName())
                                .withUsername(faker.name().username())
                                .withEmail(faker.internet().emailAddress())
                                .build()
                ).forEach(employee::save);

        IntStream.range(0,10)
                        .mapToObj(
                                n-> DeviceDTO.builder()
                                        .withSerialNumber(faker.number().numberBetween(1000000000L, 9999999999L))
                                        .withBrand(faker.company().name())
                                        .withModel(faker.lorem().word())
                                        .withScreenSize(faker.number().randomDouble(2, 6, 16))
                                        .withStatus(Status.AVAILABLE)
                                        .build()
                        ).forEach(smartphone::save);

        IntStream.range(0,10)
                .mapToObj(
                        n-> DeviceDTO.builder()
                                .withSerialNumber(faker.number().numberBetween(1000000000L, 9999999999L))
                                .withBrand(faker.company().name())
                                .withModel(faker.lorem().word())
                                .withScreenSize(faker.number().randomDouble(2, 6, 16))
                                .withStatus(Status.AVAILABLE)
                                .build()
                ).forEach(tablet::save);

        IntStream.range(0,10)
                .mapToObj(
                        n-> DeviceDTO.builder()
                                .withSerialNumber(faker.number().numberBetween(1000000000L, 9999999999L))
                                .withBrand(faker.company().name())
                                .withModel(faker.lorem().word())
                                .withScreenSize(faker.number().randomDouble(2, 6, 16))
                                .withStatus(Status.AVAILABLE)
                                .build()
                ).forEach(laptop::save);

        log.info("Database populated");
    }
}
