/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025                        *
 * PURPOSE: User Interface for different modes          *
 * DATE CREATED: 09/05/2021                             *
 * LAST MODIFIED:                                       *
 ********************************************************/
import java.util.*;
import java.io.*;

public class modes
{
    // method for interactive mode
    // contains menu

    /*****************************************************
     * NAME: InteractiveMode
     * PURPOSE: display interactive menu for user
     * IMPORT: none
     * EXPORT: none
     *****************************************************/
    public static void InteractiveMode()
    {
        // Variable declaration
        int selection, subSelection;
        String filename, label, label1, label2;
        Object[][] order = null;

        // Data Structure declarations
        DSAGraph graph = new DSAGraph();

        clrScreen();
        System.out.println("\nWelcome to the interactive testing environment");
        
        do
        {
            System.out.println("    > 0. Load data");
            System.out.println("    > 1. Location Overview");
            System.out.println("    > 2. Inventory Overview");
            System.out.println("    > 3. Product Search");
            System.out.println("    > 4. Find and display inventory for a store");
            System.out.println("    > 5. Find and display distance between two locations");
            System.out.println("    > 6. Find and display route for collecting order");
            System.out.println("    > 7. Save data (serialised)");
            System.out.println("    > 8. Exit");
            selection = userInput.input("Please select an option: ", 0, 8);

            switch(selection)
            {
                // ------------------ Load data ------------------
                case 0:
                    do
                    {
                        System.out.println();
                        System.out.println("        > 1. Location Data");
                        System.out.println("        > 2. Inventory Data");
                        System.out.println("        > 3. Product Data");
                        System.out.println("        > 4. Order Data");
                        System.out.println("        > 5. Serialised Data");
                        System.out.println("        > 0. Return to main menu");
                        subSelection = userInput.input("Please select an option: ", 0, 5);

                        switch(subSelection)
                        {
                            // ---------- Import location data --------
                            case 1: 
                                System.out.println();
                                filename = userInput.input("Please enter a filename: ");

                                clrScreen();

                                try
                                {
                                    FileIO.readLocations(graph, filename);
                                    System.out.println(filename + " loaded into graph");
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                            break;
                            // ---------- Import inventory data --------
                            case 2:
                                System.out.println();
                                filename = userInput.input("Please enter a filename: ");

                                clrScreen();

                                try
                                {
                                    FileIO.readInventory(graph, filename);
                                    System.out.println(filename + " loaded into graph");
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                catch(InputMismatchException e)
                                {
                                    System.out.println("Error: location data not imported");
                                }
                            break;
                            // ----------- Import product data ---------
                            case 3:
                                // program reads product data into array
                                System.out.println();
                                filename = userInput.input("Please enter a filename: ");

                                clrScreen();

                                try
                                {
                                    FileIO.readCatalogue(graph, filename);
                                    System.out.println(filename + " loaded into program");
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                            break;
                            // ------------ Import order data ----------
                            case 4:
                                System.out.println();
                                filename = userInput.input("Please enter a filename: ");

                                clrScreen();

                                try
                                {
                                    order = FileIO.readOrder(filename);
                                    System.out.println(filename + " loaded into program");
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                            break;
                            case 5:
                                
                                filename = userInput.input("Please enter a filename: ");

                                clrScreen();

                                System.out.println("Graph imported from serialized file - " + filename);
                
                                FileInputStream fileStrm;
                                ObjectInputStream objStrm;
                                
                                try
                                {
                                    fileStrm = new FileInputStream(filename);
                                    objStrm = new ObjectInputStream(fileStrm);
                                    graph = (DSAGraph)objStrm.readObject();

                                    objStrm.close();
                                }
                                catch(ClassNotFoundException e)
                                {
                                    System.out.println("Class not found " + e.getMessage());
                                }
                                catch(Exception e)
                                {
                                    throw new IllegalArgumentException("Unable to load object from file!");
                                }
                            break;
                            case 0:
                                clrScreen();
                                System.out.println("Exiting sub menu...");
                            break;
                            default:
                                // do nothing
                                // possible errors/exceptions handled by userInput class
                        }
                    } while(subSelection != 0);
                break;
                // ----------------- Lists info on locations -------------------
                case 1:
                    clrScreen();

                    System.out.println("\nListing locations and their adjacent stores:\n");
                    graph.display();
                    System.out.println();
                break;
                // ---------------- Lists locations inventories -----------------
                case 2:
                    DSALinkedList list = null;

                    try
                    {
                        list = graph.getVertices();

                        System.out.println("\nListing all locations inventories:\n");
                    
                        while(!list.isEmpty())
                        {
                            label = list.removeFirst().toString();
                            if(!label.equals("Home"))
                            {
                                System.out.println(label);
                                graph.displayInventory(label);
                                System.out.println();
                            }
                        }
                    }
                    catch(NullPointerException e)
                    {
                        clrScreen();
                        System.out.println("Error: no inventory information imported");
                    }
                break;
                // ------- Give details on particular product in catalogue ------
                case 3:
                    String[] products;
                    String productName = userInput.input("Please enter a product code to search catalogue for: ");
                    boolean found = false;

                    clrScreen();

                    products = graph.getCatalogue();

                    try
                    {
                        for(int ii = 0; ii < products.length; ii++)
                        {
                            String[] split = products[ii].split(":");
                            if(productName.equals(split[0]))
                            {
                                System.out.println(products[ii] + "\n");
                                found = true;
                            }
                        }

                        if(!found)
                        {
                            System.out.println(productName + " does not exist!\n");
                        }
                    }
                    catch(NullPointerException e)
                    {
                        System.out.println("No product data imported!\n");
                    }
                break;
                // ------------ Find and display inventory for a store ----------
                case 4:
                    label = userInput.input("Please enter a store name: ");

                    clrScreen();

                    try
                    {
                        graph.displayInventory(label);
                        System.out.println();
                    }
                    catch(NullPointerException e)
                    {
                        System.out.println(label + " doesn't exist!\n");
                    }
                break;
                // ------ Display distances between two entered locations -------
                case 5:
                    label = userInput.input("Please enter store names: ");

                    clrScreen();

                    String[] split = label.split(" ");
                    double num;

                    try
                    {
                        label1 = split[0];
                        label2 = split[1];

                        num = graph.getDistance(label1, label2);

                        if(num == -1)
                        {
                            System.out.println("\nEdge does not exist between entered stores !");
                        }
                        else
                        {
                            System.out.println("\nDistance between stores: " + num);
                        }
                        System.out.println();
                    }
                    catch(NullPointerException e)
                    {
                        System.out.println("\nEdge does not exist between entered stores!");
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("\n" + e.getMessage());
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {
                        System.out.println("\nIncorrect arguments entered"
                                            + "\nusage: <store_1> <store_2>\n");
                    }
                break;
                // ------ print locations in order and items to collect ---------
                case 6:
                    clrScreen();

                    try
                    {
                        route.findRoute(graph, order);
                    }
                    catch(NullPointerException e)
                    {
                        System.out.println("No order loaded");
                    }
                break;
                // ------------------ Save graph in serialized form -------------
                case 7:
                    
                    filename = userInput.input("Please enter a filename for serialized file: ");

                    clrScreen();
                    
                    FileOutputStream fileStrm;
                    ObjectOutputStream objStrm;

                    if(graph.isEmpty())
                    {
                        System.out.println("Graph is empty: nothing to serialise\n");
                    }
                    else
                    {
                        try
                        {
                            fileStrm = new FileOutputStream(filename);
                            objStrm = new ObjectOutputStream(fileStrm);
                            objStrm.writeObject(graph);

                            objStrm.close();
                            fileStrm.close();
                        }
                        catch(Exception e)
                        {
                            throw new IllegalArgumentException("Unable to save object to file!");
                        }

                        System.out.println("Data serialized to " + filename);
                    }
                break;
                // --------- Exit Interactive mode and terminate program ---------
                case 8:
                    System.out.println("\nProgram Terminated");
                break;
                default:
                    // do nothing
            }
        } while(selection != 8);
    }

        // method for report mode

    /****************************************************
     * NAME: ReportMode
     * PURPOSE: display routing info 
     * IMPORT: locationFilename (String), productsFilename (String)
     *         inventoryFilename (String), orderFilename (String)
     * EXPORT: none
     ******************************************************/
    public static void ReportMode(String locationFilename, String productsFilename,
                                  String inventoryFilename, String orderFilename)
    {
        DSAGraph graph = new DSAGraph();
        Object[][] order = null;

        try
        {
            FileIO.readLocations(graph, locationFilename);
            FileIO.readInventory(graph, inventoryFilename);
            FileIO.readCatalogue(graph, productsFilename);
            order = FileIO.readOrder(orderFilename);

            route.findRoute(graph, order);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    // --------------------- private submodules --------------------------

    private static void clrScreen()
    {
        System.out.println("\033[H\033[2J");
    }
}
