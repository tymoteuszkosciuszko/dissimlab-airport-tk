// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNaprawa extends BasicSimEvent<Element, Object> {
    private Element element;
    private double delay;
    private double fixtime;

    public ZdarzenieNaprawa(Element element, double delay) throws SimControlException {
        super(element, delay);
        this.fixtime = simTime();
        this.element = element;
        this.delay = delay;
    }

    @Override
    protected void stateChange() throws SimControlException {
        if(!element.getState()) {
            element.setState(true);
            System.out.println(simTime() + ": " + element.getName() + " naprawiony, OK otwarte:" + element.struct.ok.struct.state);
            element.createBreakEvent(element);

            if (element.struct.ok.ZdarzenieZakonczObsluge != null) {
                Pasazer currentp = element.struct.ok.ZdarzenieZakonczObsluge.p;
                double stuckdelay = element.struct.ok.ZdarzenieZakonczObsluge.delay;
                element.struct.ok.ZdarzenieZakonczObsluge.terminate();
                new ZdarzenieZakonczObsluge(element.struct.ok, stuckdelay, currentp);
                System.out.println(simTime() + ": Nowe ZdarzenieZakonczObsluge utworzone dla zablokowanego pasażera");
            }
            else {
                element.struct.ok.ZdarzenieRozpocznijObsluge = new ZdarzenieRozpocznijObsluge(element.struct.ok);
            }
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
