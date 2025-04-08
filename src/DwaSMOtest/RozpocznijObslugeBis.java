package DwaSMOtest;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie początkowe aktywności gniazda obsługi. Rozpoczyna obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RozpocznijObslugeBis extends BasicSimEvent<SmoBis, Zgloszenie>
{
    private SmoBis smoParent;
    private RNGenerator generator;

    public RozpocznijObslugeBis(SmoBis parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new RNGenerator();
        this.smoParent = parent;
    }

    public RozpocznijObslugeBis(SmoBis parent) throws SimControlException
    {
    	super(parent);
    	generator = new RNGenerator();
        this.smoParent = parent;
    }

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
        if (smoParent.liczbaZgl() > 0)
        {
        	// Zablokuj gniazdo
        	smoParent.setWolne(false);
        	// Pobierz zgłoszenie
        	Zgloszenie zgl = smoParent.usun();
        	//Otwarcie semafora blokującego gniazdo 1-sze     	
        	if (smoParent.liczbaZgl() == smoParent.getMaxDlKolejki() - 1) {
        		try {
                    System.out.println(simTimeFormatted()+": SMO2- otwarcie semafora - zwolnienie: " + smoParent.getSemafor().readFirstBlocked().toString());					
				} catch (Exception e) {
				}
        		smoParent.getSemafor().open();
        	}
        	// Wygeneruj czas obsługi
        	double czasObslugi = generator.normal(30.0, 1.0);
            // Zapamiętaj dane monitorowane
            smoParent.MVczasy_obslugi.setValue(czasObslugi);
            smoParent.MVczasy_oczekiwania.setValue(simTime()
                    - zgl.getCzasOdniesienia());
            System.out.println(simTimeFormatted()+": SMO2-Początek obsługi zgl. nr: " + zgl.getTenNr());
        	// Zaplanuj koniec obsługi
        	smoParent.zakonczObsluge = new ZakonczObslugeBis(smoParent, czasObslugi, zgl); 
        	//Oznaczenie zdarzenia do opublikowania w obiekcie Dispatcher
        	smoParent.zakonczObsluge.setPublishable(true);
        }
		
	}

	@Override
	public Zgloszenie getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}