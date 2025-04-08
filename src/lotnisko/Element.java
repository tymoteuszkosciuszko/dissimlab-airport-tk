// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class Element extends BasicSimObj {
    private String name;
    private double lambda;
    private double mi;
    private double sigma;
    Structure struct;
    private boolean State;
    private RNGenerator rng;

    private ZdarzenieUszkodzenie zdarzenieUszkodzenie;
    private ZdarzenieNaprawa zdarzenieNaprawa;
    MonitoredVar mvState;


    public Element(String name, double lambda, double mi, double sigma, Structure struct) throws SimControlException {
        this.name = name;
        this.lambda = lambda;
        this.mi = mi;
        this.sigma = sigma;
        this.struct = struct;
        this.State = true;
        this.rng = new RNGenerator();
        this.mvState = new MonitoredVar();
        mvState.setValue(1.0);
        this.createBreakEvent(this);

    }

    public void createBreakEvent(Element element) throws SimControlException {
        double time;
        do {
            time = rng.exponential(lambda);
        } while (time <= 0.0);
        new ZdarzenieUszkodzenie(this, time, struct.ok);
    }

    public void createFixEvent(Element element) throws SimControlException {
        double time;
        do {
            time = rng.normal(mi, sigma);
        } while (time <= 0.0);
        new ZdarzenieNaprawa(this, time);
    }

    public boolean getState() {
        return State;
    }

    public void setState(boolean state) throws SimControlException {
        this.State = state;
        if (state) {
            mvState.setValue(1.0);
        } else {
            mvState.setValue(0.0);
        }
        this.struct.checkState();
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }
}
