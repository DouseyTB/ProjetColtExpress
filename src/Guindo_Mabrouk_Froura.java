import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

class ColtExpress {

    public static final int NB_WAGONS = 4;
    public static final int NB_BANDITS = 2;
    public static final int NB_VOYAGEURS = 6;

    public static void main(String[] args)
    {

    }


}

class VueTrain extends JPanel {
    private Observers observer;

    public VueTrain()
    {

    }
}

class Marshall implements IPersonnage, IObservable {

    private Position pos;

    public Marshall()
    {
        pos = new Position(0, true);
    }

    public String Name()
    {
        return "Marshall";
    }

    public Position GetPosition()
    {
        return pos;
    }

    public void Update()
    {
        // TODO
    }

}

class Bandit implements IPersonnage, IObservable {

    private final String nom;
    private Position pos;
    private int argent = 0;

    public Bandit(String name, Position pos)
    {
        nom = name;
        this.pos = pos;
    }

    public String Name()
    {
        return nom;
    }

    public Position GetPosition()
    {
        return pos;
    }

    public void AddArgent(int value)
    {
        argent += value;
    }

    public int GetArgent()
    {
        return argent;
    }

    public void ResetArgent()
    {
        argent = 0;
    }

    public boolean Move(Direction dir)
    {
        switch (dir)
        {
            case HAUT:
                if (pos.IsInsideWagon())
                {
                    pos.GoOutside();
                    return true;
                }
                else
                    return false;


            case BAS:
                if (!pos.IsInsideWagon())
                {
                    pos.GoInside();
                    return true;
                }
                else
                    return false;

            case AVANT:
                if (pos.GetWagon()  < ColtExpress.NB_WAGONS)
                {
                    pos.SetWagon(pos.GetWagon() + 1);
                    return true;
                }
                else
                    return false;

            case ARRIERE:
                if (pos.GetWagon() > 0)
                {
                    pos.SetWagon(pos.GetWagon() - 1);
                    return true;
                }
                else
                    return false;

            default:
                return false;           // Non-reachable statement (just here to avoid compilation error)
        }
    }

    public void Update()
    {
        // TODO
    }
}


interface IPersonnage {

    public String Name();

    public Position GetPosition();
}

interface IObservable {

    public void Update();
}

interface IWagon {

    public void AddBandit(Bandit element);

    public boolean RemoveBandit(Bandit element);

    public boolean IsMarshall();

    public void AddMarshall();

    public void RemoveMarshall();
}


class Train extends Observers {

    public static int LONGUEUR = 60, LARGEUR = 40;

    private ArrayList<IWagon> elem;
    private ArrayList<IPersonnage> characters;
    private Random random;

    public Train() {
        super();
        elem = new ArrayList<IWagon>();
        characters = new ArrayList<IPersonnage>();
        random = new Random();

        IWagon first = new Locomotive();
        elem.add(first);

        for (int i = 1; i < ColtExpress.NB_WAGONS; i++)
        {
            IWagon next = new Wagon(i);
            elem.add(next);
        }


        IPersonnage marshall = new Marshall();
        characters.add(marshall);

        for (int i = 0; i < ColtExpress.NB_BANDITS; i++)
        {
            int position = random.nextInt(ColtExpress.NB_WAGONS);
            IPersonnage next = new Bandit(String.format("Bandit %s", i), new Position(position, true));
            AddObserver((IObservable) next);
        }

        for (int i = 0; i < ColtExpress.NB_VOYAGEURS; i++)
        {
            int position = random.nextInt(ColtExpress.NB_WAGONS);
            IPersonnage next = new Voyageur(new Position(position, true));
            AddObserver((IObservable) next);
        }
    }
}

class Locomotive implements IWagon {

    public static int LONGUEUR = 70, LARGEUR = 40;

    private ArrayList<Bandit> bandits;

    private final int id = 0;
    private boolean isMarshall = true;

    private boolean isMagot = true;

    public Locomotive()
    {
        bandits = new ArrayList<Bandit>();
    }

    private boolean ContainsBandit(Bandit element)
    {
        for (Bandit b : bandits)
            if (b.Name() == element.Name())
                return true;

        return false;
    }

    public void AddBandit(Bandit element)
    {
        if (!ContainsBandit(element))
            bandits.add(element);
    }

    public boolean RemoveBandit(Bandit element)
    {
        if (ContainsBandit(element))
        {
            bandits.remove(element);
            return true;
        }
        else
            return false;
    }

    public boolean IsMarshall()
    {
        return isMarshall;
    }

