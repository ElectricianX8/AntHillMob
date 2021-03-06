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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * Class responsible for rendering the hex grid within the UI.
 */
public class HexagonMap extends JPanel {

    private Cell[][] map;
    private int height;
    private int width;

    /**
     * Constructor
     * @param map The board game map to be rendered within the UI.
     */
    public HexagonMap(Cell[][] map) {

        this.map = map;
        height = (map.length * 9) + 15;
        width = (map[0].length * 11) + 30;

    }

    //Create a hexagon unit at the specified coordinate
    private Polygon getHex(int posX, int posY) {
        Polygon hex = new Polygon();

        int hexSize = 6;
        
        for (int i = 0; i < 6; i++) {
            hex.addPoint((int) (posX + (hexSize * Math.cos((1 + i * 2) * Math.PI / 6))),
                    (int) (posY + (hexSize * Math.sin((1 + i * 2) * Math.PI / 6))));
        }
        return hex;

        //11.5 roughly, cast to int so round down to 11
    }

    //Set black outline around each hexagon
    private void setCellBorder(Graphics2D g) {

        g.setStroke(new BasicStroke(1));
        g.setColor(new Color(0, 0, 0));
    }

    //Set colour of the cell containing ants
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

    //Set colour of a cell.
    private void setCellColour(Graphics2D g, Polygon polygon, Color colour) {

        g.setColor(colour);
        g.fillPolygon(polygon);
        setCellBorder(g); //add border

    }

    //enables anti-aliasing for smoothing outlines
    private void enableAntiAliasing(Graphics2D g) {

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setRenderingHints(hints);

    }

    //Generate a hexagon at the specified coordinates
    private Polygon getHexAtCoordinates(int h_count, int w_count) {

        final int MINIMUM_Y_CORD = 0;
        final int MINIMUM_X_CORD = 10;
        int wOffset;
        if (h_count % 2 != 0) {
            wOffset = MINIMUM_X_CORD + (w_count * 11); //11 for size 6
        } else {
            wOffset = MINIMUM_X_CORD + (w_count * 11) + 6; // +6 is the offset to make hexagons align
        }
        int hOffset = MINIMUM_Y_CORD + (h_count * 9); //9 for 6
        Polygon polygon = getHex(wOffset, hOffset);
        return polygon;

    }
    
    /*
    private Polygon getTriangleAtCoordinates(int h_count, int w_count, int direction){
        
        final int MINIMUM_Y_CORD = 0;
        final int MINIMUM_X_CORD = 10;
        int wOffset;
        if (h_count % 2 != 0) {
            wOffset = MINIMUM_X_CORD + (w_count * 11);
        } else {
            wOffset = MINIMUM_X_CORD + (w_count * 11) + 6; //11 for 6
        }
        int hOffset = MINIMUM_Y_CORD + (h_count * 9); //9 for 6
        Polygon polygon = getTriangle(wOffset, hOffset, direction);
        return polygon;
        
    }
    
    
    private Polygon getTriangle(int posX, int posY, int direction){
        
        Polygon hex = new Polygon();

        int trHeight = 6;
        int trWidth = 6;
        
        
        if(direction==0){
            hex.addPoint((int) posX - trWidth/2, (int) posY-trHeight/2 );
            hex.addPoint((int) posX + trWidth/2, (int) posY);
            hex.addPoint((int) posX - trWidth/2, (int) posY+trHeight/2);
            
            
        }
        //hex.addPoint((int) posX, (int) posY-trHeight/2 );
        //hex.addPoint((int) posX - trWidth/2, (int) posY+trHeight/2);
        //hex.addPoint((int) posX + trWidth/2, (int) posY+trHeight/2);
 
        return hex;
        
    }
    */
    
    /*
    * Returns the size of the map.
    */
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(width, height);
    }

    /*
    * Paint the map UI
    */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        enableAntiAliasing(g2);

        int heightSize = map.length;
        int widthSize = map[0].length;

        int h_count = 1;
        int w_count = 1;


        for (int i = 0; i < heightSize; i++) {

            for (int j = 0; j < widthSize; j++) {

                Polygon polygon = getHexAtCoordinates(h_count, w_count);
                w_count++;

                if(map[i][j].getTerrain() == Terrain.ROCKY){
                    setCellColour(g2, polygon, new Color(139,69,19)); //brown
                }
                else if(map[i][j].isOccupied()){
                    setCellColour(g2, polygon, map[i][j].getAntColour());
                    //Polygon triangle = getTriangleAtCoordinates(h_count, w_count-1, map[i][j].getAnt().getDirection());
                    //g2.setColor(Color.BLUE);
                    //g2.fillPolygon(triangle);
                }
                else if(map[i][j].isAnthill(Colour.RED)){
                    setCellColour(g2, polygon, Color.PINK );
                }
                else if(map[i][j].isAnthill(Colour.BLACK)){
                    setCellColour(g2, polygon, Color.GRAY);
                }
                else if(map[i][j].getFoodCount() > 0){
                    setCellColour(g2, polygon, Color.GREEN);
                    //different colour intensity to represent how much food is contained within a cell?
                }
                else{
                    setCellColour(g2, polygon, new Color(250,250,210)); //bleached yellow for less epilepsy
                }
                g2.drawPolygon(polygon);

            }

            h_count++;
            w_count = 1;
        }
    }

}
