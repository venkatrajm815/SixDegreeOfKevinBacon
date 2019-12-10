package com.KB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;

public class A3
{
    public static void main(String[] args)
    {
        //Hash map to represent a graph of the actors
        HashMap<String, LinkedList<String>> actors = new HashMap<String, LinkedList<String>>();

        File inputFile = null;
        //sets the first argument as the inputFile
        if (args.length > 0)
        {
            inputFile = new File(args[0]);
        }


        //first, read in the file entered through the command line,
        //second, parses the actors/actresses names
        //then, store
        try
        {
            BufferedReader br =  new BufferedReader(new FileReader(inputFile));
            JSONParser parser = new JSONParser();


            // read in each line from the file
            String line ;
            while ((line = br.readLine()) != null)
            {

                //this is an array list that stores the actors temporarily
                ArrayList<String> actorsArray = new ArrayList<String>();

                if (line.indexOf("[") != -1)
                {

                    //This takes care of cases with extra brackets and replaces it
                    if (line.contains("[Singing voice]"))
                    {
                        line = line.replace("[Singing voice]", "(Singing voice)");
                    }
                    if (line.contains("[Cameo]"))
                    {
                        line = line.replace("[Cameo]", "(Cameo)");
                    }
                    if (line.contains("[REC]"))
                    {
                        line = line.replace("[REC]", "(REC)");
                    }
                    if (line.contains("[cameo]"))
                    {
                        line = line.replace("[cameo]", "(cameo)");
                    }


                    //removes double quotes for parsing
                    line = line.substring(line.indexOf("["), line.indexOf("]") + 1);
                    line = line.replace("\"\"", "\"");
                    Object cast = (Object) parser.parse(line);

                    //get the names of each object and adds them to a temporary set and a temporary array list called castArray
                    JSONArray castArray = (JSONArray) cast;
                    for (Object obj : castArray)
                    {
                        obj = ((JSONObject) obj).get("name");
                        String actorName = ((String) obj);
                        actorName = actorName.toLowerCase();
                        actorsArray.add(actorName);

                        LinkedList<String> connections = new LinkedList<String>();
                        if (!actors.containsKey(actorName))
                        {
                            actors.put(actorName, connections);
                        }
                    }


                    // this for loop adds the edges or connections for each actor
                    for (String actor : actorsArray)
                    {
                        LinkedList<String> actorConnections = actors.get(actor);
                        for (String connectedActor : actorsArray)
                        {
                            if (!connectedActor.equals(actor))
                            {
                                actorConnections.add(connectedActor);
                            }
                        }
                    }

                }
            }


        }

        //these are all exception handlers
        catch (EOFException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }



        boolean tryAgain = true;
        //while loop to check connection between different actors
        while(tryAgain)
        {
            //initializes BFS to find the shortest path between the two actors
            BFS shortestPath = new BFS();
            //This scanner is used to get user input such as the actor names
            Scanner scanner = new Scanner(System.in);

            System.out.println("Actor 1 name: ");
            String actor1 = (scanner.nextLine()).toLowerCase();


            //to check if Actor 1 exists
            if (!actors.containsKey(actor1))
            {
                System.out.println("No such actor. ");
            }

            //to check if Actor 2 exists
            System.out.println("Actor 2 name: ");
            String actor2 = (scanner.nextLine()).toLowerCase();

            if (!actors.containsKey(actor2))
            {
                System.out.println("No such actor. ");
            }

            // if both actors exist, calculate the shortest path
            if(actors.containsKey(actor1)&&actors.containsKey(actor2))
            {
                shortestPath.bfs(actors, actor1, actor2);
            }
            System.out.println("");

            /*
            System.out.println("\nWould you like to try again?");
            String userinput = scanner.nextLine();
            if(userinput=="y"||userinput=="Y")
            {
                tryAgain = true;
            }
            else
            {
                tryAgain = false;
            }

            */
        }
    }
}