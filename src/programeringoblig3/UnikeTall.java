package programeringoblig3;

import java.text.DecimalFormat;
import java.util.Random;
import static javax.swing.JOptionPane.*;

public class UnikeTall {

    private int[] Array;

    public static void main(String[]args){
        /*UnikeTall u = new UnikeTall(64);
        u.fyllArray();
        System.out.println(u.gjennomsnittsVerdi());
        System.out.println(u.nærmesteHeltall());
        u.visArrayinfo();*/
        int a = 1;
        int b = 1 ;
         b *=a;
        b *=a;
        b=2 ;
        b*=a;
        a=2;


        b*=a;
        System.out.println(b);

    }

    public UnikeTall(int lengde) {
        Array = new int[lengde];
    }

    private boolean existsInArray(int n){
        for(int i =0;i<Array.length;i++) {
            if (Array[i]==n)
                return true;
        }
        return false;
    }

    private  void fyllArray() {
        Random r = new Random();
        for (int i = 0 ; i<Array.length ; i++){
            int random_Number = r.ints(100, 1000).findFirst().getAsInt();
            if(existsInArray(random_Number)){
                i--;
            } else {
                Array[i] = random_Number;
            }
        }
    }

    private int min() {
        int min = Array[0];
        for (int i = 0; i<Array.length;i++){
            if (Array[i]<min){
                min = Array[i];
            }
        }
        return min;
    }

    private int maks() {
        int maks = Array[0];
        for (int i = 0; i<Array.length;i++){
            if (Array[i]>maks){
                maks = Array[i];
            }
        }
        return maks;
    }

    private double gjennomsnittsVerdi(){
        double gjennomsnittsverdi=0;
        for (int i = 0;i<Array.length;i++){
            gjennomsnittsverdi = gjennomsnittsverdi+Array[i];
        }
        return gjennomsnittsverdi/Array.length;
    }

    private int gjennomsnittHeltall2(){
        int nedrundet = (int) gjennomsnittsVerdi();
        int opprundet = (int) gjennomsnittsVerdi()+1;
        if (opprundet-gjennomsnittsVerdi()<gjennomsnittsVerdi()-nedrundet){
            return opprundet;
        } else {
            return nedrundet;
        }
    }

    //returnere det heltallet i tabellen som ligger nærmest gjennomsnittssverdien.
    private int nærmesteHeltall(){
        int nærmeste_Heltall = Array[0];
        double differanse =Math.abs(gjennomsnittsVerdi()-Array[0]);
        for(int i =1;i<Array.length;i++){
            if (Math.abs(gjennomsnittsVerdi()-Array[i])<differanse){
                differanse=Math.abs(gjennomsnittsVerdi()-Array[i]);
                nærmeste_Heltall=Array[i];
            }
        }
        return nærmeste_Heltall;
    }

    private void visArrayinfo(){
        StringBuilder sb = new StringBuilder("");
        for (int i =0; i<Array.length;i++){
            if (i % 12 == 0&& i !=0){
                sb.append("\n");
            }
            sb.append(Array[i]+" ");
        }
        DecimalFormat value = new DecimalFormat("#.#");
        String arrayString = sb.toString();
        showMessageDialog(null, arrayString+"\n\n"+"Minste tall er: "+min()+
        "\n"+"Største tall er: "+maks()+"\n"+"Gjennomsnittsverdien er: "+value.format(gjennomsnittsVerdi())+"\n"+
        "Heltallsverdi nærmest gjennomsnittet er: "+nærmesteHeltall()+"\n");
    }
}
