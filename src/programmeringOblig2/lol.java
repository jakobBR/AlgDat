package programmeringOblig2;

import static javax.swing.JOptionPane.*;

public class lol {

    int nedreGrense;
    int øvreGrense;

    public static void main(String[] args) {

        lol a = new lol();

        a.getLimits();

        int sum=0;
        int counter=0;

        for(int i = a.nedreGrense; i<=a.øvreGrense;i++){
            if (counter%10==0 && counter!=0 )
                System.out.println();System.out.print(i+" ");
            if (i<a.øvreGrense){
                System.out.print("+ ");
            }
            sum = sum+i;
            counter++;
        }
        System.out.println("= "+sum);

    }

    void getLimits() {
        nedreGrense =  Integer.parseInt(showInputDialog("skriv inn nedregrense"));
        øvreGrense = Integer.parseInt(showInputDialog("skriv inn øvregrense"));
        if (øvreGrense<=nedreGrense)
            getLimits();
    }
}
