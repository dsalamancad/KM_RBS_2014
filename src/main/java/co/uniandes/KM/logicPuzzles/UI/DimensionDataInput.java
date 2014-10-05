package co.uniandes.KM.logicPuzzles.UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.apache.poi.util.StringUtil;
import org.drools.core.util.StringUtils;

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
    private static Pattern DIMENSION_PATTERN;
    
    private LogicDimension[] dimensions;
    
    private Tablero tablero;
    
    
    @SuppressWarnings("restriction")
    public DimensionDataInput(Tablero tablero)
    {        
        super();
        DIMENSION_PATTERN = Pattern.compile(".*:.*" + StringUtils.repeat(",.*", Configuration.ITEMS_PER_DIMENSION - 1));
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
        
        textArea = new JTextArea("Names: Chester, Melvin, Nathan, Victor\nTimes: 9:00 am, 10:00 am, 11:00 am, 12 noon\nAilments: Back pain, Hip pain, Migraines, Shingles");
        textArea.setSize(100, 60);
        textArea.setMinimumSize(new Dimension(100,60));
        textArea.setPreferredSize(new Dimension(400,200));
        textArea.setLocation(10, 40 );
        textArea.addKeyListener(this);
        this.keyTyped(null);
        add(textArea,BorderLayout.CENTER);
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)screenSize.getWidth()/2-getWidth()/2,(int)screenSize.getHeight()/2-getHeight()/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SIGUIENTE")) {
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
            String[] data = matcher.group().split(":",2);
            String[] currentDimensionItems = new String[Configuration.ITEMS_PER_DIMENSION];
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
