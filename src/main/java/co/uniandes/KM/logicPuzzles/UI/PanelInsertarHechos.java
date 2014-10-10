package co.uniandes.KM.logicPuzzles.UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelInsertarHechos extends JPanel implements ActionListener{
	private static final String agregarHecho= "HECHO";
	private static final String agregarOR="OR";
	private static final String agregarLess="Less";
	private static final String agregarXOR="Xor";
	
	private Tablero tablero;

    private JButton btnHecho;

    private JButton btnLess;

    private JButton btnOr;

    private JButton btnXor;
    
    public PanelInsertarHechos( Tablero principal )
    {
    	tablero=principal;
    	setBorder( new TitledBorder( "Agregar informaci�n" ) );
    	setLayout( new FlowLayout( ) );
    	
    	// Bot�n opci�n 1
    	btnHecho = new JButton( "Hecho" );
    	btnHecho.setActionCommand( agregarHecho );
    	btnHecho.addActionListener( this );
        add( btnHecho );

        // Bot�n opci�n 2
        btnOr = new JButton( "OR" );
        btnOr.setActionCommand( agregarOR );
        btnOr.addActionListener( this );
        add( btnOr );

        // Bot�n opci�n 3
        btnLess = new JButton( "Menor que" );
        btnLess.setActionCommand( agregarLess );
        btnLess.addActionListener( this );
        add( btnLess );

        // Bot�n opci�n 4
        btnXor = new JButton( "OR exclusivo" );
        btnXor.setActionCommand( agregarXOR );
        btnXor.addActionListener( this );
        add( btnXor );
    }
	
    public void actionPerformed( ActionEvent evento )
    {
    	String comando = evento.getActionCommand( );

        if( agregarHecho.equals( comando ) )
        {
            
        }
    	
    }
}
