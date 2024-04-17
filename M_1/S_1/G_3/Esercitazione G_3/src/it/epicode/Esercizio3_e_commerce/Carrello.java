package it.epicode.Esercizio3_e_commerce;

import java.util.ArrayList;

public class Carrello {
    private Cliente cliente;
    private ArrayList<Articolo> elencoArticoli;
    private long totaleCostoArticoli;

    public long getTotaleCostoArticoli() {
        return totaleCostoArticoli;
    }

    public void setTotaleCostoArticoli() {
        for (int i = 0; i < this.elencoArticoli.size(); i++) {
            this.totaleCostoArticoli += elencoArticoli.get(i).getPrezzo();
        }
    }

    public void getElencoArticoli() {

        for (int i = 0; i < this.elencoArticoli.size(); i++) {
            System.out.println(elencoArticoli.get(i).getDescrizioneArticolo());
        }
    }

    public void setElencoArticoli(Articolo articolo) {
        this.elencoArticoli.add(articolo);

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carrello(Cliente a){
        this.cliente = a;
        this.elencoArticoli = new ArrayList<Articolo>();
        this.totaleCostoArticoli = 0;
    }


}
