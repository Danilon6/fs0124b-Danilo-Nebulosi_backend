package it.epicode.menu;

import it.epicode.menu.enums.ToppingName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//e
@SpringBootApplication
@Slf4j
public class MenuPizzeriaApplication {

	public static void main(String[] args) {
		try (var ctx = new AnnotationConfigApplicationContext(MyAppConfig.class)) {
			var toppings = (Toppings)ctx.getBean(Toppings.class);
			var pdefault = (Pizza)ctx.getBean(Pizza.class);
			var pWithToppings = (Pizza)ctx.getBean(Pizza.class);
			var menu = (Menu)ctx.getBean(Menu.class);

			pWithToppings.addTopping(ToppingName.ONIONS, toppings);
			pWithToppings.addTopping(ToppingName.CHEESE, toppings);

			var menuPizzaList = menu.getAllPizza();

			menuPizzaList.add(pdefault);
			menuPizzaList.add(pWithToppings);

			menu.setAllPizza(menuPizzaList);
			System.out.println(menu);
		} catch(BeansException ex){
			log.error("Exception in main()", ex);
		}
	}

}
