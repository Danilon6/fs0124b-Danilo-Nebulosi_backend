package it.epicode.designpatterns.composite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Order(2)
public class CompositeRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        var p1 = new PageItem(1, "content Page 1");
        var p2 = new PageItem(2, "content Page 2");
        var p3 = new PageItem(3, "content Page 3");
        var p4 = new PageItem(4, "content Page 4");
        var p5 = new PageItem(5, "content Page 5");
        var p6 = new PageItem(6, "content Page 6");
        var p7 = new PageItem(7, "content Page 7");


        var s1 = new SectionItem("Section 1", List.of(p1,p2,p3));
        var s2 = new SectionItem("Section 2", List.of(p4,p5));
        var s3 = new SectionItem("Section 3", List.of(p6,p7));


        var c1 = new SectionItem("Capitolo 1", List.of(s1,s2));
        var c2 = new SectionItem("Capitolo 2", List.of(s3));


        var book = new Book("Primo Libro", List.of(c1,c2), List.of("io", "io"), 20.5 );
        System.out.println(book.getNumeroPagine());
        book.stampa();
    }
}
