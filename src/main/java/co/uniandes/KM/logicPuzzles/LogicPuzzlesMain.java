/**
 * 
 */
package co.uniandes.KM.logicPuzzles;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import co.uniandes.KM.logicPuzzles.UI.Tablero;
import co.uniandes.KM.logicPuzzles.mundo.LogicPuzzle;

/**
 * @author danielsalamanca
 *
 */
public class LogicPuzzlesMain {
	
	private KieSession kSession;
	private LogicPuzzle logicPuzzle;
	
	public LogicPuzzlesMain(int dimensions, int itemsPerDimension) {
	    Configuration.DIMENSION_AMOUNT = dimensions;
	    Configuration.ITEMS_PER_DIMENSION = itemsPerDimension;
	    
		//Initialize UI
		Tablero tablero = new Tablero();
		tablero.setVisible(true);
		
		while(tablero.getLogicPuzzle() == null){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Get backend
		this.logicPuzzle = tablero.getLogicPuzzle();
		
		//Initialize drools
        try {
        	initializeDrools();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(0);
        }
        
        logicPuzzle.setSession(kSession);
	}

	private void initializeDrools() throws Throwable {
        // load up the knowledge base
        KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	kSession = kContainer.newKieSession("ksession-rules");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    int dimensions = args.length == 2 ? Integer.parseInt(args[0]):3;
	    int itemsPerDimension = args.length == 2 ? Integer.parseInt(args[1]):3;
		LogicPuzzlesMain lpm = new LogicPuzzlesMain(dimensions, itemsPerDimension);
	}

}
