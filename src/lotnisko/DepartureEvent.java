// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class DepartureEvent extends BasicSimEvent<Lotnisko, Object> {

	private RNGenerator rng;
	private Lotnisko lotnisko;

	public DepartureEvent(Lotnisko lotnisko, double delay) throws SimControlException {
		super(lotnisko, delay);
		rng = new RNGenerator();
		this.lotnisko = lotnisko;
	}

	@Override
	protected void stateChange() throws SimControlException {
		lotnisko.onTheGround--;
		System.out.println("Samolot został obsłużony i odleciał. Liczba samolotów na ziemi: " + lotnisko.onTheGround);
		lotnisko.mvOnGroundWaitTime.setValue(lotnisko.samolotyOnGround.get(0).getWaitTime());
		lotnisko.samolotyOnGround.get(0).setClock();


//		System.out.println(simTime() + " - Departure | In Air: " + lotnisko.inTheAir + " | On Ground: " + lotnisko.onTheGround + " | Runway Free: " + lotnisko.runwayFree);
		if (lotnisko.onTheGround>0)
			new DepartureEvent(lotnisko, lotnisko.departureInterval);

		lotnisko.mvOnTheGround.setValue(lotnisko.onTheGround);

		int liczbaPasazer = 0;
		switch (lotnisko.samolotyOnGround.get(0).getType()) {
			case 1:
					liczbaPasazer = rng.uniformInt(0, 6);
				break;
			case 2:
				liczbaPasazer = rng.uniformInt(0, 21);
				break;
			case 3:
				liczbaPasazer = rng.uniformInt(1,3);
				break;
		}
		lotnisko.samolotyOnGround.remove(0);
		for (int i = 0; i < liczbaPasazer; i++) {
			Pasazer pasazer = new Pasazer(simTime(), lotnisko.okienkoObslugi);
			lotnisko.okienkoObslugi.addPasazer(pasazer);
			System.out.println(simTimeFormatted() + "Pasażer nr: " + pasazer.getNextNr() + " w kolejce do okienka obsługi");
			if (lotnisko.okienkoObslugi.isWolne() && lotnisko.okienkoObslugi.liczbaPasazerow() == 1)
				lotnisko.okienkoObslugi.ZdarzenieRozpocznijObsluge = new ZdarzenieRozpocznijObsluge(lotnisko.okienkoObslugi);
		}
	}

	@Override
	protected void onTermination() throws SimControlException {
	}

	@Override
	public Object getEventParams() {
		return null;
	}
}
