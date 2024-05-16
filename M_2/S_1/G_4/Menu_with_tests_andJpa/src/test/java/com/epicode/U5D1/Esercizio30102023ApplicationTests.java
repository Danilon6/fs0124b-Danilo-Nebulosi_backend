package com.epicode.U5D1;

import com.epicode.U5D1.entities.Item;
import com.epicode.U5D1.entities.Order;
import com.epicode.U5D1.entities.Table;
import com.epicode.U5D1.enums.OrderStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Esercizio30102023ApplicationTests {


	@Autowired
	@Qualifier("table1")
	Table table1;

	@Autowired
	@Qualifier("water")
	Item water;

	@Autowired
	@Qualifier("pizza_margherita")
	Item pizza_margherita;

	@Value("${costPerSeat.value}")
	int seatCost;

	private Order order;

	@BeforeEach
	void initializeOrderSample() {
		List<Item> itemList = new ArrayList<>();
		itemList.add(water);
		itemList.add(pizza_margherita);
		order = Order.builder()
				.withOrderNumber("1")
				.withOrderStatus(OrderStatus.SERVED)
				.withAcquisitonDate(LocalDate.now())
				.withTable(table1)
				.withSeatCost(seatCost)
				.withNumberOfSeats(3)
				.withItemList(itemList)
				.build();
	}


	@Test
	@DisplayName("Test per vedere se i posti a sedere di un ordine non " +
			"sueperano quelli massimi previsti dal tavolo")
	void SeatNumberTest(){
		assertTrue(order.getNumberOfSeats()<= table1.getMaxSeats());
	}

	@Test
	@DisplayName("Test per verificare che il costo non sia null o inferiore a 1")
	void SeatCostTest(){
		assertTrue(order.getSeatCost()>= 1);
	}

	@Test
	@DisplayName("Test per verificare che il metodo getTotalAmount() funzioni correttamente")
	void GetTotalAmountTest(){
		water.setPrice(1);
		pizza_margherita.setPrice(5);
		assertEquals(9, order.getTotalAmount());
	}

	@ParameterizedTest
	@DisplayName("Test parametrizzato per verificare che il metodo getTotalAmount() funzioni correttamente")
	@CsvSource({"1,5,2,4,14", "2,4,1,2,9"})
	void GetTotalAmountParameterizedTest(int waterPrice, int pizzaPrice, int seatCost, int numberOfSeats, int expected){
		water.setPrice(waterPrice);
		pizza_margherita.setPrice(pizzaPrice);
		order.setSeatCost(seatCost);
		order.setNumberOfSeats(numberOfSeats);
		assertEquals(expected, order.getTotalAmount());
	}

}
