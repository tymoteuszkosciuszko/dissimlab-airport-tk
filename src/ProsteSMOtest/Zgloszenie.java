package ProsteSMOtest;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

/**
 * Description: Klasa zgloszenia obsługiwanego w gnieździe obsługi.
 * 
 * @author Dariusz Pierzchala
 */

public class Zgloszenie extends BasicSimObj
{
    double czasUtworzenia;
    static int nr=0;
    int kolejnyNr;
    public Smo smo;
    public ZdarzenieKoniecNiecierpliwienia zdarzenieKoniecNiecierpliwienia;

    public int getKolejnyNr() {
		return kolejnyNr;
	}

	public void setKolejnyNr() {
		this.kolejnyNr = nr++;
	}

	public Zgloszenie(double Czas, Smo smo) throws SimControlException
    {
        czasUtworzenia = Czas;
        setKolejnyNr();
        this.smo = smo;
        ZdarzenieStartNiecierpliwienia stN = new ZdarzenieStartNiecierpliwienia(this);
    }

    public void setCzasOdniesienia(double t)
    {
        czasUtworzenia = t;
    }

    public double getCzasOdniesienia()
    {
        return czasUtworzenia;
    }

	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		return false;
	}
}