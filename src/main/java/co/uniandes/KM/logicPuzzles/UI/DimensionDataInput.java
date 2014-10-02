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


public class DimensionDataInput extends JFrame implements ActionListener, KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 3993697247458834659L;
    
    private final static int DIMENSION_AMOUNT = 3;
    private final static int ITEM_PER_DIMENSION_AMOUNT = 4;
    
    private JButton buttonSiguiente;
    private JTextArea textArea;
    private final static Pattern DIMENSION_PATTERN = Pattern.compile("((?:\\w+\\s*)+):\\s(\\w+),\\s(\\w+),\\s(\\w+),\\s(\\w+)[?:\\n|\\r]");
    private String[][] dimensions;
    
    
    public DimensionDataInput()
    {
        super();
        dimensions = new String[DIMENSION_AMOUNT][ITEM_PER_DIMENSION_AMOUNT];
        setLayout(new BorderLayout(10,20));
        buttonSiguiente = new JButton("Siguiente");
        buttonSiguiente.setSize(100, 20);
        buttonSiguiente.setMaximumSize(new Dimension(100,20));
        buttonSiguiente.setLocation(10, 10);
        buttonSiguiente.setEnabled(false);
        buttonSiguiente.setActionCommand("SIGUIENTE");
        buttonSiguiente.addActionListener(this);
        add(buttonSiguiente,BorderLayout.SOUTH);
        
        textArea = new JTextArea();
        textArea.setSize(100, 60);
        textArea.setMinimumSize(new Dimension(100,60));
        textArea.setLocation(10, 40 );
        textArea.addKeyListener(this);
        add(textArea,BorderLayout.CENTER);
        
        setBounds(0, 0, 600, 200);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SIGUIENTE"))
        {
            Tablero miTablero = new Tablero(dimensions);
            miTablero.setBounds(10, 10, 960, 580);
            miTablero.setVisible(true);
            miTablero.setResizable(false);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        Matcher matcher = DIMENSION_PATTERN.matcher(textArea.getText());
        int counter = 0;
        while(matcher.find())
        {
            String[] currentDimension  = new String[ITEM_PER_DIMENSION_AMOUNT+1];
            String[] data = matcher.group().split(":");
            currentDimension[0] = data[0];
            data = data[1].split(",");
            for (int i = 0; i < data.length; i++) {
                currentDimension[i+1] = data[i];
            }
            dimensions[counter] = currentDimension;
            counter++;
        }
        
        if(counter == DIMENSION_AMOUNT)
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
