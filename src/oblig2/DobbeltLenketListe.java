package oblig2;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class DobbeltLenketListe<T> implements Liste<T>
{
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) // konstruktør
        {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi)  // konstruktør
        {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    // hjelpemetode
    private Node<T> finnNode(int indeks)
    {
        Node<T> p;

        if (indeks < antall / 2)
        {
            p = hode;  // leter fra hode mot høyre
            for (int i = 0; i < indeks; i++)
            {
                p = p.neste;
            }
        }
        else
        {
            p = hale;  // leter fra hale mot venstre
            for (int i = antall - 1; i > indeks; i--)
            {
                p = p.forrige;
            }
        }
        return p;
    }

    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                             // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public DobbeltLenketListe()  // standardkonstruktør
    {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a)   // konstruktør
    {
        this();  // bruker standardkonstruktøren

        Objects.requireNonNull(a, "a er null!");

        hode = hale = new Node<>(null);  // en midlertidig node

        for (T verdi : a)
        {
            if (verdi != null)
            {
                hale = hale.neste = new Node<>(verdi, hale, null);  // ny node bakerst
                antall++;
            }
        }

        // fjerner den midlertidige noden
        if (antall == 0) hode = hale = null;
        else (hode = hode.neste).forrige = null;

    }

    public Liste<T> subliste(int fra, int til)
    {
        fratilKontroll(antall, fra, til);  // sjekker intevallet

        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>();  // ny liste

        Node<T> p = finnNode(fra);        // finner noden med indeks lik fra

        for (int i = fra; i < til; i++)   // henter verdiene i [fra:til>
        {
            liste.leggInn(p.verdi);
            p = p.neste;
        }

        return liste;
    }

    @Override
    public int antall()
    {
        return antall;
    }

    @Override
    public boolean tom()
    {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        Node<T> p = new Node<>(verdi, hale, null);
        hale = tom() ? (hode = p) : (hale.neste = p);

        antall++;
        endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi)
    {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);

        if (tom())                              // tom liste
        {
            hode = hale = new Node<>(verdi, null, null);
        }
        else if (indeks == 0)                   // ny verdi forrest
        {
            hode = hode.forrige = new Node<>(verdi, null, hode);
        }
        else if (indeks == antall)              // ny verdi bakerst
        {
            hale = hale.neste = new Node<>(verdi, hale, null);
        }
        else                                    // ny verdi på plass indeks
        {
            Node<T> p = finnNode(indeks);     // ny verdi skal til venstre for p
            p.forrige = p.forrige.neste = new Node<>(verdi, p.forrige, p);
        }

        antall++;            // ny verdi i listen
        endringer++;   // en endring i listen
    }

    @Override
    public boolean inneholder(T verdi)
    {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks)
    {
        indeksKontroll(indeks, false);

        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi)
    {
        if (verdi == null) return -1;

        Node<T> p = hode;

        for (int indeks = 0; indeks < antall; indeks++, p = p.neste)
        {
            if (p.verdi.equals(verdi)) return indeks;
        }

        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi)
    {
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");
        indeksKontroll(indeks, false);

        Node<T> p = finnNode(indeks);

        T gammelverdi = p.verdi;
        p.verdi = nyverdi;

        endringer++;

        return gammelverdi;
    }

    private T fjernNode(Node<T> p)  // private hjelpemetode
    {
        if (p == hode)
        {
            if (antall == 1) hode = hale = null;      // kun en verdi i listen
            else (hode = hode.neste).forrige = null;  // fjerner den første
        }
        else if (p == hale) (hale = hale.forrige).neste = null;  // fjerner den siste
        else (p.forrige.neste = p.neste).forrige = p.forrige;    // fjerner p

        antall--;     // en mindre i listen
        endringer++;  // en endring

        return p.verdi;
    }

    @Override
    public boolean fjern(T verdi)
    {
        if (verdi == null) return false;  // ingen nullverdier i listen

        for (Node<T> p = hode; p != null; p = p.neste)
        {
            if (p.verdi.equals(verdi))
            {
                fjernNode(p);   // bruker den private hjelpemetoden
                return true;
            }
        }
        return false;  // verdi ligger ikke i listen
    }

    @Override
    public T fjern(int indeks)
    {
        indeksKontroll(indeks, false);

        return fjernNode(finnNode(indeks)); // bruker de to hjelpemetodene
    }

    @Override
    public void nullstill()
    {
        Node<T> p = hode;
        while (p != null)
        {
            Node<T> q = p.neste;
            p.verdi = null;
            p.forrige = null;
            p.neste = null;
            p = q;
        }

        hode = hale = null;

        antall = 0;
        endringer++;
    }

    // Det viser seg at det er liten forskjell i effektivitet
    // mellom nullstill() slik den er koden ovenfor og slik
    // den er kodet under (som nullstill2)

    public void nullstill2()
    {
        while (antall > 0) fjern(0);
    }

    @Override
    public String toString()
    {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Node<T> p = hode; p != null; p = p.neste) sj.add(p.verdi.toString());
        return sj.toString();
    }

    public String omvendtString()
    {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Node<T> p = hale; p != null; p = p.forrige) sj.add(p.verdi.toString());
        return sj.toString();
    }


    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
    {

    }

    @Override
    public Iterator<T> iterator()
    {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks)
    {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator()
        {
            denne = hode;     // denne starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks)
        {
            denne=finnNode(indeks);
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext()
        {
            return denne != null;  // denne koden skal ikke endres!
        }

        @Override
        public T next()
        {
            if (iteratorendringer!=endringer){
                throw new ConcurrentModificationException();
            }
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            fjernOK = true;
            T temp=denne.verdi;
            denne=denne.neste;
            return temp;
        }


        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke laget ennå!");
        }

    } // DobbeltLenketListeIterator




} // DobbeltLenketListe
