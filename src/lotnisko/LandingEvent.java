// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class LandingEvent extends BasicSimEvent<Lotnisko, Object> {

	public LandingEvent(Lotnisko lotnisko, double delay) throws SimControlException {
		super(lotnisko, delay);
	}

	@Override
	protected void stateChange() throws SimControlException {
		Lotnisko lotnisko = getSimObj();
		lotnisko.mvRunwayFree.setValue(1);
		lotnisko.onTheGround++;
		lotnisko.samolotyInAir.get(0).setClock();
		lotnisko.samolotyOnGround.add(lotnisko.samolotyInAir.get(0));
		lotnisko.samolotyInAir.remove(0);
		lotnisko.mvInAirWaitTime.setValue(lotnisko.samolotyOnGround.getLast().getWaitTime());
		System.out.println("Samolot wylądował. Liczba samolotów na ziemi: " + lotnisko.onTheGround);


		if (lotnisko.onTheGround == 1) {
			new DepartureEvent(lotnisko, lotnisko.departureInterval);
		}
		if (lotnisko.inTheAir > 0) {
			lotnisko.runwayFree = false;
			lotnisko.mvRunwayFree.setValue(0);
			lotnisko.inTheAir--;

			lotnisko.mvInAirWaitTime.setValue(lotnisko.samolotyInAir.get(0).getWaitTime());
			lotnisko.samolotyInAir.get(0).setClock();


			switch (lotnisko.samolotyInAir.get(0).getType()) {
				case 1:
					do {
						lotnisko.landingDuration = lotnisko.rng.normal(3, 1);
					} while (lotnisko.landingDuration < 1);
					break;
				case 2:
					lotnisko.landingDuration = lotnisko.rng.uniformInt(1, 4);
					break;
				case 3:
					lotnisko.landingDuration = 2;
					break;
			}
			new LandingEvent(lotnisko, lotnisko.landingDuration);
			System.out.println(simTime() + " - Zaplanowano lądowanie | In Air: " + lotnisko.inTheAir + " | Runway Free: " + lotnisko.runwayFree);
		} else {
			lotnisko.runwayFree = true;
			lotnisko.mvRunwayFree.setValue(1);
			System.out.println(simTime() + " - Zwolniono pas lądowania | Runawy Free: " + lotnisko.runwayFree);
		}

		lotnisko.mvInTheAir.setValue(lotnisko.inTheAir);
	}

	@Override
	protected void onTermination() throws SimControlException {
	}

	@Override
	public Object getEventParams() {
		return null;
	}
}
