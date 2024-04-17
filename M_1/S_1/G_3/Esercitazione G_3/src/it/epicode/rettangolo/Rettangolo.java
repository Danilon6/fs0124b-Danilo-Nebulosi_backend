package it.epicode.rettangolo;

public class Rettangolo {
    private int altezza;
    private int lunghezza;

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(int lunghezza) {
        this.lunghezza = lunghezza;
    }

    public Rettangolo(int a, int b){
        this.altezza = a;
        this.lunghezza = b;
    }

    public int perimetro(){
        return (this.altezza + this.lunghezza) * 2;
    }

    public int area(){
        return this.altezza * this.lunghezza;
    }



}
