package ColtExpress.Elements;

import ColtExpress.ColtExpress;
import ColtExpress.Ressources.*;
import ColtExpress.Interface.*;
import ColtExpress.Interface.IObservable;
import ColtExpress.Ressources.Observers;

import java.util.ArrayList;
import java.util.Random;

public class Train extends Observers {

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
