package StepByStepTest;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Wydruk extends BasicSimEvent<Stan, Object> {

	public Wydruk(Stan entity,Object params, double period, int priority) throws SimControlException {
		super(entity, params, period, priority);
	}

	@Override
	protected void stateChange() throws SimControlException {
		if (getSimObj().val % 10 == 0)
			System.out.println(simTimeFormatted()+ " : " + Integer.toString(getSimObj().val));
	}

	@Override
	public Object getEventParams() {
		return null;
	}

	@Override
	protected void onTermination() throws SimControlException {
	}

}
