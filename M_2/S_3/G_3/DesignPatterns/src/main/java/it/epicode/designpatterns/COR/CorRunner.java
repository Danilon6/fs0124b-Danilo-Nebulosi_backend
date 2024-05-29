package it.epicode.designpatterns.COR;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class CorRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        var t = new Tenente();
        var ca = new Capitano();
        var m = new Maggiore();
        var co = new Colonnello();
        var g = new Generale();


        t.setSuperiore(ca);
        ca.setSuperiore(m);
        m.setSuperiore(co);
        co.setSuperiore(g);

        t.doCheck(2000);
    }
}
