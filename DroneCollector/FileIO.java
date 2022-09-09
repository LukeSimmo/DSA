/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025                        *
 * PURPOSE: Provide File I/O for assignment             *
 * DATE CREATED: 10/05/2021                             *
 * LAST MODIFIED: 10/05/2021                            *
 ********************************************************/
import java.util.*;
import java.io.*;

public class FileIO
{
    /****************************************************
     * NAME: readLocations
     * PURPOSE: read location file
     * IMPORT: graph (DSAGraph), filename (String)
     * EXPORT: none
     ****************************************************/
    public static void readLocations(DSAGraph graph, String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();

            for(int ii = 0; ii < getRows(filename); ii++)
            {
                String[] lineSplit;
                lineSplit = line.split(",");
                
                graph.addEdge(lineSplit[0], lineSplit[1], Double.parseDouble(lineSplit[2]));

                line = bufRdr.readLine();
            }

            bufRdr.close();
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("File " + filename + " not found!");
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)
                {

                }
            }
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }

    /****************************************************
     * NAME: readInventory
     * PURPOSE: read inventory file and store in graph
     * IMPORT: graph (DSAGraph), filename (String)
     * EXPORT: none
     ***************************************************/
    public static void readInventory(DSAGraph graph, String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        String line;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();

            for(int ii = 0; ii < getRows(filename); ii++)
            {
                if(!line.equals("Store,Product,StockOnHand,"))
                {
                    String[] lineSplit = line.split(",");

                    graph.setInventory(lineSplit[0], lineSplit[1], Integer.parseInt(lineSplit[2]));
                }

                line = bufRdr.readLine();
            }

            fileStrm.close();
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("File " + filename + " not found!");
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)
                {

                }
            }
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }

    /****************************************************
     * NAME: readCatalogue
     * PURPOSE: import catalogue file to graph
     * IMPORT: graph (DSAGraph), filename (String)
     * EXPORT: String[]
     *****************************************************/
    public static String[] readCatalogue(DSAGraph graph, String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        String[] lines = new String[getRows(filename)];
        String line;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();

            for(int ii = 0; ii < getRows(filename); ii++)
            {
                lines[ii] = line;
                line = bufRdr.readLine();
            }

            graph.setCatalogue(lines);
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("File " + filename + " not found!");
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)
                {

                }
            }
            System.out.println("Error while reading file: " + e.getMessage());
        }

        return lines;
    }

    /****************************************************
     * NAME: readOrder
     * PUROPSE: read in order info
     * IMPORT: filename (String)
     * EXPORT: order (Object[][])
     ***************************************************/
    public static Object[][] readOrder(String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        Object[][] order = new Object[getRows(filename)][2];
        String line;

        try
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();

            for(int ii = 0; ii < getRows(filename); ii++)
            {
                String[] lineSplit;
                if(ii < 3)
                {
                    lineSplit = line.split(":");

                    order[ii][0] = lineSplit[0];
                    order[ii][1] = lineSplit[1];
                }
                else
                {
                    lineSplit = line.split(",");

                    order[ii][0] = lineSplit[0];
                    order[ii][1] = lineSplit[1];
                }

                line = bufRdr.readLine();
            }
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException("File " + filename + " not found!");
        }
        catch(IOException e)
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)
                {

                }
            }
            System.out.println("Error while reading file: " + filename);
        }

        return order;
    }

// --------------------- Private Submodules ----------------------

    /***************************************************
     * NAME: getRows
     * PURPOSE: return amount of rows in file
     * IMPORT: filename (String)
     * EXPORT: rows (int)
     ***************************************************/
    private static int getRows(String filename)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;

        int rows = 0;
        String line;

        try 
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            
            line = bufRdr.readLine();

            while(line != null)
            {
                rows++;
                line = bufRdr.readLine();
            }
            fileStrm.close();
        } 
        catch (IOException e) 
        {
            if(fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch(IOException ex2)
                {

                }

                System.out.println("Error while reading file: "
                                    + e.getMessage());
            }
        }

        return rows;
    }
}
