import java.util. *;
import java.io.*;

//Asymmetric, uses a public key to encrypt and a private key to decrypt
public class Asymmetric{

    //fed from interface, else generated and printed
    private static int publicKey;

    //fed from interface, else generated and printed
    private static int privateKey;

    //fed from interface
    private static String originalText;

    //fed from interface
    private static String modifiedText;

    //Constructor
    public Asymmetric(String originalText, String modifiedText, int publicKey, int privateKey){
	this.originalText = originalText;
	this.modifiedText = modifiedText;
	this.publicKey = publicKey;
	this.privateKey - privateKey;
    }

    private static void getPublicKey(){
	if(publicKey == 0){

	}
    }

    private static void getPrivateKey(){
	if(privateKey == 0){

	}
    }
}
    
