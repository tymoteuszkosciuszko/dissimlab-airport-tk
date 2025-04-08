package DwaSMOtest;

/**
 * @author Dariusz Pierzchala
 * 
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 * 
 * Wersja testowa.
 */

import java.math.BigDecimal;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Diagram.DiagramType;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventBarrier;
import dissimlab.simcore.SimManager;

public class AppSMO {
	public static void main(String[] args) {
		try {
			SimManager simMngr = SimManager.initInstance();
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
			simMngr.setEndSimTime(1000);
			// lub
			//SimControlEvent stopEvent = new SimControlEvent(1000.0, SimControlStatus.STOPSIMULATION);
			// Uruchomienie symulacji za pośrednictwem metody "start" 
			simMngr.startSimulation();

			// Wyniki 
			double wynik = new BigDecimal(Statistics
					.arithmeticMean(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu oczekiwania na obsługę:   "
							+ wynik);
			wynik = new BigDecimal(Statistics
					.weightedMean(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia czasu oczekiwania na obsługę:   "
							+ wynik);			
			wynik = new BigDecimal(Statistics
					.standardDeviation(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dla czasu obsługi:       "
							+ wynik);
			wynik = new BigDecimal(Statistics.max(smo.MVczasy_oczekiwania))
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu oczekiwania na obsługę: "
					+ wynik);
			wynik = new BigDecimal(Statistics
					.arithmeticMean(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia długości kolejki:       "
							+ wynik);
			wynik = new BigDecimal(Statistics
					.weightedMean(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia długości kolejki:       "
							+ wynik);
			wynik = new BigDecimal(Statistics
					.max(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość maksymalna długości kolejki:       "
							+ wynik);
		
			Diagram d1 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania");
			d1.add(smo.MVczasy_obslugi, java.awt.Color.GREEN);
			d1.show();

			Diagram d2 = new Diagram(DiagramType.HISTOGRAM,
					"Dlugość kolejki SMO nr 2 - HISTOGRAM");
			d2.add(smoBis.MVdlKolejki, java.awt.Color.BLUE);
			d2.show();

			Diagram d3 = new Diagram(DiagramType.HISTOGRAM,
					"Czasy oczekiwania na obsługę");
			d3.add(smo.MVczasy_oczekiwania, java.awt.Color.BLUE);
			d3.show();

			Diagram d4 = new Diagram(DiagramType.TIME,
					"Długość kolejki SMO nr 1 - w czasie");
			d4.add(smo.MVdlKolejki, java.awt.Color.RED);
			d4.show();
			
		} catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
