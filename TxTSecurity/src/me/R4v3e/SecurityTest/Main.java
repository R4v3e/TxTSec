package me.R4v3e.SecurityTest;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

public class Main implements ActionListener{
	static JFrame MainFrame = new JFrame("TxTCrypt");
	static JFrame CryptFrame = new JFrame("Crypt");
	static JFrame DeCryptFrame = new JFrame("Decrypt");
	
	
	static String input = "";
	static String output = "";
	
	static int numb1, numb2,numb3,numb4;
	static String txtpath = ""; 
	static char ch1, ch2;
	
	
	
	private static void Crypt(String TxTFile, int number1, int number2, int number3, int number4, char space, char space2) {
		String Path = TxTFile;
		input = "";
		output = "";    	
    	BufferedReader reader;
		// read Dialog Text
		try {
			reader = new BufferedReader(new FileReader(Path));
			String line = reader.readLine();
			while (line != null) {				
				
				input = input + line + " ";					
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e1) {
			
		}
		int ASCII;		
	
		
		char[] ch = new char[input.length()];
		for(int x=0; x<input.length();x++) {
	        ch[x] = input.charAt(x);
	        ASCII = (int) ch[x];
	        
	        
	        
	        //100  1421 142100  24212 2221 111
	       //
	        
	        
	       if(ch[x]!=' ') {
	    	   ASCII = (((ASCII * number1) + number3) - number4) * number2;
	           output = output + ASCII + space2 ;
	        }else {
	        	output = output + space;
	        }
	       
	      /*  ASCII = (((ASCII / number2 )+ number4) - number3) / number1;
	        char test = (char) ASCII;
	        */
	        if(ch[x]!=' ')    System.out.println(ch[x] + " " + " = " + ASCII);
	    
		}
		
		try {	        		     		
	    	File myObj = new File("Crypted.txt"); // try to create Dialog.txt for created character
	      
	        	  FileWriter myWriter = new FileWriter("Crypted.txt");	        	
	        	  myWriter.write(output);
	              myWriter.close();  																				// close File Writer
	        
	      } catch (IOException e1) {
	
	      }	
		input = "";
		output = "";
		
	}
	
	private static void DeCrypt(String TxTFile, int number1, int number2, int number3, int number4, char space, char space2) {
		input = "";
		output = "";
		String Path = TxTFile;
    	String test = "";
    	char out;
    	int test2;
    	BufferedReader reader;
		// read Dialog Text
		try {
			reader = new BufferedReader(new FileReader(Path));
			String line = reader.readLine();
			while (line != null) {				
				
				input = input + line + " ";					
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e1) {
			System.out.println("Error Reading");	
		}
		char[] ch = new char[input.length()];
		for(int x=0; x<input.length();x++) {
	        ch[x] = input.charAt(x);
	        if(ch[x]!=space2 && ch[x] != space) {
	        	test = test + ch[x];	        
	        }else {
	        	if(ch[x]==space2) {
		        	try {
		        		
		        	    test2 = Integer.parseInt(test);
		        	    test2 = (((test2 / number2 )+ number4) - number3) / number1;
		        	    out = (char)test2;
		        	    output = output + out;
		        	    test = "";
		        	   
		        	}
		        	catch (NumberFormatException e)
		        	{
		        	   test2 = 0;
		        	}
	        	}else {
	        		  output = output + " ";
	        	}
	        	
	        }
	        
	    }
		try {	        		     		
	    	File myObj = new File("DeCrypted.txt"); 						// try to create Dialog.txt for created character
	      
	        	  FileWriter myWriter = new FileWriter("DeCrypted.txt");	        	
	        	  myWriter.write(output);
	              myWriter.close();  																				// close File Writer
	        
	      } catch (IOException e1) {
	
	      }	
		
		input = "";
		output = "";
	}
	
	private static void mainMenu(){
		

		JLabel dialogText = new JLabel("<html><b>Main Menu</b></html>",JLabel.CENTER); // Title Label
		
		JButton option1 = new JButton("Crypt"); // Create Character Button
		JButton option2 = new JButton("Decrypt"); 	// Create Dialog Button 
		
		

		JPanel Window = new JPanel(); // set new Panel for Main Menu  
		
		// Main Menu Frame settings	
		MainFrame.setBounds(1,1,240,250); 							// x,y,width,height
		MainFrame.setBackground(Color.darkGray);					// main frame Background Color
		MainFrame.setResizable(false); 								// turn off Resize option
		MainFrame.setVisible(true);									// set Visible on start 
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Default Close Operation
		
		// Main Menu Panel Settings
		Window.setBounds(1,1 ,240, 250); 		// x,y,width,height  
		Window.setBackground(Color.darkGray);	// Background Color (more important cause visible one)
		Window.setVisible(true);				// set panel visible to true
		Window.setLayout(null);					// set Panel layout to null  (not the best choice, but it work for small project)
				
		// Buttons Settings
		option1.setBounds(20,120,200,20);	// x,y,width,height 
		option2.setBounds(20,150,200,20);	// x,y,width,height 
		
		
		// Title Label Settings		
		dialogText.setBounds(20,50,200,20);				// x,y,width,height 
		dialogText.setVerticalAlignment(JLabel.TOP);	// set vertical alignment 
		dialogText.setForeground(Color.WHITE);			// set Text color 
							
		// add items to panel
		Window.add(dialogText); // add title label
		Window.add(option1);	// add Button 1	
		Window.add(option2);	// add Button 2
		
		
	     
	    option1.addActionListener(new ActionListener(){ 	// button action listener (on button1 click event)
	        public void actionPerformed(ActionEvent e){ 	   
	        	
	        	CryptFrame.setVisible(true);
	        	MainFrame.setVisible(false);
	        }  
	    }); 
	    option2.addActionListener(new ActionListener(){  	// button action listener (on button2 click event)
		    public void actionPerformed(ActionEvent e){  
		    	DeCryptFrame.setVisible(true);
		    	MainFrame.setVisible(false);
	        }  
	    });  
	   
	    
		MainFrame.add(Window); // add Main Menu Panel with all items to Main Frame
	}
	
	private static void CryptWindow() {
		
		
		
		JLabel dialogText = new JLabel("<html><b>Crypt</b></html>",JLabel.CENTER); // Title Label
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter formatter = new NumberFormatter(longFormat);
	
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(-1);
	    formatter.setMaximum(999);
	    formatter.setAllowsInvalid(false);
		
		JFormattedTextField number1 = new JFormattedTextField(formatter);
		JFormattedTextField number2 = new JFormattedTextField(formatter);
		JFormattedTextField number3 = new JFormattedTextField(formatter);
		JFormattedTextField number4 = new JFormattedTextField(formatter);
		
		JTextField Path = new JTextField("DeCrypted.txt");
		JTextField Space = new JTextField();
		JTextField Space2 = new JTextField();
		
		JButton Crypt = new JButton("Crypt");
		
		JPanel Window = new JPanel(); // set new Panel for Main Menu  
		
		
		// Main Menu Frame settings	
		CryptFrame.setBounds(1,1,400,500); 							// x,y,width,height
		CryptFrame.setBackground(Color.darkGray);					// main frame Background Color
		CryptFrame.setResizable(false); 								// turn off Resize option
		CryptFrame.setVisible(false);									// set Visible on start 
		CryptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Default Close Operation
		
		// Main Menu Panel Settings
		Window.setBounds(1,1 ,400, 500); 		// x,y,width,height  
		Window.setBackground(Color.darkGray);	// Background Color (more important cause visible one)
		Window.setVisible(true);				// set panel visible to true
		Window.setLayout(null);					// set Panel layout to null  (not the best choice, but it work for small project)
				
		number1.setBounds(50,50,150,30);
		number2.setBounds(50,100,150,30);
		number3.setBounds(50,150,150,30);
		number4.setBounds(50,200,150,30);
		Path.setBounds(50,250,250,40);
		Space.setBounds(50,300,250,40);
		Space2.setBounds(50,350,250,40);
		Crypt.setBounds(300,450,75, 35);
		
		// Title Label Settings		
		dialogText.setBounds(0,25,400,20);				// x,y,width,height 
		dialogText.setVerticalAlignment(JLabel.TOP);	// set vertical alignment 
		dialogText.setForeground(Color.WHITE);			// set Text color 
							
		// add items to panel
		Window.add(dialogText); // add title label
		Window.add(number1);
		Window.add(number2);
		Window.add(number3);
		Window.add(number4);
	    Window.add(Path);
	    Window.add(Space);
	    Window.add(Space2);
	    Window.add(Crypt);
		CryptFrame.add(Window); // add Main Menu Panel with all items to Main Frame
		
		Space.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Space.getText().length() >= 1 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
		Space2.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Space2.getText().length() >= 1 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
		
		Crypt.addActionListener(new ActionListener(){  	// button action listener (on button2 click event)
		    public void actionPerformed(ActionEvent e){  
		    	txtpath = Path.getText();
		    	try {	        		
	        	numb1 = Integer.parseInt(number1.getText());
	        	numb2 = Integer.parseInt(number2.getText());
	        	numb3 = Integer.parseInt(number3.getText());
	        	numb4 = Integer.parseInt(number4.getText());
	        	}
	        	catch (NumberFormatException e1)
	        	{
	        	numb1 = 0;
	        	numb2 = 0;
	        	numb3 = 0;
	        	numb4 = 0;
	        	} 
		    	ch1 = Space.getText().charAt(0);
		    	ch2 = Space.getText().charAt(0);
		    	Crypt(txtpath,numb1,numb2,numb3,numb4,ch1,ch2);
		    	CryptFrame.setVisible(false);
		    	MainFrame.setVisible(true);
	        }  
	    });  
		
		
	}
	
private static void DeCryptWindow() {
		
		
		
		JLabel dialogText = new JLabel("<html><b>DeCrypt</b></html>",JLabel.CENTER); // Title Label
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter formatter = new NumberFormatter(longFormat);
	
		formatter.setValueClass(Integer.class);
	    formatter.setMinimum(-1);
	    formatter.setMaximum(999);
	    formatter.setAllowsInvalid(false);
		
		JFormattedTextField number1 = new JFormattedTextField(formatter);
		JFormattedTextField number2 = new JFormattedTextField(formatter);
		JFormattedTextField number3 = new JFormattedTextField(formatter);
		JFormattedTextField number4 = new JFormattedTextField(formatter);
		
		JTextField Path = new JTextField("Crypted.txt");
		JTextField Space = new JTextField();
		JTextField Space2 = new JTextField();
		
		JButton Crypt = new JButton("DeCrypt");
		
		JPanel Window = new JPanel(); // set new Panel for Main Menu  
		
		
		// Main Menu Frame settings	
		DeCryptFrame.setBounds(1,1,400,500); 							// x,y,width,height
		DeCryptFrame.setBackground(Color.darkGray);					// main frame Background Color
		DeCryptFrame.setResizable(false); 								// turn off Resize option
		DeCryptFrame.setVisible(false);									// set Visible on start 
		DeCryptFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Default Close Operation
		
		// Main Menu Panel Settings
		Window.setBounds(1,1 ,400, 500); 		// x,y,width,height  
		Window.setBackground(Color.darkGray);	// Background Color (more important cause visible one)
		Window.setVisible(true);				// set panel visible to true
		Window.setLayout(null);					// set Panel layout to null  (not the best choice, but it work for small project)
				
		number1.setBounds(50,50,150,30);
		number2.setBounds(50,100,150,30);
		number3.setBounds(50,150,150,30);
		number4.setBounds(50,200,150,30);
		Path.setBounds(50,250,250,40);
		Space.setBounds(50,300,250,40);
		Space2.setBounds(50,350,250,40);
		Crypt.setBounds(275,450,120, 35);
		
		// Title Label Settings		
		dialogText.setBounds(0,25,400,20);				// x,y,width,height 
		dialogText.setVerticalAlignment(JLabel.TOP);	// set vertical alignment 
		dialogText.setForeground(Color.WHITE);			// set Text color 
							
		// add items to panel
		Window.add(dialogText); // add title label
		Window.add(number1);
		Window.add(number2);
		Window.add(number3);
		Window.add(number4);
	    Window.add(Path);
	    Window.add(Space);
	    Window.add(Space2);
	    Window.add(Crypt);
		DeCryptFrame.add(Window); // add Main Menu Panel with all items to Main Frame
		
		Space.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Space.getText().length() >= 1 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
		Space2.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (Space2.getText().length() >= 1 ) // limit textfield to 3 characters
		            e.consume(); 
		    }  
		});
		
		Crypt.addActionListener(new ActionListener(){  	// button action listener (on button2 click event)
		    public void actionPerformed(ActionEvent e){  
		    	txtpath = Path.getText();
		    	try {	        		
	        	numb1 = Integer.parseInt(number1.getText());
	        	numb2 = Integer.parseInt(number2.getText());
	        	numb3 = Integer.parseInt(number3.getText());
	        	numb4 = Integer.parseInt(number4.getText());
	        	}
	        	catch (NumberFormatException e1)
	        	{
	        	numb1 = 0;
	        	numb2 = 0;
	        	numb3 = 0;
	        	numb4 = 0;
	        	} 
		    	ch1 = Space.getText().charAt(0);
		    	ch2 = Space.getText().charAt(0);
		    	DeCrypt(txtpath,numb1,numb2,numb3,numb4,ch1,ch2);
		    	DeCryptFrame.setVisible(false);
		    	MainFrame.setVisible(true);
	        }  
	    });  
		
		
	}
	
	public static void main(String[] args) {		
			
		
		mainMenu();
		CryptWindow();
		DeCryptWindow();
		
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
