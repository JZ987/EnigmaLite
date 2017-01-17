import java.util.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
    private Container pane;

    private JButton button1, button2, chooseFile, encrypt, decrypt;
    private JLabel label1, label2, fileName, l3, l4, l5, l6, l7;
    private JTextField tf1, tf2, tf3, tf4, tf5;
    private JFileChooser fileChooser;
    private JComboBox<String> chooseMethod;

    private String selector, method, originalText, modifiedText, algoType, password;
    private boolean swapDigits, swapSymbols;
    private int shift;
    private ArrayList<Character> skips;
    private String[] msg = {"Cipher", "Symmetric"};
    
    public void actionPerformed(ActionEvent e){
	if(e.getSource() == chooseMethod){
	    JComboBox cb = (JComboBox)e.getSource();
	    String choice = (String)cb.getSelectedItem();
	    switch (choice) {
	    case "Cipher": method = "cipher";
		break;
	    case "Symmetric": method = "symmetric";
		break;
	    default: method = "cipher";
	    }
	}
	
	String cmd = e.getActionCommand();
	if(cmd.equals("encrypt")){
	    selector = "encrypt";
	    Encrypt();
	}
	if(cmd.equals("decrypt")){
	    selector = "decrypt";
	    Decrypt();
	}
	if(cmd.equals("generate")){
	    conversion();
	}
    }


    public GUI(){
	this.setTitle("Welcome to EnigmaLite!!");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(null);

	label1 = new JLabel("Please choose encryption method first");
	label2 = new JLabel("Do you want to do Encryption or Decryption?");
	
	button1 = new JButton("Encrypt");
	button1.addActionListener(this);
	button1.setActionCommand("encrypt");

	button2 = new JButton("Decrypt");
	button2.addActionListener(this);
	button2.setActionCommand("decrypt");

	chooseMethod = new JComboBox<String>(msg);
	chooseMethod.setSelectedIndex(0);
	chooseMethod.addActionListener(this);
	
	pane.add(button1);
	pane.add(button2);
	pane.add(label1);
	pane.add(label2);
	pane.add(chooseMethod);

	label1.setBounds(150, 15, 400, 50);
	chooseMethod.setBounds(225, 75, 100, 25);
	label2.setBounds(125, 200, 400, 50);
	button1.setBounds(160, 275, 100, 75);
	button2.setBounds(300, 275, 100, 75);
	
	setVisible(true);
    }

        
    private void conversion(){
	if(method.equals("cipher")){
	    //SwapDigit
	    if(tf1.getText().equals("yes")){
		swapDigits = true;
	    }else if(tf1.getText().equals("no")){
		swapDigits = false;
	    }else{
		tf1.setText("Please input <yes> or <no>");
	    }
	    //SwapSymbols
	    if(tf2.getText().equals("yes")){
		swapSymbols = true;
	    }else if(tf2.getText().equals("no")){
		swapSymbols = false;
	    }else{
		tf2.setText("Please input <yes> or <no>");
	    }
	    //Shift
	    try{
		shift = Integer.parseInt(tf3.getText());
	    }catch(Exception e){
		tf3.setText("Please input numbers");
	    }
	    //Skips
	    if(tf4.getText().equals("")){
		skips = new ArrayList<Character>();
	    }else{
		skips = new ArrayList<Character>();
		for (char c : tf4.getText().toCharArray()){
		    skips.add(c);
		}
	    }
	    //Create file
	    createFile(tf5.getText());
	}else if(method.equals("symmetric")){
	    //choose Algorithm Type
	    switch (tf1.getText()) {
	    case "1": algoType = "AES";
		break;
	    case "2": algoType = "ARCFOUR";
		break;
	    case "3": algoType = "Blowfish";
		break;
	    case "4": algoType = "DES";
		break;
	    case "5": algoType = "DESede";
		break;
	    case "6": algoType = "RC2";
		break;
	    default: tf1.setText("Please input a number 1-7");
	    }
	    //Create file
	    createFile(tf2.getText());
	}
    }

    
    //Encryption Purpose
    public void Encrypt(){
	resetPane();
	
        chooseFile = new JButton("Choose File");
	chooseFile.addActionListener(new OpenF());
	fileName = new JLabel("Please choose your text file");

	encrypt = new JButton("Encrypt");
	encrypt.addActionListener(this);
	encrypt.setActionCommand("generate");

	if(method.equals("cipher")){	
	    l3 = new JLabel("Would you like to shift number symbols? <yes> or <no>");
	    tf1 = new JTextField();
	    
	    l4 = new JLabel("Would you like to shift non-alphanumeric symbols? <yes> or <no>");
	    tf2 = new JTextField();
	    
	    l5 = new JLabel("How many shifts do you want?");
	    tf3 = new JTextField();

	    l6 = new JLabel("Input skip symbols if you want. (Don't put spaces between characters)");
	    tf4 = new JTextField();

	    l7 = new JLabel("What would you like the encrypted file name to be?");
	    tf5 = new JTextField();

	    pane.add(l3);
	    pane.add(l4);
	    pane.add(l5);
	    pane.add(l6);
	    pane.add(l7);
	    pane.add(tf1);
	    pane.add(tf2);
	    pane.add(tf3);
	    pane.add(tf4);
	    pane.add(tf5);

	    l3.setBounds(170, 100, 400, 25);
	    l4.setBounds(125, 160, 500, 25);
	    l5.setBounds(250, 220, 400, 25);
	    l6.setBounds(100, 280, 650, 25);
	    l7.setBounds(175, 340, 400, 25);
	    tf1.setBounds(250, 130, 200, 25);
	    tf2.setBounds(250, 190, 200, 25);
	    tf3.setBounds(250, 250, 200, 25);
	    tf4.setBounds(250, 310, 200, 25);
	    tf5.setBounds(250, 370, 200, 25);
	}else if(method.equals("symmetric")){
	    l3 = new JLabel("Which algorithm do you want to use?");
	    l4 = new JLabel("1)AES 2)ARCFOUR 3)Blowfish 4)DES 5)DESede 6)RC2 [input the number]");
	    tf1 = new JTextField(1);

	    l5 = new JLabel("What would you like the encrypted file name to be?");
	    tf2 = new JTextField();

	    pane.add(l3);
	    pane.add(l4);
	    pane.add(l5);
	    pane.add(tf1);
	    pane.add(tf2);

	    l3.setBounds(225, 100, 400, 25);
	    l4.setBounds(100, 130, 600, 25);
	    l5.setBounds(175, 190, 400, 25);
	    tf1.setBounds(250, 160, 200, 25);
	    tf2.setBounds(250, 220, 200, 25);
	}



	pane.add(fileName);
	pane.add(chooseFile);
	pane.add(encrypt);

	fileName.setBounds(260, 10, 200, 50);
	chooseFile.setBounds(290, 65, 125, 25);
	encrypt.setBounds(300, 600, 100, 50);
	
	setVisible(true);
    }


    //Decryption Purpose
    public void Decrypt(){
	resetPane();
	
        chooseFile = new JButton("Choose File");
	chooseFile.addActionListener(new OpenF());
	fileName = new JLabel("Please choose your text file");

	encrypt = new JButton("Decrypt");
	encrypt.addActionListener(this);
	encrypt.setActionCommand("generate");

	if(method.equals("cipher")){	
	    l3 = new JLabel("Did you shift number symbols? <yes> or <no>");
	    tf1 = new JTextField();
	    
	    l4 = new JLabel("Did you shift non-alphanumeric symbols? <yes> or <no>");
	    tf2 = new JTextField();
	    
	    l5 = new JLabel("How many shifts did you do?");
	    tf3 = new JTextField();

	    l6 = new JLabel("Input skip symbols if you did skip any. (Don't put spaces between characters)");
	    tf4 = new JTextField();

	    l7 = new JLabel("What would you like the decrypted file name to be?");
	    tf5 = new JTextField();

	    pane.add(l3);
	    pane.add(l4);
	    pane.add(l5);
	    pane.add(l6);
	    pane.add(l7);
	    pane.add(tf1);
	    pane.add(tf2);
	    pane.add(tf3);
	    pane.add(tf4);
	    pane.add(tf5);

	    l3.setBounds(170, 100, 400, 25);
	    l4.setBounds(125, 160, 500, 25);
	    l5.setBounds(250, 220, 400, 25);
	    l6.setBounds(100, 280, 650, 25);
	    l7.setBounds(175, 340, 400, 25);
	    tf1.setBounds(250, 130, 200, 25);
	    tf2.setBounds(250, 190, 200, 25);
	    tf3.setBounds(250, 250, 200, 25);
	    tf4.setBounds(250, 310, 200, 25);
	    tf5.setBounds(250, 370, 200, 25);
	}else if(method.equals("symmetric")){
	    l3 = new JLabel("Which algorithm did you use to encrypt your file?");
	    l4 = new JLabel("1)AES 2)ARCFOUR 3)Blowfish 4)DES 5)DESede 6)HmacMD5 7)RC2 [input the number]");
	    tf1 = new JTextField(1);

	    l5 = new JLabel("What would you like the decrypted file name to be?");
	    tf2 = new JTextField();

	    pane.add(l3);
	    pane.add(l4);
	    pane.add(l5);
	    pane.add(tf1);
	    pane.add(tf2);

	    l3.setBounds(225, 100, 400, 25);
	    l4.setBounds(100, 130, 600, 25);
	    l5.setBounds(175, 190, 400, 25);
	    tf1.setBounds(250, 160, 200, 25);
	    tf2.setBounds(250, 220, 200, 25);
	}



	pane.add(fileName);
	pane.add(chooseFile);
	pane.add(encrypt);

	fileName.setBounds(260, 10, 200, 50);
	chooseFile.setBounds(290, 65, 125, 25);
	encrypt.setBounds(300, 600, 100, 50);
	
	setVisible(true);
    }


    public class OpenF implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    JFileChooser fc = new JFileChooser();
	    fc.setCurrentDirectory(new java.io.File("./EnigmaLitebin"));
	    
	    int rVal = fc.showOpenDialog(GUI.this);
	    
	    if (rVal == JFileChooser.APPROVE_OPTION) {
		getText("EnigmaLitebin/" + fc.getSelectedFile().getName());
		fileName.setText(fc.getSelectedFile().getName());
		fileName.setBounds(320, 10, 200, 50);
	    }
	    if (rVal == JFileChooser.CANCEL_OPTION) {
		fileName.setText("Please choose a text file");
	    }
	}
    }

    
    private void resetPane(){
	pane.removeAll();
	pane.revalidate();
	pane.repaint();
	if(selector.equals("encrypt")){
	    this.setTitle("Encryption");
	}else if(selector.equals("decrypt")){
	    this.setTitle("Decryption");
	}
	this.setSize(750,750);
    }


    
    private void getText(String file){
	try{
	    Reader reader = new InputStreamReader(new FileInputStream(file));
	    StringBuilder stringBuffer = new StringBuilder();
	    char[] buff = new char[500];
	    for (int charsRead; (charsRead = reader.read(buff)) != -1; ) {
		stringBuffer.append(buff, 0, charsRead);
	    }
	    originalText = stringBuffer.toString();
	}catch(Exception e){
	    System.out.println("Wrong path");
	}
    }

    private void createFile(String file){
	if(!originalText.equals(null)){
	    Cipher cipher = new Cipher(originalText, modifiedText, shift, swapDigits, swapSymbols, skips);
	    Symmetric symmetric = new Symmetric(originalText, modifiedText, algoType);
	    
	    if(method.equals("cipher")){
		if(selector.equals("encrypt")){
		    cipher.encrypt();
		}else if(selector.equals("decrypt")){
		    cipher.decrypt();
		}else{
		    System.out.println("Error");
		}
	    }else if(method.equals("symmetric")){
		if(selector.equals("encrypt")){
		    symmetric.encrypt();
		}else if(selector.equals("decrypt")){
		    symmetric.decrypt();
		}else{
		    System.out.println("Error");
		}
	    }
	    
	    //Writes the file to the EnigmaLite folder
	    try{
		PrintWriter writer = new PrintWriter("EnigmaLitebin/" + file + ".txt", "UTF-8");
		if(method.equals("cipher")){
		    writer.println(cipher.getModifiedText());
		}else if(method.equals("symmetric")){
		    writer.println(symmetric.getModifiedText());
		}
		writer.close();
		if(selector.equals("encrypt")){
		    System.out.println("\nGreat Job!!! Your file was successfully encrypted and placed in the folder EnigmaLitebin!");
		}else if(selector.equals("decrypt")){
		    System.out.println("\nGreat Job!!! Your file was successfully decrypted and placed in the folder EnigmaLitebin!");
		}
	    } catch (IOException e) {
		System.out.println("Unexpected Error: Unable to write to file");
		System.out.println(e);
		System.exit(1);
	    }	    
	}else{
	    System.out.println("Please pick a text file first");
	}
    }
    
}
