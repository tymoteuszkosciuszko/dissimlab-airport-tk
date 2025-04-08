// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Diagram.DiagramType;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class AppSingle {
	
	public static void main(String[] args) {
		try{
			SimManager simMgr = SimManager.getInstance();

			OkienkoObslugi okienko = new OkienkoObslugi("Skaner", "Drzwi", 0.1, 6, 1, 0.1, 8, 0.2);

			Lotnisko lotnisko = new Lotnisko(okienko,2, 3.5, 2);

			simMgr.setEndSimTime(20);
			simMgr.startSimulation();

			
			Diagram d1 = new Diagram(DiagramType.TIME, "R-inTheAir G-onTheGround B-runwayFree");
			Diagram d2 = new Diagram(DiagramType.TIME, "Queue length");
			Diagram d3 = new Diagram(DiagramType.TIME, "R - Drzwi, G - Skaner, B - Stan");
			d1.add(lotnisko.mvInTheAir, java.awt.Color.RED);
			d1.add(lotnisko.mvOnTheGround, java.awt.Color.GREEN);
			d1.add(lotnisko.mvRunwayFree, java.awt.Color.BLUE);
			d2.add(okienko.MVqueueLength, java.awt.Color.RED);
			d3.add(okienko.struct.e1.mvState, Color.RED);
			d3.add(okienko.struct.e2.mvState, Color.GREEN);
			d3.add(okienko.struct.mvState, Color.BLUE);

			d2.show();
			d3.show();

			System.out.println("Średni czas oczekiwania na odprawę: " + Statistics.arithmeticMean(okienko.MVwaitTime));
			System.out.println("Średni czas obsługi: " + Statistics.arithmeticMean(okienko.MVserviceTime));
			System.out.println("Średnia długość kolejki: " + Statistics.arithmeticMean(okienko.MVqueueLength));
		}

		catch (SimControlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
