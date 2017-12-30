/* GUIExample4Mangat.java
 * Mr. Mangat
 * Introduction to GUI in java.
 * ---> Some random GUI stuff
 */ 


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIExample4MangatPLUS extends JFrame implements ActionListener { 
  
  String[] anArrayGetsDeclaredHere;
  
  JPanel pan1 = new JPanel();  //Create a Panel
  JPanel pan2 = new JPanel();  //Create another Panel
  JPanel pan3 = new JPanel();  //Create yet another Panel
  
  //Create some GUI Components
  JLabel nameLabel = new JLabel("Name: ", JLabel.RIGHT); 
  JLabel messageLabel = new JLabel("You may now quit! ", JLabel.RIGHT); 
  JTextField nameField = new JTextField(10);
  JButton okButton1 = new JButton("Yes");  
  JButton okButton2 = new JButton("No");  
  JButton okButton3 = new JButton("Maybe");  
  JButton okButton4 = new JButton("Maybe not?"); 
  JButton quitButton = new JButton("QUIT"); 
  JComboBox donuts = new JComboBox();
  JTabbedPane menuPane = new JTabbedPane();
  
  // Constructor - Setup your GUI
  public GUIExample4MangatPLUS() { 
    setTitle("Choose your donut!");  //Create a window with a title
    setSize(640, 480);         //Set the size of the window
    setResizable(false);     // Do not allow the user to resize the window
    
    FlowLayout layout1 = new FlowLayout();   //Create a FlowLayout
    GridLayout layout2 = new GridLayout(2,1);   //Create a GridLayout
    BoxLayout layout3 = new BoxLayout(pan2,BoxLayout.Y_AXIS); // Create a BoxLayout for pan1
    setLayout(layout1);  // Set the layout of the FRAME to flow
    
    pan1.setLayout(layout1);  //Set the layout of the 1st PANEL to Flow
    pan2.setLayout(layout2);  //Set the layout of the 2nd PANEL to Box
    
    // Add some components to panel 1
    pan1.add(nameLabel);
    pan1.add(nameField);
    
    //Add items to the combo box before adding it to the panel
    donuts.addItem("Chocolate");
    donuts.addItem("Glazed");
    donuts.addItem("Sparkles");
    pan1.add(donuts);
    
    // Add some components to Panel 2
    
    okButton1.addActionListener(this);
    
    pan2.add(okButton1);
    pan2.add(okButton2);
    pan2.add(okButton3);
    pan2.add(okButton4);
    
    
    // Add some components to Panel 2
    
    quitButton.addActionListener(this);
    
    pan3.setLayout(layout1);
    pan3.add(pan2);
    pan3.add(messageLabel);
    pan3.add(quitButton);
    
    messageLabel.setVisible(false);
    quitButton.setEnabled(false);
    
    
    // Add both PANELS to the MenuPane
    menuPane.add(pan1, "Tab 1");
    menuPane.add(pan3, "Tab 2");
    
    add(menuPane);
    
    //intialize array
    anArrayGetsDeclaredHere = new String[10];
    for (int i=0;i<10;i++) { 
      anArrayGetsDeclaredHere[i]="stuff";
    }
    
    
    
    setVisible(true);  // Display the GUI
  }
  
  
  
  public void actionPerformed(ActionEvent event) { 
    String command = event.getActionCommand();
    
    if (command.equals("Yes")) { 
      messageLabel.setVisible(true);
      quitButton.setEnabled(true);
      repaint();
    } else if (command.equals("QUIT")) { 
      this.dispose();
      
    }
    
    
    
  }
  
  
  
  public static void main(String[] args) {   // MAIN METHOD
    GUIExample4MangatPLUS frame1 = new GUIExample4MangatPLUS();  // Start the GUI
    frame1.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
  }
  
}