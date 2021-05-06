package ColtExpress.Ressources;

import ColtExpress.ColtExpress;

public class Position {

    private int _idWagon;       // Identifier of the train-car in which the element is located
    private boolean _isInside;  // Defines whether or not the element in inside the train-car

    public Position(int idWagon, boolean isInside)
    {
        _idWagon = idWagon;
        _isInside = isInside;
    }

    /**
     * @return The identifier of the train-car in which the element is located
     */
    public int GetWagon()
    {
        return _idWagon;
    }

    /**
     * @return true if the element is located inside the train-car, false otherwise
     */
    public boolean IsInsideWagon()
    {
        return _isInside;
    }

    /**
     * Changes the identifier of the train-car in which the element is located
     * @param value the new identifier
     * @return true if the given value is correct and has been updated, false otherwise
     */
    public boolean SetWagon(int value)
    {
        if (value < 0 || value >= ColtExpress.NB_WAGONS)
            return false;

        _idWagon = value;
        return true;
    }

    /**
     * Changes the _isInside parameter
     * @return true if the element can go outside, false otherwise
     */
    public boolean GoOutside()
    {
        if (_isInside)
        {
            _isInside = false;
            return true;
        }
        else
            return false;
    }

    /**
     * Changes the _isInside parameter
     * @return true is the element can go inside, false otherwise
     */
    public boolean GoInside()
    {
        if (!_isInside)
        {
            _isInside = true;
            return true;
        }
        else
            return false;
    }
}
