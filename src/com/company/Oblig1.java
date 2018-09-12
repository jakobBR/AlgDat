package com.company;

import java.util.Arrays;

import static com.company.Oblig1Test.randPerm;
import static java.util.Arrays.sort;

public class Oblig1 {

    //
    public static void main(String[] args) {/*
        /*/
        String a = "";
        String b = "";
        System.out.println(flett( a, b));/*
        int[] a = {7, 4, 5, 9, 2, 3, 10, 11, 7};
        delsortering(a);
        System.out.println(Arrays.toString(a));
        //int sum=0 ;
       /* for (int i = 1; i<=10000 ;i++) {
            j=j+ ombyttinger(randomArray1(6));
        }
        char[] b = {'a', 'b', 'c', 'd'};
        rotasjon(b,-5);
        System.out.println(Arrays.toString(b));
        // System.out.println(j/10000);*/
    }


    public static int[] randomArray1(int num_values) {
        System.out.println("randomArray1 lager et array");
        int values[] = new int[num_values];
        int taken[] = new int[num_values];

        // Loop over arrayen og fyll med tall
        for (int i = 0; i < num_values; ++i) {
            //values[i] = i+1;
            int random_value = (int) (Math.random() * num_values + 1);
            if (taken[random_value - 1] == 1) {
                i = i - 1;
            } else {
                values[i] = random_value;
                taken[random_value - 1] = 1;
            }
        }

        return values;
    }

    //Oppgave1
    // Når blir det flest ombyttinger?
    //Når tabbellen a inneholder høyeste verdi først, i index 0, og så lavere verdier for hver index slik at siste
    // index inneholder den laveste verdien. Da vil uttrykket  a[i-1]>a[i] i if statementet være sant for hver iterasjon
    //  i for løkka. if statementet vil da kjøre n-1 ganger.
    // Når blir det færrest?
    // Dersom verdien ved hver indeks øker slik at den største verdien i tabbellen er i høyeste/siste index,
    // Da vil uttrykket a[i-1]>a[i] være false og koden i if statementet vil ikke kjøre.
    // Hvor mange blir det i gjennomsnitt?
    // Det blir i gjennomsnitt nesten n ombyttninger når n stor.
    // n-ln(n)-0,577 ser ut til å gi et godt estimat på gjennomsnittelig antall ombyttninger der n er størrelsen på array.
    // Metoden er mindre effektiv enn andre maks metoder vi har sett på, fordi den må bytte hver gang verdien
    // i neste indeks er lavere enn en av verdiene i en tidligere index. Dette medfører at den må bytte
    // for nesten hver iterasjon når størrelsen på arrayen er stor. En mer effektiv løsning blir da å kun måtte
    // bytte når neste verdi i arrayen er høyere enn alle de tidligere verdiene, slik som i andre maks metoder vi har
    // sett på.
    public static int maks(int[] a) {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        int temp;
        for (int i = 1; i < a.length; i++)
            if (a[i - 1] > a[i]) {
                temp = a[i - 1];
                a[i - 1] = a[i];
                a[i] = temp;
            }
        return a[a.length - 1];
    }

    public static int ombyttinger(int[] a) {
        if (a.length < 1)
            throw new java.util.NoSuchElementException("Tabellen a er tom!");
        int temp;
        int hjelpeVariabel = 0;
        for (int i = 1; i < a.length; i++)
            if (a[i - 1] > a[i]) {
                hjelpeVariabel++;
                temp = a[i - 1];
                a[i - 1] = a[i];
                a[i] = temp;
            }
        return hjelpeVariabel;

    }


    //oppgave 2

    public static int antallUlikeSortert(int[] a) {
        for (int i = 1; i < a.length; i++) {
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

    public static void delsortering(int[] a) {
        if (a.length == 0 || a.length == 1)
            return;
        int v = 0;
        int h = a.length - 1;
        while (true) {
            while (a[v] % 2 != 0 && v < h)
                v++;

            while ((a[h]) % 2 == 0 && h > v)
                h--;

            if (v < h) {
                bytt(a, v, h);
            }
            if (v == h) {
                kvikksortering(a, 0, h);
                if (v == a.length - 1) {
                    kvikksortering(a, v - 1, a.length);
                } else {
                    kvikksortering(a, v, a.length);
                }
                break;
            }
        }
    }

    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void kvikksortering0(int[] a, int v, int h) {
        if (v >= h) return;  // a[v:h] er tomt eller har maks ett element
        int k = sParter0(a, v, h, (v + h) / 2);  // bruker midtverdien
        kvikksortering0(a, v, k - 1);     // sorterer intervallet a[v:k-1]
        kvikksortering0(a, k + 1, h);     // sorterer intervallet a[k+1:h]
    }

    public static void kvikksortering(int[] a, int fra, int til) // a[fra:til>
    {
        kvikksortering0(a, fra, til - 1);  // v = fra, h = til - 1
    }

    private static int sParter0(int[] a, int v, int h, int indeks) {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h − 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien .Dden er nå sortert
    }


    private static int parter0(int[] a, int v, int h, int skilleverdi) {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) {
                bytt(a, v, h);                 // bytter om a[v] og a[h]
            } else return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    ///Opgave 5

    public static void rotasjon(char[] a) {
        if (a.length == 0)
            return;
        char temp = a[0];
        if (a.length == 2) {
            a[0] = a[1];
            a[1] = temp;
            return;
        }

        a[0] = a[a.length - 1];
        for (int i = a.length - 2; i > 0; i--) {
            a[i + 1] = a[i];
            if (i == 1)
                a[i] = temp;
        }
    }

    //oppgave 6

    public static void rotasjon(char[] a, int k) {
        if (a.length <2 || k == 0)
            return;
        char temp = a[0];
        k %= a.length;
        if (k < 0)
            k = k + a.length;
        char[] b = new char[k];
        for (int i = 0; i < k; i++) b[k - 1 - i] = a[a.length - 1 - i];
        for (int i = a.length - 1; i >= k; i--) a[i] = a[i - k];
        for (int i = 0; i < b.length; i++) a[i] = b[i];
    }

  ////  Oppgave7 //////////////

    public static String flett(String s, String t) {

        
        char[] a = new char[s.toCharArray().length+t.toCharArray().length];
        int i=0;
        int j=0;
        int k=0;
        while (i < s.toCharArray().length && j<t.toCharArray().length){
            a[k] = s.charAt(i);
            k++;
            i++;
            a[k] = t.charAt(j);
            k++;
            i++;
        }
        while (i<s.toCharArray().length){
            a[k]= s.charAt(i);
            k++;
            i++;
        }
        while (j<t.toCharArray().length) {
            a[k]= t.charAt(j);
            k++;
            i++;
        }
        return Arrays.toString(a);
    }

    //public static String flett(String[] a) {
    //}
}



/*
        int counter=0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] % 2 != 0) {
                counter++;
                for (int j = 1; j <= i; j++) {
                    if (a[i] < a[j - 1]&& a[i]%2!=0 || (a[i]%2!=0&&a[j-1]%2==0)) {
                        bytt(a, i, j - 1);
                    }
                }
            }
        }
        if (counter ==0)
            counter=1;
        for(int i= counter; i <a.length;i++){
            if (a[i]%2==0) {
                for (int j = counter; j <= i; j++) {
                    if ((a[i] < a[j - 1])&&(a[j-1]%2==0)) {
                        bytt(a, i, j - 1);
                    }
                }
            }
        }*/