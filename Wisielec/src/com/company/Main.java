package com.company;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System wisielec = new System();
        wisielec.setVisible(true);
        wisielec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    String [] tablica_hasel = {"onomatopeja","anoreksja","elementarz","fizyka"};
        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        int index = random.nextInt(3);
	    String haslo = tablica_hasel[index]; // przekazanie jednego z hasła z tablicy haseł do zmiennej string
	    String litera;
	    char li;
	    char [] odg = new char [haslo.length()];
	    char [] przechowuje = new char [haslo.length()];
	    int count=0;

	    for(int i=0; i<haslo.length(); i++)      // Zamiana hasła ze stringa na tablice znaków String
        {
            odg[i] = haslo.charAt(i);
            System.out.print(odg[i]);
        }

        System.out.println("______WISIELEC______");
        System.out.println();
        System.out.println();

        for(int i=3;i>=1;i--) {
            System.out.println("\nPodaj literke: ");
            litera = scan.nextLine();
            System.out.println("Masz do wykorzystania: " + i);
            li = litera.charAt(0);  // Zmienna litera jest łańcuchem znaków dlatego wyciągamy pierwszą litere z łańcucha

                for (int j = 0; j < haslo.length(); j++) {

                    if(przechowuje[j]<'a' || przechowuje[j]>'z')
                        przechowuje[j] = '_';

                    if (li == odg[j]) {
                        przechowuje[j] = odg[j];
                        if(i!=3)
                            i++;
                    }

                    System.out.print(przechowuje[j]+" ");
                }

                for(int m=0;m<haslo.length();m++)
                {
                    if(przechowuje[m] != '_')
                        count++;
                }

                if(count == haslo.length()){
                    System.out.println("Brawo zgadłeś hasło !!!");
                    System.exit(0);
                }
                count=0;
        }
    }
}
