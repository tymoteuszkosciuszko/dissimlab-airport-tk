// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

import java.util.LinkedList;

public class OkienkoObslugi extends BasicSimObj {
    private boolean wolne = true;
    Structure struct;
    private LinkedList<Pasazer> kolejka;
    public ZdarzenieRozpocznijObsluge ZdarzenieRozpocznijObsluge;
    public ZdarzenieZakonczObsluge ZdarzenieZakonczObsluge;
    public MonitoredVar MVserviceTime;
    public MonitoredVar MVwaitTime;
    public MonitoredVar MVqueueLength;


    public OkienkoObslugi(String name1, String name2, double lambda1, double mi1, double sigma1, double lambda2, double mi2, double sigma2) throws SimControlException {
        this.struct = new Structure(name1, name2, lambda1, mi1, sigma1, lambda2, mi2, sigma2, this);
        kolejka = new LinkedList<Pasazer>();
        MVserviceTime = new MonitoredVar();
        MVwaitTime = new MonitoredVar();
        MVqueueLength = new MonitoredVar();
    }

    public int addPasazer(Pasazer p) {
        kolejka.add(p);
        MVqueueLength.setValue(kolejka.size());
        return kolejka.size();
    }

    public Pasazer removePasazer() {
        Pasazer p = kolejka.removeFirst();
        MVqueueLength.setValue(kolejka.size());
        return p;
    }

    public boolean isWolne() {
        return wolne;
    }

    public void setWolne(boolean wolne) {
        this.wolne = wolne;
    }

    public int liczbaPasazerow() {
        return kolejka.size();
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
