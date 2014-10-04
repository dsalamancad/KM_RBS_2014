package co.uniandes.KM.logicPuzzles.UI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.sun.org.apache.bcel.internal.generic.DMUL;

import co.uniandes.KM.logicPuzzles.Configuration;
import co.uniandes.KM.logicPuzzles.mundo.LogicDimension;
import co.uniandes.KM.logicPuzzles.mundo.LogicPuzzle;

public class Tablero extends JFrame implements ActionListener {
    
    /**
     * Generated serial version ID
     */
    private static final long serialVersionUID = 96492363195714367L;
    
    private static final int LABEL_TITLE_SIZE = 1;
    
    private JLabel    nombreHorizontal, nombreVertical;
    private JButton   fila1A, fila2A, fila3A, fila4A, fila1B, fila2B, fila3B,
            fila4B, fila1C, fila2C, fila3C, fila4C;
    
    private LogicPuzzle logicPuzzle;
    
    private JPanel board;
    private JPanel factList;

    public Tablero() {
    	DimensionDataInput dDI = new DimensionDataInput(this);
        dDI.setVisible(true);
    }

	/**
	 * @param dimensions
	 */
	public void initialize(LogicDimension[] dimensions) {
		
		this.logicPuzzle = new LogicPuzzle(dimensions);
		
		BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        
        board = new JPanel();
        board.setLayout(null);
        factList = new JPanel();
        factList.setLayout(null);
        TextArea txt = new TextArea("adsf");
        txt.setSize(50, 300);
        factList.add(txt);
        factList.setSize(50, 300);
        
//        int posX = 30;
//        
//        String[] etiquetas = new String[Configuration.DIMENSION_AMOUNT*Configuration.ITEM_PER_DIMENSION_AMOUNT];
//        for (int i = 0; i < dimensions.length; i++) {
//            LogicDimension currentDimension = dimensions[i];
//            String[] currentDimensionItems = currentDimension.getItems();
//            for (int j = 0; j < currentDimensionItems.length; j++) {
//                etiquetas[i*currentDimensionItems.length + j] =currentDimensionItems[j];
//            }
//        }

//        String botones[] = { "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12", "a13", "a14", "a15", "a16" };
//        JButton filas[] = { fila1A, fila2A, fila3A, fila4A, fila1B, fila2B, fila3B, fila4B, fila1C, fila2C, fila3C, fila4C };
//
//        /* para construir cuadrante a */
//        for (int ia = 0; ia < 4; ia++) {
//            nombreHorizontal = new JLabel(etiquetas[ia]);
//            nombreHorizontal.setBounds(25 + posX, 20, 200, 30);
//            board.add(nombreHorizontal);
//
//            nombreVertical = new JLabel(etiquetas[ia + 8]);
//            nombreVertical.setBounds(10, posX + 25, 200, 30);
//            board.add(nombreVertical);
//
//            for (int ja = 0; ja < 4; ja++) {
//                filas[ja] = new JButton(botones[ia]);
//                filas[ja].setBounds(posX, 50 + (50 * ja), 50, 50);
//                board.add(filas[ja]);
//            }
//
//            posX += 50;
//        }
//
//        /* para construir cuadrante b */
//        for (int ib = 4; ib < 8; ib++) {
//            nombreHorizontal = new JLabel(etiquetas[ib]);
//            nombreHorizontal.setBounds(posX + 35, 20, 200, 30);
//            board.add(nombreHorizontal);
//
//            for (int jb = 4; jb < 8; jb++) {
//                filas[jb] = new JButton(botones[jb]);
//                filas[jb].setBounds(posX + 10, (50 * (jb - 3)), 50, 50);
//                board.add(filas[jb]);
//            }
//            posX += 50;
//
//        }
//
//        /* para construir cuadrante c */
//        for (int ic = 8; ic < 12; ic++) {
//            nombreHorizontal = new JLabel(etiquetas[ic - 4]);
//            nombreHorizontal.setBounds(10, posX - 160, 200, 30);
//            board.add(nombreHorizontal);
//
//            for (int jc = 8; jc < 12; jc++) {
//                filas[jc] = new JButton(botones[jc]);
//                filas[jc].setBounds(posX - 400, 10 + (50 * (jc - 3)), 50, 50);
//                board.add(filas[jc]);
//            }
//            posX += 50;
//
//        }
//        
//        add(board,BorderLayout.CENTER);
//        //add(factList,FlowLayout.LEFT);
        add(createBoard(dimensions));
        setBounds(10, 10, 960, 580);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    private JPanel createBoard(LogicDimension[] dimensions){
        
        /*
         * GridBagLayout es un manejador automático de la posición de componentes gráficos.
         * Cada vez que ingrese un JLabel (o cualquier componente) lo ingreso en una cuadrícula
         * invisible y lo posiciono de acuerdo con dos criterios: Posición y "tamaño".
         * La posición es un x,y relativa a la cuadrícula ( [0,0] para el primer elemento, etc).
         * El tamaño es la cantidad de filas y columnas que ocupará el componente.
         * Ambos criterios se mandan con un objeto GridBagConstraints que se modifica justo antes de
         * ingresar el componente.
         * El objeto tiene cuatro propiedades relevantes:
         * Tamaño -> gridheight, gridwidth
         * Posición -> gridx, gridy
         * 
         * Cuando se agrega un componente, se vé así:   add(componente, gridBagConstraints);
         */
        GridBagLayout gBL = new GridBagLayout();
        JPanel board = new JPanel();
        board.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        board.setLayout(gBL);
        
        //Constraints para elementos de tamaño 1 x 1
        GridBagConstraints singleCellConstraint = new GridBagConstraints();
        singleCellConstraint.gridheight = 1;
        singleCellConstraint.gridwidth = 1;
        singleCellConstraint.ipadx = 15;
        singleCellConstraint.ipady = 15 ;
        singleCellConstraint.fill = GridBagConstraints.BOTH;
        
        //Constraints para elementos de tamaño Configuration.ITEM_PER_DIMENSION_AMOUNT x 1
        GridBagConstraints dimensionHorizontalLabel = new GridBagConstraints();
        dimensionHorizontalLabel.gridwidth = Configuration.ITEM_PER_DIMENSION_AMOUNT;
        dimensionHorizontalLabel.gridheight = LABEL_TITLE_SIZE;
        
        //Constraints para elementos de tamaño 1 x Configuration.ITEM_PER_DIMENSION_AMOUNT
        GridBagConstraints dimensionVerticalLabel = new GridBagConstraints();
        dimensionVerticalLabel.gridwidth = LABEL_TITLE_SIZE;
        dimensionVerticalLabel.gridheight = Configuration.ITEM_PER_DIMENSION_AMOUNT;

        
        // Drawing help
        int horizontalThreshold = Configuration.DIMENSION_AMOUNT - 1;
        int currentRow = 0;
        int currentColumn = 0;
        boolean[][] haveIAlreadyDrawnThisParticularSide = new boolean[Configuration.DIMENSION_AMOUNT][Configuration.DIMENSION_AMOUNT]; 
        LogicDimension currentDimension;
        String[] currentItems;
        RotatedLabel dimensionLabel;
        // Safety all-false initialization of boolean matrix because reasons
        for (int i = 0; i < haveIAlreadyDrawnThisParticularSide.length; i++) {
            for (int j = 0; j < haveIAlreadyDrawnThisParticularSide.length; j++) {
                haveIAlreadyDrawnThisParticularSide[i][j] = false;
            }
        }
        
        
        for (int i = 0; i < dimensions.length; i++) {
            
            if( (currentColumn + 1) > horizontalThreshold ) {
                horizontalThreshold--;
                currentRow++;
                currentColumn = 0;
            }
            int xOffset = currentColumn * Configuration.ITEM_PER_DIMENSION_AMOUNT;
            int yOffset = currentRow * Configuration.ITEM_PER_DIMENSION_AMOUNT;
            //I only need to draw the labels when either currentRow or currentColumn is 0
            if(currentRow == 0 && currentColumn == 0) {
                
                currentDimension = dimensions[i];
                currentItems = currentDimension.getItems();
                dimensionLabel = new RotatedLabel(currentDimension.getName());
                //Upwards title label
                dimensionHorizontalLabel.gridx = xOffset +  LABEL_TITLE_SIZE *2;
                dimensionHorizontalLabel.gridy = yOffset;
                board.add(dimensionLabel, dimensionHorizontalLabel);
                
                //Upwards item title labels
                for (int j = 0; j < currentItems.length; j++) {
                    RotatedLabel itemTitleLabel = new RotatedLabel(currentItems[j]);
                    itemTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
                    itemTitleLabel.setDirection(RotatedLabel.Direction.VERTICAL_UP);
                    singleCellConstraint.gridx = xOffset + LABEL_TITLE_SIZE + j + 1;
                    singleCellConstraint.gridy = yOffset + LABEL_TITLE_SIZE ;
                    singleCellConstraint.ipady = 45;
                    board.add(itemTitleLabel,singleCellConstraint);
                    singleCellConstraint.ipady = 15;
                }
                
                currentDimension = dimensions[i+1];
                currentItems = currentDimension.getItems();
                
                dimensionLabel = new RotatedLabel(currentDimension.getName());
                dimensionVerticalLabel.gridx = currentColumn + xOffset;
                dimensionVerticalLabel.gridy = currentRow + 2;
                dimensionVerticalLabel.ipady = 60;
                dimensionLabel.setHorizontalAlignment(SwingConstants.CENTER);
                dimensionLabel.setDirection(RotatedLabel.Direction.VERTICAL_UP);
                board.add(dimensionLabel, dimensionVerticalLabel);
                
                //Item title labels
                for (int j = 0; j < currentItems.length; j++) {
                    JLabel itemTitleLabel = new JLabel(currentItems[j]);
                    itemTitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    singleCellConstraint.gridx = xOffset + LABEL_TITLE_SIZE;
                    singleCellConstraint.gridy = yOffset + LABEL_TITLE_SIZE + j + 1 ;
                    board.add(itemTitleLabel,singleCellConstraint);
                }
            }
            else if(currentRow == 0) {
                
                currentDimension = dimensions[dimensions.length-1];
                currentItems = currentDimension.getItems();
                dimensionLabel = new RotatedLabel(currentDimension.getName());
                
                //Dimension title label
                dimensionHorizontalLabel.gridx = xOffset +  LABEL_TITLE_SIZE *2;
                dimensionHorizontalLabel.gridy = yOffset;
                board.add(dimensionLabel, dimensionHorizontalLabel);
                
                //Item title labels
                for (int j = 0; j < currentItems.length; j++) {
                    RotatedLabel itemTitleLabel = new RotatedLabel(currentItems[j]);
                    itemTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
                    itemTitleLabel.setDirection(RotatedLabel.Direction.VERTICAL_UP);
                    singleCellConstraint.gridx = xOffset + LABEL_TITLE_SIZE + j + 1;
                    singleCellConstraint.gridy = yOffset + LABEL_TITLE_SIZE ;
                    singleCellConstraint.ipady = 45;
                    board.add(itemTitleLabel,singleCellConstraint);
                    singleCellConstraint.ipady = 15;
                }
            }
            else if (currentColumn == 0) {
                currentDimension = dimensions[dimensions.length-1];
                currentItems = currentDimension.getItems();
                dimensionLabel = new RotatedLabel(currentDimension.getName());
                dimensionVerticalLabel.gridx = currentColumn + xOffset;
                dimensionVerticalLabel.gridy = currentRow + yOffset  ;
                dimensionVerticalLabel.ipady = 60;
                dimensionLabel.setHorizontalAlignment(SwingConstants.LEFT);
                dimensionLabel.setDirection(RotatedLabel.Direction.VERTICAL_UP);
                board.add(dimensionLabel, dimensionVerticalLabel);
                
                //Item title labels
                for (int j = 0; j < currentItems.length; j++) {
                    JLabel itemTitleLabel = new JLabel(currentItems[j]);
                    itemTitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    singleCellConstraint.gridx = xOffset + LABEL_TITLE_SIZE;
                    singleCellConstraint.gridy = yOffset + LABEL_TITLE_SIZE + j + 1 ;
                    board.add(itemTitleLabel,singleCellConstraint);
                }
            }
            
            // If i haven't already drawn this particular side, then i should draw it
            if(!haveIAlreadyDrawnThisParticularSide[currentColumn][currentRow])
            {
                for (int j = 0; j < Configuration.ITEM_PER_DIMENSION_AMOUNT; j++) {
                    for (int k = 0; k < Configuration.ITEM_PER_DIMENSION_AMOUNT; k++) {
                        JLabel cellLabel = new JLabel("?");
                        cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        // The x position is 'j' plus the amount of items already drawn plus the space for both the dimension and items labels.
                        // ditto for the y position
                        singleCellConstraint.gridx = j + xOffset + LABEL_TITLE_SIZE*2;
                        singleCellConstraint.gridy = k + yOffset + LABEL_TITLE_SIZE*2;
                        board.add(cellLabel,singleCellConstraint);
                    }
                }
                haveIAlreadyDrawnThisParticularSide[currentColumn][currentRow] = true;
            }
            
            currentColumn++;
        }
        
        board.setPreferredSize(new Dimension(500,500));
        board.setSize(new Dimension(500,500));
        
        return board;
    }
	
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

	public LogicPuzzle getLogicPuzzle() {
		return logicPuzzle;
	}

	public void setLogicPuzzle(LogicPuzzle logicPuzzle) {
		this.logicPuzzle = logicPuzzle;
	}

}
