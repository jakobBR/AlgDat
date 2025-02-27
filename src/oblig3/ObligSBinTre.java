package oblig3;

/**
 *  Navn Jakob Braseth Ramstad
 *  Studentnummer s325854
 */

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
        {
            this.verdi = verdi;
            venstre = v; høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString(){ return "" + verdi;}

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public ObligSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi,q);                   // oppretter en ny node, q er forelder

        if (q == null) rot = p;                  // p blir rotnodgte
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        endringer++;
        return true;                             // vellykket innlegging
    }

    @Override
    public boolean inneholder(T verdi)
    {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi); // svarer til verdi - p.verdi. negativ hvis verdi er mindre enn p.verdi
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    @Override
    public boolean fjern(T verdi)
    {
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) rot = b;
            else if (p == q.venstre) {
                q.venstre = b;
                if (b!=null)b.forelder=q;
            }
            else {
                q.høyre = b;
                if (b!=null)b.forelder=q;
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
                r.forelder=s;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }

        antall--;   // det er nå én node mindre i treet
        endringer++;
        return true;
    }

    public int fjernAlle(T verdi)
    {
        int antall=0;
        while (fjern(verdi)){
            antall++;
        }
        return antall;
    }

    @Override
    public int antall()
    {
        return antall;
    }

    public int antall(T verdi)
    {
        if (!inneholder(verdi)){ // sjekker om verdien er i treet
            return 0; // returnerer 0 dersom verdien ikke er i treet ;
        }

        Node<T> p = rot;
        int forekomster = 0 ;

        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi); // svarer til verdi - p.verdi. negativ hvis verdi er mindre enn p.verdi
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else {
                p = p.høyre;
                forekomster++;
            }
        }

        return forekomster;
    }

    @Override
    public boolean tom()
    {
        return antall == 0;
    }

    public void nullstill2(Node<T> p){

        while (p.høyre!=null)
        {
            nullstill2(p.høyre);
            p.høyre = null;
        }
        while (p.venstre!=null)
        {
            nullstill2(p.venstre);
            p.venstre = null;
        }
        p.verdi = null;
        p.forelder=null;
    }

    @Override
    public void nullstill()
    {

        if (rot == null ){
            return;
        } else {
            nullstill2(rot);
        }
        rot = null; antall = 0; endringer= 0;


    }

    private static <T> Node<T> nesteInorden(Node<T> p)
    {
        Node<T> q ;

        if(p.høyre !=null) {
            p=p.høyre;
            while (p.venstre!=null){
                p = p.venstre;
            }
            return p ;
        }

        while (p.forelder != null){
            q=p.forelder;
            if (q.venstre==p){
                return q;
            } else {
                p=q;
            }
        }
        return null;

    }

    @Override
    public String toString()
    {
        if (antall==0){
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node<T> p = rot;
        //setter p til første inorden
        while (p.venstre != null)
            p=p.venstre;

        sb.append(p.verdi).append(", ");
        while (nesteInorden(p)!=null) {
            p=nesteInorden(p);
            sb.append(p.verdi).append(", ");
        }
        sb.delete(sb.length()-2,sb.length()); //fjerner ", " bakerst i stringen.
        sb.append("]");

        return sb.toString();
    }

    public String omvendtString() {
        if (rot == null) return "[]";            // tomt tre

        Stakk<Node<T>> stakk = new TabellStakk<>();
        Stakk<Node<T>> stakk2 = new TabellStakk<>();
        Node<T> p = rot;   // starter i roten og går til venstre
        for (; p.venstre != null; p = p.venstre) stakk.leggInn(p);

        while (true) {
            stakk2.leggInn(p);
            if (p.høyre != null)          // til venstre i høyre subtre
            {
                for (p = p.høyre; p.venstre != null; p = p.venstre) {
                    stakk.leggInn(p);
                }
            } else if (!stakk.tom()) {
                p = stakk.taUt();   // p.høyre == null, henter fra stakken
            } else break;          // stakken er tom - vi er ferdig

        }
        return stakk2.toString();
    }

    public String høyreGren()
    {
        if (rot == null) return "[]";
        String gren="[";
        Node<T> p = rot;

        gren = gren.concat(p.verdi.toString());

        while (p.høyre !=null || p.venstre != null) {
            if (p.høyre !=null) {
                p = p.høyre;
                gren=gren.concat(", "+p.verdi.toString());
            } else {
                p=p.venstre;
                gren=gren.concat(", "+p.verdi.toString());
            }
        }
        gren=gren.concat("]");
        return gren;
    }

    public String lengstGren()
    {
        if (rot == null) return "[]";
        Node<T> p = rot;

        ArrayList<String> gren = new ArrayList<>();
        lengstGren2(p,gren);
        String LengsteGren = gren.get(0);
        gren.clear();
        return LengsteGren;
    }
    //hjelpemetode
    //finner alle bladnoder, bruker de andre hjelpemetodene til å lage greinene og lagrer den
    //lengste på indeks 0 i Arraylisten gren.
    public void lengstGren2(Node<T> p, ArrayList gren){
        while (p.venstre !=null || p.høyre !=null) {
            if (p.venstre !=null && p.høyre !=null ){
                lengstGren2(p.venstre,gren);
                lengstGren2(p.høyre,gren);
                return;
            }
            if (p.venstre !=null) {
                p = p.venstre;
            }
            if (p.høyre !=null) {
                p = p.høyre;
            }
        }
        String g = grenBygger(p);
        if (gren.size()==0){
            gren.add(g);
            return;
        }
        String a = String.valueOf(gren.get(0));
        if (Verdier_i_gren(g)>Verdier_i_gren(a)){
            gren.set(0,g);
        }
    }
    //hjelpemetode
    //returnerer antall verdier / noder i en gren som er representert som en string.
    private static int Verdier_i_gren(String g){
        int verdier = 0 ;
        if (g=="[]"){
            return verdier;
        }
        verdier++;
        for (int i =0; i<g.length();i++){
            if (g.charAt(i)==','){
                verdier++;
            }
        }
        return verdier;
    }
    //hjelpemetode
    //Bygger Stringen til grenen der p er bladnode
    private String grenBygger(Node<T> p) {
        TabellStakk stakk = new TabellStakk();
        while (p.forelder!=null ){
            stakk.leggInn(p);
            p=p.forelder;
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append(p);
        while (stakk.antall() != 0){
            sb.append(", ");
            sb.append(stakk.taUt());
        }
        sb.append("]");
        return sb.toString();
    }

    public String[] grener()
    {
        String[] a = {};
        if (rot == null) return a;

        String greinerArray[] = grenGreier(rot).split("'-,;'");

        return greinerArray;
    }

    public String grenGreier(Node<T> p){
        while (p.venstre !=null || p.høyre !=null) {
            if (p.venstre !=null && p.høyre !=null ){
                return grenGreier(p.venstre)+grenGreier(p.høyre);
            }
            if (p.venstre !=null) {
                p = p.venstre;
            }
            if (p.høyre !=null) {
                p = p.høyre;
            }
        }
        String GrenerString = grenBygger(p)+"'-,;'";//legger til greina i stringen. '-,;' brukes til å skille grener
        return GrenerString;
    }

    public String bladnodeverdier()
    {
        if (rot==null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        bladnodeverdier(rot,sb);
        sb.deleteCharAt(sb.length()-1);//sletter ", "
        sb.deleteCharAt(sb.length()-1);//sletter ", "
        sb.append("]");
        return sb.toString();
    }
    //rekursivhjelpemetode
    public void bladnodeverdier(Node<T> p, StringBuilder sb)
    {
        while (p.venstre !=null || p.høyre !=null) {
            if (p.venstre !=null && p.høyre !=null ){
                bladnodeverdier(p.venstre,sb);
                bladnodeverdier(p.høyre,sb);
                return ;
            }
            if (p.venstre !=null) {
                bladnodeverdier(p.venstre,sb);
                return ;
            } else {
                bladnodeverdier(p.høyre,sb);
                return ;
            }
        }
        sb.append(p.verdi);
        sb.append(", ");
    }


    public String postString()
    {
        if (rot == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> p = rot ;
        posthjelp(sb);
        return sb.toString();
    }

    public Node<T> neste(Node<T> p, Stakk s){
        while (((p.forelder.høyre==null)||(p==p.forelder.høyre))){
            p=p.forelder;
        }
        p=p.forelder.høyre;
        while (p.venstre != null || p.høyre != null){
            if (p.venstre!=null){
                s.leggInn(p);
                p=p.venstre;
            } else {
                s.leggInn(p);
                p=p.høyre;
            }
        }
        return p;
    }

    public void posthjelp(StringBuilder sb){
        Stakk<Node<T>> stakk = new TabellStakk();
        Node<T> p=rot;
        while (p.venstre != null || p.høyre != null){//finner første bladnode
            if (p.venstre!=null){
                stakk.leggInn(p);
                p=p.venstre;
            } else {
                stakk.leggInn(p);
                p=p.høyre;
            }
        }
        while (!stakk.tom()) {
            sb.append(p + ", ");
            if (p.forelder.høyre != null && p.forelder.høyre != p) {
                p = neste(p, stakk);
            } else p = stakk.taUt();
        }
        sb.append(rot+"]");
    }


    public Node<T> finnFørsteBladNode(Node p){
        while (p.venstre != null || p.høyre != null){//finner første bladnode
            if (p.venstre!=null){
                p=p.venstre;
            } else {
                p=p.høyre;
            }
        }
        return p;
    }

    public Node<T> finnNeste(Node<T> p){
        if(p.forelder==null){
            return null;
        }
        while (((p.forelder.høyre==null)||(p==p.forelder.høyre))){
            if ((p.forelder==rot)){
                return null;
            }
            p=p.forelder;
        }
        p=p.forelder.høyre;
        while (p.venstre != null || p.høyre != null){
            if (p.venstre!=null){

                p=p.venstre;
            } else {

                p=p.høyre;
            }
        }
        return p;
    }


    @Override
    public Iterator<T> iterator()
    {
        return new BladnodeIterator();
    }

    private class BladnodeIterator implements Iterator<T>
    {
        private Node<T> p = rot, q = null;
        private boolean removeOK = false;
        private int iteratorendringer = endringer;

        private BladnodeIterator()  // konstruktør
        {
            if (tom()){return;}
            p=finnFørsteBladNode(p);
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext()
        {
            return p != null;  // Denne skal ikke endres!
        }

        @Override
        public T next()
        {removeOK = true;
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            if (endringer != iteratorendringer) throw new
                    ConcurrentModificationException("");


            q=p;
            p=finnNeste(p);

            return q.verdi;
        }

        @Override
        public void remove()
        {
            if (!removeOK){
                throw new IllegalStateException();
            }

            if(q.forelder==null) {
                q=null;//q skal fjernes og q er rot
            } else if (q.forelder.venstre==q){
                q.forelder.venstre=null;
            } else if (q.forelder.høyre==q){
                q.forelder.høyre=null;
            }
            removeOK=false;
            iteratorendringer++;
            endringer++;
            antall--;
        }

    } // BladnodeIterator

} // ObligSBinTre
