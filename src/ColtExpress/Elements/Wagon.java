package ColtExpress.Elements;

import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IWagon;

import java.util.ArrayList;
import java.util.Random;

public class Wagon implements IWagon {

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
