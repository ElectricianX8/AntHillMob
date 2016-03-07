/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boardlayoutgenerator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ot44
 */
public class BLGMain {
    public static void main(String[] args) {
        BoardLayoutGenerator blg = new BoardLayoutGenerator();
        try {
            blg.generate(123, 30, 1, 5, true, 99);
            blg.out();
        } catch (Exception ex) {
            System.out.println("parameters meant that board couldn't be generated");
            System.out.println("reason: "+ex.getMessage());   
        }
    }
}
