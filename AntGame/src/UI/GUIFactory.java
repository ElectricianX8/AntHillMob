/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UI.EnumHolder.ListMode;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * Helper class with methods that contain premade UI features.
 * 
 */
public class GUIFactory {

    /**
     * Default Constructor.
     */
    public GUIFactory() {

    }

    /**
     * Create a standard label.
     * @param text Text of the label
     * @param align Whether to center the label or not.
     * @return A label.
     */
    public JLabel createLabel(String text, boolean align) {

        JLabel label = new JLabel(text);
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        return label;
    }

    /**
     * Create a standard label with custom size.
     * @param text Text of the label
     * @param size Size of the label
     * @param align Whether to center the label or not.
     * @return A label.
     */
    public JLabel createLabel(String text, float size, boolean align) {

        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(size));
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }

    /**
     * Create a colour label with custom size.
     * @param text Text of the label
     * @param size Size of the label
     * @param align Whether to center the label or not.
     * @param color Colour of the label
     * @return A label.
     */
    public JLabel createColourLabel(String text, float size, boolean align, Color color) {
        JLabel label = createLabel(text, size, align);
        label.setForeground(color);
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }

    /**
     * Create a colour label.
     * @param text Text of the label
     * @param align Whether to center the label or not.
     * @param color Colour of the label
     * @return A label.
     */
    public JLabel createColourLabel(String text, boolean align, Color color) {
        JLabel label = createLabel(text, align);
        label.setForeground(color);
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }

    /**
     * Create a standard black line border.
     * @param top Size of the top side.
     * @param left Size of the left side.
     * @param bottom Size of the bottom side.
     * @param right Size of the right side.
     * @return A black matte border with the specified sizes.
     */
    public MatteBorder createBlackLine(int top, int left, int bottom, int right) {
        return BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK);
    }

    /**
     * Create an invisible box used for padding.
     * @param horizontal Horizontal length
     * @param vertical Vertical length
     * @return An invisible box used for padding.
     */
    public Component createBoxPadding(int horizontal, int vertical) {

        return Box.createRigidArea(new Dimension(horizontal, vertical));

    }

    /**
     * Sets the layout of the specified panel to vertical box layout.
     * @param panel The panel to alter the layout.
     */
    public void setVerticalBoxLayout(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    /**
     * Sets the layout of the specified panel to horizontal box layout.
     * @param panel The panel to alter the layout.
     */
    public void setHorizontalBoxLayout(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }

    /**
     * Set the file filter of the specified file browser.
     * @param fc The file browser to alter.
     * @param mode The mode of the list where the file chooser is contained.
     */
    public void setFileFilter(JFileChooser fc, ListMode mode) {

        if (mode == ListMode.PLAYER) {

            fc.setFileFilter(new FileNameExtensionFilter("Text and ANT documents", "txt", "ant"));
            fc.setAcceptAllFileFilterUsed(false);

        } else {
            fc.setFileFilter(new FileNameExtensionFilter("Text and WORLD documents", "txt", "world"));
            fc.setAcceptAllFileFilterUsed(false);
        }

    }

    /**
     * Create a standard file browser.
     * @return The created file browser.
     */
    public JFileChooser createFileBrowser() {

        JFileChooser fc = new JFileChooser();

        return fc;

    }
    
    /**
     * Create a dropdown box with integers ranging from 0 to the specified parameter.
     * @param numberCount The upper range of the available values.
     * @return The created dropdown box
     */
    public JComboBox createIntDropdownBox(int numberCount){
        
        JComboBox<Integer> numbers = new JComboBox<Integer>();
        for(int i = 1; i<=numberCount; i++){
            numbers.addItem(i);
        }
        
        return numbers;
    }
    
    /**
     * Create a panel containing an integer combo box with a label next to it.
     * @param label Label alongisde the combo box
     * @param numberCount The upper range of the available values in the combo box.
     * @return The panel.
     */
    public JPanel createLabelAndComboPanel(String label, int numberCount){
        
        JPanel panel = new JPanel();
        
        panel.add(createLabel(label, false));
        panel.add(createIntDropdownBox(numberCount));
        
        return panel;
    }
    
    /**
     * Create the panel for inputting world size in world creation.
     * @param heightCount The upper limit on available sizes.
     * @param widthCount The upper limit on available sizes.
     * @return The panel.
     */
    public JPanel createSizeInputPanel(int heightCount, int widthCount){
        
        JPanel panel = new JPanel();
   
        panel.add(createLabel("Height: ", false));
        
        JComboBox<Integer> numbers = new JComboBox<Integer>();
        for(int i = 20; i<=heightCount; i+=10){
            numbers.addItem(i);
        }
        panel.add(numbers);
        panel.add(createLabel("Width: ", false));
        
        JComboBox<Integer> numbers2 = new JComboBox<Integer>();
        for(int i = 20; i<=widthCount; i+=10){
            numbers2.addItem(i);
        }
        
        panel.add(numbers2);
        
        return panel;
    }
    
    /**
     * Create the panel for inputting AntHill size in world creation.
     * @param heightCount The upper limit on available sizes.
     * @param widthCount The upper limit on available sizes.
     * @return The panel.
     */
    public JPanel createAnthillSizeInputPanel(int minSize, int maxSize){
        
        JPanel panel = new JPanel();
   
        panel.add(createLabel("Anthill Size: ", false));
        
        JComboBox<Integer> numbers = new JComboBox<Integer>();
        for(int i = minSize; i<=maxSize; i+=2){
            numbers.addItem(i);
        }
        panel.add(numbers);
        
        return panel;
    }
    
    /**
     * Create a panel with label and dropdown box containing true and false values.
     * @param label Label of the panel
     * @return The panel.
     */
    public JPanel createBooleanComboPanel(String label){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        panel.add(createLabel(label, false));
        JComboBox<String> box = new JComboBox<String>();
        box.addItem("True");
        box.addItem("False");
        panel.add(box);
        return panel;
    }

}
