package it.epicode.booking.config;

import it.epicode.booking.entities.Booking;
import it.epicode.booking.entities.Building;
import it.epicode.booking.entities.User;
import it.epicode.booking.entities.WorkStation;
import it.epicode.booking.enums.Cities;
import it.epicode.booking.enums.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class BeansConfiguration {

    @Bean(name = "user1")
    public User user1(){
        return User.builder()
                        .withName("Danilo")
                        .withLast_name("Nebulosi")
                        .withUsername("Danilo6")
                        .withEmail("danilonebulosi@gmail.com")
                        .build();
    }

    @Bean(name = "user2")
    public User user2(){
        return User.builder()
                        .withName("Mario")
                        .withLast_name("Rossi")
                        .withUsername("Mario5")
                        .withEmail("mariorossi@gmail.com")
                .build();
    }

    @Bean(name = "user3")
    public User user3(){
        return User.builder()
                .withName("Anna")
                .withLast_name("Neri")
                .withUsername("Anna4")
                .withEmail("annaneri@gmail.com")
                .build();
    }

    @Bean(name = "BuildingInRome")
    public Building buildingInRome(){
        return Building.builder()
                .withName("Building In Rome 1")
                .withAddress("Street Tiburtina 10")
                .withCity(Cities.ROME)
                .build();
    }

    @Bean(name = "BuildingInNaples")
    public Building buildingInNaples(){
        return Building.builder()
                .withName("Building In Naples 1")
                .withAddress("Street Toledo 110")
                .withCity(Cities.NAPLES)
                .build();
    }

    @Bean(name = "BuildingInNaples2")
    public Building buildingInNaples2(){
        return Building.builder()
                .withName("Building In Naples 2")
                .withAddress("Street Partenope 50")
                .withCity(Cities.NAPLES)
                .build();
    }

    @Bean(name = "workstationPrivateNaples")
    public WorkStation workstationPrivateNaples(){
        return WorkStation.builder()
                .withDescription("Workstation Private in Naples ")
                .withType(Type.PRIVATE)
                .withMax_seats(1)
                .withBuilding(buildingInNaples())
                .build();
    }

    @Bean(name = "workstationPrivateRome")
    public WorkStation workstationPrivateRome(){
        return WorkStation.builder()
                .withDescription("Workstation Private in Rome ")
                .withType(Type.PRIVATE)
                .withMax_seats(1)
                .withBuilding(buildingInRome())
                .build();
    }


    @Bean(name = "workstationOpenSpaceNaples")
    public WorkStation workstationOpenSpaceNaples(){
        return WorkStation.builder()
                .withDescription("Workstation OpenSpace in Naples ")
                .withType(Type.OPENSPACE)
                .withMax_seats(10)
                .withBuilding(buildingInNaples2())
                .build();
    }


    @Bean(name = "bookingUser1First")
    public Booking bookingUser1First(){
        return Booking.builder()
                .withDate(LocalDate.now())
                .withUser(user1())
                .withWorkStation(workstationPrivateNaples())
                .build();
    }

    @Bean(name = "bookingUser2First")
    public Booking bookingUser2First(){
        return Booking.builder()
                .withDate(LocalDate.now())
                .withUser(user2())
                .withWorkStation(workstationPrivateRome())
                .build();
    }

    @Bean(name = "bookingUser1Second")
    public Booking bookingUser1Second(){
        return Booking.builder()
                .withDate(LocalDate.of(2024,5,20))
                .withUser(user2())
                .withWorkStation(workstationOpenSpaceNaples())
                .build();
    }

    @Bean(name = "bookingUser3First")
    public Booking bookingUser3First(){
        return Booking.builder()
                .withDate(LocalDate.of(2024,5,20))
                .withUser(user3())
                .withWorkStation(workstationOpenSpaceNaples())
                .build();
    }
}
