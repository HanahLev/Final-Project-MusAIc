//************************************************************************
// File: BlackKey.java                CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal		      Email: hanah.leventhal@yale.edu
//
// Class: BlackKey
//
//   --------------------
//   
//      This program extends JButton to create a 'black' piano key button.
//
//************************************************************************

import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;

public class BlackKey extends JButton {
    private static final Color YaleBlue = new Color(0, 53, 107);
    private static final int borderThickness = 1;

    public BlackKey() {
        this.setBackground(YaleBlue);
        this.setForeground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderThickness));
    }
}