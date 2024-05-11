1. Download folder concatenation and place it to any FOLDER_NAME folder (inside there java classes)
2. Download folder tests and place it to the same FOLDER_NAME folder (inside there are tests for classes above)

HOW TO RUN PROGRAM:
1. In IDE: Open your folder as a project in your IDE and run it using instructions of your IDE.
2. In CMD:
	1) COMPILATION:
	(Before you start compiling, make sure Java is installed on your system)
	Open cmd, got to FOLDER_NAME folder and run the following command:

		javac concatenation/*.java
	2) RUNNING:
	In the same folder in cmd run the following command:

		java concatenation/TestConcatenation.java

HOW TO ADD TESTS:
1. In tests folder create 
	a) input FILE_NAME.txt file (for example "my_test.txt")
	b) answer file FILE_NAME_ANS.txt (use the same FILE_NAME as for input file) (for example "my_test_ANS.txt"
2. Open TestConcatenation.java file from concatenation folder and in function main add element "FILE_NAME" to String[] testNames array.

		
