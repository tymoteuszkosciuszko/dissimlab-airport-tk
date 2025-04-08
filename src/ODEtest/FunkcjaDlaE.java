package ODEtest;

import dissimlab.numerical.EquationObj;

public class FunkcjaDlaE extends EquationObj{
	
	public FunkcjaDlaE(boolean mvb) {
		super(mvb);
	}
	
	public double function (double t, double y){		
		return (y-t*t);
	}

	@Override
	public void stateInfo() {
		System.out.println("FunkcjaDlaE t: "+simTime()+" y: "+getY());		
	}
}
