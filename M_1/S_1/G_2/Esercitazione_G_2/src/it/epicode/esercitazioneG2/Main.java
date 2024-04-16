package it.epicode.esercitazioneG2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    //ESERCIZIO 1
    public static boolean stringaPariDispari(String a){
        return a.length() % 2 == 0;
    }

    //ESERCIZIO 1.1
    public static boolean annoBisestile(int anno){
        boolean primaCondizione = anno % 4 == 0;
        boolean secondaCondizione = anno % 100 == 0 && anno % 400 == 0;
        return primaCondizione || secondaCondizione;
    }

    //ESERCIZIO 2
    public static String printNumber(int number){
        if (number >= 0 && number <= 3){

            switch (number) {
                case 0:
                    return "zero";
                case 1:
                    return "uno";
                case 2:
                    return "due";
                case 3:
                    return "tre";
            }
        }
        return "errore";

    }

    //ESERCIZIO 3
    public static void convertString(String prompt) {
        String inputString;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(prompt + ":");
            inputString = scanner.nextLine();
            System.out.println(Arrays.toString(inputString.toCharArray()));

        } while (!inputString.equals(":q"));
        scanner.close();
        System.out.println("Exit");
    }

    //ESERCIZIO 4
    public static void countdown (){
        int number = inputInteger("Inserisci un numero");

        for (int i = number; i >=0 ; i--) {
            System.out.println(i);
        }
    }
    public static Integer inputInteger(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt + ": ");
        return sc.nextInt();
    }


    public static String input(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt + ": ");
        return sc.nextLine();
    }
    public static void main(String[] args) {
        System.out.println(stringaPariDispari("ciao"));
        System.out.println(annoBisestile(2024));
        convertString("Inserisci una stringa");
        System.out.println(printNumber(4));
        countdown();


    }
}