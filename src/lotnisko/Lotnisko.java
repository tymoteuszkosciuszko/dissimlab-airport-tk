// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

import java.util.ArrayList;

public class Lotnisko extends BasicSimObj {
	public OkienkoObslugi okienkoObslugi;
	ArrayList<Samolot> samolotyInAir = new ArrayList<Samolot>();
	ArrayList<Samolot> samolotyOnGround = new ArrayList<Samolot>();
	int inTheAir; // liczba samolotów w powietrzu
	int onTheGround; // liczba samolotów czekających na lotnisku
	boolean runwayFree; // dostępność pasa lądowania
	double arrivalInterval; // okres pomiedzy kolejnymi przylotami
	double landingDuration; // czas trwania lądowania
	double departureInterval; // okres pomiędzy odlotami
	ArrivalEvent arrivalEvent;
	LandingEvent landingEvent;
	DepartureEvent departureEvent;
	MonitoredVar mvOnTheGround, mvInTheAir, mvRunwayFree, mvInAirWaitTime, mvOnGroundWaitTime;
	RNGenerator rng = new RNGenerator();

	public Lotnisko(OkienkoObslugi okienko, double arrivalInterval, double landingDuration, double departureInterval)
			throws SimControlException {
		this.okienkoObslugi = okienko;
		this.inTheAir = 0;
		this.onTheGround = 0;
		this.runwayFree = true;
		this.arrivalInterval = arrivalInterval;
		this.landingDuration = landingDuration;
		this.departureInterval = departureInterval;
		mvOnTheGround = new MonitoredVar();
		mvInTheAir = new MonitoredVar();
		mvRunwayFree = new MonitoredVar();
		mvInAirWaitTime = new MonitoredVar();
		mvOnGroundWaitTime = new MonitoredVar();
		arrivalEvent = new ArrivalEvent(this, arrivalInterval);

	}


	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		return false;
	}

	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
	}

}
