package parsers;

import java.util.Random;

/**
 *
 * Class for generating random game worlds.
 */
public class BoardLayoutGenerator {

    int height;
    int width;
    char[][] board;

    /**
     * Generate a random world map using the specified parameters.
     * @param h The height of the world.
     * @param w The width of the world.
     * @param anthillSize The size of each anthill.
     * @param numOfFoodBlobs The total number of food spread throughout the world.
     * @param randomFoodBlobs Whether to keep uniform food spread or not.
     * @param numOfRocks The total number of non-border rocks within the world.
     * @throws Exception Incorrect parameters.
     */
    public void generate(int h, int w, int anthillSize, int numOfFoodBlobs, boolean randomFoodBlobs, int numOfRocks) throws Exception {
        if (anthillSize%2 == 0) {
            throw new Exception("Ant Hill Size must be an odd number");
        }
        this.height = h;
        this.width = w;
        this.board = new char[height][width];
        addExteriorWalls(this.board);
        addAntHills(this.board, anthillSize);
        for (int i = 0; i < numOfFoodBlobs; i++) {
            addFood(this.board, randomFoodBlobs);
        }
        for (int i = 0; i < numOfRocks; i++) {
            addRockyTerrain(this.board);
        }
        fillIn(this.board);

    }

    /**
     * Generate a random world map using the specified parameters.
     * @param h The height of the world.
     * @param w The width of the world.
     * @throws Exception Incorrect parameters.
     */
    public void generate(int h, int w) throws Exception {
        this.height = h;
        this.width = w;
        this.board = new char[height][width];
        addExteriorWalls(this.board);
        addAntHills(this.board, 7);
        for (int i = 0; i < 11; i++) {
            addFood(this.board, false);
        }
        for (int i = 0; i < 14; i++) {
            addRockyTerrain(this.board);
        }
        fillIn(this.board);

    }

    //Generate the rocky outline for the world.
    private void addExteriorWalls(char[][] ca) {
        for (int i = 0; i < ca.length; i++) {
            for (int j = 0; j < ca[0].length; j++) {
                if (i == 0 || j == 0 || i == (ca.length - 1) || j == (ca[0].length - 1)) {
                    ca[i][j] = '#';
                }
            }
        }
    }

    //Assess whether an anthill can be placed.
    private void addAntHills(char[][] ca, int size) throws Exception {
        if (size%2 == 0) {
            throw new Exception("Odd size anthills only pls");
        }
        boolean possible = false;
        int antHillsPlaced = 0;
        int maxTries = ca.length * ca[0].length;
        while (!possible && (maxTries > 0)) {
            int y = getRandom(ca.length);
            int x = getRandom(ca[0].length);
            possible = checkPosAntHill(x, y, ca, size);
            if (possible) {
                placeAntHill(x, y, ca, antHillsPlaced, size);
                antHillsPlaced++;
            }
            maxTries--;
        }
        if (maxTries <= 0) {
            throw new Exception("couldn't place anthill");
        }
        possible = false;
        maxTries = ca.length * ca[0].length;
        while (!possible && (maxTries > 0)) {
            int y = getRandom(ca.length);
            int x = getRandom(ca[0].length);
            possible = checkPosAntHill(x, y, ca, size);
            if (possible) {
                placeAntHill(x, y, ca, antHillsPlaced, size);
                antHillsPlaced++;
            }
            maxTries--;
        }
        if (maxTries <= 0) {
            throw new Exception("couldn't place anthill");
        }

    }

    //check if the position of the ant hill is valid.
   private boolean checkPosAntHill(int x, int y, char[][] ca, int size) {
        if (x+(((2*size)+(size-2)-1)/2)+1 >= ca[0].length || x-(((2*size)+(size-2)-1)/2)-1 < 0) {
            return false;
        } else if (y+size >= ca.length || y-size <= 0) {
            return false;
        }
        
        for (int i = -size; i <= size; i++) {
            for (int j = -(((2*size+(size-2)-1)/2)-1)-1; j <= (((2*size+(size-2)-1)/2))+1; j++) {
                if (ca[(y + i)][(x + j)] != '\0') {
                    return false;
                }
            }
        }
        return true;
    }

