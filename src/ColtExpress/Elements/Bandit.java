package ColtExpress.Elements;

import ColtExpress.ColtExpress;
import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IPersonnage;
import ColtExpress.Ressources.Direction;

import ColtExpress.Interface.IObservable;

public class Bandit implements IPersonnage, IObservable {

    private final String _name;
    private Position _position;
    private int _butin = 0;

    public Bandit(String name, Position pos)
    {
        _name = name;
        _position = pos;
    }

    public String Name()
    {
        return _name;
    }

    public Position GetPosition()
    {
        return _position;
    }

    public void AddButin(int value)
    {
        _butin += value;
    }

    public int GetButin()
    {
        return _butin;
    }

    public void ResetButin()
    {
        _butin = 0;
    }

    public boolean Move(Direction dir)
    {
        switch (dir)
        {
            case HAUT:
                if (_position.IsInsideWagon())
                {
                    _position.GoOutside();
                    return true;
                }
                else
                    return false;


            case BAS:
                if (!_position.IsInsideWagon())
                {
                    _position.GoInside();
                    return true;
                }
                else
                    return false;

            case AVANT:
                if (_position.GetWagon()  < ColtExpress.NB_WAGONS)
                {
                    _position.SetWagon(_position.GetWagon() + 1);
                    return true;
                }
                else
                    return false;

            case ARRIERE:
                if (_position.GetWagon() > 0)
                {
                    _position.SetWagon(_position.GetWagon() - 1);
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
