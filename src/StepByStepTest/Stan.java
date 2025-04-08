package StepByStepTest;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Stan extends BasicSimObj {
	int val = 0;

	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		return false;
	}

}
