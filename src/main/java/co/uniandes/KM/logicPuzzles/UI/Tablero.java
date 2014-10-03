package co.uniandes.KM.logicPuzzles.UI;

import javax.swing.*;

import java.awt.event.*;

public class Tablero extends JFrame implements ActionListener {
    
    static final long serialVersionUID = 1L;
    private JLabel    nombreHorizontal, nombreVertical;
    private JButton   fila1A, fila2A, fila3A, fila4A, fila1B, fila2B, fila3B,
            fila4B, fila1C, fila2C, fila3C, fila4C;

    public Tablero(String[][] dimensions) {
        setLayout(null);
        int posX = 30;
        
        String etiquetas[] = new String[12];
        /* porque es esto? */
        for (int i = 0; i < dimensions.length; i++) {
            for (int j = 0; j < dimensions[i].length-1; j++) {
                etiquetas[j+(i*dimensions.length)] = dimensions[i][j+1];
            }
        }
        
        String botones[] = { "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10", "a11", "a12", "a13", "a14", "a15", "a16" };
        JButton filas[] = { fila1A, fila2A, fila3A, fila4A, fila1B, fila2B, fila3B, fila4B, fila1C, fila2C, fila3C, fila4C };

        /* para construir cuadrante a */
        for (int ia = 0; ia < 4; ia++) {
            nombreHorizontal = new JLabel(etiquetas[ia]);
            nombreHorizontal.setBounds(25 + posX, 20, 200, 30);
            add(nombreHorizontal);

            nombreVertical = new JLabel(etiquetas[ia + 8]);
            nombreVertical.setBounds(10, posX + 25, 200, 30);
            add(nombreVertical);

            for (int ja = 0; ja < 4; ja++) {
                filas[ja] = new JButton(botones[ia]);
                filas[ja].setBounds(posX, 50 + (50 * ja), 50, 50);
                add(filas[ja]);
            }

            posX += 50;
        }

        /* para construir cuadrante b */
        for (int ib = 4; ib < 8; ib++) {
            nombreHorizontal = new JLabel(etiquetas[ib]);
            nombreHorizontal.setBounds(posX + 35, 20, 200, 30);
            add(nombreHorizontal);

            for (int jb = 4; jb < 8; jb++) {
                filas[jb] = new JButton(botones[jb]);
                filas[jb].setBounds(posX + 10, (50 * (jb - 3)), 50, 50);
                add(filas[jb]);
            }
            posX += 50;

        }

        /* para construir cuadrante c */
        for (int ic = 8; ic < 12; ic++) {
            nombreHorizontal = new JLabel(etiquetas[ic - 4]);
            nombreHorizontal.setBounds(10, posX - 160, 200, 30);
            add(nombreHorizontal);

            for (int jc = 8; jc < 12; jc++) {
                filas[jc] = new JButton(botones[jc]);
                filas[jc].setBounds(posX - 400, 10 + (50 * (jc - 3)), 50, 50);
                add(filas[jc]);
            }
            posX += 50;

        }

    }

    public static void main(String[] args) {
        DimensionDataInput dDI = new DimensionDataInput();
        dDI.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
