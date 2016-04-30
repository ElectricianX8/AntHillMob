/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.util.ArrayList;

/**
 *
 * Class representing the hexagonal grid within the game.
 */
public class GameBoard {

    private Cell[][] board;
    private ArrayList<Coordinate> antHills;

    /**
     * Simple constructor, used only for testing
     *
     * @param height
     * @param width
     */
    public GameBoard(int height, int width) {
        //testing only
        board = new Cell[height][width];
        board[0][0] = new Cell(Terrain.ROCKY, null);
        board[0][1] = new Cell(Terrain.ROCKY, null);
        board[1][1] = new Cell(Terrain.ROCKY, null);
    }

    /**
     * The main constructor to initiate the game board.
     *
     * @param board 2D array of cells.
     * @param antHills List containing the coordinates of anthills.
     */
    public GameBoard(Cell[][] board, ArrayList<Coordinate> antHills) {
        this.board = board;
        this.antHills = antHills;
    }

    /**
     * Returns the cell at the specified position.
     *
     * @param position The coordinate of the cell.
     * @return The cell at the specified position.
     */
    public Cell getCellAtPosition(Coordinate position) {

        return board[position.getY()][position.getX()];
    }

    /**
     * Returns whether the specified coordinate contains a rocky cell or not.
     *
     * @param position The coordinate of the cell.
     * @return True if the specified coordinate is a rocky cell, false
     * otherwise.
     */
    public boolean isRocky(Coordinate position) {

        return getCellAtPosition(position).getTerrain() == Terrain.ROCKY;
    }

    /**
     * Returns whether the specified positions contains an ant.
     * @param position The coordinate of the cell.
     * @return True if the cell contains an ant, false otherwise.
     */
    public boolean isAntAt(Coordinate position) {

        return getCellAtPosition(position).isOccupied();
    }

    /**
     * Returns the ant currently occupying the specified coordinate.
     * @param position The coordinate of the cell.
     * @return Returns the ant occupying this cell, null if empty.
     */
    public Ant antAt(Coordinate position) {

        return getCellAtPosition(position).getAnt();
    }

    /**
     * Set the specified ant at the specified coordinate.
     * @param position The coordinate of the cell.
     * @param ant The ant to be inserted.
     */
    public void setAntAt(Coordinate position, Ant ant) {

        getCellAtPosition(position).setOccupied(ant);
    }

    /**
     * Remove any ant occupying the specified coordinate.
     * @param position The coordinate of the cell to clear.
     */
    public void clearAntAt(Coordinate position) {
        
        getCellAtPosition(position).clearAnt();
    }

    /**
     * Returns the food count at the specified coordinate.
     * @param position The coordinate of the cell.
     * @return Returns the amount of food within the specified coordinate.
     */
    public int getFoodAt(Coordinate position) {
        return getCellAtPosition(position).getFoodCount();
    }

    /**
     * Set the specified amount of food at the specified coordinate.
     * @param position The coordinate of the cell.
     * @param foodCount The amount of food to place at the specified coordinate.
     */
    public void setFoodAt(Coordinate position, int foodCount) {
        getCellAtPosition(position).setFoodCount(foodCount);
    }

    /**
     * Set the specified marker of the particular colour at the specified coordinate.
     * @param position The coordinate of the cell.
     * @param colour The colour of the ant placing the marker.
     * @param marker The marker to be placed.
     */
    public void setMarkerAt(Coordinate position, Colour colour, int marker) {

        getCellAtPosition(position).setMarker(colour, marker);
    }

    /**
     * Clear the specified marker of the particular colour at the specified coordinate.
     * @param position The coordinate of the cell.
     * @param colour The colour of the marker.
     * @param marker The marker to be removed.
     */
    public void clearMarkerAt(Coordinate position, Colour colour, int marker) {

        getCellAtPosition(position).clearMarker(colour, marker);
    }

    /**
     * Check whether the specified marker of the particular colour exists at the specified coordinate.
     * @param position The coordinate of the cell.
     * @param colour The colour of the marker.
     * @param marker The marker to find.
     * @return True if the marker exists within the specified coordinate, false otherwise.
     */
    public boolean checkMarkerAt(Coordinate position, Colour colour, int marker) {

        return getCellAtPosition(position).findMarker(colour, marker);
    }

    /**
     * Check whether any markers of the specified colour exist at the specified coordinate.
     * @param position The coordinate of the cell.
     * @param colour The colour of markers to find.
     * @return True if any marker of the specified colours is placed, false otherwise.
     */
    public boolean checkAnyMarkerAt(Coordinate position, Colour colour) {

        return getCellAtPosition(position).findAnyMarker(colour);
    }

    /**
     * Check whether the specified coordinate is the anthill of the particular colour.
     * @param position The coordinate of the cell.
     * @param colour The colour of the anthill to check.
     * @return True if the coordinate is an anthill of the specified colour, false otherwise.
     */
    public boolean isAnthillAt(Coordinate position, Colour colour) {

        return getCellAtPosition(position).isAnthill(colour);
    }

    /**
     * Return the coordinate adjacent of the specified coordinate facing the specified direction.
     * @param position The coordinate the cell.
     * @param direction The direction of the adjacent cell.
     * @return Return the adjacent cell if within the game bounds, null otherwise.
     */
    public Coordinate getAdjacentCell(Coordinate position, int direction) {

        int x = position.getX();
        int y = position.getY();
        boolean isEven;

        
        isEven = y % 2 == 0;         //check if even or not

        if (direction == 0) {
            x += 1;
        } else if (direction == 1) {
            if (isEven) {
                y += 1;
            } else {
                x += 1;
                y += 1;
            }
        } else if (direction == 2) {
            if (isEven) {
                x -= 1;
                y += 1;
            } else {
                y += 1;
            }
        } else if (direction == 3) {
            x -= 1;
        } else if (direction == 4) {
            if (isEven) {
                x -= 1;
                y -= 1;
            } else {
                y -= 1;
            }
        } else if (direction == 5) {
            if (isEven) {
                y -= 1;
            } else {
                x += 1;
                y -= 1;
            }
        }

        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1) {
            return null;
        } else {
            return new Coordinate(y, x);
        }
    }

    /**
     * Returns the 2D cell array used for this game board.
     * @return The 2D cell array used for this game board.
     */
    public Cell[][] getHexGrid() {
        return board;
    }

    /**
     * Returns the list of anthill coordinates within this gameboard.
     * @return The list of anthill coordinates within this gameboard.
     */
    public ArrayList<Coordinate> getAnthills() {
        return antHills;
    }

}
