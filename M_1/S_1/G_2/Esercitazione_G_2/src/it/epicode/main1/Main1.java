package it.epicode.main1;

public class Main1 {
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
    public static void main(String[] args) {
        System.out.println(printNumber(3));
    }
}
