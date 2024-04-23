package it.epicode.esercizio1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Esercizio1 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quante parole vuoi inserire?");
        int choice = scanner.nextInt();
        scanner.nextLine();

        var s = new HashSet<String>();
        var arrayListDuplicates = new ArrayList<String>();
        for (int i = 0; i < choice; i++) {
            System.out.println("Inserisci la parola n" + (i+1));
            String word = scanner.nextLine();
            if (s.add(word)) s.add(word);
            else arrayListDuplicates.add(word);
        }

        System.out.println("Elenco delle parole");
        System.out.println(s);

        System.out.println("Parole duplicate");
        System.out.println(arrayListDuplicates);

        System.out.println("Il numero di parole contneute nell'hashSet sono: ");
        System.out.print(s.size());
    }
}
