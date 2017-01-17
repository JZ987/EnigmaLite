public class TEST {
    public static void main(String[]args){
	/*
	System.out.println(10 % 26);
	System.out.println(-10 % 26);
	System.out.println(100 % 26);
	System.out.println(-100 % 26);
	*/
	int shift = 0;
	
        //ensures shift is a valid input
	if(Integer.signum(shift % 26) < 0){
	    shift = 26 - (shift % 26);
	}
	else{
	    shift = shift % 26;
	}
	//ensures shift works for digitswap
	if(Integer.signum(shift % 10) < 0){
	    shift = 10 - (shift % 10);
	}
	else{
	    shift = shift % 10;
	}
	//ensures shift works for symbolswap
	if(Integer.signum(shift % 31) < 0){
	    shift = 31 - (shift % 31);
	}
	else{
	    shift = shift % 30;
	}
    }
}
