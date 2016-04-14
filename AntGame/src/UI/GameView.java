/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

import antgame.Cell;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * 
 */


public class GameView extends JFrame{

    JPanel mapContent;
    
    
    public GameView(){
        super("Setup the game");
        
        JPanel mainPanel = new JPanel(new BorderLayout(5,5));
        mapContent = new JPanel(new BorderLayout(5,5)); //5,5 gap between panels
        JPanel sideContent = new JPanel(new BorderLayout(5,5));
        JButton button = new JButton("test_button");
        sideContent.add(button, BorderLayout.WEST);
        mainPanel.add(sideContent, BorderLayout.WEST);
        mainPanel.add(mapContent, BorderLayout.CENTER);
        
        getContentPane().add(mainPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250,150)); 
        pack();
        setVisible(true);
        
    }
    
    public void updateView(Cell[][] map){
        mapContent.removeAll();
        HexagonMap testRect = new HexagonMap(map);
        mapContent.add(testRect);
    }
    
}
