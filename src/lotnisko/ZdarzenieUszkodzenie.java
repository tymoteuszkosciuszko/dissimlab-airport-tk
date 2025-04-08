// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieUszkodzenie extends BasicSimEvent<Element, Object> {

    OkienkoObslugi ok;
    private Element element;
    private double delay;
    private double breaktime;

    public ZdarzenieUszkodzenie(Element element, double delay, OkienkoObslugi ok) throws SimControlException {
        super(element, delay, ok);
        this.breaktime = simTime();
        this.ok = ok;
        this.element = element;
        this.delay = delay;
    }

    @Override
    protected void stateChange() throws SimControlException {
        if (element.getState()) {
            element.setState(false);
            element.createFixEvent(element);
            System.out.println(simTime() + ": " + element.getName() + " uszkodzony, OK otwarte:" + element.struct.ok.struct.state);}
            if (element.struct.ok.ZdarzenieZakonczObsluge != null && (element.struct.ok.liczbaPasazerow() > 0)) {
                Pasazer currentp = element.struct.ok.ZdarzenieZakonczObsluge.p;
                double stuckdelay = element.struct.ok.ZdarzenieZakonczObsluge.delay;
                element.struct.ok.ZdarzenieZakonczObsluge.terminate();
            }
    }
    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}