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
	
	public LogicPuzzlesMain() {
		//Initialize UI
		Tablero tablero = new Tablero();
		tablero.setVisible(true);
		
		while(tablero.getLogicPuzzle() == null){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
		LogicPuzzlesMain lpm = new LogicPuzzlesMain();
	}

}
