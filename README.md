> Software Project Lab 1

# Static Code Analyzer
Static Code Analyzer (SCA) analyzes the ‘C’ source codes
without executing the program. It determines the software 
metrics (such as LOC and Halstead Complexity metrics) and 
detect syntactic code clones.


## Table of Contents
* [Project Descriptioin](#project-description)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Project Status](#project-status)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)
* [License](#license)


## Project Description
My software tool named Static Code Analyzer (SCA) analyzes the ‘C’ source codes
without executing the program. It determines the software metrics (such as LOC and
Halstead Complexity metrics) and detect syntactic code clones.

Static analysis of source code is very important and useful analysis for software quality
and security. Software developers use static code analysis for better understanding of
the software, maintaining coding standards, identifying potential bugs, detecting code
clones and code smells, code simplification and sanitizing, improving application
performance, better resource utilization, etc.

The whole project consists of two major parts software metrics generation and clone
detection. In metrics part to calculate the LOC metrics(such as physical lines of code,
number of comment lines and number of logical statements, etc.), I have followed the
counting rules of SLOC according to SEI and the IEEE’s standards. At first, a single C
source code file is taken as input from the user. The user can input the file path or
choose a file to be processed. Following the counting rules and using several string
manipulations (linear searching, substring generation, string matching) the LOC metrics
are then generated.

For Halstead metrics (such as distinct operators and operands, the total number of
operators and operators) and Halstead measures (such as program vocabulary, length,
volume, difficulty, effort, time and number of delivered bugs) generation I have followed
several steps. At first the preprocessing of source code (such as removal of blank lines,
comments, extra spaces, function declarations and hash directives) is done. Then
tokenize the preprocessed code and get tokens of all types (such as character tokens,
string tokens, number tokens, identifier tokens, etc.) which are later categorized into
two major types of tokens (Operator token and Operator token). Lastly, counting tokens
and calculating the Halstead measures we get the Halstead metrics.

Clone detection is another core part of my project. To detect code clones, I have
followed a token-based approach with the application of winnowing algorithm. At first the
source code is preprocessed such as removal of blank lines, comments, hash directives,
punctuations, extra white space, and capitalization of letters. Then from the
preprocessed code k-grams are generated. The k-grams are then use for hashing and
using rolling hash we get the hash values of the k-grams. Next the k-gram hashes are
pushed into winnowing algorithm. The winnowing algorithm generates fingerprints from
the hash values. Then comparing the fingerprints using statistical methods (Jaccard
Similarity and Dice Similarity Coefficient), we get the clone result.

Through this project we get the static analysis of ‘C’ programs. We get software metrics
of LOC and Halstead “software science” which provides objective information throughout
the software organization. This reduces the ambiguity that often surrounds complex and
constrained software projects. Metrics are the key to objectively representing the
progress of project activities and the quality of associated software products across the
project life cycle. We also can detect code clones which is very important in software
engineering. Applications of code clone detection and management are restructuring of
clone codes without any change in the external behavior of the code, duplicate code can
be removed, bug detected in copied code leads to identifying bugs in original code or
vice versa, code size can be reduced, reuse of existing code, etc.



## Technologies Used
- Java - openjdk 11.0.8
- JavaFX - version 17.0.4 
- CSS


## Features
- LOC
- Halstead
- Code Clone 
- GUI


## Screenshots
![Example screenshot](/screenshots/metrics%20main.png)
![Example screenshot](/screenshots/loc.png)
![Example screenshot](/screenshots/halstead.png)
![Example screenshot](/screenshots/clone%20main.png)
![Example screenshot](/screenshots/clone%20result.png)
![Example screenshot](/screenshots/csv.png)

## Setup
* SCA -> without GUI
* SCA_GUI -> GUI added

- Requirements: Java (openjdk 11.0.8) and JavaFX - version 17.0.4 

- Installation Instructions: 
    1. clone this repository:
        ```
        $ git clone https://github.com/KamruzzamanAsif/SPL-1.git
        ```
    2. Open the project directory in an IDE (IntelliJ/NetBeans/VsCode/etc.)
    3. Go to ``` SPL-1/SCA_GUI/src/main/java/GUI/Main.java ``` and run 


## Project Status
Project is:  _complete_ & _no longer being worked on_


## Acknowledgements
- This project was inspired by my supervisor
- Many thanks to my seniors and mates


## Contact
Created by [@KamruzzamanAsif](https://github.com/KamruzzamanAsif) - feel free to contact me!



## License
> Unlicensed: This project is open and available for all

