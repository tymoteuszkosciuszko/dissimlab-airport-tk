package ProsteSMOtest;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * 
 * @author Dariusz Pierzchala
 * Description: Zdarzenie początkowe niecierpliwości zgłoszenia. Rozpoczyna niecierpliwość przez losowy czas
 */
public class ZdarzenieStartNiecierpliwienia extends BasicSimEvent<Zgloszenie, Object>
{
    private RNGenerator generator;
    private Zgloszenie parent;

    public ZdarzenieStartNiecierpliwienia(Zgloszenie parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new RNGenerator();
        this.parent = parent;
    }

    public ZdarzenieStartNiecierpliwienia(Zgloszenie parent) throws SimControlException
    {
    	super(parent);
    	generator = new RNGenerator();
        this.parent = parent;
    } 

	@Override
	protected void onTermination() throws SimControlException {
	}

	@Override
	protected void stateChange() throws SimControlException {
        System.out.println(simTimeFormatted()+": Początek niecierpliwości zgłoszenia nr: " + parent.getKolejnyNr());
        double odstep = generator.normal(15.0, 1.0);
        parent.zdarzenieKoniecNiecierpliwienia = new ZdarzenieKoniecNiecierpliwienia(parent, odstep);
	}

	@Override
	public Object getEventParams() {
		return null;
	}
}