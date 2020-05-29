package com.company;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.round;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList <Pracownik> lista_pracownikow = new ArrayList<Pracownik>();
        lista_pracownikow.add(new Pracownik("Marek","Kowalski",'M',1,2500.0f,35,2,true));
        lista_pracownikow.add(new Pracownik("Andrzej","Nowak",'M',2,2200.0f,28,1,true));
        lista_pracownikow.add(new Pracownik("Anna","Skalska",'K',3,2350.0f,30,3,true));
        lista_pracownikow.add(new Pracownik("Katarzyna","Adamecka",'K',1,2600.0f,24,0,false));
        lista_pracownikow.add(new Pracownik("Dominika","Domańska",'M',2,2100.0f,20,0,false));
        lista_pracownikow.add(new Pracownik("Kamil","Gwóźdź",'M',4,3000.0f,32,1,true));

        System.out.println("_____ SYSTEM FIRMOWY _____");
        System.out.println("1.Wypisz liste wszystkich pracowników");
        System.out.println("2.Dodaj nowego pracownika");
        System.out.println("3.Eksportuj liste pracowników do pliku .txt");
        System.out.println("4.Usuń pracownika");
        System.out.println("5.Edycja danych");
        System.out.println("6.Dodatkowe funkcje");
        System.out.println("Wybierz opcje: ");
        Scanner scan = new Scanner(System.in);
        int option;
        option = scan.nextInt();

        switch(option) {
            case 1:                                             // wypisywanie listy wszystkich pracowników
                for(int i=0; i<lista_pracownikow.size();i++)
                {
                    System.out.println((i+1)+".");
                    lista_pracownikow.get(i).wyswietlanie_okrojone();
                    System.out.println("");
                }
                break;
            case 2:                                             // dodawanie nowego pracownika
                String imie, nazwisko, plec;
                int nr_dzialu, wiek, dzieci,ilosc;
                float placa;
                boolean stan_cywilny;
                System.out.println("Ilu pracowników chcesz dodać ?");
                ilosc = scan.nextInt();
                for(int i=0; i<ilosc;i++) {
                    System.out.println("Podaj imie: ");
                    scan.nextLine();
                    imie = scan.nextLine();
                    System.out.println("Podaj nazwisko: ");
                    nazwisko = scan.nextLine();
                    System.out.println("Podaj płeć (K/M): ");
                    plec = scan.nextLine();
                    System.out.println("Podaj numer działu: ");
                    nr_dzialu = scan.nextInt();
                    System.out.println("Podaj płacę: ");
                    placa = scan.nextFloat();
                    System.out.println("Podaj wiek: ");
                    wiek = scan.nextInt();
                    System.out.println("Podaj ilosc dzieci: ");
                    dzieci = scan.nextInt();
                    System.out.println("Podaj stan cywilny (true/false): ");
                    stan_cywilny = scan.nextBoolean();
                    lista_pracownikow.add(new Pracownik(imie, nazwisko, plec.charAt(0), nr_dzialu, placa, wiek, dzieci, stan_cywilny));
                }
                break;
            case 3:                                              // eksport listy pracowników do pliku
                scan.nextLine();
                String name;
                System.out.println("Podaj nazwę pliku pliku: ");
                name = scan.nextLine();
                PrintWriter zapis = new PrintWriter("F:\\Projekty\\KursJava\\Firma\\"+name+".txt");
                for(int i=0; i<lista_pracownikow.size();i++)
                {
                    zapis.write(lista_pracownikow.get(i).zapis_plik());
                }
                zapis.close();
                break;
            case 4:                                                 // usuwanie pracownika
                int value;
                for(int i=0; i<lista_pracownikow.size();i++)
                {
                    System.out.println((i+1)+".");
                    lista_pracownikow.get(i).wyswietlanie_okrojone();
                    System.out.println("");
                }
                System.out.println("Którego pracownika chcesz usunąć : ");
                value = scan.nextInt();
                lista_pracownikow.remove(value);
                System.out.println("Pracownik usunięty");
                System.out.println("");
                for(int i=0; i<lista_pracownikow.size();i++)
                {
                    System.out.println((i+1)+".");
                    lista_pracownikow.get(i).wyswietlanie_okrojone();
                    System.out.println("");
                }
                break;
            case 5:                                                 // edycja danych danego pracownika
                int wartosc,zmienna;
                for(int i=0; i<lista_pracownikow.size();i++)
                {
                    System.out.println((i+1)+".");
                    lista_pracownikow.get(i).wyswietlanie_okrojone();
                    System.out.println("");
                }
                System.out.println("Którego pracownika chcesz edytować :");
                wartosc = scan.nextInt();
                lista_pracownikow.get(wartosc-1).wyswietlanie_specjalne();
                System.out.println("Które pole chcesze zmienić ?");
                if(lista_pracownikow.get(wartosc).getPlec() == 'M')
                {
                    System.out.println("1.Numer działu");
                    System.out.println("2.Płaca");
                    System.out.println("3.Wiek");
                    System.out.println("4.Liczba dzieci");
                    System.out.println("5.Stan cywilny");
                    int v = scan.nextInt();
                    switch(v){
                        case 1:
                            System.out.println("Podaj na jaki numer działu chcesz zmienić: ");
                            int dzial = scan.nextInt();
                            lista_pracownikow.get(wartosc-1).setNr_dzialu(dzial);
                            break;
                        case 2:
                            System.out.println("Podaj wartosc płacy na jaką chcesz zmienić: ");
                            float plac_a = scan.nextFloat();
                            lista_pracownikow.get(wartosc-1).setPlaca(plac_a);
                            break;
                        case 3:
                            System.out.println("Podaj wiek na jaki chcesz zmienić : ");
                            int wie_k = scan.nextInt();
                            lista_pracownikow.get(wartosc-1).setWiek(wie_k);
                            break;
                        case 4:
                            System.out.println("Podaj liczbe dzieci na jaką chcesz zmienić : ");
                            int liczba_d = scan.nextInt();
                            lista_pracownikow.get(wartosc-1).setDzieci(liczba_d);
                            break;
                        case 5:
                            System.out.println("Podaj wartość stanu cywilnego na jaki chcesz zmienić (true/false) : ");
                            boolean stan_c = scan.nextBoolean();
                            lista_pracownikow.get(wartosc-1).setStan_cywilny(stan_c);
                            break;
                        default:
                            System.out.println("Nie ma takiej wartości !!!");
                            break;
                    }
                }
                else
                {
                    System.out.println("1.Nazwisko");
                    System.out.println("2.Numer działu");
                    System.out.println("3.Płaca");
                    System.out.println("4.Wiek");
                    System.out.println("5.Liczba dzieci");
                    System.out.println("6.Stan cywilny");
                    int v = scan.nextInt();
                    switch(v){
                        case 1:
                            System.out.println("Podaj nazwisko na jakie chcesz zmienić: ");
                            String nazw = scan.nextLine();
                            lista_pracownikow.get(wartosc-1).setNazwisko(nazw);
                            break;
                        case 2:
                            System.out.println("Podaj na jaki numer działu chcesz zmienić: ");
                            int dzial = scan.nextInt();
                            lista_pracownikow.get(wartosc-1).setNr_dzialu(dzial);
                            break;
                        case 3:
                            System.out.println("Podaj wartosc płacy na jaką chcesz zmienić: ");
                            float plac_a = scan.nextFloat();
                            lista_pracownikow.get(wartosc-1).setPlaca(plac_a);
                            break;
                        case 4:
                            System.out.println("Podaj wiek na jaki chcesz zmienić : ");
                            int wie_k = scan.nextInt();
                            lista_pracownikow.get(wartosc-1).setWiek(wie_k);
                            break;
                        case 5:
                            System.out.println("Podaj liczbe dzieci na jaką chcesz zmienić : ");
                            int liczba_d = scan.nextInt();
                            lista_pracownikow.get(wartosc-1).setDzieci(liczba_d);
                            break;
                        case 6:
                            System.out.println("Podaj wartość stanu cywilnego na jaki chcesz zmienić (true/false) : ");
                            boolean stan_c = scan.nextBoolean();
                            lista_pracownikow.get(wartosc-1).setStan_cywilny(stan_c);
                            break;
                        default:
                            System.out.println("Nie ma takiej wartości !!!");
                            break;
                    }
                }
                break;
            case 6:                                                                                 //dodatkowe funkcje
                System.out.println("1.Obliczanie liczby pracowników z daną pensją lub wiekszą");
                System.out.println("2.Obliczanie średniej płacy w dziale");
                System.out.println("3.Wyświetlanie największych pensji ");
                System.out.println("4.Wyświetlanie wszystkich działów");
                System.out.println("5.Wyświetlanie stosunku średniej płacy kobiet do mężczyzn");
                System.out.println("6.Zwiększanie pensji");
                System.out.println("7.Zwiększanie pensji o podaną kwotę");
                System.out.println("8.Sortowanie pracowników według nazwisk");
                System.out.println("9.Sortowanie pracowników według pensji");
                Scanner scan2 = new Scanner(System.in);
                int v2 = scan2.nextInt();
                switch(v2){
                    case 1:
                        System.out.println("Podaj wartosc pensji :");
                        float pensja = scan2.nextFloat();
                        int suma = 0;
                        for(int i=0;i<lista_pracownikow.size();i++)
                        {
                            if(lista_pracownikow.get(i).sprawdz_pensje(pensja) == true)
                                suma++;
                        }
                        System.out.println("Liczba pracowników z pensją nie mniejszą niż "+pensja+" = "+suma);
                        break;
                    case 2:
                        System.out.println("Podaj numer dzialu: ");
                        int nr_dzial = scan2.nextInt();
                        int zlicz = 0;
                        float suma2 = 0.0f, srednia;
                        for(int i=0;i<lista_pracownikow.size();i++)
                        {
                            if(lista_pracownikow.get(i).getNr_dzialu() == nr_dzial) {
                                suma2 += lista_pracownikow.get(i).getPlaca();
                                zlicz++;
                            }
                        }
                        srednia = suma2/zlicz;
                        System.out.println("Srednia płaca w dziale nr "+nr_dzial+" = "+srednia);
                        break;
                    case 3:
                        float max1 = 0.0f, max2 = 0.0f;
                        for(int j=0; j<lista_pracownikow.size(); j++)
                        {
                            if(lista_pracownikow.get(j).getPlec() == 'K') {
                                if (lista_pracownikow.get(j).getPlaca() > max1)
                                    max1 = lista_pracownikow.get(j).getPlaca();
                            }else{
                                if (lista_pracownikow.get(j).getPlaca() > max2)
                                    max2 = lista_pracownikow.get(j).getPlaca();
                            }
                        }
                        System.out.println("Największa płaca wśród kobiet to : "+max1+" a wsród mężczyzn to: "+max2);
                        break;
                    case 4:
                        int [] dzialy = new int[10];
                        dzialy[0] = lista_pracownikow.get(0).getNr_dzialu();
                        int x , add;
                        for(int i=1; i<lista_pracownikow.size(); i++)
                        {
                            add = 0;
                            x = lista_pracownikow.get(i).getNr_dzialu();
                          for(int j=0;j<i;j++)
                          {
                              if(x == dzialy[j])
                                  add++;
                          }
                          if(add == 0)
                              dzialy[i] = x;
                        }
                        int l_k , l_m, zlicza=0;
                        float sumA = 0.0f, sredniA;
                        for(int i=0;i<dzialy.length;i++)
                        {
                            l_k = 0;
                            l_m = 0;
                            if(dzialy[i] != 0) {
                                for (int j = 0; j < lista_pracownikow.size(); j++) {
                                    if (lista_pracownikow.get(j).getNr_dzialu() == dzialy[i]) {
                                        if (lista_pracownikow.get(j).getPlec() == 'K')
                                            l_k++;
                                        else
                                            l_m++;
                                        sumA += lista_pracownikow.get(j).getPlaca();
                                        zlicza++;
                                    }
                                }
                                sredniA = sumA / zlicza;
                                if(l_k > l_m)
                                    System.out.println("Nr działu " + dzialy[i] + " liczba kobiet jest większa niż liczba mężczyzn ,a średnie zarobki to: " + sredniA);
                                else if(l_k < l_m)
                                    System.out.println("Nr działu "+dzialy[i]+" liczba kobiet jest mniejsza niż liczba mężczyzn ,a średnie zarobki to: "+sredniA);
                                else
                                    System.out.println("Nr działu "+dzialy[i]+" liczba kobiet jest równa liczbie mężczyzn ,a średnie zarobki to: "+sredniA);
                            }
                        }
                        break;
                    case 5:
                        float suma1 = 0.0f, suma3 = 0.0f;
                        int ilosc1 = 0, ilosc2 = 0;
                        float srednia1 , srednia2, stosunek;
                        for(int i=0; i<lista_pracownikow.size(); i++)
                        {
                            if(lista_pracownikow.get(i).getPlec() == 'K') {
                                suma1 += lista_pracownikow.get(i).getPlaca();
                                ilosc1++;
                            } else {
                                suma3 += lista_pracownikow.get(i).getPlaca();
                                ilosc2++;
                            }
                        }
                        srednia1 = suma1/ilosc1;
                        srednia2 = suma3/ilosc2;
                        stosunek = srednia1/srednia2;
                        System.out.println("Stosunek średniej pensji kobiet do mężczyzn wynosi "+ stosunek);
                        break;
                    case 6:
                        for(int i=0; i<lista_pracownikow.size();i++)
                        {
                            lista_pracownikow.get(i).oblicz_podwyzke(10.0f);
                        }
                        PrintWriter zapis2 = new PrintWriter("F:\\Projekty\\KursJava\\Firma\\lista_pracownikow.txt");
                        for(int i=0; i<lista_pracownikow.size();i++)
                        {
                            zapis2.write(lista_pracownikow.get(i).zapis_plik());
                        }
                        zapis2.close();
                        break;
                    case 7:
                        float kwota, placa2;
                        System.out.println("Podaj kwotę o jaką chcesz zwiększyć pensje pracowników: ");
                        Scanner scan3 = new Scanner(System.in);
                        kwota = scan3.nextFloat();
                        float suma_p = 0.0f;
                        int pod_k = 0, pod_m = 0;
                        for(int i=0;i<lista_pracownikow.size();i++)
                        {
                            if(lista_pracownikow.get(i).getPlec() == 'K'){
                                pod_k++;
                                placa2 = lista_pracownikow.get(i).getPlaca();
                                lista_pracownikow.get(i).setPlaca(placa2+kwota);
                                suma_p+=kwota;
                            }
                            else{
                                pod_m++;
                                placa2 = lista_pracownikow.get(i).getPlaca();
                                lista_pracownikow.get(i).setPlaca(placa2+kwota);
                                suma_p+=kwota;
                            }
                        }
                        System.out.println("Suma podwyżek dla wszystkich pracowników: "+suma_p);
                        System.out.println("Stosunek podwyżek kobiet i mężczyzn : "+pod_k+" do "+pod_m);
                        break;
                    case 8:
                        scan.nextLine();
                        System.out.println("True or False");
                        boolean stan = scan.nextBoolean();
                        if(stan) {
                            Collections.sort(lista_pracownikow);
                            PrintWriter zapis_sort = new PrintWriter("F:\\Projekty\\KursJava\\Firma\\lista_sort.txt");
                            for(int i=0; i<lista_pracownikow.size();i++)
                            {
                                zapis_sort.write(lista_pracownikow.get(i).zapis_plik());
                            }
                            zapis_sort.close();
                        }
                        else {
                            Collections.sort(lista_pracownikow, Collections.reverseOrder());
                            PrintWriter zapis_reverse = new PrintWriter("F:\\Projekty\\KursJava\\Firma\\lista_reverse.txt");
                            for(int i=0; i<lista_pracownikow.size();i++)
                            {
                                zapis_reverse.write(lista_pracownikow.get(i).zapis_plik());
                            }
                            zapis_reverse.close();
                        }
                        break;
                    case 9:
                        scan.nextLine();
                        System.out.println("True or False");
                        boolean stan2 = scan.nextBoolean();
                        if(stan2) {
                            Collections.sort(lista_pracownikow, new Pracownik.PracownikPensja());
                            PrintWriter zapis_sort_pensja = new PrintWriter("F:\\Projekty\\KursJava\\Firma\\lista_sort_pensja.txt");
                            for(int i=0; i<lista_pracownikow.size();i++)
                            {
                                zapis_sort_pensja.write(lista_pracownikow.get(i).zapis_plik());
                            }
                            zapis_sort_pensja.close();
                        }
                        else {
                            Collections.sort(lista_pracownikow, new Pracownik.PracownikPensja());
                            Collections.reverse(lista_pracownikow);
                            PrintWriter zapis_reverse_pensja = new PrintWriter("F:\\Projekty\\KursJava\\Firma\\lista_reverse_pensja.txt");
                            for (int i = 0; i < lista_pracownikow.size(); i++) {
                                zapis_reverse_pensja.write(lista_pracownikow.get(i).zapis_plik());
                            }
                            zapis_reverse_pensja.close();
                        }
                        break;
                }
                break;
            default:
                System.out.println("Nie ma takiej opcji");
                break;
        }
    }
}
