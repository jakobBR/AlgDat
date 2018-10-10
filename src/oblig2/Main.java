package oblig2;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
      /*  String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);

        liste.forEach(s -> System.out.print(s + " "));
        System.out.println();
        for (String s : liste) System.out.print(s + " ");*/

        // Utskrift:
        // Lars Anders Bodil Kari Per Berit
        // Lars Anders Bodil Kari Per Berit

        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste1 = new DobbeltLenketListe<>(navn);
        DobbeltLenketListe.sorter(liste1, Comparator.naturalOrder());
        System.out.println(liste1);
        // [Anders, Berit, Bodil, Kari, Lars, Per]

        // Tabellen navn er upåvirket:
         System.out.println(Arrays.toString(navn));
        // [Lars, Anders, Bodil, Kari, Per, Berit]

    }
}
