package it.epicode.designpatterns.adapters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Slf4j
@Order(1)
public class AdapterRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        var u = new UserData();
        var i = new Info("Danilo", "Nebulosi", LocalDate.of(2005,1,23));
        var a = new Adaptee(i);
        u.getData(a);
        log.info(String.valueOf(u));

    }
}
