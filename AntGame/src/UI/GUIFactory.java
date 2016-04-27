/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UI.EnumHolder.ListMode;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 *
 */
public class GUIFactory {

    public GUIFactory() {

    }

    public JLabel createLabel(String text, boolean align) {

        JLabel label = new JLabel(text);
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        return label;
    }

    public JLabel createLabel(String text, float size, boolean align) {

        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(size));
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }

    public JLabel createColourLabel(String text, float size, boolean align, Color color) {
        JLabel label = createLabel(text, size, align);
        label.setForeground(color);
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }

    public JLabel createColourLabel(String text, boolean align, Color color) {
        JLabel label = createLabel(text, align);
        label.setForeground(color);
        if (align) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }

    public MatteBorder createBlackLine(int top, int left, int bottom, int right) {
        return BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK);
    }

    public Component createBoxPadding(int horizontal, int vertical) {

        return Box.createRigidArea(new Dimension(horizontal, vertical));

    }

    public void setVerticalBoxLayout(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    public void setHorizontalBoxLayout(JPanel panel) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }

    public void setFileFilter(JFileChooser fc, ListMode mode) {


        if (mode == ListMode.PLAYER) {

            fc.setFileFilter(new FileNameExtensionFilter("Text and ANT documents", "txt", "ant"));
            fc.setAcceptAllFileFilterUsed(false);

        } else {
            fc.setFileFilter(new FileNameExtensionFilter("Text and WORLD documents", "txt", "world"));
            fc.setAcceptAllFileFilterUsed(false);
        }

    }

    public JFileChooser createFileBrowser() {

        JFileChooser fc = new JFileChooser();

        return fc;

    }
    
    
    public JComboBox createIntDropdownBox(int numberCount){
        
        JComboBox<Integer> numbers = new JComboBox<Integer>();
        for(int i = 1; i<=numberCount; i++){
            numbers.addItem(i);
        }
        
        return numbers;
    }
    
    
    
    public JPanel createLabelAndComboPanel(String label, int numberCount){
        
        JPanel panel = new JPanel();
        
        panel.add(createLabel(label, false));
        panel.add(createIntDropdownBox(numberCount));
        
        return panel;
    }
    
    public JPanel createSizeInputPanel(int heightCount, int widthCount){
        
        JPanel panel = new JPanel();
        
        
        
        panel.add(createLabel("Height: ", false));
        panel.add(createIntDropdownBox(heightCount));
        panel.add(createLabel("Width: ", false));
        panel.add(createIntDropdownBox(widthCount));
        
        return panel;
    }
    
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
