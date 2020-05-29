package com.company;

import java.util.Comparator;

public class Pracownik implements Comparable<Pracownik>{
    private String imie;
    private String nazwisko;
    private char plec;
    private int nr_dzialu;
    private float placa;
    private int wiek;
    private int dzieci;
    private boolean stan_cywilny;


    public Pracownik(String imie, String nazwisko, char plec, int nr_dzialu, float placa, int wiek, int dzieci, boolean stan_cywilny) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.nr_dzialu = nr_dzialu;
        this.placa = placa;
        this.wiek = wiek;
        this.dzieci = dzieci;
        this.stan_cywilny = stan_cywilny;
    }
    public void wyswietl_pracownika(){
        System.out.println("Imie: "+ imie);
        System.out.println("Nazwisko: "+ nazwisko);
        System.out.println("Płeć: "+plec);
        System.out.println("Numer działu: "+nr_dzialu);
        System.out.println("Płaca: "+placa);
        System.out.println("Wiek: "+wiek);
        System.out.println("Liczba dzieci: "+dzieci);
        System.out.println("Stan cywilny: "+stan_cywilny);
    }

    public void wyswietlanie_okrojone(){
        System.out.println("Imie: "+ imie);
        System.out.println("Nazwisko: "+ nazwisko);
        System.out.println("Płaca: "+placa);
    }

    public String zapis_plik(){
        String obiekt = imie +" "+nazwisko+" "+plec+" "+nr_dzialu+" "+placa+" "+wiek+" "+dzieci+" "+stan_cywilny+"\n";
        return obiekt;
    }

    public void wyswietlanie_specjalne(){
        System.out.println("Imie: "+ imie.toUpperCase());
        System.out.println("Nazwisko: "+ nazwisko.toUpperCase());
    }

    public boolean sprawdz_pensje(float value){
        if(placa >= value)
            return true;
        else if(placa < value || placa == value)
            return false;
        return false;
    }

    public float oblicz_podwyzke(float procent){
        if(dzieci > 0)
            procent = procent + dzieci*2.0f;

        if(stan_cywilny == true)
            procent += 3.0;

        procent/=100;
        placa = placa + placa*procent;
        return placa;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public char getPlec() {
        return plec;
    }

    public int getNr_dzialu() {
        return nr_dzialu;
    }

    public float getPlaca() {
        return placa;
    }

    public int getWiek() {
        return wiek;
    }

    public int getDzieci() {
        return dzieci;
    }

    public boolean isStan_cywilny() {
        return stan_cywilny;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setNr_dzialu(int nr_dzialu) {
        this.nr_dzialu = nr_dzialu;
    }

    public void setPlaca(float placa) {
        this.placa = placa;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public void setDzieci(int dzieci) {
        this.dzieci = dzieci;
    }

    public void setStan_cywilny(boolean stan_cywilny) {
        this.stan_cywilny = stan_cywilny;
    }

    @Override
    public int compareTo(Pracownik pracownik) {
        return this.nazwisko.compareTo(pracownik.nazwisko);
    }
    static class PracownikPensja implements Comparator<Pracownik>{
        public int compare (Pracownik a, Pracownik b){
            return (int)(a.placa - b.placa);
        }
    }
}
