import it.epicode.Esercizio3_e_commerce.Articolo;
import it.epicode.Esercizio3_e_commerce.Carrello;
import it.epicode.Esercizio3_e_commerce.Cliente;
import it.epicode.Esercizio2.Chiamata;
import it.epicode.Esercizio2.Sim;
import it.epicode.rettangolo.Rettangolo;

public class Main {

    public static void stampaRettangolo(Rettangolo rettangolo){
        System.out.println("Il perimetro del rettangolo è: " + rettangolo.perimetro());
        System.out.println("L'area del rettangolo è: " + rettangolo.area());
    }

    public static void stampaDueRettangoli(Rettangolo a, Rettangolo b){
        stampaRettangolo(a);
        stampaRettangolo(b);
        System.out.println("La somma dei due perimetri è: " + (a.perimetro() + b.perimetro()));
        System.out.println("La somma delle due aree è: " + (a.area() + b.area()));

    }
    public static void main(String[] args) {


        //ESERCIZIO 1
        Rettangolo rettangolo1 = new Rettangolo(10,20);
        Rettangolo rettangolo2 = new Rettangolo(30,25);
        stampaRettangolo(rettangolo1);
        stampaDueRettangoli(rettangolo1, rettangolo2);


        //ESERCIZIO 2
        Sim sim1 = new Sim(3333852173L);
        Chiamata c1 = new Chiamata(333948390L, 10);
        Chiamata c2 = new Chiamata(339962590L, 30);
        sim1.setChiamateRecenti(c1);
        sim1.setChiamateRecenti(c2);
        sim1.getChiamateRecenti();
        sim1.stampaDati();


        //ESERCIZIO 3
        Cliente cliente1 = new Cliente(1, "Danilo", "Nebulosi", "danilonebulosi@gmail.com", "16/04/2024");
        Articolo mouse = new Articolo(1, "mouse", 30, 10);
        Articolo tastiera = new Articolo(2, "tastiera", 10, 5);
        Carrello carrello = new Carrello(cliente1);
        carrello.setElencoArticoli(mouse);
        carrello.setElencoArticoli(tastiera);
        System.out.println(carrello.getTotaleCostoArticoli());
        carrello.getElencoArticoli();
    }
}
