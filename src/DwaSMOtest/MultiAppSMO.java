package DwaSMOtest;

public class MultiAppSMO {
	 
	public static void main(String[] args) throws InterruptedException {
	
		int numOfTrials = 30;
		App app = new App();
		for (int i=0;i<numOfTrials;i++) {
			app.run(i);
			System.out.println("Zakończył się eksperyment nr: "+(i+1));
		}
		
	}
}
