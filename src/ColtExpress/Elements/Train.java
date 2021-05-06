package ColtExpress.Elements;

import ColtExpress.ColtExpress;
import ColtExpress.Elements.*;
import ColtExpress.Ressources.*;
import ColtExpress.Interface.*;
import ColtExpress.Interface.IObservable;
import ColtExpress.Ressources.Observers;

import java.util.ArrayList;
import java.util.Random;

public class Train extends Observers {

    public static int LONGUEUR = 60, LARGEUR = 40;

    private ArrayList<IWagon> _elements;
    private ArrayList<IPersonnage> _personnages;
    private Random _random;

    public Train()
    {
        super();

        _elements = new ArrayList<IWagon>();
        _personnages = new ArrayList<IPersonnage>();
        _random = new Random();

        IWagon first = new Locomotive();
        _elements.add(first);

        for (int i = 1; i < ColtExpress.NB_WAGONS; i++)
        {
            IWagon next = new Wagon(i);
            _elements.add(next);
        }


        IPersonnage marshall = new Marshall();
        _personnages.add(marshall);

        for (int i = 0; i < ColtExpress.NB_BANDITS; i++)
        {
            int position = _random.nextInt(ColtExpress.NB_WAGONS);
            IPersonnage next = new Bandit(String.format("Bandit %s", i), new Position(position, true));
            AddObserver((IObservable) next);
        }

        for (int i = 0; i < ColtExpress.NB_VOYAGEURS; i++)
        {
            int position = _random.nextInt(ColtExpress.NB_WAGONS);
            IPersonnage next = new Voyageur(new Position(position, true));
            AddObserver((IObservable) next);
        }
    }
}
