// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRozpocznijObsluge extends BasicSimEvent<OkienkoObslugi, Pasazer> {

    private OkienkoObslugi okienko;
    private RNGenerator rng;

    public ZdarzenieRozpocznijObsluge(OkienkoObslugi okienko) throws SimControlException {
        super(okienko);
        this.okienko = okienko;
        rng = new RNGenerator();
    }

    protected void stateChange() throws SimControlException {
        if (okienko.liczbaPasazerow() > 0 && okienko.isWolne() && okienko.struct.state){
            okienko.ZdarzenieRozpocznijObsluge = null;
            okienko.setWolne(false);
            Pasazer p = okienko.removePasazer();
            double czasObslugi;
            do {
                czasObslugi = rng.exponential(2.0);
            } while (czasObslugi <= 0.0);

            okienko.MVserviceTime.setValue(czasObslugi);
            okienko.MVwaitTime.setValue(simTime() - p.getArrivalTime());
            System.out.println(simTimeFormatted() + ": Rozpoczęto obsługę pasażera: " + p.getNextNr() + ", Czas oczekiwania = " + (simTime() - p.getArrivalTime()));
            okienko.ZdarzenieZakonczObsluge = new ZdarzenieZakonczObsluge(okienko, czasObslugi, p);
            p.setServiceStartTime(simTime());


        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Pasazer getEventParams() {
        return null;
    }
}
