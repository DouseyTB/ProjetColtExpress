package ColtExpress.Elements;

import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IWagon;

import java.util.ArrayList;
import java.util.Random;

public class Wagon implements IWagon {

    private ArrayList<Voyageur> _voyageurs;
    private ArrayList<Bandit> _bandits;

    private final int _identifiant;
    private boolean _isMarshall = false;


    public Wagon(int id)
    {
        _identifiant = id;
        _voyageurs = new ArrayList<Voyageur>();
        _bandits = new ArrayList<Bandit>();

        Random rand = new Random();                     // This is used to generate a random number of Voyageur
        int nbVo = rand.nextInt(4) + 1;          // We decide that they will be at least one Voyageur, and a maximum of 4

        for (int i = 0; i < nbVo; i++)
        {
            _voyageurs.add(new Voyageur(new Position(_identifiant, true)));
        }
    }

    private boolean ContainsBandit(Bandit element)
    {
        for (Bandit b : _bandits)
            if (b.Name() == element.Name())
                return true;

        return false;
    }


    public void AddBandit(Bandit element)
    {
        if (!ContainsBandit(element))
            _bandits.add(element);
    }

    public boolean RemoveBandit(Bandit element)
    {
        if (ContainsBandit(element))
        {
            _bandits.remove(element);
            return true;
        }
        else
            return false;
    }

    public boolean IsMarshall()
    {
        return _isMarshall;
    }

    public void AddMarshall()
    {
        _isMarshall = true;
    }

    public void RemoveMarshall()
    {
        _isMarshall = false;
    }
}
