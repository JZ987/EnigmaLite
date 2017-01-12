import java.util.*;
import java.io.*;

public class Implementation{


    private static String selector, method, originalText, modifiedText, algoType, password;
    private static boolean swapDigits, swapSymbols;
    private static int shift;
    private static Scanner sc;
    private static ArrayList<Character> skips;

    //debug mode for cipher
    //UNFINISHED
    /*
    public static void cipherDebug(){
	System.out.println("\nPrint file selector swapdigit swapsymbol shift skips");
	String nfile = args[0];
	selector = args[1];
	getText(nfile);
	swapDigits = Boolean.parseBoolean(args[3]);
	swapSymbols = Boolean.parseBoolean(args[4]);
	shift = Integer.parseInt(args[5]);
	for(int i = 6; i < args.length; i++){
	    skips.add(Character.parseChar(args[i]));
	}
    }
    */

    //Begins running the terminal interface
    public static void terminal(){
	originalText = "";
	modifiedText = "";
	sc = new Scanner(System.in);
	chooseWhatToDo();
    }

    //gets text from a specified file
    public static void getText(String file){
	try{
	    Scanner in = new Scanner(new File(file));
	    for(int p = 0; in.hasNext(); p++){
		String word = in.next();
		originalText += word + " ";
	    }
	}catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path!");
	    chooseFile();
	}
    }

    //creates the file and runs the selected methods from cipher to create
    //the encoded/decoded text
    public static void createFile(String fileName){
	Cipher cipher = new Cipher(originalText, modifiedText, shift, swapDigits, swapSymbols, skips);
	Symmetric symmetric = new Symmetric(originalText, modifiedText, algoType);
	
	if(method.equals("cipher")){
	    if(selector.equals("encrypt")){
		cipher.cipher();
	    }else if(selector.equals("decrypt")){
		cipher.decipher();
	    }else{
		System.out.println("Error, restart from chooseWhatToDo");
		chooseWhatToDo();
	    }
	}else if(method.equals("symmetric")){
	    if(selector.equals("encrypt")){
		symmetric.encrypt();
	    }else if(selector.equals("decrypt")){
		symmetric.decrypt();
	    }else{
		System.out.println("Error, restart from chooseWhatToDo");
	    }
	}
	
	//Writes the file to the EnigmaLite folder
	try{
	    PrintWriter writer = new PrintWriter("EnigmaLitebin/" + fileName + ".txt", "UTF-8");
	    if(method.equals("cipher")){
		writer.println(cipher.getModifiedText());
	    }else if(method.equals("symmetric")){
		writer.println(symmetric.getModifiedText());
	    }
	    writer.close();
	    System.out.println("\nGreat Job!!! Your coded file was successfully created and placed in the folder EnigmaLitebin.");
	} catch (IOException e) {
	    System.out.println("Unexpected Error: Invalid Input");
	    System.exit(1);
	}	    
    }

    //choose to do encryption or decryption
    public static void chooseWhatToDo(){
	System.out.println("\nDo you want to <Encrypt> or <Decrypt> your file?");
	String input = sc.nextLine();
	if(input.equals("Encrypt") || input.equals("encrypt")){
	    selector = "encrypt";
	    chooseEncryption();
	}else if(input.equals("Decrypt") || input.equals("decrypt")){
	    selector = "decrypt";
	    chooseEncryption();
	    /*
	}else if(input.equals("debug")){
	    cipherDebug();
	    */
	}else{
	    System.out.println("Unknown command");
	    chooseWhatToDo();
	}
    }

    //input the text file
    public static void chooseFile(){
	if(selector.equals("encrypt")){
	    System.out.println("\nInput the location of the text file you want to be encrypted:");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nInput the location of the text file you want to be decrypted:");
	}
	getText(sc.nextLine());
    }

    //choose which type of encryption do you want to use
    public static void chooseEncryption(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWhich encryption method do you want to use? [Cipher | Symmetric]");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nWhich decryption method do you want to use? [Cipher | Symmetric]");
	}
	String input = sc.nextLine();
	if(input.equals("Cipher") || input.equals("cipher")){
	    method = "cipher";
	    chooseFile();
	    optionCipher();
	}else if(input.equals("Symmetric") || input.equals("symmetric")){
	    method = "symmetric";
	    chooseFile();
	    optionSymmetric();
	}else{
	    System.out.println("<Encryption not known!>");
	    chooseEncryption();
	}
    }

    
    //choose the name of the finishing encrypted text file name
    public static void chooseEncryptedFileName(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWhat would you like the encrypted file name to be? (You don't need to put .txt)");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nWhat would you like the decrypted file name to be? (You don't need to put .txt)");
	}

	//concluding message
	String input = sc.nextLine();
	if(input.equals("")){
	    System.out.println("Please choose a filename");
	    chooseEncryptedFileName();
	}else{
	    createFile(input);
	}
    }

    //Cipher Encryption
    public static void optionCipher(){
	chooseSwapDigit();
	chooseSwapSymbols();
	chooseShift();
	skips = new ArrayList<Character>();
	if(chooseSkipping()){
	    chooseSkipSymbol();
	}
	chooseEncryptedFileName();
    }

    //choose whether you want to shift numbers
    public static void chooseSwapDigit(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWould you like to shift number symbols? <yes|no>");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nDid you shift number symbols? <yes|no>");
	}
	String input = sc.nextLine();
	if(input.equals("yes") || input.equals("y")){
	    swapDigits = true;
	}else if(input.equals("no") || input.equals("n")){
	    swapDigits = false;
	}else{
	    System.out.println("Please type either <yes> or <no>");
	    chooseSwapDigit();
	}
    }

    //choose whether you want to shift non-alphanumeric symbols
    public static void chooseSwapSymbols(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWould you like to shift non-alphanumeric symbols? <yes|no>");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nDid you shift non-alphanumeric symbols? <yes|no>");
	}
	String input = sc.nextLine();
	if(input.equals("yes") || input.equals("y")){
	    swapSymbols = true;
	}else if(input.equals("no") || input.equals("n")){
	    swapSymbols = false;
	}else{
	    System.out.println("Please type either <yes> or <no>");
	    chooseSwapSymbols();
	}
    }

    //choose how many shifts you want
    public static void chooseShift(){
	if(selector.equals("encrypt")){
	    System.out.println("\nHow many shifts do you want?");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nBy how much did you shift your symbols?");
	}
	try{
	    shift = Integer.parseInt(sc.nextLine());
	}catch(NumberFormatException e){
	    System.out.println("Please type numbers!");
	    chooseShift();
	}
    }

    //choose whether you want to skip certain symbols
    public static boolean chooseSkipping(){
	if(selector.equals("encrypt")){
	    System.out.println("\nDo you want to skip certain symbols? <yes|no>");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nDid you skip certain symbols? <yes|no>");
	}
	String input = sc.nextLine();
	if(input.equals("yes") || input.equals("y")){
	    return true;
	}else if(input.equals("no") || input.equals("n")){
	    return false;
	}else{
	    System.out.println("Please pick either <yes> or <no>");
	    return chooseSkipping();
	}
    }

    //if chooseSkipping is true
    public static void chooseSkipSymbol(){
	if(selector.equals("encrypt")){
	    System.out.println("\nInput the letters, symbols, and/or numbers you want the cipher to skip:");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nInput the letters, symbols, and/or numbers you have skipped");
	}
	System.out.println("No spacing is required");
	String input = sc.nextLine();
	for (char c : input.toCharArray()) {
	    skips.add(c);
	}
    }


    
    public static void optionSymmetric(){
	chooseAlgorithm();
	if(selector.equals("encrypt")){
	    System.out.println("\nYour password will be generated randomly at the end.");
	    System.out.println("<Making your own password will be implemented on a later note>");
	}
	chooseEncryptedFileName();
    }

    public static void chooseAlgorithm(){
	if(selector.equals("encrypt")){
	    System.out.println("\nWhich algorithm do you want to use?");
	}else if(selector.equals("decrypt")){
	    System.out.println("\nWhich algorithm did you use to encrypt the text?");
	}
	System.out.println("\n1)AES 2)ARCFOUR 3)Blowfish 4)DES 5)DESede 6)HmacMD5 7)RC2 [input the number]");
	String input = sc.nextLine();
	if(input.equals("1")){
	    algoType = "AES";
	}else if(input.equals("2")){
	    algoType = "ARCFOUR";
	}else if(input.equals("3")){
	    algoType = "Blowfish";
	}else if(input.equals("4")){
	    algoType = "DES";
	}else if(input.equals("5")){
	    algoType = "DESede";
	}else if(input.equals("6")){
	    algoType = "HmacMD5";
	}else if(input.equals("7")){
	    algoType = "RC2";
	}else{
	    System.out.println("Please input a number corresponding with the algorithm you want to use!");
	    chooseAlgorithm();
	}
    }


    /*public static void choosePassword(){
	if(selector.equals("encrypt")){
	    System.out.println("\nDo you want to make your own password or generate a random one? <make> or <generate>");
	    String input = sc.nextLine();
	    if(input.equals("make")){
		System.out.println("Please don't type your password: ");
		password = sc.nextLine();
		System.out.println("Retype password: ");
		if(sc.nextLine().equals(password)){
		    System.out.println("Password created!!");
		}else{
		    System.out.println("Mismatch password!!");
		    choosePassword();
		}
	    }else if(input.equals("generate")){
		System.out.println("\nYour password will be given at the end");
		}
	}else if(selector.equals("decrypt")){
	    System.out.println("\nInput the password: ");
	    password = sc.nextLine();
	}else{
	    System.out.println("Unknown command! Please input <make> or <generate>");
	    choosePassword();
	}
	}*/
    //END
}
