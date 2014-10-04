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
    private static final long  serialVersionUID = 96492363195714367L;

    private static final int   LABEL_TITLE_SIZE = 1;

    private LogicPuzzle        logicPuzzle;

    private GridBagConstraints singleCellConstraint;
    private GridBagConstraints dimensionHorizontalLabel;
    private GridBagConstraints dimensionVerticalLabel;

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

        // 1x1
        singleCellConstraint = new GridBagConstraints();
        singleCellConstraint.gridheight = 1;
        singleCellConstraint.gridwidth = 1;
        singleCellConstraint.ipadx = 15;
        singleCellConstraint.ipady = 15;
        // Configuration.ITEM_PER_DIMENSION_AMOUNT x LABEL_TITLE_SIZE
        dimensionHorizontalLabel = new GridBagConstraints();
        dimensionHorizontalLabel.gridwidth = Configuration.ITEM_PER_DIMENSION_AMOUNT;
        dimensionHorizontalLabel.gridheight = LABEL_TITLE_SIZE;
        // LABEL_TITLE_SIZE x Configuration.ITEM_PER_DIMENSION_AMOUNT
        dimensionVerticalLabel = new GridBagConstraints();
        dimensionVerticalLabel.gridwidth = LABEL_TITLE_SIZE;
        dimensionVerticalLabel.gridheight = Configuration.ITEM_PER_DIMENSION_AMOUNT;

        add(createBoard());
        setBounds(10, 10, 960, 580);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createBoard() {
        /*
         * GridBagLayout es un manejador automático de la posición de
         * componentes gráficos. Cada vez que ingrese un JLabel (o cualquier
         * componente) lo ingreso en una cuadrícula invisible y lo posiciono de
         * acuerdo con dos criterios: Posición y "tamaño". La posición es un x,y
         * relativa a la cuadrícula ( [0,0] para el primer elemento, etc). El
         * tamaño es la cantidad de filas y columnas que ocupará el componente.
         * Ambos criterios se mandan con un objeto GridBagConstraints que se
         * modifica justo antes de ingresar el componente. El objeto tiene seis
         * propiedades relevantes:
         * Tamaño -> gridheight, gridwidth 
         * Posición -> gridx, gridy 
         * Tamaño mínimo(px) -> ipadx,ipady
         * 
         * Cuando se agrega un componente, se vé así: add(componente, gridBagConstraints);
         */
        GridBagLayout gBL = new GridBagLayout();
        JPanel board = new JPanel();
        board.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        board.setLayout(gBL);

        LogicDimension[] dimensions = logicPuzzle.getDimensions();

        // Drawing help
        int horizontalThreshold = Configuration.DIMENSION_AMOUNT - 1;
        int currentRow = 0;
        int currentColumn = 0;

        boolean[][] haveIAlreadyDrawnThisParticularSide = new boolean[Configuration.DIMENSION_AMOUNT][Configuration.DIMENSION_AMOUNT];
        // Safety all-false initialization of boolean matrix because reasons
        for (int i = 0; i < haveIAlreadyDrawnThisParticularSide.length; i++) {
            for (int j = 0; j < haveIAlreadyDrawnThisParticularSide.length; j++) {
                haveIAlreadyDrawnThisParticularSide[i][j] = false;
            }
        }

        // Ask me Sunday for this
        int[] xAxisDimensionIndexes = new int[Configuration.DIMENSION_AMOUNT - 1];
        xAxisDimensionIndexes[0] = 0; // First dimension goes first on X
        for (int i = 1; i < xAxisDimensionIndexes.length; i++) {
            xAxisDimensionIndexes[i] = Configuration.DIMENSION_AMOUNT - i;
        }
        int[] yAxisDimensionIndexes = new int[Configuration.DIMENSION_AMOUNT - 1];
        for (int i = 0; i < yAxisDimensionIndexes.length; i++) {
            yAxisDimensionIndexes[i] = i + 1;
        }

        for (int i = 0; i < dimensions.length; i++) {

            if ((currentColumn + 1) > horizontalThreshold) {
                horizontalThreshold--;
                currentRow++;
                currentColumn = 0;
            }

            LogicDimension currentDimensionX = dimensions[xAxisDimensionIndexes[currentColumn]];
            LogicDimension currentDimensionY = dimensions[yAxisDimensionIndexes[currentRow]];

            int xOffset = currentColumn * Configuration.ITEM_PER_DIMENSION_AMOUNT;
            int yOffset = currentRow * Configuration.ITEM_PER_DIMENSION_AMOUNT;

            // I only need to draw the labels when either currentRow or currentColumn is 0
            if (currentRow == 0) {
                drawHorizontalSectionLabels(board, currentDimensionX, xOffset, yOffset);
            }
            if (currentColumn == 0) {
                drawVerticalSectionLabels(board, currentDimensionY, xOffset, yOffset);
            }
            if (!haveIAlreadyDrawnThisParticularSide[currentColumn][currentRow]) {
                drawCellGrid(board, xOffset, yOffset);
                haveIAlreadyDrawnThisParticularSide[currentColumn][currentRow] = true;
            }
            currentColumn++;
        }

        return board;
    }
    
    private void drawHorizontalSectionLabels(JPanel board, LogicDimension currentDimension, int xOffset, int yOffset) {
        String[] currentItems;
        RotatedLabel dimensionLabel;
        currentItems = currentDimension.getItems();
        dimensionLabel = new RotatedLabel(currentDimension.getName());
        // Upwards title label
        dimensionHorizontalLabel.gridx = xOffset + LABEL_TITLE_SIZE * 2;
        dimensionHorizontalLabel.gridy = yOffset;
        board.add(dimensionLabel, dimensionHorizontalLabel);

        // Upwards item title labels
        for (int j = 0; j < currentItems.length; j++) {
            RotatedLabel itemTitleLabel = new RotatedLabel(currentItems[j]);
            itemTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
            itemTitleLabel.setDirection(RotatedLabel.Direction.VERTICAL_UP);
            singleCellConstraint.gridx = xOffset + LABEL_TITLE_SIZE + j + 1;
            singleCellConstraint.gridy = yOffset + LABEL_TITLE_SIZE;
            singleCellConstraint.ipady = 45;
            board.add(itemTitleLabel, singleCellConstraint);
            singleCellConstraint.ipady = 15;
        }
    }

    private void drawVerticalSectionLabels(JPanel board, LogicDimension currentDimension, int xOffset, int yOffset) {
        String[] currentItems;
        RotatedLabel dimensionLabel;
        currentItems = currentDimension.getItems();

        dimensionLabel = new RotatedLabel(currentDimension.getName());
        dimensionVerticalLabel.gridx = xOffset;
        dimensionVerticalLabel.gridy = yOffset + 2;
        dimensionVerticalLabel.ipady = 60;
        dimensionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dimensionLabel.setDirection(RotatedLabel.Direction.VERTICAL_UP);
        board.add(dimensionLabel, dimensionVerticalLabel);

        // Item title labels
        for (int j = 0; j < currentItems.length; j++) {
            JLabel itemTitleLabel = new JLabel(currentItems[j]);
            itemTitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            singleCellConstraint.gridx = xOffset + LABEL_TITLE_SIZE;
            singleCellConstraint.gridy = yOffset + LABEL_TITLE_SIZE + j + 1;
            board.add(itemTitleLabel, singleCellConstraint);
        }
    }
    
    private void drawCellGrid(JPanel board, int xOffset, int yOffset) {
        for (int j = 0; j < Configuration.ITEM_PER_DIMENSION_AMOUNT; j++) {
            for (int k = 0; k < Configuration.ITEM_PER_DIMENSION_AMOUNT; k++) {
                JLabel cellLabel = new JLabel("?");
                cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
                // The x position is 'j' plus the amount of items already drawn
                // plus the space for both the dimension and items labels.
                // ditto for the y position
                singleCellConstraint.gridx = j + xOffset + LABEL_TITLE_SIZE * 2;
                singleCellConstraint.gridy = k + yOffset + LABEL_TITLE_SIZE * 2;
                board.add(cellLabel, singleCellConstraint);
            }
        }
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
