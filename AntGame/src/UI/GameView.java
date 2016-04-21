/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UI.EnumHolder.ListMode;
import antgame.Cell;
import antgame.Colour;
import antgame.Game;
import antgame.GameBoard;
import antgame.GameController;
import antgame.InvalidMapTokenException;
import antgame.LexerException;

import antgame.ParsingException;
import antgame.Player;
import antgame.QueueMatch;
import antgame.Result;
import antgame.WorldParser;
import instructions.NotValidInstructionException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JViewport;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class GameView extends JFrame {

    JPanel mainPanel;
    JFileChooser fileBrowser;
    GUIFactory factory;
    JPanel playerListPanel;
    JPanel worldListPanel;
    boolean tournament; //test to check adaptability with single player
    GameController gameController;

    public GameView() {
        super("Setup the game");

        //game panel
        factory = new GUIFactory();
        fileBrowser = factory.createFileBrowser();
        gameController = new GameController();

        mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.add(createStartPanel());

        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(250, 150));
        pack();
        setVisible(true);

    }

    // Start Menu Panel
    public JPanel createStartPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 0, 5, 20));
        panel.setBorder(new EmptyBorder(20, 5, 20, 5));

        JButton button1 = new JButton("Start game");
        button1.addActionListener(new ActionListener() { //button 1

            @Override
            public void actionPerformed(ActionEvent e) {
                tournament = false;
                resetMainPanel();
                createSelectionPanel();
            }

        });
        JButton button2 = new JButton("Start tournament");
        button2.addActionListener(new ActionListener() { //button 2

            @Override
            public void actionPerformed(ActionEvent e) {
                tournament = true;
                resetMainPanel();
                createSelectionPanel();
            }
        });
        panel.add(button1);
        panel.add(button2);

        return panel;
    }

    // Player and World selection Panel
    public void createSelectionPanel() {

        mainPanel.add(factory.createLabel("Input players", 25, true), BorderLayout.NORTH);
        mainPanel.add(createPlayerList(), BorderLayout.CENTER);
        mainPanel.add(createWorldSelectionPanel(), BorderLayout.SOUTH);
        setMinimumSize(new Dimension(400, 500));
        refreshUI();
    }

    private JPanel createWorldSelectionPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(factory.createLabel("Select World", 25, true), BorderLayout.NORTH);
        panel.add(createWorldList(), BorderLayout.CENTER);
        panel.add(createTournamentStartButton(), BorderLayout.SOUTH);

        return panel;

    }

    private JPanel createPlayerList() {

        JPanel panel = new JPanel();

        playerListPanel = new JPanel();
        factory.setVerticalBoxLayout(playerListPanel); //box layout

        //check whether we are setting up a tournament or single game
        if (tournament) {
            playerListPanel.add(createAddButton(ListMode.PLAYER));
        } else {
            playerListPanel.add(createInputPanel(ListMode.PLAYER));
            playerListPanel.add(createInputPanel(ListMode.PLAYER));
        }

        JScrollPane pane = new JScrollPane(playerListPanel);
        pane.setPreferredSize(new Dimension(350, 150));
        panel.add(pane);

        return panel;

    }

    private JPanel createWorldList() {

        JPanel panel = new JPanel();

        worldListPanel = new JPanel();
        factory.setVerticalBoxLayout(worldListPanel);

        if (tournament) {
            worldListPanel.add(createAddButton(ListMode.WORLD));
        } else {
            worldListPanel.add(createInputPanel(ListMode.WORLD));
        }

        JScrollPane pane = new JScrollPane(worldListPanel);
        pane.setPreferredSize(new Dimension(350, 150));
        panel.add(pane);

        return panel;
    }

    private JButton createAddButton(final ListMode mode) {

        final JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel parent = (JPanel) addButton.getParent();
                if (mode == ListMode.PLAYER) {
                    parent.add(createInputPanel(mode), parent.getComponentCount() - 1);
                } else {
                    parent.add(createInputPanel(mode), parent.getComponentCount() - 1);
                }
                parent.revalidate();
                parent.repaint();
            }
        });
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        return addButton;
    }

    private JPanel createInputPanel(final ListMode mode) {

        final JPanel panel = new JPanel();
        final JButton fileButton;

        if (mode == ListMode.PLAYER) {
            //layout for player panel
            panel.setLayout(new GridLayout(0, 2, 10, 5));
            JTextField playerName = new JTextField();
            fileButton = new JButton("Load Brain");
            panel.add(playerName);
        } else {
            //layout for list panel
            factory.setVerticalBoxLayout(panel);
            fileButton = new JButton("Load World");
            fileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        panel.add(fileButton);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setMaximumSize(new Dimension(250, 35));

        fileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fileBrowser.resetChoosableFileFilters();
                factory.setFileFilter(fileBrowser, mode);
                int returnVal = fileBrowser.showOpenDialog(panel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileBrowser.getSelectedFile();

                    markInputPanel(file.getPath(), panel, mode);
                    System.out.println("Opening: " + file.getPath());
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        });
        return panel;
    }

    private void markInputPanel(String filepath, final JPanel parent, final ListMode mode) {

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));
        panel.setPreferredSize(new Dimension(150, 35));

        int removeIndex;
        if (mode == ListMode.PLAYER) {
            removeIndex = 1;
            panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        } else {
            removeIndex = 0;
        }

        JButton deleteButton = new JButton("X");
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel mainParent = (JPanel) parent.getParent();

                //if single game format, handle panels differently
                if (!tournament) {
                    int index = 1;
                    if (mainParent.getComponent(0) instanceof JPanel && (JPanel) mainParent.getComponent(0) == parent) { //determine which box is being deleted for player
                        index = 0;
                    }
                    mainParent.remove(parent);
                    if (mode == ListMode.PLAYER) {
                        mainParent.add(createInputPanel(mode), index);
                    } else {
                        mainParent.add(createInputPanel(mode), 0);
                    }
                } else {
                    mainParent.remove(parent);
                }
                refreshPanel(mainParent);
            }
        });
        panel.add(factory.createLabel(filepath, false));
        panel.add(deleteButton);
        parent.remove(removeIndex);
        parent.add(panel, removeIndex);

        refreshPanel(panel);
    }

    private JPanel createTournamentStartButton() {

        String buttonText;

        if (tournament) {
            buttonText = "Start Tournament";
        } else {
            buttonText = "Start Game";
        }

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton(buttonText); // add functionality

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder msg = new StringBuilder();

                ArrayList<Player> players = processPlayers(msg);
                ArrayList<GameBoard> worlds = processWorlds(msg);
                if (players != null && worlds != null) {
                    //create tournament
                    //next screen
                    //add check for file format here, can't do it in file chooser I think
                    resetMainPanel();
                    if (tournament) {
                        gameController.setTournament(players, worlds);
                        createTournamentPanel();
                    } else {
                        gameController.setSingleGame(players, worlds);
                        gameController.changeDelay(60);
                        //createGameViewPanel(game.getBoard());
                        startGame(false);

                    }

                } else {
                    warningMessage(msg.toString());
                }
            }
        });
        panel.add(button);

        return panel;
    }

    public void startGame(boolean skip) {

        Thread t = new Thread(gameController);
        t.start();

        while (gameController.getCurrentGame() == null); //semi-semaphore

        if (!skip) {
            resetMainPanel();
            updateView(gameController.getCurrentGame());
        } else {
            changeReadyStatus();

            final Timer timer = new Timer(70, null);
            timer.start();
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    if (gameController.getCurrentGame()==null) {
                        timer.stop();
                        resetMainPanel();
                        createTournamentPanel();
                    }
                }
            });


        }

    }

    public void changeReadyStatus() {

        JPanel tourneyPanel = (JPanel) mainPanel.getComponent(0);
        JPanel rightSide = (JPanel) tourneyPanel.getComponent(1);
        JLabel progressLabel = (JLabel)rightSide.getComponent(1);
        progressLabel.setText("In progress");
        progressLabel.setForeground(Color.RED);

    }

    //Tournament Menu Panel
    public void createTournamentPanel() {

        JPanel panel = new JPanel();
        factory.setHorizontalBoxLayout(panel);

        JPanel leftSide = new JPanel();
        factory.setVerticalBoxLayout(leftSide);
        leftSide.add(factory.createLabel("Leaderboard", 25, true));
        leftSide.add(createLeaderboardTable());

        QueueMatch currentMatch = gameController.getNextMatch();

        JPanel rightSide = new JPanel();
        factory.setVerticalBoxLayout(rightSide);
        rightSide.setBorder(new EmptyBorder(0, 10, 0, 20));

        if (currentMatch != null) {
            rightSide.add(factory.createLabel("Current Match", 25, true));
            rightSide.add(factory.createColourLabel("Ready", 16, true, Color.GREEN));
            rightSide.add(Box.createRigidArea(new Dimension(0, 15)));
            rightSide.add(createCurrentMatchPanel(currentMatch));
            rightSide.add(Box.createRigidArea(new Dimension(0, 40)));
            rightSide.add(createTournamentButtonsPanel());
            rightSide.add(Box.createVerticalGlue());
        } else {
            rightSide.add(createCurrentMatchPanel(currentMatch));
            rightSide.add(Box.createRigidArea(new Dimension(0, 40)));
        }

        panel.add(leftSide);
        panel.add(rightSide);

        mainPanel.add(panel);
        setMinimumSize(new Dimension(560, 350));
        refreshUI();
    }

    private JPanel createLeaderboardTable() {

        JPanel panel = new JPanel();
        String[] columnNames = {"Ranking", "Player Name", "Score"};

        ArrayList<Player> players = gameController.getLeaderboard();
        String[][] sampleData = new String[players.size()][];

        for (int i = 0; i < players.size(); i++) {

            String[] playerInfo = new String[3];
            playerInfo[0] = Integer.toString(i);
            playerInfo[1] = players.get(i).getName();
            playerInfo[2] = Integer.toString(players.get(i).getScore());
            sampleData[i] = playerInfo;
        }

        JTable table = new JTable(sampleData, columnNames) {

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
        pane.setPreferredSize(new Dimension(250, 250));
        panel.add(pane);

        return panel;
    }

    private JPanel createCurrentMatchPanel(QueueMatch currentMatch) {

        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);

        JPanel players = new JPanel();
        factory.setHorizontalBoxLayout(players);

        if (currentMatch == null) {
            players.add(factory.createLabel("The tournament is done!", 20, true));

        } else {
            panel.add(factory.createLabel("Map name", true));

            JPanel firstPlayer = new JPanel();
            factory.setVerticalBoxLayout(firstPlayer);
            firstPlayer.add(factory.createLabel(currentMatch.getRedPlayer().getName(), 20, true));
            firstPlayer.add(factory.createColourLabel("Red", 14, true, Color.RED));

            JPanel secondPlayer = new JPanel();
            factory.setVerticalBoxLayout(secondPlayer);
            secondPlayer.add(factory.createLabel(currentMatch.getBlackPlayer().getName(), 20, true));
            secondPlayer.add(factory.createColourLabel("Black", 14, true, Color.BLACK));

            players.add(firstPlayer);
            players.add(Box.createHorizontalGlue());
            players.add(secondPlayer);
        }

        panel.add(players);

        return panel;
    }

    private JPanel createTournamentButtonsPanel() {

        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);
        //panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //panel.setBorder(new EmptyBorder(20,5,20,5));

        JButton button1 = new JButton("Watch game");
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameController.getCurrentGame()==null && gameController.getNextMatch()!=null){
                   gameController.changeDelay(60);
                    startGame(false); 
                }
                else{
                    warningMessage("Please wait until the current match is finished!");
                }
                
            }

        });

        panel.add(button1);
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton button2 = new JButton("Next game");
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.changeDelay(0); //no delay,
                if (gameController.getCurrentGame() == null && gameController.getNextMatch()!=null) {
                    startGame(true);
                } else {
                    warningMessage("Please wait until the current match is finished!");
                }

            }

        });
        panel.add(button2);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        return panel;
    }

    public void updateView(final Game match) {
        //update score here, move score labels to main?

        createGameViewPanel(match.getBoard());
        final Timer timer = new Timer(70, null);
        timer.start();
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                updateMap(match.getBoard().getHexGrid());
                updateScores(match);
                refreshPanel(mainPanel);
                if (match.isGameDone()) {
                    timer.stop();
                    System.out.println("Timer done");
                    resultMessage(gameController.getLastResult());

                }
            }
        });

        //resetMainPanel();
        //createGameViewPanel(board);
        //refreshUI();
        //revalidate();
        //repaint();
        // pack();
    }

    public void createGameViewPanel(GameBoard board) {

        mainPanel.add(createSidePanel(), BorderLayout.WEST);
        mainPanel.add(createMapPanel(board.getHexGrid()), BorderLayout.CENTER);

    }

    public void updateMap(Cell[][] map) {

        JPanel mapPanel = (JPanel) mainPanel.getComponent(1);
        JScrollPane pane = (JScrollPane) mapPanel.getComponent(0);
        pane.getComponent(0).repaint();
    }

    public void updateScores(Game match) {

        JPanel sidePanel = (JPanel) mainPanel.getComponent(0);
        JPanel scorePanel = (JPanel) sidePanel.getComponent(0);

        JPanel scoreP1 = (JPanel) ((JPanel) scorePanel.getComponent(3)).getComponent(0);
        JPanel scoreP2 = (JPanel) ((JPanel) scorePanel.getComponent(6)).getComponent(0);

        ((JLabel) scoreP1.getComponent(2)).setText(Integer.toString(match.getPlayerFoodCount(Colour.RED)));
        ((JLabel) scoreP1.getComponent(3)).setText(Integer.toString(match.getPlayerAntCount(Colour.RED)));
        ((JLabel) scoreP2.getComponent(2)).setText(Integer.toString(match.getPlayerFoodCount(Colour.BLACK)));
        ((JLabel) scoreP2.getComponent(3)).setText(Integer.toString(match.getPlayerAntCount(Colour.BLACK)));
    }

    public JPanel createMapPanel(Cell[][] map) {

        JPanel mapContent = new JPanel(new BorderLayout(5, 5)); //5,5 gap between panels
        mapContent.setPreferredSize(new Dimension(1200, 700)); //x,y
        setMinimumSize(new Dimension(1600, 900)); //min size of frame
        HexagonMap mapGraphic = new HexagonMap(map);

        JScrollPane pane = new JScrollPane(mapGraphic, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(100, 100));
        pane.getVerticalScrollBar().setUnitIncrement(12);
        //pane.setWheelScrollingEnabled(false);
        JViewport port = pane.getViewport();
        port.setScrollMode(JViewport.SIMPLE_SCROLL_MODE); //use simple or backing for performance gains, backing takes more ram
        pane.setViewport(port);

        mapContent.add(pane);

        return mapContent;

    }

    // Game Window panels
    private JPanel createSidePanel() {

        //side panel for the game window
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setPreferredSize(new Dimension(300, 900));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(createScorePanel(), BorderLayout.NORTH);
        if (tournament) {
            panel.add(createLeaderboardPanel(), BorderLayout.CENTER);
            panel.add(createScoreButtonPanel(), BorderLayout.SOUTH);
        } else {
            panel.add(createScoreButtonPanel(), BorderLayout.CENTER);
        }

        return panel;
    }

    private JPanel createScorePanel() {

        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);

        JLabel p1Score = factory.createColourLabel("0", true, new Color(50, 205, 50)); //move to global?
        JLabel p1Ants = factory.createColourLabel("0", true, new Color(50, 205, 50));
        JLabel p2Score = factory.createColourLabel("0", true, new Color(50, 205, 50));
        JLabel p2Ants = factory.createColourLabel("0", true, new Color(50, 205, 50));

        JLabel scoreLabelP1 = factory.createLabel("Score", true);
        JLabel antLabelP1 = factory.createLabel("Ants", true);
        JLabel scoreLabelP2 = factory.createLabel("Score", true);
        JLabel antLabelP2 = factory.createLabel("Ants", true);

        JPanel p1Wrapper = new JPanel();
        JPanel p1Info = new JPanel(new GridLayout(0, 2, 20, 5));
        p1Info.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1Info.add(scoreLabelP1);
        p1Info.add(antLabelP1);
        p1Info.add(p1Score);
        p1Info.add(p1Ants);

        p1Wrapper.add(p1Info);
        p1Wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel p2Wrapper = new JPanel();
        JPanel p2Info = new JPanel(new GridLayout(0, 2, 20, 5));
        p2Info.setAlignmentX(Component.CENTER_ALIGNMENT);
        p2Info.add(scoreLabelP2);
        p2Info.add(antLabelP2);
        p2Info.add(p2Score);
        p2Info.add(p2Ants);
        p2Wrapper.add(p2Info);
        p2Wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);

        QueueMatch currentMatch = gameController.getNextMatch();

        panel.add(factory.createLabel("Current Game", 22, true));
        panel.add(factory.createBoxPadding(0, 20));
        panel.add(factory.createColourLabel(currentMatch.getRedPlayer().getName(), 16, true, Color.RED));
        panel.add(p1Wrapper);
        panel.add(factory.createBoxPadding(0, 25));
        panel.add(factory.createLabel(currentMatch.getBlackPlayer().getName(), 16, true));
        panel.add(p2Wrapper);
        panel.add(factory.createBoxPadding(0, 20));
        panel.setBorder(factory.createBlackLine(0, 0, 1, 0));

        return panel;
    }

    private JPanel createLeaderboardPanel() {

        JPanel panel = new JPanel();
        factory.setVerticalBoxLayout(panel);

        panel.add(createLeaderboardTable());
        panel.setBorder(factory.createBlackLine(0, 0, 1, 0));

        return panel;
    }

    private JPanel createScoreButtonPanel() {

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 1));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        JButton button = new JButton("Fast-Forward");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.changeRunningDelay(0);
            }

        });

        buttonPanel.add(button);

        return buttonPanel;
    }

    // Misc
    public void warningMessage(String s) {
        JOptionPane.showMessageDialog(null, s, "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void resultMessage(Result result) {

        JPanel resultPanel = new JPanel();
        if (!result.gameWasATie()) {
            resultPanel.add(factory.createLabel("Player " + result.getWinner().getName() + " won the game!", 20, true));
        } else {
            resultPanel.add(factory.createLabel("Draw!", 20, true));
        }

        JOptionPane.showMessageDialog(null, resultPanel, "Match is over!",
                JOptionPane.INFORMATION_MESSAGE);

        if (tournament) {
            resetMainPanel();
            createTournamentPanel();
        }

    }

    private boolean isValidName(JTextField field) {

        boolean valid = true;
        if (field.getText().trim().equals("") || field.getText().trim() == null) {
            valid = false;
        }
        return valid;
    }

    private boolean isValidBrain(String filename, StringBuilder errMsg) {

        File file = new File(filename);

        if (!file.exists()) {
            errMsg.append("Error: Brain file " + "\"").append(filename).append("\"" + " does not exist!\n");
            return false;
        } else {
            return true;
        }
    }

    private GameBoard isValidWorld(String filename, StringBuilder errMsg) {

        File file = new File(filename);

        if (!file.exists()) {
            errMsg.append("Error: World file " + "\"").append(filename).append("\"" + " does not exist!\n");
            return null;
        } else {
            WorldParser parser = new WorldParser();

            try {
                GameBoard board = parser.parse(filename);
                return board;
            } catch (IOException e) {
                errMsg.append("Error: Unable to open the world file: ").append(filename).append("\n");
                return null;
            } catch (InvalidMapTokenException e) {
                errMsg.append("Error: Invalid world input: ").append(filename).append("\n");
                return null;
            }
        }

    }

    private ArrayList<Player> processPlayers(StringBuilder errMsg) {

        ArrayList<Player> validPlayers = new ArrayList<Player>();
        boolean valid = true;
        int playerCount = 0;

        for (int i = 0; i < playerListPanel.getComponentCount(); i++) {
            if (playerListPanel.getComponent(i) instanceof JPanel) {
                playerCount++;
                boolean localValid = true;
                JPanel panel = (JPanel) playerListPanel.getComponent(i);

                if (panel.getComponent(0) instanceof JTextField && panel.getComponent(1) instanceof JPanel) {

                    JTextField field = (JTextField) panel.getComponent(0);
                    JPanel childPanel = (JPanel) panel.getComponent(1);
                    String antLabel = ((JLabel) childPanel.getComponent(0)).getText();

                    if (!isValidName(field)) {
                        errMsg.append("Error: Player field #").append(i).append(" has incorrect name!\n");
                        localValid = false;
                    }
                    if (!isValidBrain(antLabel, errMsg)) {
                        //errMsg.append("Error: Brain field #").append(i).append(" contains illegal brain structure!\n");
                        localValid = false;
                    }
                    if (localValid) {
                        Player player = new Player(field.getText(), i, null);
                        try {
                            File file = new File(antLabel);
                            player.loadAntBrain(file);
                            validPlayers.add(player); // PARSE VALID BRAIN HERE DONT FORGET
                        } catch (IOException e) {
                            errMsg.append("Error: Brain field #").append(i).append(" can't be loaded!\n");
                            valid = false;

                        } catch (LexerException | NotValidInstructionException | ParsingException ex) {
                            errMsg.append("Error: Brain field #").append(i).append(" contains illegal brain structure!\n");
                            valid = false;
                        }

                    } else {
                        valid = false;
                    }
                    System.out.println("player check");
                } else {
                    errMsg.append("Error: Do not leave empty players (Player #").append(i).append(")! \n");
                    valid = false;
                }
            }
        }
        if (playerCount < 2) {
            errMsg.append("Error: Minimum of two players is required to start!\n");
            valid = false;
        }
        if (valid && validPlayers.size() > 0) {
            return validPlayers;
        } else {
            return null; //null even if one invalid
        }
    }

    private ArrayList<GameBoard> processWorlds(StringBuilder errMsg) {

        ArrayList<GameBoard> validWorlds = new ArrayList<GameBoard>();
        boolean valid = true;
        int worldCount = 0;

        for (int i = 0; i < worldListPanel.getComponentCount(); i++) {
            boolean localValid = true;
            if (worldListPanel.getComponent(i) instanceof JPanel) {
                worldCount++;
                JPanel panel = (JPanel) worldListPanel.getComponent(i);

                if (panel.getComponent(0) instanceof JPanel) {

                    JLabel worldPath = ((JLabel) ((JPanel) panel.getComponent(0)).getComponent(0));
                    GameBoard board = isValidWorld(worldPath.getText(), errMsg);
                    if (board == null) {
                        localValid = false;
                    }
                    if (localValid) {
                        validWorlds.add(board);
                    }
                    System.out.println("player check");
                } else {
                    errMsg.append("Error: Do not leave empty worlds (World #").append(i).append(")!\n");
                    valid = false;
                }
            }
        }

        if (worldCount < 1) { //at least 1 world needed
            valid = false;
            errMsg.append("Error: Minimum of one world is required to start!\n");
        }

        if (valid && validWorlds.size() > 0) {
            return validWorlds;
        } else {
            return null;
        }
    }

    private void resetMainPanel() { //check this

        mainPanel.removeAll();

    }

    private void refreshUI() { //check this
        revalidate();
        repaint();
        pack();
    }

    private void createPlaceholderPanel() {
        JPanel panel = new JPanel();
        panel.add(factory.createLabel("Test", true));
        mainPanel.add(panel);
        refreshUI();

    }

    private void refreshPanel(JPanel panel) {

        panel.revalidate();
        panel.repaint();
    }

}
