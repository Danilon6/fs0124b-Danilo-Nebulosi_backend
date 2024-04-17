package it.epicode.Esercizio2;

import java.util.ArrayList;

public class Sim {
    private long numeroDiTelefono;
    private int creditoResiduo;
    private ArrayList<Chiamata> chiamateRecenti;

    public long getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(long numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }


    public void setCreditoResiduo(int creditoResiduo) {
        this.creditoResiduo = creditoResiduo;
    }

    public int getCreditoResiduo() {
        return creditoResiduo;
    }

    public void setChiamateRecenti(Chiamata chiamata) {
        chiamateRecenti.add(chiamata);
    }

    public void getChiamateRecenti() {
        for (int i = 0; i < chiamateRecenti.size(); i++) {
            long numero = chiamateRecenti.get(i).getNumeroDiTelefono();
            int durata = chiamateRecenti.get(i).getDurata();
            System.out.println("la chiamata al numero:" + numero + " è durata: " + durata + " minuti");
        }
    }



    public Sim(long a){
        this.numeroDiTelefono = a;
        this.creditoResiduo = 0;
        this.chiamateRecenti =  new ArrayList<Chiamata>();
    }

    public void stampaDati(){
        System.out.println("Il numero di telefono della tua sim è : "+  this.numeroDiTelefono);
        System.out.println("Il credito residuo della tua sim è : "+  this.creditoResiduo);

    }

}
