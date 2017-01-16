# EnigmaLite

Driver.java:
This is the executable. It calls methods from the other classes but this is the only thing you have to run Implementation.java This class implements the
terminal interface (and soon the GUI). It recieves user input and calls methods from the other classes to act on it.
Cipher.java:
This class contains the necessary methods for running a simple or complex Caesar Cipher on input text. It is called by the Implementation class,
Symmetric.java:
This class contains the necessary methods for encrypting/decrypting text with a single key, in multiple variations. It is called by the Implementation class.
Asymmetric.java:
This class contains the necessary methods for encrypting text with a public encryption key an a separate, private decryption key, in multiple variations.
It is called by the Implementation class. This class is currently unfinished.
Hash.java:
This class contains the methods for creating hashes out of strings of text. It is called by the Implementation class. This class is currently unfinished.
alphabet.txt:
This document contains variations on the alphabet and is used for testing/debugging encryption methods.
caesar.txt:
This document is a string of text, the first 10 chapters of Caesar's Gallic Wars, that can be used for testing encryption and decryption algorithms.

Running the Program:
Simply compile and run the Driver.java file while all files are in the same folder. Files can be read from anywhere as long as a path is specified, but
currently they can only be written into the EnigmaLitebin folder in the program folder. We are working on a debug mode that converts everything to a single
commandline, as well as a GUI.

For Testing:
Everything can be tested by using the program to encrypt and decrypt text files, especially using edge cases. Use the Driver.java file to run the code. Running this will prompt the user to choose an interface.