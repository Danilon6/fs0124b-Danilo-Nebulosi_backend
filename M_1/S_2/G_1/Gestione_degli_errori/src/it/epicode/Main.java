package it.epicode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws indexOutOfBoundException {

        Scanner scanner = new Scanner(System.in);

        Random random = new Random();

        int[] array = random.ints(5, 1, 11).toArray();

        logger.info("Array di numeri casuali: " + Arrays.toString(array));


        while(true) {
            logger.info("Cambia un elemento dell'array. Scegli in quale posizione vuoi scrivere il nuovo numero");
            int choice = scanner.nextInt();

            if (choice == 0){
                break;
            }

            try {
                if (choice > 5 || choice < 0) throw new indexOutOfBoundException("Posizione non valida");

                logger.info("Quale numero vuoi inserire in posizione " + choice);
                int newValue = scanner.nextInt();
                scanner.nextLine();

                array[choice - 1] = newValue;
                logger.trace("Array di numeri casuali: " + Arrays.toString(array));
            } catch (indexOutOfBoundException ex){
                logger.error(ex.getMessage());
            }

        }




    }
}