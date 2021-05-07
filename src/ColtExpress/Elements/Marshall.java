package ColtExpress.Elements;

import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IPersonnage;
import ColtExpress.Interface.IObservable;

public class Marshall implements IPersonnage, IObservable {

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
