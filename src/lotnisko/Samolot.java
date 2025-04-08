// Tymoteusz Kościuszko

package lotnisko;
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

public class Samolot extends BasicSimObj {
    RNGenerator rng2 = new RNGenerator();

    int type;
    double Clock;

    public Samolot() {
        this.type = rng2.uniformInt(1, 4);
        this.Clock = simTime();

    }




    public int getType() {
        return type;
    }

    public double getWaitTime() {
        return simTime() - Clock;
    }

    public double getClock() {
        return Clock;
    }

    public void setClock() {
        this.Clock = simTime();
    }


    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}