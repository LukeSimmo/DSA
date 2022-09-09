/********************************************************
 * AUTHOR: LUKE SIMPSON                                 *
 * PURPOSE: Implement adjacency list graph              *
 * DATE CREATED: 24/04/2021                             *
 * LAST MODIFIED: 10/05/2021                            *
 * ******************************************************
 * 
 * REFERENCES:
 * 
 * File DSAGraph
 * comprised of previously submitted code by Luke Simpson in
 * practical 6 - Graphs - DSA COMP1002
 * 
 * note - this data stucture has been significantly modified 
 *        to fit the droneCollector specifications
 * 
 ********************************************************/
import java.io.Serializable;
import java.util.*;

public class DSAGraph implements Serializable
{
    //CLASSFIELDS
    private DSALinkedList vertices;
    private DSALinkedList edges;
    private String[] catalogue;

    // DEFAULT CONSTRUCTOR
    public DSAGraph()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
        catalogue = null;
    }

    /****************************************************
     * NAME: isEmpty
     * PURPOSE: determine if graph is empty
     * IMPORTS: none
     * EXPORTS: empty(boolean)
     ****************************************************/
    public boolean isEmpty()
    {
        boolean empty = false;

        if((vertices.isEmpty()) && (edges.isEmpty()) && (catalogue == null))
        {
            empty = true;
        }

        return empty;
    }

    /****************************************************
     * NAME: addVertex
     * PURPOSE: add vertex to graph
     * IMPORTS: label (String), value (int)
     * EXPORTS: none
     ****************************************************/
    public void addVertex(String label, int value)
    {
        if(hasVertex(label))
        {
            throw new IllegalArgumentException("Label " + label + " already exists!");
        }
        else
        {
            DSAGraphVertex newVrtx = new DSAGraphVertex(label, value);
            vertices.insertLast(newVrtx);
        }
    }

    /****************************************************
     * NAME: addEdge
     * PURPOSE: add edge to graph
     * IMPORTS: label1 (String), label2 (String), value(double)
     * EXPORTS: none
     ****************************************************/
    public void addEdge(String label1, String label2, double value)
    {
        DSAGraphVertex from = null;
        DSAGraphVertex to = null;
        DSAGraphEdge edge = null;
        DSAGraphEdge edge2 = null;
        String labelFrom;
        String labelTo;

        labelFrom = label1 + label2;
        labelTo = label2 + label1;

        if(!hasVertex(label1))
        {
            addVertex(label1, 0);
        }
        
        if(!hasVertex(label2))
        {
            addVertex(label2, 0);
        }

        from = getVertex(label1);
        to = getVertex(label2);

        if(edge == null)
        {
            edge = new DSAGraphEdge(from, to, value, labelFrom);
            edge2 = new DSAGraphEdge(to, from, value, labelTo);
        }

        from.addEdge(to, edge);
        to.addEdge(from, edge2);
        edges.insertLast(edge);
        edges.insertLast(edge2);
        
    }

    /****************************************************
     * NAME: hasVertex
     * PUROSE: check if graph has vertex
     * IMPORT: label (String)
     * EXPORT: boolean
     ****************************************************/
    public boolean hasVertex(String label)
    {
        boolean val = false;

        Iterator itr = vertices.iterator();

        while(itr.hasNext())
        {
            DSAGraphVertex vrtx = (DSAGraphVertex)itr.next();
            if(vrtx.getLabel().equals(label))
            {
                val = true;
            }
        }

        return val;
    }

    /****************************************************
     * NAME: getVertexCount
     * PUROSE: check amount of vertices in graph
     * IMPORT: none
     * EXPORT: count (int)
     ***************************************************/
    public int getVertexCount()
    {
        int count = 0;

        Iterator itr = vertices.iterator();

        while(itr.hasNext())
        {
            itr.next();
            count++;
        }

        return count;
    }

    /****************************************************
     * NAME: getEdgeCount
     * PURPOSE: return amount of edges
     * IMPORT: none
     * EXPORT: count (int)
     ****************************************************/
    public int getEdgeCount()
    {
        int count = 0;

        Iterator itr = edges.iterator();

        while(itr.hasNext())
        {
            itr.next();
            count++;
        }

        return count;
    }

    /****************************************************
     * NAME: getVertex
     * PURPOSE: return a vertex
     * IMPORT: label (String)
     * EXPORT: newVrtx (DSAGraphVertex)
     ***************************************************/
    public DSAGraphVertex getVertex(String label)
    {  
        Iterator itr = vertices.iterator();
        DSAGraphVertex newVrtx = null;

        while(itr.hasNext())
        {
            DSAGraphVertex vertex = (DSAGraphVertex)itr.next();
            if(label.equals(vertex.getLabel()))
            {
                newVrtx = vertex;
            }
        }

        return newVrtx;
    }

    /****************************************************
     * NAME: getEdge
     * PURPOSE: return an edge
     * IMPORT: label (String)
     * EXPORT: newEdge (DSAGraphEdge)
     ****************************************************/
    public DSAGraphEdge getEdge(String label)
    {
        Iterator itr = edges.iterator();
        DSAGraphEdge newEdge = null;

        while(itr.hasNext())
        {
            DSAGraphEdge edge = (DSAGraphEdge)itr.next();
            if(label.equals(edge.getLabel()))
            {
                newEdge = edge;
            }
        }

        return newEdge;
    }

    /****************************************************
     * NAME: getAdjacent
     * PURPOSE: returns all adjacent vertices of a vertex
     * IMPORT: label (String)
     * EXPORT: returnList (DSALinkedList)
     ***************************************************/
    public DSALinkedList getAdjacent(String label)
    {
        DSALinkedList list = null;
        DSAGraphVertex vetx = null;
        DSALinkedList returnList = new DSALinkedList();

        vetx = getVertex(label);

        list = vetx.getAdjacent();

        Iterator itr = list.iterator();

        while(itr.hasNext())
        {
            DSAGraphVertex vertex = null;
            vertex = (DSAGraphVertex)itr.next();
            returnList.insertLast(vertex.getLabel());
        }

        if(list.isEmpty())
        {
            list.insertLast("No connected stores");
        }

        return returnList;
    }

    /****************************************************
     * NAME: getAdjacentE
     * PURPOSE: return all edges attached to a vertex
     * IMPORT: label (String)
     * EXPORT: returnList (DSALinkedList)
     ***************************************************/
    public DSALinkedList getAdjacentE(String label)
    {
        DSALinkedList list = null;
        DSAGraphVertex vetx = null;
        DSALinkedList returnList = new DSALinkedList();

        vetx = getVertex(label);

        list = vetx.getAdjacentE();

        Iterator itr = list.iterator();

        while(itr.hasNext())
        {
            DSAGraphEdge edge = null;
            edge = (DSAGraphEdge)itr.next();
            returnList.insertLast(edge.getLabel());
        }

        return returnList;
    }

    /****************************************************
     * NAME: getDistance
     * PURPOSE: returns distance between two nodes
     * IMPORT: label1 (String), label2 (String)
     * EXPORT: num (double)
     ****************************************************/
    public double getDistance(String label1, String label2)
    {
        String edgeLabel;
        double num = -1;

        if(!hasVertex(label1))
        {
            throw new InputMismatchException("Vertex " + label1 + " does not exist!");
        }
        else if(!hasVertex(label2))
        {
            throw new InputMismatchException("Vertex " + label2 + " does not exist!");
        }

        if(label1.equals(label2))
        {
            num = 0;
        }

        edgeLabel = label1 + label2;

        Iterator itr = edges.iterator();
        DSAGraphEdge newEdge = null;

        while(itr.hasNext())
        {
            DSAGraphEdge edge = (DSAGraphEdge)itr.next();
            if(edgeLabel.equals(edge.getLabel()))
            {
                newEdge = edge;
                num = newEdge.getValue();
            }
        }

        return num;
    }

    /****************************************************
     * NAME: setInventory
     * PURPOSE: add items to inventory
     * IMPORT: label (String), key (String), value (int)
     * EXPORT: none
     ****************************************************/
    public void setInventory(String label, String key, int value)
    {
        DSAGraphVertex vetx = null;

        if(!hasVertex(label))
        {
            throw new InputMismatchException("Vertex " + label + " does not exist!");
        }

        vetx = getVertex(label);

        vetx.addEntry(key, value);
    }

    /****************************************************
     * NAME: displayInventory
     * PURPOSE: display inventory for imported vertex
     * IMPORT: label (String)
     * EXPORT: none
     ****************************************************/
    public void displayInventory(String label)
    {
        DSAGraphVertex vetx = null;

        vetx = getVertex(label);

        vetx.displayTable();
    }

    /*****************************************************
     * NAME: getVertices
     * PURPOSE: return a list of all vertiecs in graph 
     * IMPORT: none
     * EXPORT: list (DSALinkedList)
     *****************************************************/
    public DSALinkedList getVertices()
    {
        DSALinkedList list = new DSALinkedList();

        Iterator itr = vertices.iterator();

        while(itr.hasNext())
        {
            DSAGraphVertex vertex = (DSAGraphVertex)itr.next();
            list.insertLast(vertex.getLabel());
        }

        return list;
    }

    //----------------CATALOGUE ARRAY---------------
    public void setCatalogue(String[] array)
    {
        catalogue = array;
    }

    public String[] getCatalogue()
    {
        return catalogue;
    }

    /****************************************************
     * NAME: display
     * PURPOSE: display adjacency list
     * IMPORT: none
     * EXPORT: none
     ****************************************************/
    public void display()
    {
        Iterator itr = vertices.iterator();

        while(itr.hasNext())
        {
            DSAGraphVertex vetx = (DSAGraphVertex)itr.next();
            System.out.print(vetx.getLabel() + " --> ");
            DSALinkedList list = vetx.getAdjacent();
            Iterator inItr = list.iterator();
            while(inItr.hasNext())
            {
                DSAGraphVertex inVtx = (DSAGraphVertex)inItr.next();
                System.out.print(inVtx.getLabel() + " ");
            }
            System.out.println();
        }

        if(vertices.isEmpty())
        {
            System.out.println("No location information imported");
        }
    }

    // Finds which store an item exists in

    /*****************************************************
     * NAME: findInventory
     * PURPOSE: search hash table for product and return store
     * IMPORT: key (String)
     * EXPORT: store (String)
     ****************************************************/
    public String findInventory(String key)
    {
        DSAGraphVertex vertex;
        int value = 0;
        String store = "";

        Iterator itr = vertices.iterator();

        while((itr.hasNext()) && (store.equals("")))
        {
            vertex = (DSAGraphVertex)itr.next();

            if(vertex.containsEntry(key))
            {
                store = vertex.getLabel();
            }
        }

        return store;
    }

    //-------------------INNER CLASSES-------------------

    private class DSAGraphVertex implements Serializable
    {
        //CLASSFIELDS
        private String label;
        private int value;
        private DSALinkedList links;
        private DSALinkedList edges;
        private DSAHashTable table;
        private boolean visited;

        public DSAGraphVertex(String inLabel, int inValue)
        {
            label = inLabel;
            value = inValue;
            links = new DSALinkedList();
            edges = new DSALinkedList();
            table = new DSAHashTable(10);
            visited = false; 
        }

        //ACCESSORS
        public String getLabel()
        {
            return label;
        }

        public int getValue()
        {
            return value;
        }

        public DSALinkedList getAdjacent()
        {
            return links;   
        }

        public DSALinkedList getAdjacentE()
        {
            return edges;
        }

        //MUTATORS
        public void addEdge(DSAGraphVertex vertex, DSAGraphEdge edge)
        {
            links.insertLast(vertex);
            edges.insertLast(edge);
        }

        public void setVisited()
        {
            visited = true;
        }

        public void clearVisited()
        {
            visited = false;
        }

        public boolean getVisited()
        {
            return visited;
        }

        // stores inventory information
        public void addEntry(String key, int quantity)
        {
            table.put(key, quantity);
        }

        /************************************************
         * NAME: containsEntry
         * PURPOSE: checks hash table for products
         * IMPORT: key (String)
         * EXPORT: boolean
         *************************************************/
        public boolean containsEntry(String key)
        {
            return table.containsKey(key);
        }

        /************************************************
         * NAME: displayTable
         * PURPOSE: display inventory
         * IMPORT: none
         * EXPORT: none
         ************************************************/
        public void displayTable()
        {
            String[] lines;

            lines = table.export();

            for(int ii = 0; ii < lines.length; ii++)
            {
                System.out.println(lines[ii]);
            }
            System.out.println();
        }
    }    

    private class DSAGraphEdge implements Serializable
    {
        //CLASSFIELDS
        private DSAGraphVertex from;
        private DSAGraphVertex to;
        private double value;
        private String label;

        public DSAGraphEdge(DSAGraphVertex fromVertex, DSAGraphVertex toVertex,
                            double inValue, String inLabel)
        {
            from = fromVertex;
            to = toVertex;
            value = inValue;
            label = inLabel;
        }

        //ACCESSORS
        public double getValue()
        {
            return value;
        }

        public DSAGraphVertex getFrom()
        {
            return from;
        }

        public DSAGraphVertex getTo()
        {
            return to;
        }
        
        public String getLabel()
        {
            return label;
        }
    }
}
