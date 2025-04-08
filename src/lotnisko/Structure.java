// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.SimControlException;

public class Structure {
    Element e1;
    Element e2;
    boolean state;
    OkienkoObslugi ok;

    MonitoredVar mvState;

    public Structure(String name1, String name2, double lambda1, double mi1, double sigma1, double lambda2, double mi2, double sigma2, OkienkoObslugi ok) throws SimControlException {
        this.e1 = new Element(name1, lambda1, mi1, sigma1, this);
        this.e2 = new Element(name2, lambda2, mi2, sigma2, this);
        this.ok = ok;
        this.state = true;
        this.mvState = new MonitoredVar();
        mvState.setValue(1.0);
    }

    public void checkState() throws SimControlException {
        state = e1.getState() && e2.getState();
        if (state) {
            mvState.setValue(1.0);
        } else {
            mvState.setValue(0.0);
        }

    }
}
