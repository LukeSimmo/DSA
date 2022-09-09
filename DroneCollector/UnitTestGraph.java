/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025                        *
 * PURPOSE: Test Harness for DSAGraph                   *
 * DATE CREATED: 17/05/2021                             *
 * LAST MODIFIED: 17/05/2021                            *
 ********************************************************/
import java.util.*;

public class UnitTestGraph
{
    public static void main(String[] args)
    {
        int passed, tests;
        DSAGraph graph = null;
        DSALinkedList list = new DSALinkedList();
        String[] array = new String[1];

        passed = 0;
        tests = 0;

//----------------------CONSTRUCTOR-----------------------

        bold("\n\nTesting constructor");
        System.out.println("---------------------------------------------------");

        //TEST 1: CONSTRUCTOR (isEmpty())

        try
        {
            tests++;

            graph = new DSAGraph();
            System.out.print("Testing creation of DSAGraph (isEmpty()): ");
            if(!graph.isEmpty())
            {
                throw new IllegalArgumentException("Classfields must be null");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//----------------------ADDING

        bold("\nTest adding vertex's and edges");
        System.out.println("---------------------------------------------------");

        // addVertex()
        try
        {
            tests++;
            System.out.print("Testing addVertex(): ");
            graph.addVertex("A", 1);
            graph.addVertex("B", 2);
            graph.addVertex("C", 3);
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // addEdge()

        try
        {
            tests++;
            System.out.print("Testing addEdge(): ");
            graph.addEdge("A", "B", 4);
            graph.addEdge("A", "C", 5);
            graph.addEdge("B", "C", 6);
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-----------------------------------------------------------------------------

        bold("\nTest existence of entered items");
        System.out.println("---------------------------------------------------");

        // hasVertex()
        try
        {
            tests++;
            System.out.print("Testing hasVertex(): ");
            if(!graph.hasVertex("A"))
            {
                throw new IllegalArgumentException("Vertex must exist");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // getVertexCount()
        try
        {
            tests++;
            System.out.print("Testing getVertexCount(): ");
            if(graph.getVertexCount() != 3)
            {
                throw new IllegalArgumentException("Wrong number of vertex's");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // getEdgeCount()
        try
        {
            tests++;
            System.out.print("Testing getEdgeCount(): ");
            if(graph.getEdgeCount() != 6)
            {
                throw new IllegalArgumentException("Wrong number of edges");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-----------------------------------------------------------------------------

        bold("\nTesting adjacent items");
        System.out.println("---------------------------------------------------");

        // getAdjacent()
        try
        {
            tests++;
            System.out.print("Testing getAdjacent(): ");
            list = graph.getAdjacent("A");
            if(!(list.removeFirst()).toString().equals("B"))
            {
                throw new IllegalArgumentException("Adjacent vertices dont match");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // getAdjacentE()
        try
        {
            tests++;
            System.out.print("Testing getAdjacentE(): ");
            list = graph.getAdjacentE("A");
            if(!(list.removeFirst()).toString().equals("AB"))
            {
                throw new IllegalArgumentException("Adjacent edges don't match");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-----------------------------------------------------------------------------

        bold("\nTesting distance");
        System.out.println("---------------------------------------------------");

        // getDistance()
        try
        {
            tests++;
            System.out.print("Testing getDistance(): ");
            if(graph.getDistance("A", "B") != 4)
            {
                throw new IllegalArgumentException("Distance mismatch");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-----------------------------------------------------------------------------

        bold("\nTesting Inventory");
        System.out.println("---------------------------------------------------");

        // setInventory()
        try
        {
            tests++;
            System.out.print("Testing setInventory(): ");
            graph.setInventory("A", "test", 5);
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // findInventory()
        try
        {
            tests++;
            System.out.print("Testing findInventory(): ");
            graph.findInventory("test");
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-----------------------------------------------------------------------------

        bold("\nTesting existence of vertices list");
        System.out.println("---------------------------------------------------");

        // getVertices()
        try
        {
            tests++;
            System.out.print("Testing getVertices(): ");
            list = graph.getVertices();
            if(!list.removeFirst().equals("A"))
            {
                throw new IllegalArgumentException("vertices mismatch");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-----------------------------------------------------------------------------

        bold("\nTesting catalogues");
        System.out.println("---------------------------------------------------");

        // setCatalogue()
        try
        {
            tests++;
            System.out.print("Testing setCatalogue(): ");
            graph.setCatalogue(array);
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // getCatalogue()
        try
        {
            tests++;
            System.out.print("Testing getCatalogue(): ");
            array = graph.getCatalogue();
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-----------------------------------------------------------------------------

        System.out.print("\nNumber PASSED: " + passed + "/" + tests);
        System.out.print(" -> " + (int)(double)passed/tests*100 + "%\n");

//-----------------------------------------------------------------------------

    }

    public static void bold(String str)
    {
        System.out.println("\u001b[1m" + str + "\u001b[0m");
    }
}