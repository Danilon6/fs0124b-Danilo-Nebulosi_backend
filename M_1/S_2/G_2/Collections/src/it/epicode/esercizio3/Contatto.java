package it.epicode.esercizio3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Contatto {

    private static void addContact(HashMap map, String nome, Integer num){
        map.put(nome, num);
    }

    private static void removeContact(HashMap map, String nome){
        map.remove(nome);
    }

    private static void findByNumber(HashMap map, Integer number){
        for (Integer number : map.keySet()) {

        }
    }



    public static void main(String[] args) {

        var rubrica = new HashMap<>();
        addContact(rubrica, "Danilo", 333385213);
    }
}
