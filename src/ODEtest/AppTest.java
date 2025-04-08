package ODEtest;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Diagram.DiagramType;
import dissimlab.numerical.EulerEvent;
import dissimlab.numerical.RungeKuttaEvent;

/**
 * @author Dariusz Pierzchala
 * 
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 * 
 * Wersja testowa.
 */

import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class AppTest {
	public static void main(String[] args) {
		try {
			SimManager model = SimManager.getInstance();
					
			//funkcja realizujaca obliczenia w kolejnych krokach
			FunkcjaDlaRK fRK= new FunkcjaDlaRK(true);
			FunkcjaDlaE fE= new FunkcjaDlaE(true);
			
	        EulerEvent eulerEvent=new EulerEvent(fE, 0.1, 0.0); 
	        RungeKuttaEvent rkEvent=new RungeKuttaEvent(fRK, 0.1, 0.0); 
		
			SimControlEvent stopEvent = new SimControlEvent(10.0, SimControlStatus.STOPSIMULATION);
			// Uruchomienie symulacji za pośrednictwem metody "startSimulation" 
			model.startSimulation();
			
			Diagram d = new Diagram(DiagramType.TIME,"Przebieg w czasie");
			d.add(fRK.getMvY(), java.awt.Color.RED, "Runge Kutty");
			d.add(fE.getMvY(), java.awt.Color.BLUE, "Euler");
			d.show();
		

		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
