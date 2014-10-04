package co.uniandes.KM.logicPuzzles.UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import co.uniandes.KM.logicPuzzles.Configuration;
import co.uniandes.KM.logicPuzzles.mundo.LogicDimension;


public class DimensionDataInput extends JFrame implements ActionListener, KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 3993697247458834659L;
    
    private JButton buttonSiguiente;
    private JTextArea textArea;
    
    /**
     * 
     */
    private final static Pattern DIMENSION_PATTERN = Pattern.compile("((?:\\w+\\s*)+):\\s(\\w+),\\s(\\w+),\\s(\\w+),\\s(\\w+)[?:\\n|\\r]");
    
    private LogicDimension[] dimensions;
    
    private Tablero tablero;
    
    
    public DimensionDataInput(Tablero tablero)
    {
        super();
        this.tablero = tablero;
        dimensions = new LogicDimension[Configuration.DIMENSION_AMOUNT];
        setLayout(new BorderLayout(10,20));
        buttonSiguiente = new JButton("Siguiente");
        buttonSiguiente.setSize(100, 20);
        buttonSiguiente.setMaximumSize(new Dimension(100,20));
        buttonSiguiente.setLocation(10, 10);
        buttonSiguiente.setEnabled(false);
        buttonSiguiente.setActionCommand("SIGUIENTE");
        buttonSiguiente.addActionListener(this);
        add(buttonSiguiente,BorderLayout.SOUTH);
        
        textArea = new JTextArea("Names: Chester, Melvin, Nathan, Victor\nTimes: 900am, 1000am, 1100am, 12noon\nAilments: backpain, hippain, migraines, shingles");
        textArea.setSize(100, 60);
        textArea.setMinimumSize(new Dimension(100,60));
        textArea.setLocation(10, 40 );
        textArea.addKeyListener(this);
        add(textArea,BorderLayout.CENTER);
        
        setBounds(0, 0, 600, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SIGUIENTE"))
        {
            tablero.initialize(dimensions);
            this.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        Matcher matcher = DIMENSION_PATTERN.matcher(textArea.getText());
        int counter = 0;
        String currentDimensionName = "";
        int longestString = 0;
        while(matcher.find())
        {
            String[] data = matcher.group().split(":");
            String[] currentDimensionItems = new String[Configuration.ITEM_PER_DIMENSION_AMOUNT];
            currentDimensionName = data[0];
            data = data[1].split(",");
            for (int i = 0; i < data.length; i++) {
                currentDimensionItems[i] = data[i].trim();
                if (currentDimensionItems[i].length() > longestString) longestString = currentDimensionItems[i].length();
            }
            dimensions[counter] = new LogicDimension(currentDimensionName, currentDimensionItems);
            counter++;
        }
        
        if(counter == Configuration.DIMENSION_AMOUNT)
            buttonSiguiente.setEnabled(true);
        else
            buttonSiguiente.setEnabled(false);
        
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}
