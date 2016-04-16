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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



public class GameView extends JFrame{

    JPanel mainPanel;
    JPanel mapContent;
    JFileChooser fc;
    GUIFactory factory;
    JPanel playerListPanel;
    JPanel worldListPanel;
    
    
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
        setMinimumSize(new Dimension(400,500));
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
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkPlayerList() && checkWorldList()){ 
                    //create tournament
                    //next screen
                    //add check for file format here, can't do it in file chooser I think
                    resetMainPanel();
                    createTournamentPanel();
                }
                else{
                    if(playerListPanel.getComponents().length < 3){
                        warningMessage("Need at least 2 players to start a tournament!");
                    } else{
                        warningMessage("Incorrect player and/or world information!");
                    }
                    
                }
            }
            
        });
    
        panel.add(button);
        
        return panel;
        
    }
    
    
    private void createTournamentPanel(){
        
        JPanel panel = new JPanel();
        factory.setHorizontalBoxLayout(panel);
        
        
        JPanel leftSide = new JPanel();
        factory.setVerticalBoxLayout(leftSide);
        leftSide.add(factory.createLabel("Leaderboard", 25, true));
        leftSide.add(createLeaderboardTable());
        
        JPanel rightSide = new JPanel();
        factory.setVerticalBoxLayout(rightSide);
        rightSide.setBorder(new EmptyBorder(0,10,0,20));
        rightSide.add(factory.createLabel("Current Match", 25, true));
        rightSide.add(factory.createColourLabel("In progress", 16, true, Color.RED));
        rightSide.add(Box.createRigidArea(new Dimension(0, 15)));
        rightSide.add(createCurrentMatchPanel());
        rightSide.add(Box.createRigidArea(new Dimension(0, 40)));
        rightSide.add(createTournamentButtonsPanel());
        rightSide.add(Box.createVerticalGlue());
        
        panel.add(leftSide);
        panel.add(rightSide);
        
        mainPanel.add(panel);
        setMinimumSize(new Dimension(560,350));
        refreshUI();
    }

    
    private JPanel createCurrentMatchPanel(){
        
        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);
        
        panel.add(factory.createLabel("Map name", true));
        
        JPanel players = new JPanel();
        factory.setHorizontalBoxLayout(players);
        
        JPanel firstPlayer = new JPanel();
        factory.setVerticalBoxLayout(firstPlayer);
        firstPlayer.add(factory.createLabel("Player1", 20, true));
        firstPlayer.add(factory.createColourLabel("Red", 14, true, Color.RED));
        
        JPanel secondPlayer = new JPanel();
        factory.setVerticalBoxLayout(secondPlayer);
        secondPlayer.add(factory.createLabel("Player2", 20, true));
        secondPlayer.add(factory.createColourLabel("Black", 14, true, Color.BLACK));
        
        
        
        players.add(firstPlayer);
        players.add(Box.createHorizontalGlue());
        players.add(secondPlayer);

        
        panel.add(players);
        
        return panel;
    }
    
        
    private JPanel createTournamentButtonsPanel(){
        
        
        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);
        //panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //panel.setBorder(new EmptyBorder(20,5,20,5));
        
        JButton button1 = new JButton("Watch game");
        //add action listener for buttons
        
        JButton button2 = new JButton("Next game");
        
        panel.add(button1);
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(button2);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return panel;
    }
    
    private JPanel createLeaderboardTable(){
        
        JPanel panel = new JPanel();
        String[] columnNames = {"Ranking", "Player Name", "Score"};
        String[][] sampleData = { {"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},{"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},
        {"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},{"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},
        {"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},{"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},
        {"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},{"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},
        {"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},{"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},
        {"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},{"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},
        {"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"},{"1", "Player1", "20"}, {"2", "Player2", "10"},{"3", "Player3", "5"}};
        JTable table = new JTable(sampleData, columnNames){
            
            @Override
            public boolean isCellEditable(int row, int column) { //make all cells uneditable
            return false;
            }
        };
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setShowHorizontalLines(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(250,250));
        panel.add(pane);
        
        return panel;
    }
    
    private boolean isValidName(JTextField field){
        
        boolean valid = true;
        if(field.getText().trim().equals("") || field.getText().trim() == null){
            valid = false;
        }
        return valid;
    }
    
    private boolean checkPlayerList(){
        
        boolean valid = true;
        for(Component c : playerListPanel.getComponents()){
            if(c instanceof JPanel){
                JPanel panel = (JPanel) c;
                if(panel.getComponent(0) instanceof JTextField && isValidName((JTextField)panel.getComponent(0)) && panel.getComponent(1) instanceof JPanel){
                    //valid, maybe process players here as pairs
                    System.out.println("player check");
                }
                else{
                    valid = false;
                }
            }
        }
        if(playerListPanel.getComponents().length < 3 ){
            valid = false; //only add button?
        }
        return valid;
    }
    
    private boolean checkWorldList(){
        
        boolean valid = true;
        for(Component c : worldListPanel.getComponents()){
            if(c instanceof JPanel){
                JPanel panel = (JPanel) c;
                if(panel.getComponent(0) instanceof JPanel){
                    System.out.println("player check");//valid, maybe process players here as pairs
                }
                else{
                    valid = false;
                }
            }
        }
        
        if(worldListPanel.getComponents().length < 2 ){ //at least 1 world needed
            valid = false; //only add button?
        }
        return valid;
        
        
        
    }
    
    
    private JPanel createWorldList(){
        
        JPanel panel = new JPanel(); //default flow
        
        worldListPanel = new JPanel();
        factory.setVerticalBoxLayout(worldListPanel); //box layout
        worldListPanel.add(createAddWorldButton());
        
        JScrollPane pane = new JScrollPane(worldListPanel);
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
        
        playerListPanel = new JPanel();
        
        factory.setVerticalBoxLayout(playerListPanel); //box layout
        playerListPanel.add(createAddPlayerButton());
        
        JScrollPane pane = new JScrollPane(playerListPanel);
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
        

        panel.add(createLeaderboardTable());
        
        
        panel.setBorder(factory.createBlackLine(0,0,1,0,Color.BLACK));
        return panel;
        
    }
    
    private void prepareFileChooser(){
        
        fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "TXT and WORLD documents", "txt", "world");
        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
    }
    
    public void warningMessage(String s) {
        JOptionPane.showMessageDialog(null, s, "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

  
}
