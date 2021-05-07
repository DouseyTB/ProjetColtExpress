package ColtExpress.Elements;

import ColtExpress.Ressources.Position;
import ColtExpress.Interface.IPersonnage;
import ColtExpress.Interface.IObservable;
import java.util.Random;

public class Voyageur implements IPersonnage, IObservable {

    private int bourse;
    private int nbBijoux;

    private Position pos;


    public Voyageur(Position pos)
    {
        Random rand = new Random();                // This is used to generated random values for _bourse and _nombreBijoux

        this.pos = pos;
        bourse = rand.nextInt(501);        // Sets a random value between [0;501[
        nbBijoux = rand.nextInt(4);    // Sets a random value between [0;4[
    }


    /**
     * Takes the amount of money that the Voyageur has
     * @return The value of _bourse
     */
    public int GetBourse()
    {
        int res = bourse;
        bourse = 0;
        return res;
    }

    /**
     * @return 500 if the Voyageur does have jewels (and decrement _nombreBijoux), 0 otherwise
     */
    public int GetBijoux()
    {
        if (nbBijoux == 0)
            return 0;

        nbBijoux--;
        return 500;
    }

    public String Name()
    {
        return "Voyageur";
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
