CS 245 Assignment 2
Six Degrees of Kevin Bacon

Name: Venkatraj Mohan
Date/Time Finished: 11:45pm
I used IntelliJ to run and do my assignment.

Files:

    A3.java
        - This is my main class.
        - I used JSON parser in my implementation.
        - To use it, I installed the jar file for json-simple-1.1.1. Then I went to File->Project structure and added a new library and uploaded the jar file.
        - In order to read the source file, the .csv file needs to be in the same directory as the other files. I put in the tmdb_5000_credits.csv as command line arguments before running.
            To do this, you press edit Configurations and add tmdb_5000_credits.csv as the Program arguments.
            This is equivalent to doing java A3 tmdb_5000_credits.csv
        - After storing the names from the csv, connections are stored in a linked list.
        - Then the user is asked to enter two actor names.
        - If no such actor is found, tells users that "There is no such actor"
        - If both actors are there, BFS.java is called and the shortest path between the two actors is found
        - Users can try multiple combinations of two actors until they want to stop
        - If the user enters an actor that doesn't exist for the second actor, you can re enter both the actor names from the start
        - If the user enters an actor that doesn't exist for the first actor, you can type any name for second actor and it wont calculate the shortest path since actor one doesn't exist
        - I have added comments in this java class to explain what is happening

    BFS.java
        - This is the class that is called to calculate the shortest path using breadth first search.
        - a Hashtable is used to store the path between two actors
        - if there is direct connection between the two actors, it returns that right away
        - otherwise check all the connections of the actor
        - I have added comments in this java class as well to explain what is happening

    tmdb_5000_credits.csv
        - This is the source file which is read to store the actor/actress names

    json-simple-1.1.1.jar
        - This is the  jar file that is used for parsing.
        - Instructions of installing:
            You can download directly from http://www.java2s.com/Code/Jar/j/Downloadjsonsimple111jar.htm
            It will be in a zip form, you will need to unzip it and store it in the same directory as your other files.
            As I used IntelliJ, I uploaded the jar file as a library.
            This allows you to use JSONParser JSONObject and JSONArray.
