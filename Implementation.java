public class Implementation{

    private String originalText, encryptedText, decryptedText, fileName;
    
    public Implementation(){
        encryptedText = "";
	decryptedText = "";
    }

    public static void getText(){
	try{
	    Scanner in = new Scanner(new File(file));
	    for(int p = 0; in.hasNext(); p++){
		String word = in.next();
		originalText += word + " ";
	    }
	}catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path");
	    System.exit(1);
	}
     }

}
