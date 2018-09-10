package com.company;

public class Oblig1 {

    //
    public static void main(String[] args) {
        // write your code here
        int[] array = {10,9,8,7,6,5,4,3,2,1};
        int[] a = {3,1};
        //int sum=0 ;
        //for (int i = 1; i<=10000 ;i++) {
           // System.out.println(ombyttinger(randomArray1(100)));
        //}


    }


    public static int[] randomArray1(int num_values) {
        System.out.println("randomArray1 lager et array");
        int values[] = new int[num_values];
        int taken[] = new int[num_values];

        // Loop over arrayen og fyll med tall
        for (int i=0; i<num_values; ++i) {
            //values[i] = i+1;
            int random_value = (int) (Math.random()*num_values + 1);
            if (taken[random_value-1] == 1) {
                i = i-1;
            }
            else {
                values[i] = random_value;
                taken[random_value-1] = 1;
            }
        }

        return values;
    }

    //Oppgave1
    // Når blir det flest ombyttinger?
    //Når tabbellen a inneholder n(høyeste verdi) i index 0, n-1 i index 1 osv slik at siste index inneholder verdien 1
    // da vil uttrykket  a[i-1]>a[i] i if statementet alltid være sant og koden i if statementet vil kjøre n-1 ganger.
    // Når blir det færrest?
    // Dersom den største verdien i tabbellen a er i høyeste/siste index, Da vil uttrykket a[i-1]>a[i]
    // være false og koden i if statementet vil ikke kjøre
    // Hvor mange blir det i gjennomsnitt?
    // n-1,5 - 2*log(n)
    // Det blir i gjennomsnitt nesten n ombyttninger når n er stor.
    // Den er mindre effektiv enn andre maks metoder vi har sett på, fordi den må bytte hver gang verdien
    // i neste indeks er lavere enn en av verdiene i en tidligere index. Dette medfører at den må bytte
    // nesten hver iterasjon når størrelsen på arrayen er stor og den består av tilfeldige tall. Dersom arrayen er
    //sortert i stigende rekkefølge der første index har laveste verdi vil den være mer effektiv da a[i-1]>a[i] vil
    //være false.
    public static int maks(int[] a) {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        int temp;
        for (int i = 1; i < a.length;i++)
            if (a[i-1]>a[i]) {
                temp = a[i-1];
                a[i-1] = a[i];
                a[i] = temp;
            }
        return a[a.length-1];
    }

    public static int ombyttinger(int[] a) {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        int temp;
        int hjelpeVariabel=0;
        for (int i = 1; i < a.length;i++)
            if (a[i-1]>a[i]) {
                hjelpeVariabel++;
                temp = a[i-1];
                a[i-1] = a[i];
                a[i] = temp;
            }
        return hjelpeVariabel;

    }


    //oppgave 2

    public static int antallUlikeSortert(int[] a){
        for (int i = 1; i<a.length;i++) {
            if (a[i - 1] > a[i])
                throw new java.lang.IllegalStateException("tabbelen er ikke sortert");
        }
        if (a.length == 0)
            return 0;
        int counter = 1;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1]) {
                counter++;
            }
        }
        return counter;
    }

    //Oppgave 3
    public static int antallUlikeUsortert(int[] a) {
        if (a.length == 0)
            return 0;
        int sum = 1;
        for (int i = 1; i < a.length; i++) {
            int counter = 0;
            for (int j = 1; j <= i; j++) {
                if (a[i] == a[j - 1]) {
                    counter++;
                }
            }
            if (counter == 0)
                sum++;
        }
        return sum;
    }

    //Oppgave 4

    public static void delsortering(int[] a){

    }



}

