package ProsteSMOtest;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;


public class Otoczenie extends BasicSimObj {
    public ZdarzenieZglaszaj zdarzenieZglaszaj;
    public MonitoredVar MVczasyMiedzyZgl;
    public Smo smo;

    public Otoczenie(Smo smo, double mean, double stdev) throws SimControlException {
        // Powołanie instancji pierwszego zdarzenia
    	zdarzenieZglaszaj = new ZdarzenieZglaszaj(this, 0.0, mean, stdev);
        // Deklaracja zmiennych monitorowanych
        MVczasyMiedzyZgl = new MonitoredVar();
        // SMO dla zgłoszeń
        this.smo = smo;
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
