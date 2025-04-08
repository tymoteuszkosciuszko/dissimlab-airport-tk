package StepByStepTest;

import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

public class AppStepByStep {
	public static void main(String[] args) {
		try {
			SimManager simMngr = SimManager.initInstance();
			
			Stan stan = new Stan();
			Licznik licznik = new Licznik(stan, null, 1.0, 5);
			Wydruk wydruk = new Wydruk(stan, null, 1.0, 4);
			
			simMngr.setEndSimTime(100);
			simMngr.startSimulation();
			
		} catch (SimControlException e) {
			e.printStackTrace();
		}

	}
}
