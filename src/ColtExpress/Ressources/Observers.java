package ColtExpress.Ressources;

import ColtExpress.Interface.IObservable;

import java.util.ArrayList;

public class Observers {

    private ArrayList<IObservable> _elements;

    public Observers()
    {
        _elements = new ArrayList<IObservable>();
    }

    public void AddObserver(IObservable o) throws IllegalArgumentException
    {
        if (!_elements.contains(o))
            _elements.add(o);
        else
            throw new IllegalArgumentException("This elements has already been added here");
    }

    public void NotifyObservers()
    {
        for (IObservable o : _elements)
        {
            o.Update();
        }
    }
}
