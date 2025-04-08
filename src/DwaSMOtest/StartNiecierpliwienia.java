package DwaSMOtest;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * 
 * @author Dariusz Pierzchala
 * Description: Zdarzenie początkowe niecierpliwości zgłoszenia. Rozpoczyna niecierpliwość przez losowy czas
 */
public class StartNiecierpliwienia extends BasicSimEvent<Zgloszenie, Object>
{
    private RNGenerator generator;
    private Zgloszenie parent;

    public StartNiecierpliwienia(Zgloszenie parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new RNGenerator();
        this.parent = parent;
    }

    public StartNiecierpliwienia(Zgloszenie zgloszenie) throws SimControlException
    {
    	super(zgloszenie);
    	generator = new RNGenerator();
        this.parent = zgloszenie;
    } 

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
        System.out.println(simTimeFormatted()+": Początek niecierpliwości zgl. nr: " + parent.getTenNr());
        double odstep = generator.normal(15.0, 1.0);
        parent.koniecNiecierpliwosci = new KoniecNiecierpliwienia(parent, odstep);
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}