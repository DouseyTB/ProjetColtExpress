package ColtExpress.Elements;

import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IPersonnage;
import ColtExpress.Interface.IObservable;

public class Marshall implements IPersonnage, IObservable {

    private Position _position;

    public Marshall()
    {
        _position = new Position(0, true);
    }

    public String Name()
    {
        return "Marshall";
    }

    public Position GetPosition()
    {
        return _position;
    }

    public void Update()
    {
        // TODO
    }

}
