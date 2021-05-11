//************************************************************************
// File: PianoKey.java                CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal		      Email: hanah.leventhal@yale.edu
//
// Class: PianoKey
//
//   --------------------
//   
//      This program extends JButton to create a white piano key button.
//
//************************************************************************

import java.awt.*;
import javax.swing.*;
import javax.swing.JButton;

public class PianoKey extends JButton {
    private static final Color YaleBlue = new Color(0, 53, 107);
    private static final int borderThickness = 1;

    public PianoKey() {
        this.setBackground(Color.WHITE);
        this.setForeground(YaleBlue);
        this.setBorder(BorderFactory.createLineBorder(YaleBlue, borderThickness));
    }
}