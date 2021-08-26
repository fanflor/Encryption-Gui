import javax.swing.*; // Needed for Swing classes
import java.awt.event.*; // Needed for event listener interface
import java.io.*;
import java.awt.*;//for layout 


public class EventObject extends JFrame
{
	private JButton button1; // Button 1
	private JButton button2; // Button 2
	private JLabel message1;// to make label
	private JLabel message2;// to make label
	private JLabel message3;// to make label
	private JLabel message4;// to make label
	private JLabel messageLabel = new JLabel("Message text will appear here!");
	private JTextField Text1;// to create text field
	private JTextField Text2;
	private JTextField Text3;// to create text field
	private JTextField Text4;
	private JPanel panel; // A panel to hold components
	private final int WINDOW_WIDTH = 1280; // Window width
	private final int WINDOW_HEIGHT =1080; // Window height
	public EventObject()
	{
		// Set the title bar text.
		setTitle("fanflor theodore Gui");

		// Set the size of the window.
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//  close button .
		
		button1 = new JButton("Encrypting");// Create the two buttons.
		button2 = new JButton("Decrypting");

		message1= new JLabel("output flie");//label for message 
		message2= new JLabel("input flie");//label for message 
		message3= new JLabel("message to encrypt");//label for message 
		message4= new JLabel("message to decrypt");//label for message 
		Text1= new JTextField(20);//text box with 100 charters allowed
		Text2= new JTextField(20);//text box with 100 charters allowed
		Text3= new JTextField(20);//text box with 100 charters allowed
		Text4= new JTextField(20);//text box with 100 charters allowed

		// Register an event listener with all 2 buttons.
		button1.addActionListener(new ButtonListener());
		button2.addActionListener(new ButtonListener());

		// Create a panel and add the buttons to it.
		panel = new JPanel();
		GridLayout exlayout=new GridLayout(0,5);
		JPanel compsToExperiment = new JPanel();
		compsToExperiment.setLayout(exlayout);


		///KEY///
		//output file txt 1/////////////
		//// message two/////////////
		//// input file 3///////////
		//messge to decprit4///////
		//////////////////////////

		compsToExperiment.add(message3);
		compsToExperiment.add(Text2);
		compsToExperiment.add(message1);
		compsToExperiment.add(Text1);
		compsToExperiment.add(button1);


		compsToExperiment.add(message2);
		compsToExperiment.add(Text3);
		compsToExperiment.add(message4);
		compsToExperiment.add(Text4);
		compsToExperiment.add(button2);

		// Add the panel to the content pane.
		add(panel);
		panel.add(compsToExperiment, BorderLayout.NORTH);
		// Display the window.
		setVisible(true);
	}


	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// Get the action command.
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("Encrypting"))//encrypt section
			{
				
				String en = "";
				String s = Text2.getText();  //get the Text from the field
				for(int i = 0; i < s.length(); i++) 
				{    
					char c = s.charAt(i);//go through each char
					int shifted = (int)c + 10;		//shift 10 places for DEMO PUR
					en+=(char)shifted;	//turn back into char and add to String
				}
				
				try 
				{
					String outputLoc = Text1.getText(); //get File name
					DataOutputStream output = new DataOutputStream(new FileOutputStream(outputLoc)); //create the File
					output.writeUTF(en);		//write String to file
					output.close();	//close file
					JOptionPane.showMessageDialog(null,"Encrypted the message to "+outputLoc);	//show message in label
				} 
				catch (Exception e1) 
				{ 
					JOptionPane.showMessageDialog(null,"An error has occurred when saving to the file!" + e1.getMessage());//error message
				} 
			}
			else if (actionCommand.equals("Decrypting"))////de-encrypt section
			{
				{		
					String de = "";			//String to return	
					String jumbled = "";	//The jumbled file
					try
					{
						DataInputStream input = new DataInputStream(new FileInputStream(Text3.getText()));   //read in the file
						jumbled = input.readUTF();		//grab the String
						input.close();					//close the file
					} 
					catch (Exception e1) 
					{
						messageLabel.setText("Something terrible has happened when reading file."); //error message
					} 
					for(int i = 0; i < jumbled.length(); i++) 
					{   //go through all the characters
						char c = jumbled.charAt(i);
						int shifted = (int)c - 10;			//unshift/unkey it
						de+=(char)shifted;
					}
					Text4.setText(de);  
					//show the decrypted message
					JOptionPane.showMessageDialog(null,"Successfully decrypted the message!\n"+Text4.getText());//show message
				}
			}	
		}
	}
}