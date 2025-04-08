package DwaSMOtest;

import java.util.Date;

import dissimlab.simcore.SimControlEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventBarrier;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters.SimControlStatus;

public class App {
	
	private int id;
	 
    public App() {
    }
    
	synchronized public void run(int i) {
		try {
			id=i;
			SimManager model = SimManager.initInstance();
			//Utylizator zgłoszeń
			UtylizatorKoncowy kosz = new UtylizatorKoncowy();
			//Semafor
			SimEventBarrier semafor = new SimEventBarrier("Semafor dla SMO");
			// Powołanie 2-ego Smo
			SmoBis smoBis = new SmoBis(3, semafor);
			// Powołanie Smo nr 1 
			Smo smo = new Smo(smoBis, semafor);
			// Utworzenie otoczenia
			Otoczenie generatorZgl = new Otoczenie(smo);
			// Dwa sposoby zaplanowanego końca symulacji
			//model.setEndSimTime(10000);
			// lub
			SimControlEvent stopEvent = new SimControlEvent(200.0, SimControlStatus.STOPSIMULATION);
			// Badanie czasu trwania eksperymentu - początek
			long czst = new Date().getTime();
			// Uruchomienie symulacji za pośrednictwem metody "start" z
			model.startSimulation();
			// Badanie czasu trwania eksperymentu - koniec
			czst = new Date().getTime() - czst;
			// Wyniki
			System.out.println("Czas trwania eksperymentu: " + czst);
			
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
