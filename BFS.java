package com.KB;

import java.util.*;

class BFS
{
    String initialStart;
    private String target;
    
    //this function calculates the shortest path between the two actors
    void bfs(HashMap<String, LinkedList<String>> map, String actor1, String actor2)
    {
        this.initialStart = actor1;
        this.target = actor2;
        String tempString = target;

        //HashSet keeps track of actors that are checked and don't have connections
        HashSet<String> visited = new HashSet<String>();
        visited.add(initialStart);

        //Hashtable that stores the path between the the two actors
        Hashtable<String, String> path = new Hashtable<String, String>();
        path.put(initialStart, "");

        //Linked list to store which actor to check
        LinkedList<String> queue = new LinkedList<String>();
        queue.add(initialStart);

        //Stack that stores the final path between both actors
        Stack<String> finalPath = new Stack<String>();


        //this checks if there a direct connection between the two actors
        if (map.get(initialStart).contains(target))
        {
            System.out.println("Path between " + initialStart + " and " + target + ": " + initialStart + " --> " + target);
        }
        else
        {
            while(!queue.isEmpty()){

                //this returns and the element at the front of queue
                String currActor = queue.poll();

                LinkedList<String> connections = map.get(currActor);

                ///checks all connections of the current actor
                for (String connectedActor : connections){

                    // if actor hasn't been visited, add them to be checked
                    if (!visited.contains(connectedActor)){
                        queue.add(connectedActor);
                        visited.add(connectedActor);
                        path.put(connectedActor, currActor);

                        // if the target is one of the connections, then break
                        if (connectedActor.equals(target))
                        {
                            break;
                        }
                    }
                }
            }

            // form the final path by pushing only the specified path to the final path
            while (!tempString.equals(initialStart)){
                finalPath.push(tempString);
                String link = path.get(tempString);
                tempString = link;
            }
            // this pushes the initial start towards the final path
            finalPath.push(tempString);

            System.out.print("Path between " + initialStart + " and " + target + ": ");

            // prints out all items inside the final path stack
            while (!finalPath.isEmpty())
            {
                System.out.print(finalPath.pop());

                // this is to print an arrow if there is actor after the current actor
                try
                {
                    finalPath.peek();
                    System.out.print(" --> ");
                }
                catch (EmptyStackException e)
                {
                    break;
                }
            }
        }
    }
}