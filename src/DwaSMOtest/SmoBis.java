package DwaSMOtest;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Klasa gniazda obsługi obiektów - zgłoszeń 
 */

import java.util.LinkedList;

import DwaSMOtest.RozpocznijObslugeBis;
import DwaSMOtest.ZakonczObslugeBis;
import DwaSMOtest.Zgloszenie;
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventBarrier;



public class SmoBis extends BasicSimObj 
{
    private LinkedList <Zgloszenie> kolejka;
    private boolean wolne = true;
    private int maxDlKolejki;
    //
	SimEventBarrier semafor;
	//
    public RozpocznijObslugeBis rozpocznijObsluge;
    public ZakonczObslugeBis zakonczObsluge;
    //public OdblokujGniazdo odblokuj; 
    //
    public MonitoredVar MVczasy_obslugi;
    public MonitoredVar MVczasy_oczekiwania;
    public MonitoredVar MVdlKolejki;

    /** Creates a new instance of SmoBis 
     * @throws SimControlException */
    public SmoBis(int maxDlKolejki, SimEventBarrier semafor) throws SimControlException
    {
        kolejka = new LinkedList <Zgloszenie>();
        this.maxDlKolejki = maxDlKolejki;
        this.semafor = semafor;
        //
        // Deklaracja zmiennych monitorowanych
        MVczasy_obslugi = new MonitoredVar();
        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
    }

    // Wstawienie zgłoszenia do kolejki
    public boolean dodaj(Zgloszenie zgl)
    {
    	if(liczbaZgl() < maxDlKolejki){
    		kolejka.add(zgl);
    		MVdlKolejki.setValue(kolejka.size());
    		return true;
    	}
        return false;
    }

    // Pobranie zgłoszenia z kolejki
    public Zgloszenie usun()
    {
    	Zgloszenie zgl = (Zgloszenie) kolejka.removeFirst();
        MVdlKolejki.setValue(kolejka.size());
        return zgl;
    }

    // Pobranie zgłoszenia z kolejki
    public boolean usunWskazany(Zgloszenie zgl)
    {
    	Boolean b= kolejka.remove(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return b;
    }
    
    public int liczbaZgl()
    {
        return kolejka.size();
    }

	public boolean isWolne() {
		return wolne;
	}

	public void setWolne(boolean wolne) {
		this.wolne = wolne;
	}
	
	public SimEventBarrier getSemafor() {
		return semafor;
	}

	public void setSemafor(SimEventBarrier semafor) {
		this.semafor = semafor;
	}
	public int getMaxDlKolejki() {
		return maxDlKolejki;
	}

	public void setMaxDlKolejki(int maxDlKolejki) {
		this.maxDlKolejki = maxDlKolejki;
	}

	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}