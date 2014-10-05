package co.uniandes.KM.logicPuzzles.UI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

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
        
        // 1x1
        singleCellConstraint = new GridBagConstraints();
        singleCellConstraint.gridheight = 1;
        singleCellConstraint.gridwidth = 1;
        singleCellConstraint.ipadx = 15;
        singleCellConstraint.ipady = 15;
        // Configuration.ITEM_PER_DIMENSION_AMOUNT x LABEL_TITLE_SIZE
        dimensionHorizontalLabel = new GridBagConstraints();
        dimensionHorizontalLabel.gridwidth = Configuration.ITEMS_PER_DIMENSION;
        dimensionHorizontalLabel.gridheight = LABEL_TITLE_SIZE;
        // LABEL_TITLE_SIZE x Configuration.ITEM_PER_DIMENSION_AMOUNT
        dimensionVerticalLabel = new GridBagConstraints();
        dimensionVerticalLabel.gridwidth = LABEL_TITLE_SIZE;
        dimensionVerticalLabel.gridheight = Configuration.ITEMS_PER_DIMENSION;
    }

    /**
     * Initialize GridBag constraints, main layout manager, backend and main panels.
     * @param dimensions Array of {@link LogicDimension} grabbed from user input
     */
    public void initialize(LogicDimension[] dimensions) {
        setTitle("Logic Puzzle Engine");
        if(dimensions==null)
            System.exit(1);
        
        this.logicPuzzle = new LogicPuzzle(dimensions);

        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Puzzle grid
        JPanel board = createBoard();
        board.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(board,BorderLayout.WEST);
        pack();
        
        // Control panel
        
        add(createControlPanel(), BorderLayout.CENTER);
        pack();
        
        // Fact list
        JPanel factList = createFactList();
        factList.setPreferredSize(new Dimension((int)(screenSize.getWidth()-getWidth())/2, (int)(screenSize.getHeight()-getHeight())/2));
        add(factList, BorderLayout.EAST);
        pack();
        
        setLocation((int)screenSize.getWidth()/2-getWidth()/2,(int)screenSize.getHeight()/2-getHeight()/2);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates the Board status panel, an intersection matrix with data from {@link LogicPuzzle}.getDimensions()
     * @return {@link JPanel} A panel with the intersection matrix. Dynamic size.
     */
    private JPanel createBoard() {
        LogicDimension[] dimensions = logicPuzzle.getDimensions();

        GridBagLayout gBL = new GridBagLayout();
        JPanel board = new JPanel();
        board.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        board.setLayout(gBL);

        // =================================================================================================
        // START: Initialization of drawing aids
        // =================================================================================================
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
        
        // Arcane formula
        int totalSectionsToDraw = (int)(Configuration.DIMENSION_AMOUNT * Configuration.DIMENSION_AMOUNT - Configuration.DIMENSION_AMOUNT) / 2;
        // =================================================================================================
        // END: Initialization of drawing aids
        // =================================================================================================
        
        // =================================================================================================
        // START: Drawing of the board
        // =================================================================================================
        for (int i = 0; i < totalSectionsToDraw ; i++) {
            if ((currentColumn + 1) > horizontalThreshold) {
                horizontalThreshold--;
                currentRow++;
                currentColumn = 0;
            }
            LogicDimension currentDimensionX = dimensions[xAxisDimensionIndexes[currentColumn]];
            LogicDimension currentDimensionY = dimensions[yAxisDimensionIndexes[currentRow]];
            int xOffset = currentColumn * Configuration.ITEMS_PER_DIMENSION;
            int yOffset = currentRow * Configuration.ITEMS_PER_DIMENSION;
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
        // =================================================================================================
        //END: Drawing of the board
        // =================================================================================================
        return board;
    }
    
    /**
     * Draws the labels for both the parameter supplied dimension and its items.<br>
     * Labels are horizontal for the dimension and vertical for the items.
     * @param board {@link JPanel} to which the labels will be added. Must use a {@link GridBagLayout}
     * @param currentDimension Dimension whose labels will be drawn
     * @param xOffset Cell offset for the layout on the X axis
     * @param yOffset Cell offset for the layout on the Y axis
     */
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

    /**
     * Draws the labels for both the parameter supplied dimension and its items.<br>
     * Labels are vertical for the dimension and horizontal for the items.
     * @param board {@link JPanel} to which the labels will be added. Must use a {@link GridBagLayout}
     * @param currentDimension Dimension whose labels will be drawn
     * @param xOffset Cell offset for the layout on the X axis
     * @param yOffset Cell offset for the layout on the Y axis
     */
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
    
    /**
     * Draws a {@link Configuration}.ITEM_PER_DIMENSION_AMOUNT² cell grid <br>
     * TODO Link cells with backend
     * @param board {@link JPanel} to which the labels will be added. Must use a {@link GridBagLayout}
     * @param xOffset Cell offset for the layout on the X axis
     * @param yOffset Cell offset for the layout on the Y axis
     */
    private void drawCellGrid(JPanel board, int xOffset, int yOffset) {
        for (int j = 0; j < Configuration.ITEMS_PER_DIMENSION; j++) {
            for (int k = 0; k < Configuration.ITEMS_PER_DIMENSION; k++) {
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

    
    /**
     * Creates a scrollable list, a text field and a button to allow input.
     * @return {@link JPanel} A panel to insert the facts.
     */
    private JPanel createFactList() {
        JPanel factList = new JPanel();
        factList.setLayout(new BorderLayout());
        
        
        String[] testData = new String[30];
        for(int i = 0; i < 30; i++)
        {
            testData[ i ] = "I'm a placeholder rule. There are many like me but only i am number "+ (i+1);
        }
        JList<String> facts = new JList<String>(testData);
        JScrollPane scroll = new JScrollPane(facts);

        JPanel control = new JPanel();
        control.setLayout(new BorderLayout());
        control.add(new JTextArea(),BorderLayout.CENTER);   
        control.add(new JButton("Insert"), BorderLayout.SOUTH);
        
        factList.add(scroll, BorderLayout.CENTER);
        factList.add(control, BorderLayout.SOUTH);
        
        factList.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        return factList;
    }
    
    /**
     * Creates a two-button control panel. One button for Step-by-step execution and one for non-stop execution
     * @return {@link JPanel} A two-button panel
     */
    private JPanel createControlPanel(){
        JPanel controlPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2,1);
        gridLayout.setVgap(80);
        controlPanel.setLayout(gridLayout);
        
        JButton buttonStepByStep = new JButton(">");

        
        JButton buttonFullThrottleAhead = new JButton(">>");
        
        controlPanel.add(buttonStepByStep);
        controlPanel.add(buttonFullThrottleAhead);
        controlPanel.setBorder(new EmptyBorder(80, 10, 80, 10));
        return controlPanel;
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
