package DwaSMOtest;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Obiekt przejmujący zgłoszenia do utylizacji. Działa w wyniku powiadomień z brokera (na sybskrybowane zdarzenia)
 */

import dissimlab.broker.Dispatcher;
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;



public class UtylizatorKoncowy extends BasicSimObj
{
	//
	public Dispatcher infoDystr;
	//

    public UtylizatorKoncowy() throws SimControlException
    {
    	super();
    	infoDystr = getCommonDispatcher();
    	infoDystr.subscribe(this, ZakonczObslugeBis.class);
    }



	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
		System.out.println("-Utylizator końcowy: reflect - zgloszenie nr: " + (((ZakonczObslugeBis)event).getEventParams()).getTenNr());
	}


	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		if (((Zgloszenie)((ZakonczObslugeBis)event).getEventParams()).getTenNr()<5)
		{
			System.out.println("-Utylizator końcowy: Filtr OK");
			return true;
		}
		else
		{
			System.out.println("-Utylizator końcowy: Filtr false");
			return false;
		}
	}
}