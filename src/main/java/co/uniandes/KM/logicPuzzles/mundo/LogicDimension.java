/**
 * 
 */
package co.uniandes.KM.logicPuzzles.mundo;

/**
 * @author sebastian
 *
 */
public class LogicDimension {
    
    /**
     * Nombre de la dimensión
     */
    private String name;
    
    /**
     * Ítems de la dimensión
     */
    private String[] items;

    public LogicDimension(String name, String[] items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
    
    public String toString(){
        String result = name +":\t";
        for (int i = 0; i < items.length; i++) {
            result+=items[i]+ ((i<items.length-1)?", ":"");
        }
        return result;
    }
}
