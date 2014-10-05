package co.uniandes.KM.logicPuzzles.mundo;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

import co.uniandes.KM.logicPuzzles.UI.Tablero;

public class PuzzleEventListener implements RuleRuntimeEventListener {

	private static int event_counter = 0;
	
	public Tablero tablero;

    /**
	 * @param tablero
	 */
	public PuzzleEventListener(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	public void objectInserted(ObjectInsertedEvent ev) {
       event_counter++;
    }

    public void objectDeleted(ObjectDeletedEvent ev) {
    }
    
	public void objectUpdated(ObjectUpdatedEvent ev) {
		if(ev.getObject() instanceof Cell)
			tablero.refresh();
    }
    
    /**
	 * @return the tablero
	 */
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * @param tablero the tablero to set
	 */
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
}