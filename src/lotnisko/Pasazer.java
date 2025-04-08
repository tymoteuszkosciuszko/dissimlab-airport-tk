// Tymoteusz Kościuszko

package lotnisko;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class Pasazer extends BasicSimObj {
    double arrivalTime;
    double serviceStartTime;
    static int nr=0;
    int nextNr;
    public OkienkoObslugi ok;

    public Pasazer(double arrivalTime, OkienkoObslugi ok) throws SimControlException {
        this.arrivalTime = arrivalTime;
        this.serviceStartTime = 0;
        setNextNr();
        this.ok = ok;
    }

    public int getNextNr() {
        return nextNr;
    }

    public void setNextNr() {
        this.nextNr = nr++;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(double serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
