# EnigmaLite

Driver.java:
This is the executable. It calls methods from the other classes but this is the only thing you have to run Implementation.java This class implements the
terminal interface as well as the GUI. It recieves user input and calls methods from the other classes to act on it.

Terminal.java:
This file contains the terminal interface. It is the only interface that works completely with every excryption method in EnigmaLite.

GUI.java
This file contains the GUI for EnigmaLite. It is currently unfinished. It only partially works with symmetrc encryption and does not work at all with ciphers

Cipher.java:
This class contains the necessary methods for running a simple or complex Caesar Cipher on input text. It is called by the Implementation class.

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
This document is a string of sampletext, the first 10 chapters of Caesar's Gallic Wars, that can be used for testing encryption and decryption algorithms.

hamlet.txt:
This document is another string of seample text for testing encryption algorithms. It contains the entire text of Shakespeare's Hamlet

Running the Program:
Simply compile and run the Driver.java file while all files are in the same folder. Files can be read from anywhere as long as a path is specified, but
currently they can only be written into the EnigmaLitebin folder in the program folder. We are working on a debug mode that converts everything to a single
commandline, as well as a GUI. Currently, the program can only read and create .txt files. The program can read things like word files but the text will appear
as gibberish and cannot be used. Creating a file with the same name as an existing file will overwrite that file.

For Testing:
Everything can be tested by using the program to encrypt and decrypt text files, especially when using edge cases as parameters. Use the Driver.java file to
run the code. Running this will prompt the user to choose an interface.

Notes:
Currently, Cipher is in a limbo state. It works if symbols and skips are not used. If they are used, wierd things occur that are not fully explainable
at this time, but will hopefully be worked on soon. However, minimum viable product has been achieved in this current version.