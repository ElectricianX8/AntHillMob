/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import antgame.Cell;
import antgame.Colour;
import antgame.Terrain;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author
 */
public class HexagonMap extends JPanel {

    Cell[][] map;

    public HexagonMap(Cell[][] map) {

        this.map = map;

    }

    public Polygon getHex(int posX, int posY) {
        Polygon hex = new Polygon();

        int hexSize = 6;
        
        for (int i = 0; i < 6; i++) {
            hex.addPoint((int) (posX + (hexSize * Math.cos((1 + i * 2) * Math.PI / 6))),
                    (int) (posY + (hexSize * Math.sin((1 + i * 2) * Math.PI / 6))));
        }
        return hex;

        //11.5 roughly, cast to int so round down to 11
    }

    private void setCellBorder(Graphics2D g) {

        g.setStroke(new BasicStroke(1));
        g.setColor(new Color(0, 0, 0));
    }

    private void setAntCellColour(Graphics2D g, Polygon polygon, Colour colour) {

        if (colour == Colour.RED) {
            g.setColor(Color.RED);
            g.fillPolygon(polygon);
        } else if (colour == Colour.BLACK) {
            g.setColor(Color.BLACK);
            g.fillPolygon(polygon);
        }

        setCellBorder(g); //add border
    }

    private void setCellColour(Graphics2D g, Polygon polygon, Color colour) {

        g.setColor(colour);
        g.fillPolygon(polygon);
        setCellBorder(g); //add border

    }

    //smoothen outlines
    private void enableAntiAliasing(Graphics2D g) {

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setRenderingHints(hints);

    }

    private Polygon getHexAtCoordinates(int h_count, int w_count) {

        final int MINIMUM_Y_CORD = 30;
        final int MINIMUM_X_CORD = 10;
        int wOffset;
        if (h_count % 2 != 0) {
            wOffset = MINIMUM_X_CORD + (w_count * 11);
        } else {
            wOffset = MINIMUM_X_CORD + (w_count * 11) + 6; //11 for 6
        }
        int hOffset = MINIMUM_Y_CORD + (h_count * 9); //9 for 6
        Polygon polygon = getHex(wOffset, hOffset);
        return polygon;

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        enableAntiAliasing(g2);

        int height = map.length;
        int width = map[0].length;

        int h_count = 1;
        int w_count = 1;

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                Polygon polygon = getHexAtCoordinates(h_count, w_count);
                w_count++;

                if(map[i][j].getTerrain() == Terrain.ROCKY){
                    setCellColour(g2, polygon, new Color(139,69,19)); //brown
                }
                else if(map[i][j].getFoodCount() > 0){
                    setCellColour(g2, polygon, Color.GREEN);
                }
                else if(map[i][j].isOccupied()){
                    setAntCellColour(g2, polygon, map[i][j].getAnt().getColour());
                }
                else if(map[i][j].isAnthill(Colour.RED)){
                    setCellColour(g2, polygon, Color.PINK );
                }
                else if(map[i][j].isAnthill(Colour.BLACK)){
                    setCellColour(g2, polygon, Color.GRAY);
                }
                else{
                    setCellColour(g2, polygon, new Color(250,250,210)); //bleached yellow for less epilepsy
                }
                
                


                g2.drawPolygon(polygon);

            }

            h_count++;
            w_count = 1;
        }

        /*
         for (int i = 10; i <= (height*11)+10; i += 11) {
         //x , y cordinates ,size, y
         Polygon polygon = getHex(i, MINIMUM_Y_CORD);

         if (i % 18 == 0) {

         setAntCellColour(g2, polygon, Colour.BLACK);
         g2.drawPolygon(polygon);
         } else {
         setAntCellColour(g2, polygon, Colour.RED);
         g2.drawPolygon(polygon);
         }

         }
         */
    }

}
