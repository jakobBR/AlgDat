package uke5;

public class uke5 {

    public static void main(String[] args) {
        //oppg2
        System.out.println(maks("ABddfgnbC".toCharArray()));
        //oppg3
        Integer[] a = {5,2,7,3,9,1,8,4,6};
        System.out.println(maks(a));
        //4
        Integer b=1,c=2;
        System.out.println(c.compareTo(b));
        //5
        String s = "ABC", t = "AAA";
        System.out.println(s.compareTo(t));
    }

    //1.4.1 oppg1
    public static int maks(char[] a){
        int m = 0;                           // indeks til største verdi
        char maksverdi = a[0];             // største verdi
        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdaters
        }
        return m;     // returnerer posisjonen til største verdi
    }

    public static int maks(Integer[] a)    // legges i class Tabell
    {
        int m = 0;                          // indeks til største verdi
        Integer maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer posisjonen til største verdi
    }


}
