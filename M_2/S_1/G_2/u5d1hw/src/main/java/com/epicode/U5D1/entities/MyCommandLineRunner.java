package com.epicode.U5D1.entities;

import com.epicode.U5D1.U5D1Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D1Application.class);

        Menu m = (Menu) ctx.getBean("menu");
        Order o1 = (Order) ctx.getBean("Order1");
        Table t1 = (Table) ctx.getBean("Table1");
        m.printMenu();
        log.info(o1.toString());
        log.info(t1.toString());
        ctx.close();
    }
}
