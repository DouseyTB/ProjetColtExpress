package ColtExpress.Elements;

import ColtExpress.ColtExpress;
import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IPersonnage;
import ColtExpress.Ressources.Direction;

import ColtExpress.Interface.IObservable;

public class Bandit implements IPersonnage, IObservable {

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
