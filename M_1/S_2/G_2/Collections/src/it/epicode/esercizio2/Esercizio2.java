package it.epicode.esercizio2;

import java.util.ArrayList;
import java.util.Random;

public class Esercizio2 {

    private static ArrayList<Integer> genArrList(int n){
        Random random = new Random();

        var lista = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            lista.add(random.nextInt(101));
        }
        return lista;
    }

    private static ArrayList<Integer> invertedArrayList(ArrayList<Integer> lista){
        var lista1 = new ArrayList<Integer>();
        lista1.addAll(lista);
        lista1.addAll(lista.reversed());
        return lista1;
    }

    private static void printList(ArrayList<Integer> lista, boolean b){
        int k = b ? 1 : 0;

            for (int i = k; i < lista.size(); i = i+2) {
                System.out.println(lista.get(i));
            }

    }


    public static void main(String[] args){

        var lista = genArrList(10);
        System.out.println(lista);
        System.out.println(invertedArrayList(lista));
        System.out.println(lista);
        printList(lista, true);
        printList(lista, false);
    }
}
