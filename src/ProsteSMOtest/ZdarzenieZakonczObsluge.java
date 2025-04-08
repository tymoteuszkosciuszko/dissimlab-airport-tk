package ProsteSMOtest;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventBarrier;

public class ZdarzenieZakonczObsluge extends BasicSimEvent<Smo, Zgloszenie>
{
    private Smo smoParent;

    public ZdarzenieZakonczObsluge(Smo parent, double delay, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
        this.smoParent = parent;
    }

    public ZdarzenieZakonczObsluge(Smo parent, SimEventBarrier semafor, Zgloszenie zgl) throws SimControlException
    {
    	super(parent, semafor, zgl);
        this.smoParent = parent;
    }
  
	@Override
	protected void onTermination() throws SimControlException {		
		System.out.println(simTimeFormatted()+": Terminate zdarzenia ZakończObsluge na semaforze - dla zgl. nr: " + eventParams.getKolejnyNr());
	}

	@Override
	protected void stateChange() throws SimControlException {
        System.out.println(simTimeFormatted()+": SMO-Koniec obsługi zgl. nr: " + eventParams.getKolejnyNr());
		smoParent.setWolne(true);
		// Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
        if (smoParent.liczbaZgl() > 0)
        {
        	smoParent.rozpocznijObsluge = new ZdarzenieRozpocznijObsluge(smoParent);        	
        }		
	}

	@Override
	public Zgloszenie getEventParams() {
		return null;
	}
}