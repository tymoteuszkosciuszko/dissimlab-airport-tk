package ProsteSMOtest;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * 
 * @author Dariusz Pierzchala

 */
public class ZdarzenieKoniecNiecierpliwienia extends BasicSimEvent<Zgloszenie, Object>
{
    private RNGenerator generator;
    private Zgloszenie parent;

    public ZdarzenieKoniecNiecierpliwienia(Zgloszenie parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new RNGenerator();
        this.parent = parent;
    }

    public ZdarzenieKoniecNiecierpliwienia(Zgloszenie parent) throws SimControlException
    {
    	super(parent);
    	generator = new RNGenerator();
        this.parent = parent;
    }
    
	@Override
	protected void onTermination() throws SimControlException {
        System.out.println(simTimeFormatted()+": Przerwanie niecierpliwienia zgłoszenia nr: " + parent.getKolejnyNr());
	}

	@Override
	protected void stateChange() throws SimControlException {
        System.out.println(simTimeFormatted()+": Koniec niecierpliwości zgłoszenia nr: " + parent.getKolejnyNr());
        if (parent.smo.usunWskazany(parent)){
            System.out.println(simTimeFormatted()+": Usunięto z kolejki zgłoszenia nr: " + parent.getKolejnyNr());
            double lutrac = parent.smo.MVutraconeZgl.getValue();
            parent.smo.MVutraconeZgl.setValue(lutrac++);
        }
        else
            System.out.println(simTimeFormatted()+": Problem z usunięciem z kolejki zgłoszenia nr: " + parent.getKolejnyNr());       	
	}

	@Override
	public Object getEventParams() {
		return null;
	}
}