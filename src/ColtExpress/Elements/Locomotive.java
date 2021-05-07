package ColtExpress.Elements;

import ColtExpress.Interface.IWagon;

import java.util.ArrayList;

public class Locomotive implements IWagon {

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
