package StepByStepTest;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Licznik extends BasicSimEvent<Stan, Object> {

	public Licznik(Stan entity,Object params, double period, int priority) throws SimControlException {
		super(entity,params, period, priority);
	}

	@Override
	protected void stateChange() throws SimControlException {
		getSimObj().val++;
	}

	@Override
	public Object getEventParams() {
		return null;
	}

	@Override
	protected void onTermination() throws SimControlException {
	}

}