    public void AddMarshall()
    {
        isMarshall = true;
    }

    public void RemoveMarshall()
    {
        isMarshall = false;
    }

    public int GetMagot()
    {
        if (isMarshall || !isMagot)
            return 0;

        isMagot = false;
        return 1000;
    }
}

class Observers {

    private ArrayList<IObservable> elements;

    public Observers()
    {
        elements = new ArrayList<IObservable>();
    }

    public void AddObserver(IObservable o) throws IllegalArgumentException
    {
        if (!elements.contains(o))
            elements.add(o);
        else
            throw new IllegalArgumentException("This elements has already been added here");
    }

    public void NotifyObservers()
    {
        for (IObservable o : elements)
        {
            o.Update();
        }
    }
}


class Wagon implements IWagon {

    private ArrayList<Voyageur> travellers;
    private ArrayList<Bandit> bandits;

    private final int id;
    private boolean isMarshall = false;


    public Wagon(int id)
    {
        this.id = id;
        travellers = new ArrayList<Voyageur>();
        bandits = new ArrayList<Bandit>();

        Random rand = new Random();                     // This is used to generate a random number of Voyageur
        int nbVo = rand.nextInt(4) + 1;          // We decide that they will be at least one Voyageur, and a maximum of 4

        for (int i = 0; i < nbVo; i++)
        {
            travellers.add(new Voyageur(new Position(this.id, true)));
        }
    }

    private boolean ContainsBandit(Bandit element)
    {
        for (Bandit b : bandits)
            if (b.Name() == element.Name())
                return true;

        return false;
    }


    public void AddBandit(Bandit element)
    {
        if (!ContainsBandit(element))
            bandits.add(element);
    }

    public boolean RemoveBandit(Bandit element)
    {
        if (ContainsBandit(element))
        {
            bandits.remove(element);
            return true;
        }
        else
            return false;
    }

    public boolean IsMarshall()
    {
        return isMarshall;
    }

    public void AddMarshall()
    {
        isMarshall = true;
    }

    public void RemoveMarshall()
    {
        isMarshall = false;
    }
}

class Position {

    private int idWagon;       // Identifier of the train-car in which the element is located
    private boolean isInside;  // Defines whether or not the element in inside the train-car

    public Position(int idWagon, boolean isInside)
    {
        this.idWagon = idWagon;
        this.isInside = isInside;
    }

    /**
     * @return The identifier of the train-car in which the element is located
     */
    public int GetWagon()
    {
        return idWagon;
    }

    /**
     * @return true if the element is located inside the train-car, false otherwise
     */
    public boolean IsInsideWagon()
    {
        return isInside;
    }

    /**
     * Changes the identifier of the train-car in which the element is located
     * @param value the new identifier
     * @return true if the given value is correct and has been updated, false otherwise
     */
    public boolean SetWagon(int value)
    {
        if (value < 0 || value >= ColtExpress.NB_WAGONS)
            return false;

        idWagon = value;
        return true;
    }

    /**
     * Changes the _isInside parameter
     * @return true if the element can go outside, false otherwise
     */
    public boolean GoOutside()
    {
        if (isInside)
        {
            isInside = false;
            return true;
        }
        else
            return false;
    }

    /**
     * Changes the _isInside parameter
     * @return true is the element can go inside, false otherwise
     */
    public boolean GoInside()
    {
        if (!isInside)
        {
            isInside = true;
            return true;
        }
        else
            return false;
    }
}

enum Direction {
    AVANT,
    ARRIERE,
    HAUT,
    BAS;
}

class Voyageur implements IPersonnage, IObservable {

    private int bourse;
    private int nbBijoux;

    private Position pos;


    public Voyageur(Position pos)
    {
        Random rand = new Random();                // This is used to generated random values for _bourse and _nombreBijoux

        this.pos = pos;
        bourse = rand.nextInt(501);        // Sets a random value between [0;501[
        nbBijoux = rand.nextInt(4);    // Sets a random value between [0;4[
    }


    /**
     * Takes the amount of money that the Voyageur has
     * @return The value of _bourse
     */
    public int GetBourse()
    {
        int res = bourse;
        bourse = 0;
        return res;
    }

    /**
     * @return 500 if the Voyageur does have jewels (and decrement _nombreBijoux), 0 otherwise
     */
    public int GetBijoux()
    {
        if (nbBijoux == 0)
            return 0;

        nbBijoux--;
        return 500;
    }

    public String Name()
    {
        return "Voyageur";
    }

    public Position GetPosition()
    {
        return pos;
    }

    public void Update()
    {
        // TODO
    }
}
