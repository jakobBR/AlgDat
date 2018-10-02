package UkeOppgaver4;

public class uke4 {

    public static void main(String[] args) {

        //System.out.println(sifferRot2(986574 ));

        int x = 3242;
        int y = 235232;


    }

    //1.5.1 1)
    public static int a(int n){ // n må ikke være negativ
        if (n == 0) return 1;              // a0 = 1
        else if (n == 1) return 2;         // a1 = 2
        else return 2*a(n-1) + 3*a(n-2);   // to rekursive kall

    }

    public static  int aIterativ(int n) {
        int x=0,y=1,z=1;
        for (int i = n; i>0;i--){
            z=2*y+3*x;
            x=y;
            y=z;
        }
        return z;
    }

    //1.5.1 3)
    public static int tverrsum(int n){             // n må være >= 0
        if (n < 10) return n;                        // kun ett siffer
        else return tverrsum(n / 10) + (n % 10);     // metoden kalles
    }

    public static int tverrsumIterativ(int n) {       // n må være >= 0
        int z=0;
        while (n>0){
            z+= n%10;
            n=n/10;
        }
        return z;
    }

    //1.5.1 4)
    public static int sifferRot(int n ){
        if (n/10==0)
            return n;
        else
            return sifferRot(tverrsum(n));
    }

    /**
     * Vi har for alle ikke-negative heltall n at 9 går opp i n hvis og bare hvis 9 går opp i tverrsummen til n.
     * Se oppgave 5a) nedenfor. Det betyr at sifferroten til et heltall n der 9 går opp, er lik 9. Hvis n ikke går opp,
     * er sifferroten lik resten når n deles med 9.
     */
    public static int sifferRot2(int n)
    {
        n %= 9;
        return n == 0 ? 9 : n;
    }

    ////1.5.1 5) Oppgave 5 a)
    //Vi tar som eksempel et vilkårlig firesifret tall med a, b, c og d som de fire sifrene.
    // Dvs. tallet 1000a + 100b + 10c + d. Tverrsummen blir lik a + b + c + d.
    // Tar vi differensen får vi 1000a + 100b + 10c + d − (a + b + c + d) = 999a + 99b + 9c = 9(111a + 11b + c).
    // Dermed ser vi at 9 går opp i tallet hvis og bare hvis 9 går opp i tallets tverrsum. Samme type argument kan
    // brukes for tall hvor antall siffer både er større og mindre enn fire.
    //System.out.println("sifferRot av produkt av sifferRoter til x og y "+sifferRot2(sifferRot2(x)*sifferRot2(y))+"
    // SifferRot av x*y "+sifferRot2(x*y));

    //1.5.1 7)
    public static int sumKvadrat(int n) {
        if (n == 1)
            return 1;
        return sumKvadrat(n-1)+n*n;
    }



}
