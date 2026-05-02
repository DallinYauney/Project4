CS 2430 Project 4 - Capstone_PLO-CS-6 - Monopoly Simulation

## Links

[Weekly Report 1](https://slcc.instructure.com/courses/1235395/assignments/17800189/)  
[Weekly Report 2](https://slcc.instructure.com/courses/1235395/assignments/17800190/)  
[Weekly Report 3](https://slcc.instructure.com/courses/1235395/assignments/17800191/)  

[Checkpoint](https://slcc.instructure.com/courses/1235395/assignments/17800187/)  

[Overall Project](https://slcc.instructure.com/courses/1235395/assignments/17800167)  
[Peer Review](https://slcc.instructure.com/courses/1235395/assignments/17800188/)  

[Project UML](https://lucid.app/lucidchart/d4b6f8f0-0f76-4536-984a-e72d85497505/edit?viewport_loc=-3194%2C-3016%2C1982%2C1070%2C0_0&invitationId=inv_d86bd6cd-f6ff-4f1a-99cc-918ae412c563)

## Build Instructions

This project's entry point is the board, located in `src/main/Board.java`. We hope it's runnable by anyone, but frankly we're having some hiccup consistently running the project ourselves!

The project produces data in the form of a CSV, but it only prints it to the terminal. To get the data in a file to be processed by a spreadsheet program such as Excel, simply copy it into the file manually (Ctrl + Shift + C for most terminals), or use your shell's redirection feature. In Bash, this looks like `java src/main/Board.java > resource/output.csv`.

We even have a bash script to this effect, which can be run (using bash) with `./output.sh`. This is how we generated the output we have now.

Downloading the processing.xlsx file from Google Sheets causes a loss of some functionality. For full functionality, visit this [link](https://docs.google.com/spreadsheets/d/1gBHTmmqkg5Y-qhiWRqSWFIGelMlmJH6QFt3ctKH87t0/edit?usp=sharing) and make a copy of the processing sheet.
