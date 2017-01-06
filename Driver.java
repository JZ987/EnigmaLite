import java.util.*;

public class Driver{

    private static Scanner sc;
    private static final String CLEAR_SCREEN =  "\033[2J";

    private static void clearScreen(){
	final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
    }
	
    private static void chooseOne(){
	String choice = sc.next();	
		
	if(choice.equals("GUI")){
	    System.out.println("GUI not currently available");
	    System.out.println("Please choose either <GUI> or <terminal>");
	    chooseOne();
	}else if(choice.equals("terminal") || choice.equals("Terminal")){
	    clearScreen();
	    
	}else{
	    System.out.println("Please choose either <GUI> or <terminal>");
	    chooseOne();
	}
    }
     
    public static void main(String[] args){
	
	sc = new Scanner(System.in);

	clearScreen();
	
        System.out.println("Which interface do you want to use? [GUI/Terminal]");
	System.out.println("(GUI not currently available)");

	chooseOne();


    }
}