   //Place random anthills
    private void placeAntHill(int x, int y, char[][] ca, int ahp, int size) throws Exception {
        char toMark = '?';
        if (ahp == 0) {
            toMark = '+';
        } else if (ahp == 1) {
            toMark = '-';
        } else {
            throw new Exception("tried to place too many anthills");
        }
                
        double offset = size;
        int n = size;
        offset = Math.ceil(size/2.0);
        
        offset = size-1;
        int left = -((((2*size)+(size-2))-1)/2);
        for (int i = y-n+1; i <= y; i++ ) {
            int endof1stloop = 0;
            for (int j = left; j <= (left+offset); j++) {
                endof1stloop = j;
            }
            int endof2ndloop = 0;
            for (int j = endof1stloop; j < (endof1stloop + n); j++) {
                ca[i][x+j] = toMark;
                endof2ndloop = j;
            }
            for (int j = endof2ndloop+1; j <= ((((2*size)+(size-2))-1)/2); j++) {
            }
            offset--;
            n = n+2;
        }
        offset = 1;
        n = n-4;
        for (int i = y+1; i < y+size; i++ ) {
            int endof1stloop = 0;
            for (int j = left; j <= (left+offset); j++) {
                endof1stloop = j;
            }
            int endof2ndloop = 0;
            for (int j = endof1stloop; j < (endof1stloop + n); j++) {
                ca[i][x+j] = toMark;
                endof2ndloop = j;
            }
            for (int j = endof2ndloop+1; j <= ((((2*size)+(size-2))-1)/2); j++) {
            }
            offset++;
            n = n-2;
        }
    }

    //Return random number
    private int getRandom(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }

    //Output for testing
    private void out() {
        for (char[] ca : board) {
            for (char c : ca) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    //Check whether food can be placed
    private void addFood(char[][] ca, boolean random) throws Exception {
        boolean possible = false;
        int maxTries = ca.length * ca[0].length;
        while (!possible && (maxTries > 0)) {
            int y = getRandom(ca.length);
            int x = getRandom(ca[0].length);
            possible = checkPosFood(x, y, ca);
            if (possible) {
                placeFood(x, y, ca, random);
            }
            maxTries--;
        }
        if (maxTries <= 0) {
            throw new Exception("couldn't place food blob");
        }
    }

    //Assess whether the food can be placed at the specified position
    private boolean checkPosFood(int x, int y, char[][] ca) {
        if (x + 3 >= ca[0].length || x - 3 < 0) {
            return false;
        } else if (y + 3 >= ca.length || y - 3 < 0) {
            return false;
        }
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                if (ca[(y + i)][(x + j)] != '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    //Place food on the game board
    private void placeFood(int x, int y, char[][] ca, boolean random) {
        if (!random) {
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    ca[(y + i)][(x + j)] = '5';
                }
            }
        } else {
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    Integer a = new Integer(getRandom(9));
                    a++;
                    ca[(y + i)][(x + j)] = a.toString().toCharArray()[0];
                }
            }
        }
    }

    // Place rocky terrain
    private void addRockyTerrain(char[][] ca) throws Exception {
        boolean possible = false;
        int maxTries = ca.length * ca[0].length;
        while (!possible && (maxTries > 0)) {
            int y = getRandom(ca.length);
            int x = getRandom(ca[0].length);
            possible = checkPosRock(x, y, ca);
            if (possible) {
                placeRock(x, y, ca);
            }
            maxTries--;
        }
        if (maxTries <= 0) {
            throw new Exception("couldn't place rocky terrain");
        }
    }

    //Check position of the rock
    private boolean checkPosRock(int x, int y, char[][] ca) {
        if (x + 1 >= ca[0].length || x - 1 < 0) {
            return false;
        } else if (y + 1 >= ca.length || y - 1 < 0) {
            return false;
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (ca[(y + i)][(x + j)] != '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    //Place a rock
    private void placeRock(int x, int y, char[][] ca) {
        ca[y][x] = '#';
    }

    //fill in gaps
    private void fillIn(char[][] ca) {
        for (int y = 0; y < ca.length; y++) {
            for (int x = 0; x < ca[0].length; x++) {
                if (ca[y][x] == '\0') {
                    ca[y][x] = '.';
                }
            }
        }
    }
}
