// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZakonczObsluge extends BasicSimEvent<OkienkoObslugi, Pasazer> {


    public double delay;
    private OkienkoObslugi okienko;
    Pasazer p;
    double startTime;

    public ZdarzenieZakonczObsluge(OkienkoObslugi okienko, double delay, Pasazer p) throws SimControlException {

        super(okienko, delay, p);
        startTime = simTime();
        this.okienko = okienko;
        this.p = p;
        this.delay = delay;
    }

    protected void stateChange() throws SimControlException {
        okienko.ZdarzenieZakonczObsluge = null;
        System.out.println(simTimeFormatted() + ": Okienko - Koniec obsługi pasażera nr: " + p.getNextNr());
        okienko.setWolne(true);
            if (okienko.liczbaPasazerow() > 0) {
                okienko.ZdarzenieRozpocznijObsluge = new ZdarzenieRozpocznijObsluge(okienko);
            }
        double ServiceTime = simTime() - p.getServiceStartTime();
        okienko.MVserviceTime.setValue(ServiceTime);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Pasazer getEventParams() {
        return null;
    }
}
