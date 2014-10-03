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
    private final static Pattern DIMENSION_PATTERN = Pattern.compile("((?:\\w+\\s*)+):\\s(\\w+),\\s(\\w+),\\s(\\w+),\\s(\\w+)[?:\\n|\\r]");
    private LogicDimension[] dimensions;
    
    
    public DimensionDataInput()
    {
        super();
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
        
        textArea = new JTextArea("Dimension 1: A1, A2, A3, A4\nDimension 2: B1, B2, B3, B4\nDimension 3: C1, C2, C3, C4");
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
            Tablero miTablero = new Tablero(dimensions);
            miTablero.setBounds(10, 10, 960, 580);
            miTablero.setVisible(true);
            miTablero.setResizable(false);
            miTablero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        Matcher matcher = DIMENSION_PATTERN.matcher(textArea.getText());
        int counter = 0;
        String currentDimensionName = "";
        
        while(matcher.find())
        {
            String[] data = matcher.group().split(":");
            String[] currentDimensionItems = new String[Configuration.ITEM_PER_DIMENSION_AMOUNT];
            currentDimensionName = data[0];
            data = data[1].split(",");
            for (int i = 0; i < data.length; i++) {
                currentDimensionItems[i] = data[i];
            }
            dimensions[counter] = new LogicDimension(currentDimensionName, currentDimensionItems);
            System.out.println(dimensions[counter]);
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
