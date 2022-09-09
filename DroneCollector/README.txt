AUTHOR: LUKE SIMPSON 20171025
PURPOSE: information on the program 

This file contains a short description of all files and dependencies

ADTs:
The following files are all abstract data structures that were created and
submitted by Luke Simpson 20171025 over the course of the unit DSA COMP1002

DSAGraph.java
    - DSAGraph.java is the main adt used in this assignment and stores most
      of the information that is imported into the program. It holds the 
      location, product and inventory infromation.
    - A unit test harness is included for this file stored under the name
      UnitTestGraph.java
    - Depends on DSALinkedList and DSAHashTable to operate

DSAHashTable.java
    - DSAHashTable.java is another adt used to store the inventory information
      at each location (or vertex), without it inventory info cannot be stored
    - A unit test harness is included for this file stored under the name
      UnitTestHashTable.java
    - Does not depend on any other file to operate

DSALinkedList.java
    - DSALinkedList.java is an adt used to store data that can dynamically 
      expand and shrink, used for various purposes in this program
    - A unit test harness is included for this file stored under the name
      UnitTestLinkedList.java
    - Does not depend on any other file to operate

Other files:

FileIO.java
    - FileIO.java handles all file I/O within the program, excluding serializing
    - Does depend on DSALinkedList to operate

userInput.java
    - Handles all user inputs within the interactive mode and stops any
      exceptions from occuring 
    - Does not depend on any other files

modes.java
    - Displays the menu for the user when running interative mode, also 
      executes report mode
    - Depends on DSAGraph, DSAHashTable, DSALinkedList, userInput, FIleIO and route

droneCollector.java
    - driver code that contain main method
    - depends on all files

route.java
    - handles the routing and displaying of orders
    - depends on DSALinkedList

Usage: 

DroneCollector

    Interactive mode
    run with: droneCollector -i

    Report mode
    run with: droneCollector -r <location_file> <product_file> <inventory_file> <order_file>