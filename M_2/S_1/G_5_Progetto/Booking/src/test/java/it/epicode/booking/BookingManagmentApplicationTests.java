package it.epicode.booking;

import it.epicode.booking.dao.BookingService;
import it.epicode.booking.dao.BuildingService;
import it.epicode.booking.dao.UserService;
import it.epicode.booking.dao.WorkStationService;
import it.epicode.booking.entities.Booking;
import it.epicode.booking.entities.WorkStation;
import it.epicode.booking.enums.Cities;
import it.epicode.booking.enums.Type;
import it.epicode.booking.exceptions.NoAvailabeSeatsException;
import it.epicode.booking.exceptions.UserHasAlreadyABookedWorkStationInThisDay;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookingManagmentApplicationTests {

	//ISTANZIO I SERVICES

	@Autowired
	private UserService user;

	@Autowired
	private BuildingService building;

	@Autowired
	private WorkStationService workStation;

	@Autowired
	private BookingService booking;

	@Autowired
	//@Qualifier("bookingUser3SecondTestThrowExceptionMaxSeats")
	@Qualifier("bookingUser3SecondTest")
	Booking bookingUser3SecondTest;

	@Autowired
	@Qualifier("bookingUser1ThirdTest")
	Booking bookingUser1ThirdTest;


	@Autowired
	@Qualifier("bookingUser1Second")
	Booking bookingUser1Second;

	@Test
	@DisplayName("Exception thrown because all seats are unavailable")
	void testExceptionMaxSeats(){
		assertThrows(NoAvailabeSeatsException.class, () -> booking.create(bookingUser3SecondTest));
	}

	@Test
	@DisplayName("Exception thrown because user has already a booked workstation in this day")
	void testExceptionHasAlreadyBooked(){
		assertThrows(UserHasAlreadyABookedWorkStationInThisDay.class, () -> booking.create(bookingUser1ThirdTest));
	}

	@Test
	@DisplayName("Test to see if isValid() works")
	void testIsValidMethod(){
		assertAll(
				() ->assertTrue(booking.isValid(bookingUser1Second, LocalDate.now())),
				() ->assertTrue(booking.isValid(bookingUser1Second, LocalDate.of(2024,5,20)))
		);
	}

	@ParameterizedTest
	@DisplayName("Test to see if workStation.getByTypeAndCity() works")
	@CsvSource({"PRIVATE, NAPLES, 1", "PRIVATE, ROME, 1", "MEETING_ROOM, MILAN, 0", "OPENSPACE, NAPLES, 2"})
	void TestGetByTypeAndCityMethod(String type, String city, int expectedSize){
		Type typeEnum = Type.valueOf(type);
		Cities cityEnum = Cities.valueOf(city);
		List<WorkStation> workstationFounded = workStation.getByTypeAndCity(typeEnum, cityEnum);

				assertEquals(expectedSize,workstationFounded.size());



	}

}
