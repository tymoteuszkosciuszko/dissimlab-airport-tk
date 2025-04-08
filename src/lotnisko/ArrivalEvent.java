// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ArrivalEvent extends BasicSimEvent<Lotnisko, Object> {

	public ArrivalEvent(Lotnisko lotnisko, double delay) throws SimControlException {
		super(lotnisko, delay);

	}

	@Override
	protected void stateChange() throws SimControlException {
		Lotnisko lotnisko = getSimObj();
			new ArrivalEvent(lotnisko, lotnisko.rng.exponential(lotnisko.arrivalInterval));
			lotnisko.inTheAir++;
			lotnisko.samolotyInAir.add(new Samolot());
			System.out.println("Samolot przybył. Liczba samolotów w powietrzu: " + lotnisko.inTheAir);

			if (lotnisko.runwayFree) {
				lotnisko.inTheAir--;
				lotnisko.mvInAirWaitTime.setValue(lotnisko.samolotyInAir.get(0).getWaitTime());
				lotnisko.samolotyInAir.get(0).setClock();
				lotnisko.runwayFree = false;
				lotnisko.mvRunwayFree.setValue(0);
				switch (lotnisko.samolotyInAir.get(0).getType()) {
					case 1:
						do {
							lotnisko.landingDuration = (int) lotnisko.rng.normal(3, 1);
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
