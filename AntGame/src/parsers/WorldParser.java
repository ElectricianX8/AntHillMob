/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import antgame.Cell;
import antgame.Colour;
import antgame.Coordinate;
import antgame.GameBoard;
import antgame.Terrain;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * Class for scanning and parsing world files.
 */
public class WorldParser {

    /**
     * Default constructor
     */
    public WorldParser() {

    }

    /**
     * Parse the world file to generate a valid game board.
     * @param filename Filename of the file to parse.
     * @return A game board instance.
     * @throws FileNotFoundException File doesn't exist.
     * @throws IOException Error accessing the file.
     * @throws InvalidMapTokenException Invalid world input.
     */
    public GameBoard parse(String filename) throws FileNotFoundException, IOException, InvalidMapTokenException {

        Cell[][] board;

        ArrayList<Coordinate> antHills = new ArrayList<Coordinate>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        int height;
        int width;
        
        try{
            height = Integer.parseInt(reader.readLine());
            width = Integer.parseInt(reader.readLine());
        }
        catch(NumberFormatException e){
            throw new InvalidMapTokenException("Can't process world size input");
        }

        board = new Cell[height][width];

        String line;

        int rowCount = 0;
        while ((line = reader.readLine()) != null) {
            String trim_line = line.replaceAll("\\s+", "");
            for (int i = 0; i < trim_line.length(); i++) {
                Cell cell = scanToken(String.valueOf(trim_line.charAt(i)));
                

                if (cell.isAnthill(Colour.RED)) {
                    antHills.add(new Coordinate(rowCount, i));

                } else if (cell.isAnthill(Colour.BLACK)) {
                    antHills.add(new Coordinate(rowCount, i));

                }
                
                board[rowCount][i] = cell;
            }
            rowCount++;
        }

        return new GameBoard(board, antHills);
    }

    //Scan token and generate a cell if valid
    private Cell scanToken(String token) throws InvalidMapTokenException {

        if (token.equals("#")) { //rocky cell
            Cell cell = new Cell(Terrain.ROCKY, null);
            return cell;
        } else if (token.equals(".")) { //normal clear cell
            Cell cell = new Cell(Terrain.CLEAR, null);
            return cell;
        } else if (token.equals("+")) { //red anthill
            Cell cell = new Cell(Terrain.CLEAR, Colour.RED);
            return cell;
        } else if (token.equals("-")) { //black anthill
            Cell cell = new Cell(Terrain.CLEAR, Colour.BLACK);
            return cell;
        } else if (token.matches("[1-9]")) { //food cell
            Cell cell = new Cell(Terrain.CLEAR, null);
            int foodCount = Integer.parseInt(token);
            cell.setFoodCount(foodCount);
            return cell;
        } else {
            throw new InvalidMapTokenException("Token not found");
        }

    }

}
