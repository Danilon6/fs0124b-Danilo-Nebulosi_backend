package it.epicode.esercizio2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Esercizio2 {


    private static final Logger log = LoggerFactory.getLogger(Esercizio2.class);

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Quanti km hai percorso");
        int km = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Quanti l hai consumato?");
        int l = scanner.nextInt();
        try {
            if (l == 0) throw new DenominatorEqualToZero();
            scanner.nextLine();

            int result = km/l;
            System.out.println(result);

        }catch (DenominatorEqualToZero ex){
            log.error("Il denominatore è uguale a 0");
        }



    }
}
