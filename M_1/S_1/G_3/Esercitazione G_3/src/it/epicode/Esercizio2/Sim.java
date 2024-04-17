package it.epicode.Esercizio2;

import java.util.ArrayList;

public class Sim {
    public long getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(long numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }

    private long numeroDiTelefono;

    public int getCreditoResiduo() {
        return creditoResiduo;
    }

    public void setCreditoResiduo(int creditoResiduo) {
        this.creditoResiduo = creditoResiduo;
    }

    private int creditoResiduo;

    public ArrayList<Chiamata> getChiamateRecenti() {
        return chiamateRecenti;
    }

    public void setChiamateRecenti(Chiamata chiamata) {
        chiamateRecenti.add(chiamata);
    }

    private ArrayList<Chiamata> chiamateRecenti;

    public Sim(long a){
        this.numeroDiTelefono = a;
        this.creditoResiduo = 0;
        this.chiamateRecenti =  new ArrayList<Chiamata>();
    }

    public void stampaDati(){
        System.out.println("Il numero di telefono della tua sim è : "+  this.numeroDiTelefono);
        System.out.println("Il credito residuo della tua sim è : "+  this.creditoResiduo);
        for (int i = 0; i < chiamateRecenti.size(); i++) {
            long numero = chiamateRecenti.get(i).getNumeroDiTelefono();
            int durata = chiamateRecenti.get(i).getDurata();
            System.out.println("la chiamata al numero:" + numero + " è durata: " + durata + " minuti");
        }
    }

}
