package it.epicode.booking.runner;

import it.epicode.booking.dao.*;
import it.epicode.booking.entities.Booking;
import it.epicode.booking.entities.Building;
import it.epicode.booking.entities.User;
import it.epicode.booking.entities.WorkStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    //ISTANZIO I SERVICES
    @Autowired
    private UserService user;

    @Autowired
    private BuildingService building;

    @Autowired
    private WorkStationService workStation;

    @Autowired
    private BookingService booking;


    //ISTANZIO 3 UTENTI
    @Autowired
    @Qualifier("user1")
    private User danilo;

    @Autowired
    @Qualifier("user2")
    private User mario;

    @Autowired
    @Qualifier("user3")
    private User anna;

    //ISTANZIO 3 EDIFICI
    @Autowired
    @Qualifier("BuildingInRome")
    private Building buildingInRome;

    @Autowired
    @Qualifier("BuildingInNaples")
    private Building buildingInNaples;

    @Autowired
    @Qualifier("BuildingInNaples2")
    private Building buildingInNaples2;

    //ISTANZIO 3 WORKSTATION
    @Autowired
    @Qualifier("workstationPrivateNaples")
    private WorkStation workstationPrivateNaples;

    @Autowired
    @Qualifier("workstationPrivateRome")
    private WorkStation workstationPrivateRome;

    @Autowired
    @Qualifier("workstationOpenSpaceNaples")
    private WorkStation workstationOpenSpaceNaples;

    //ISTANZIO 4 BOOKING
    @Autowired
    @Qualifier("bookingUser1First")
    private Booking bookingUser1First;

    @Autowired
    @Qualifier("bookingUser2First")
    private Booking bookingUser2First;

    @Autowired
    @Qualifier("bookingUser1Second")
    private Booking bookingUser1Second;

    @Autowired
    @Qualifier("bookingUser3First")
    private Booking bookingUser3First;

    @Autowired
    @Qualifier("bookingUser3SecondTest")
    private Booking bookingUser3SecondTest;




    @Override
    public void run(String... args) throws Exception {

        //SALVO GLI UTENTI SUL DB
        user.create(danilo);
        user.create(mario);
        user.create(anna);
        //SALVO GLI EDIFICI SUL DB
        building.create(buildingInRome);
        building.create(buildingInNaples);
        building.create(buildingInNaples2);
        //SALVO LE WORKSTATION SUL DB
        workStation.create(workstationPrivateRome);
        workStation.create(workstationPrivateNaples);
        workStation.create(workstationOpenSpaceNaples);

        //AGGIUNGO DELLE WORKSTATION A DEGLI EDIFICI
        // VEDERE SE NE ABBIAMO VERAMENTE BISOGNO E SE LA VUOI FARE QUI O
        // DIRETTAMENTE NEL BEAN COSI E PIU PULITO SICRUAMENTE
        List<WorkStation> workStationInRomeList = new ArrayList<>();
        workStationInRomeList.add(workstationPrivateRome);
        buildingInRome.setWorkStations(workStationInRomeList);
        building.update(buildingInRome);

        List<WorkStation> workstationInNaplesList = new ArrayList<>();
        workstationInNaplesList.add(workstationPrivateNaples);
        workstationInNaplesList.add(workstationOpenSpaceNaples);
        buildingInNaples.setWorkStations(workstationInNaplesList);



        //SALVO LE BOOKING SUL DB
        booking.create(bookingUser1First);
        booking.create(bookingUser2First);
        booking.create(bookingUser1Second);
        booking.create(bookingUser3First);


        //booking.create(bookingUser3SecondTest);

    }
}
