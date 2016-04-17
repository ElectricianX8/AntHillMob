/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    
    
    public GUIFactory(){
        
    }
    
    public JLabel createLabel(String text, boolean align){
        
        JLabel label = new JLabel(text);
        if(align){
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        return label;
    }
    
    public JLabel createLabel(String text, float size, boolean align){
        
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(size));
        if(align){
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }
    
    public JLabel createColourLabel(String text, float size, boolean align, Color color){
        JLabel label = createLabel(text, size, align);
        label.setForeground(color);
        if(align){
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }
    
    public JLabel createColourLabel(String text, boolean align, Color color){
        JLabel label = createLabel(text, align);
        label.setForeground(color);
        if(align){
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        return label;
    }
    
    public MatteBorder createBlackLine(int top, int left, int bottom, int right){
        return BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK);
    }
    

    
    public Component createBoxPadding(int horizontal, int vertical){
        
        return Box.createRigidArea(new Dimension(horizontal, vertical));
        
    }

    public void setVerticalBoxLayout(JPanel panel){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
    
    public void setHorizontalBoxLayout(JPanel panel){
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }
    
    public JFileChooser createFileBrowser(){
        
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "TXT and WORLD documents", "txt", "world");
        fc.setFileFilter(filter);
        fc.setAcceptAllFileFilterUsed(false);
        
        return fc;
        
    }
    
    
    
    
    
}
