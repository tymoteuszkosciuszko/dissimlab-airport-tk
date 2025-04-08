package ProsteSMOtest;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

/**
 * Zdarzenie obsługujące generowanie zgłoszeń. Tworzy obiekt zgłoszenia co losowy czas.
 * 
 * @author Dariusz Pierzchala

 */
public class ZdarzenieZglaszaj extends BasicSimEvent<Otoczenie, Object>
{
    private RNGenerator rng;
    private Otoczenie otoczenie;
    private double mean; 
    private double stdev;

    public ZdarzenieZglaszaj(Otoczenie parent, double delay, double mean, double stdev) throws SimControlException
    {
    	super(parent, delay);
    	rng = new RNGenerator();
    	this.mean = mean;
    	this.stdev = stdev;
    	this.otoczenie = parent;
    }

	@Override
	protected void onTermination() throws SimControlException {
	}

	@Override
	protected void stateChange() throws SimControlException {
        Zgloszenie zgl = new Zgloszenie(simTime(), otoczenie.smo);
        otoczenie = getSimObj();
        otoczenie.smo.dodaj(zgl);
        System.out.println(simTimeFormatted()+": Otoczenie: Dodano nowe zgl. nr: " + zgl.getKolejnyNr());        
        System.out.println(simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Otoczenie: Dodano nowe zgl. nr: " + zgl.getKolejnyNr());
        // Aktywuj obsługę, jeżeli kolejka była pusta (gniazdo "spało")
        if (otoczenie.smo.liczbaZgl()==1 && otoczenie.smo.isWolne()) {
        	otoczenie.smo.rozpocznijObsluge = new ZdarzenieRozpocznijObsluge(otoczenie.smo);
        }
        // Wygeneruj czas do kolejnego zgłoszenia
        double odstep = rng.normal(mean, stdev);
        otoczenie.MVczasyMiedzyZgl.setValue(odstep);
        setRepetitionPeriod(odstep);
        //alternatywnie: parent.zglaszaj = new Zglaszaj(parent, odstep);
	}

	@Override
	public Object getEventParams() {
		return null;
	}
}