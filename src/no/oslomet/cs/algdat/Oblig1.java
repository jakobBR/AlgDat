// Jakob Braseth Ramstad s325854

package no.oslomet.cs.algdat;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Oblig1 {

    //Oppgave1
    // Når blir det flest ombyttinger?
    //Når tabbellen a inneholder høyeste verdi først, i index 0, og så lavere verdier for hver index slik at siste
    // index inneholder den laveste verdien. Da vil uttrykket  a[i-1]>a[i] i if statementet være sant for hver iterasjon
    //  i for løkka. if statementet vil da kjøre n-1 ganger.
    // Når blir det færrest?
    // Dersom verdien ved hver indeks øker slik at den største verdien i tabbellen er i høyeste/siste index,
    // Da vil uttrykket a[i-1]>a[i] være false og koden i if statementet vil ikke kjøre.
    // Hvor mange blir det i gjennomsnitt?
    // n-ln(n)-0,423 ser ut til å gi et godt estimat på gjennomsnittelig antall ombyttninger der n er størrelsen på
    // array.
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

    ///Oppgave 5

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

    //Oppgave 6

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
        if (s.length()==0)
            return t;
        if (t.length()==0)
            return s;
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
            j++;
        }
        while (i<s.toCharArray().length){
            a[k]= s.charAt(i);
            k++;
            i++;
        }
        while (j<t.toCharArray().length) {
            a[k++]= t.charAt(j++);
        }
        String s1 = new String(a);
        return s1;
    }

    public static String flett(String... s) {
        int maxLength=0;
        String out = "";
        for (int i = 0 ; i<s.length; i++) {
            if (s[i].length()>maxLength)
                maxLength=s[i].length();
        }
        for (int i= 0 ; i<maxLength;i++){
            for (int j=0;j<s.length;j++){
                if (s[j].length()>i){
                    out+=s[j].charAt(i);
                }
            }
        }
        return out ;
    }

    //Oppgave 8

    public static int[] indekssortering(int[] a){
        int[] b = a.clone();
        int[] indextabell = new int[a.length];
        kvikksortering(b,0,b.length);
        for( int i = 0 ; i<a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (b[i] == a[j] && valueNotInArray(indextabell,j))
                    indextabell[i] = j;
            }
        }
        return indextabell;
    }

    //Oppgave 9

    public static int[] tredjeMin(int[] a) {
        if (a.length < 3)
            throw new NoSuchElementException("Tabbellen har mindre enn 3 verdier");

        int[] b = Arrays.copyOf(a,3);
        b=indekssortering(b);

        int mv = b[0];
        int nmv = b[1];
        int nnmv = b[2];

        int mVerdi= a[mv];
        int nmVerdi= a[nmv];
        int nnmVerdi = a[nnmv];
        if(a.length==3)
            return new int[]{mv,nmv,nnmv};
        for (int i = 3 ; i < a.length ; i++){
            if (a[i]<nnmVerdi) {
                if (a[i]<nmVerdi) {
                    if (a[i]<mVerdi) {
                        nnmv=nmv;
                        nnmVerdi=nmVerdi;

                        nmv=mv;
                        nmVerdi=mVerdi;

                        mv=i;
                        mVerdi=a[mv];
                    }
                    else
                    {
                        nnmv=nmv;
                        nnmVerdi=nmVerdi;

                        nmv=i;
                        nmVerdi=a[nmv];
                    }
                }
                else
                {
                    nnmv=i;
                    nnmVerdi=a[nnmv];

                }
            }
        }

        return new int[]{mv,nmv,nnmv};

    }

    //Oppgave 10


    public static boolean inneholdt(String a, String b){

        if (a.length()== 0 || a.equals(b))
            return true;

        char[] alfabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','Æ','Ø','Å'};

        long[] aCharacterCount = new long[29];
        long[] bCharacterCount = new long[29];

        for(int i=0 ; i<a.length(); i++){
            for (int j = 0 ; j<alfabet.length;j++) {
                if (a.charAt(i)==alfabet[j])
                    aCharacterCount[j]= aCharacterCount[j] +1;
            }

        }

        for(int i=0 ; i<b.length(); i++){
            for (int j = 0 ; j<alfabet.length;j++) {
                if (b.charAt(i)==alfabet[j]){
                    bCharacterCount[j]= bCharacterCount[j] +1;
                }
            }
        }

        for (int i=0; i< aCharacterCount.length;i++) {
            if (aCharacterCount[i]>bCharacterCount[i])
                return false;
        }
        return  true;
    }

    // Hjelpemetoder////

    //returnerer true dersom verdien j ikke finnes i a
    public static boolean valueNotInArray(int[] a, int j){
        for (int i : a ) {
            if (j == i)
                return false;
        }
        return true;
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
}