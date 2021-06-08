package gameGUI;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

import javax.swing.colorchooser.*;

/* ColorChooserDemo.java requires no other files. */
public class ColorChooser extends JPanel implements ChangeListener {

	/**
	 * JColorChooser is the color chooser provided by Oracle
	 */
    protected JColorChooser tcc;
    
    /**
     * A JLabel for displaying text and the changing color during execution
     */
    protected JLabel banner;
    
    /**
     * Variable in which store the color chosen by the user
     */
    private Color chosenColor;
        
    /**
     * Constructor for the class
     */
    public ColorChooser() {
        super(new BorderLayout());

        //Set up the banner at the top of the window
        banner = new JLabel("Pick a color, then click OK to choose it!",
                            JLabel.CENTER);
        banner.setForeground(Color.BLACK);
        banner.setBackground(Color.WHITE);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(600, 170));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);

        //Set up color chooser for setting text color
        tcc = new JColorChooser(banner.getForeground());
        tcc.getSelectionModel().addChangeListener(this);
        
        //Necessary if you want to display only RGB model panel
        modifyJColorChooser();
        
        //Necessary to delete preview panel
        tcc.setPreviewPanel(new JPanel());
        
        add(bannerPanel, BorderLayout.NORTH);
        add(tcc, BorderLayout.CENTER);
    }

    /**
     * Method handling the event ChangeEvent.
     * In particular, it sets the color picked by the user to the background of the upper JLabel
     * and it saves the last chosen color from the JColorChooser in the choosenColor variable.
     * In the end, it calls saveColorInFile in order to store the color in a file.
     * 
     * @param e is the event triggering this method
     */
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        banner.setBackground(newColor);
        
        chosenColor = tcc.getColor();
        saveColorInFile(chosenColor);
    }

    /**
     * Create the GUI and show it. 
     */
    public static void createAndShowGUI() {
    	
        //Create and set up the window.
        JFrame frame = new JFrame("ColorChooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ColorChooser();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        //Button ok lets user definitely confirm the color chosen 
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(10, 60));
        ActionListener listener = new ClickListener(frame);
        okButton.addActionListener(listener);
        frame.add(okButton, BorderLayout.PAGE_END);

        //Display the window.
        frame.pack();
        frame.setVisible(true);      
        
    }

    /**
     * This method is used to modify the JColorChooser panel:
     * initially, it provides different methods for choosing the color (such as HSV and others).
     * The concern here is to establish a color token with the three main component of the
     * RGB color model, so this code simply removes every other unnecessary panel.
     */
    private void modifyJColorChooser() {
    	final AbstractColorChooserPanel[] panels = tcc.getChooserPanels();
    	for (final AbstractColorChooserPanel accp : panels) {
    	    if (!accp.getDisplayName().equals("RGB")) {
    	    tcc.removeChooserPanel(accp);
    	    }
    	}
    }
    
    /**
     * This method saves a color variable in a file.
     * 
     * Note that it is completely platform independent as it uses the "File.separator" field of the File class 
     * and the file is saved in a relative path, so it does not save it outside of the project directory.
     * 
     * @param col is the color to be saved
     */
    public void saveColorInFile(Color col) {
		File fileContainingColor = new File("colors" + File.separator + "colorForPlayer" + ".txt"); 
		try (FileWriter writer = new FileWriter(fileContainingColor)){
			writer.write(col.getRed() + " " + col.getGreen() + " " + col.getBlue());
		} catch (IOException e) {
			return;
		}
		
	}
    
    //For testing only
    public static void main(String...strings ) {
		
		ColorChooser.createAndShowGUI();
	}	
}