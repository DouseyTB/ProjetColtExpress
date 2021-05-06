package ColtExpress.Elements;

import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IWagon;

import java.util.ArrayList;

public class Locomotive implements IWagon {

    public static int LONGUEUR = 70, LARGEUR = 40;

    private ArrayList<Bandit> _bandits;

    private final int _identifiant = 0;
    private boolean _isMarshall = true;

    private boolean _isMagot = true;

    public Locomotive()
    {
        _bandits = new ArrayList<Bandit>();
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

    public int GetMagot()
    {
        if (_isMarshall || !_isMagot)
            return 0;

        _isMagot = false;
        return 1000;
    }
}
