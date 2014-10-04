package co.uniandes.KM.logicPuzzles.UI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import co.uniandes.KM.logicPuzzles.Configuration;
import co.uniandes.KM.logicPuzzles.mundo.LogicDimension;
import co.uniandes.KM.logicPuzzles.mundo.LogicPuzzle;

public class Tablero extends JFrame implements ActionListener {
    
    /**
     * Generated serial version ID
     */
    private static final long serialVersionUID = 96492363195714367L;
    
    private static final int LABEL_TITLE_SIZE = 1;

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
         * El objeto tiene seis propiedades relevantes:
         * Tamaño -> gridheight, gridwidth
         * Posición -> gridx, gridy
         * Tamaño mínimo(px) -> ipadx,ipady
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
