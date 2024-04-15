import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //ESERCIZIO 1.1
    public static int moltiplicazione(int a, int b){
        return a*b;
    }

    //ESERCIZIO 1.2
    public static String concatena(String a, int b){
        return a + b;
    }

    //ESERCIZIO 1.3
    public static String[] arrayModificato(String[] array, String nuovaStringa) {
        array[2] = nuovaStringa;
        return array;
    }

    //ESERCIZIO 2
    public static String concatenamentoDiTreStringhe(){
        StringBuilder concatenato = new StringBuilder();
        StringBuilder invertito = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String stringaInInput = input("Inserisci una stringa");
            concatenato.append(stringaInInput).append(" ");
            invertito.insert(0,stringaInInput).insert(0," ");
        }

        String concatenamentoOrdinato = concatenato.toString();
        String concatenamentoInvertito = invertito.toString();
        return concatenamentoOrdinato + " " + concatenamentoInvertito;
    }


    //ESERCIZIO 3.1
    public static double perimetroRettangolo(){
        double l1 = inputDouble("Inserisci l'altezza del rettangolo");
        double l2 = inputDouble("Inserisci la lunghezza della base del rettangolo");
        return (l1*2) + (l2 *2);
    }

    //ESERCIZIO 3.2
    public static int pariDispari(){
        int a = inputInteger("Inserisic un numero per verifcare se è pari o dispari");
        if (a % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    //ESERCIZIO 3.3
    public static double perimetroTriangolo(){
        double l1 = inputDouble("Inserisci il lato di un trinagolo per calcolarne l'area");
        double l2 = inputDouble("Inserisci il lato di un trinagolo per calcolarne l'area");
        double l3 = inputDouble("Inserisci il lato di un trinagolo per calcolarne l'area");

        double semiP = (l1+l2+l3)/2;
        double area = Math.sqrt((semiP) * (semiP -l1) * (semiP -l2) * (semiP -l3));
        return area;
    }






    //METODI DI INPUT
    public static String input(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt + ": ");
        return sc.nextLine();
    }

    public static Integer inputInteger(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt + ": ");
        return sc.nextInt();
    }

    public static Double inputDouble(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt + ": ");
        return sc.nextDouble();
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");


        //ESERCIZIO 1.1
        int n1 = 10;
        int n2 = 15;
        int risultato = moltiplicazione(n1, n2);
        System.out.println(risultato);

        //ESERCIZIO 1.2
        String stringa = "Numero:";
        int x = 1;
        String concatenamento = concatena(stringa, x);
        System.out.println(concatenamento);

        // ESERCIZIO 1.3
        String[] arrayDiStringhe = { "Primo", "Secondo", "Terzo", "Quarto", "Quinto"};
        String nuovaStringa = "Stringa Aggiunta";
        String[] nuovoArray = arrayModificato(arrayDiStringhe, nuovaStringa);
        System.out.println(Arrays.toString(nuovoArray));
        // ESERCIZIO 2
        System.out.println(concatenamentoDiTreStringhe());

        // ESERCIZIO 3.1
        System.out.println(perimetroRettangolo());

        // ESERCIZIO 3.2
        System.out.println(pariDispari());

        // ESERCIZIO 3.2
        System.out.println(perimetroTriangolo());

    }


}