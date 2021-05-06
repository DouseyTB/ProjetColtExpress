package ColtExpress.Interface;

import ColtExpress.Elements.Bandit;

public interface IWagon {

    public void AddBandit(Bandit element);

    public boolean RemoveBandit(Bandit element);

    public boolean IsMarshall();

    public void AddMarshall();

    public void RemoveMarshall();
}
