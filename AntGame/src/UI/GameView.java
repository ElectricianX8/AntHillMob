/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

import antgame.Cell;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



public class GameView extends JFrame{

    JPanel mainPanel;
    JPanel mapContent;
    JFileChooser fc;
    GUIFactory factory;
    
    
    public GameView(){
        super("Setup the game");
        
        //game panel
        factory = new GUIFactory();
        prepareFileChooser();
        
         mainPanel = new JPanel(new BorderLayout(5,5));
         mainPanel.add(createStartPanel());
        /*
        mapContent = new JPanel(new BorderLayout(5,5)); //5,5 gap between panels
        mapContent.setPreferredSize(new Dimension( 1000,700)); //x,y
          
        mainPanel.add(createSidePanel(), BorderLayout.WEST);
        mainPanel.add(mapContent, BorderLayout.CENTER);
                
        */
        
        getContentPane().add(mainPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250,150)); 
        pack();
        setVisible(true);
        
    }
    
    public JPanel createStartPanel(){
        JPanel panel = new JPanel(new GridLayout(2,0,5,20));
        panel.setBorder(new EmptyBorder(20,5,20,5));
        
        JButton button1 = new JButton("Start game");
        //add action listener for button1
        
        JButton button2 = new JButton("Start tournament");
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMainPanel();
                createTournamentSelectionPanel();
            }
        });
        
        panel.add(button1);
        panel.add(button2);
        
        return panel;
    }
    
    public void createTournamentSelectionPanel(){
        
        mainPanel.add(factory.createLabel("Input players", 25, true), BorderLayout.NORTH);
        mainPanel.add(createPlayerList(), BorderLayout.CENTER);
        mainPanel.add(createWorldSelectionPanel(),BorderLayout.SOUTH);
        mainPanel.setMinimumSize(new Dimension(400,500));
        refreshUI();
    }
    
    
    private void resetMainPanel(){
        
        mainPanel.removeAll();
        
    }
    
    private void refreshUI(){
        
        revalidate();
        repaint();
        pack();
    }
    
    
    
    
    
    
    private JPanel createWorldSelectionPanel(){
        
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(factory.createLabel("Select World", 25, true), BorderLayout.NORTH);
        panel.add(createWorldList(), BorderLayout.CENTER);
        panel.add(createTournamentStartButton(), BorderLayout.SOUTH);
        
        return panel;
        
    }
    
    private JPanel createTournamentStartButton(){
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton("Start Tournament"); // add functionality
        panel.add(button);
        
        return panel;
        
    }
    
    private JPanel createWorldList(){
        
        JPanel panel = new JPanel(); //default flow
        
        JPanel listPanel = new JPanel();
        factory.setVerticalBoxLayout(listPanel); //box layout
        listPanel.add(createAddWorldButton());
        
        JScrollPane pane = new JScrollPane(listPanel);
        pane.setPreferredSize(new Dimension(350,150));
        panel.add(pane);

        return panel;
    }
   
    private JButton createAddWorldButton(){
        
        final JButton addButton = new JButton("+");
         addButton.addActionListener(new ActionListener(){

             @Override
             public void actionPerformed(ActionEvent e) {
                 JPanel parent = (JPanel) addButton.getParent();
                 parent.add(createWorldInput(), parent.getComponentCount()-1);
                 parent.revalidate();
                  parent.repaint();
             }
         });
        
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         
        return addButton;
        
        
    }
    
    private JPanel createWorldInput(){
        
        final JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setMaximumSize(new Dimension(250,35));
        
        
        final JButton fileButton = new JButton("Load World");
        fileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(fileButton);
        fileButton.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(panel);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    
                    markSelectedWorld(file.getPath(), panel);
                    System.out.println("Opening: " + file.getPath());
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        });
        return panel;
    }

    private void markSelectedWorld(String filepath, final JPanel parent){
        
        JPanel panel = new JPanel(new GridLayout(0,2,10,5));

        panel.setPreferredSize(new Dimension(150,35)); //fix to make the list stay in place
        
        JButton deleteButton = new JButton("X"); //functionality here

        
        deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel mainParent = (JPanel) parent.getParent();
                mainParent.remove(parent);
                refreshPanel(mainParent);
            }
        });
        
        
        panel.add(factory.createLabel(filepath, false));
        panel.add(deleteButton);
        parent.remove(0);
        parent.add(panel);

        refreshPanel(panel);
    }
    
    
    
    
    private JPanel createPlayerList(){
        
        JPanel panel = new JPanel(); //default flow
        
        JPanel listPanel = new JPanel();
        
        factory.setVerticalBoxLayout(listPanel); //box layout
        listPanel.add(createAddPlayerButton());
        
        JScrollPane pane = new JScrollPane(listPanel);
        pane.setPreferredSize(new Dimension(350,150));
        
        panel.add(pane);

        return panel;
        
    }
    
    private JButton createAddPlayerButton(){
        
         final JButton addButton = new JButton("+");
         addButton.addActionListener(new ActionListener(){

             @Override
             public void actionPerformed(ActionEvent e) {
                 JPanel parent = (JPanel) addButton.getParent();
                 parent.add(createPlayerInput(), parent.getComponentCount()-1);
                 parent.revalidate();
                  parent.repaint();
             }
         });
        
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         
        return addButton;
    }

    
    private JPanel createPlayerInput(){
        
        final JPanel panel = new JPanel(new GridLayout(0,2,10,5));
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setMaximumSize(new Dimension(250,35));
        

        JTextField playerName = new JTextField();
        final JButton fileButton = new JButton("Load Brain");
        
        panel.add(playerName);
        panel.add(fileButton);
        
        fileButton.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(panel);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    
                    markSelected(file.getPath(), panel);
                    System.out.println("Opening: " + file.getPath());
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        });
        return panel;
        
    }
    
    private void markSelected(String filepath, final JPanel parent){
        
        JPanel panel = new JPanel(new GridLayout(0,2,10,5));
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setPreferredSize(new Dimension(150,35)); //fix to make the list stay in place
        
        JButton deleteButton = new JButton("X"); //functionality here
        deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel mainParent = (JPanel) parent.getParent();
                mainParent.remove(parent);
                refreshPanel(mainParent);
            }
        });
        
        
        panel.add(factory.createLabel(filepath, false));
        panel.add(deleteButton);
        parent.remove(1);
        parent.add(panel,1);

        refreshPanel(panel);
    }
    
    public JPanel createSidePanel(){

        //side panel for the game window
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setPreferredSize(new Dimension( 300,900));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        panel.add(createScorePanel(), BorderLayout.NORTH);
        panel.add(createLeaderboardPanel(), BorderLayout.CENTER);
        panel.add(createScoreButtonPanel(), BorderLayout.SOUTH);
        

        
        return panel;
    }
        
    private JPanel createScoreButtonPanel(){
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));   
        JButton button = new JButton("New Game");
        buttonPanel.add(button);
        
        return buttonPanel;
    }
    
    private void refreshPanel(JPanel panel){
        
        panel.revalidate();
        panel.repaint();
    }
 
    
    
    private JPanel createScorePanel(){
        
        
        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);

        JLabel p1Score = factory.createColourLabel("0", true, new Color(50,205,50)); //move to global?
        JLabel p2Score = factory.createColourLabel("0", true, new Color(50,205,50));

        panel.add(factory.createLabel("Current Game", 22, true));
        panel.add(factory.createBoxPadding(0, 20));
        panel.add(factory.createColourLabel("Player1", 16, true, Color.RED));
        panel.add(p1Score);
        panel.add(factory.createBoxPadding(0,25));
        panel.add(factory.createLabel("Player2",16, true));        
        panel.add(p2Score);
        panel.add(factory.createBoxPadding(0,20));
        panel.setBorder(factory.createBlackLine(0,0,1,0,Color.BLACK));

        return panel;
        
    }
    
    
    public void updateView(Cell[][] map){
                //update score here, move score labels to main?
        mapContent.removeAll();
        
        HexagonMap testRect = new HexagonMap(map);
        //testRect.setPreferredSize(new Dimension(500,500));
        JScrollPane pane = new JScrollPane(testRect,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(100,100));
        pane.getVerticalScrollBar().setUnitIncrement(8);

        
        mapContent.add(pane);
        revalidate();
        repaint();
        pack();
    }

    private JPanel createLeaderboardPanel() {
        
        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);
        

        panel.add(factory.createLabel("Ranking:", 22, false));
        panel.add(factory.createBoxPadding(0, 10));
        
        panel.add(new JLabel("Ranking # | Player Name"));
        panel.add(new JLabel("| Score"));
        panel.add(factory.createBoxPadding(0, 25));
        //create from tournament client        
        
        
        
        for(int i=0; i<10;i++){
        panel.add(factory.createLabel("#" +i + " | Player Name" + i + "| Score" + i, false));
        }
        
        
        panel.setBorder(factory.createBlackLine(0,0,1,0,Color.BLACK));
        return panel;
        
    }
    
    
    
        
    private void prepareFileChooser(){
        
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "TXT documents", "txt");
        fc.setFileFilter(filter);
    }

  
}
