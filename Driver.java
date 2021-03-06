import java.util.*;

public class Driver{

    private static Scanner sc;

    private static void clearScreen(){
	final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
    }
    
    private static void chooseOne(){
	String choice = sc.next();	
		
	if(choice.equals("GUI") || choice.equals("gui")){
	    clearScreen();
	    GUI gui = new GUI();
	}else if(choice.equals("terminal") || choice.equals("Terminal") || choice.equals("TERMINAL") || choice.equals("t")){
	    clearScreen();
	    Terminal terminal = new Terminal();
	}else{
	    System.out.println("\nPlease choose either <GUI> or <terminal>");
	    chooseOne();
	}
    }
    
    
    public static void main(String[] args){
	
	sc = new Scanner(System.in);

	clearScreen();
	
        System.out.println("Which interface do you want to use? [GUI/Terminal]");

	chooseOne();
    }
}
