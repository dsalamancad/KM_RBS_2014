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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import org.drools.core.util.StringUtils;

import co.uniandes.KM.logicPuzzles.Configuration;
import co.uniandes.KM.logicPuzzles.mundo.LogicDimension;


@SuppressWarnings("restriction")
public class DimensionDataInput extends JFrame implements ActionListener, KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 3993697247458834659L;
    
    private String[] dims={"Dimension 1", "Dimension 2", "Dimension 3"};
    private JButton buttonSiguiente;
    private JTextArea textArea;
    private JComboBox<String> combo;
    private JLabel dim;
    /**
     * 
     */
    private static Pattern DIMENSION_PATTERN;
    
    private LogicDimension[] dimensions;
    
    private int dimNumerica;
    
    private Tablero tablero;
    
    public DimensionDataInput(Tablero tablero) {        
        super();
        dimNumerica=1;
        
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
       
        combo=new JComboBox<String>();
        combo.setSize(50, 20);
        combo.setMinimumSize(new Dimension(50,20));
        combo.setPreferredSize(new Dimension(50,20));
        combo.setLocation(10, 40 );
        for (int i = 0; i < dims.length; i++){
            combo.addItem(dims[i]);
        }
        combo.setSelectedIndex(1);
        combo.setActionCommand("COMBO");
        combo.addActionListener(this);

        dim = new JLabel("Indique cual será la dimensión númerica:");
        dim.setSize(50, 20);
        
        add(combo,BorderLayout.NORTH);

        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)screenSize.getWidth()/2-getWidth()/2,(int)screenSize.getHeight()/2-getHeight()/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SIGUIENTE")) {
        	Configuration.NUMERIC_DIMENSION=dimNumerica;
            tablero.initialize(dimensions);
            this.dispose();
        }else if (e.getActionCommand().equals("COMBO")){
        	dimNumerica=combo.getSelectedIndex();
        	System.out.println(dimNumerica);
        	
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
