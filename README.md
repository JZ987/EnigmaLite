# EnigmaLite

Driver.java
    This is the executable. It calls methods from the other classes but this is the only thing you have to run
Implementation.java
    This class implements the terminal interface (and soon the GUI). It recieves user input and calls methods from the other classes to act on it
Cipher.java
    This class contains the necessary methods for running a simple or complex Caesar Cipher on input text. It is called by the Implementation class
Symmetric.java
    This class contains the necessary methods for encrypting/decrypting text with a single key, in multiple variations. It is called by the Implementation class
Asymmetric.java
    This class contains the necessary methods for encrypting text with a public encryption key an a separate, private decryption key, in multiple variations.
    It is called by the Implementation class
Hash.java
    This class contains the methods for creating hashes out of strings of text. It is called by the Implementation class
alphabet.txt
    This document contains variations on the alphabet and is used for testing/debugging encryption methods
cipher.txt
    This document is a sample string of test, that can be used for testing encryption and decryption algorithms