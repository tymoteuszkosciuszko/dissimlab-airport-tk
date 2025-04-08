package ODEtest;

import dissimlab.numerical.EquationObj;

public class FunkcjaDlaRK extends EquationObj{
	
	public FunkcjaDlaRK(boolean mvb) {
		super(mvb);
	}
	
	public double function (double t, double y){
		return (y-t*t);		
	}
	@Override
	public void stateInfo() {
		System.out.println("FunkcjaDlaRK t: "+simTime()+" y: "+getY());		
	}
	
}
