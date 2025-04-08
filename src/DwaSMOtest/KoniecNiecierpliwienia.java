package DwaSMOtest;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * 
 * @author Dariusz Pierzchala

 */
public class KoniecNiecierpliwienia extends BasicSimEvent<Zgloszenie, Object>
{
    private RNGenerator generator;
    private Zgloszenie parent;

    public KoniecNiecierpliwienia(Zgloszenie parent2, double delay) throws SimControlException
    {
    	super(parent2, delay);
    	generator = new RNGenerator();
        this.parent = parent2;
    }

    public KoniecNiecierpliwienia(Zgloszenie parent) throws SimControlException
    {
    	super(parent);
    	generator = new RNGenerator();
        this.parent = parent;
    }
    
	@Override
	protected void onTermination() throws SimControlException {
        System.out.println(simTimeFormatted()+": Przerwanie niecierpliwości zgl. nr: " + parent.getTenNr());
	}

	@Override
	protected void stateChange() throws SimControlException {
        System.out.println(simTimeFormatted()+": Koniec niecierpliwości zgl. nr: " + parent.getTenNr());
        if (parent.smo.usunWskazany(parent)){
            System.out.println(simTimeFormatted()+": Usunięto z kolejki zgl. nr: " + parent.getTenNr());
            double lutrac = parent.smo.MVutraconeZgl.getValue();
            parent.smo.MVutraconeZgl.setValue(lutrac++);
        }
        else
            System.out.println(simTimeFormatted()+": Problem z usunięciem z kolejki zgl. nr: " + parent.getTenNr());       	
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}