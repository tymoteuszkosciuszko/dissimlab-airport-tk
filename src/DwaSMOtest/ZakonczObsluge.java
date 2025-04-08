package DwaSMOtest;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventBarrier;

public class ZakonczObsluge extends BasicSimEvent<Smo, Zgloszenie>
{
    private Smo smoParent;

    public ZakonczObsluge(Smo parent, double delay, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
        this.smoParent = parent;
    }

    public ZakonczObsluge(Smo parent, SimEventBarrier semafor, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, semafor, zgl);
        this.smoParent = parent;
    }
  
	@Override
	protected void onTermination() throws SimControlException {		
		System.out.println(simTimeFormatted()+": Terminate zdarzenia ZakończObsluge na semaforze - dla zgl. nr: " + eventParams.getTenNr());
	}

	@Override
	protected void stateChange() throws SimControlException {
        if (smoParent.getSmo2().dodaj(eventParams)) {
    		smoParent.setWolne(true);
            System.out.println(simTimeFormatted()+": Koniec obsługi zgl. nr: " + eventParams.getTenNr());
            smoParent.MVczasy_obslugi.setValue(simTime() - eventParams.getCzasOdniesienia(), simTime());
        	
            if (smoParent.getSmo2().liczbaZgl()==1 && smoParent.getSmo2().isWolne()) {
            	smoParent.getSmo2().rozpocznijObsluge = new RozpocznijObslugeBis(smoParent.getSmo2());
            }
        	// Zaplanuj dalsza obsługe w tym gnieździe
        	if (smoParent.liczbaZgl() > 0)
        	{
        		smoParent.rozpocznijObsluge = new RozpocznijObsluge(smoParent);        	
        	}	
        } else {
            System.out.println(simTimeFormatted()+": Oczekiwanie na semaforze - zgl. nr: " + eventParams.getTenNr());
        	smoParent.zakonczObsluge = new ZakonczObsluge(smoParent, smoParent.getSemafor(), eventParams);        	
        }
	}

	@Override
	public Zgloszenie getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}