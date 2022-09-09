/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025                        *
 * PURPOSE: Main function for droneCollector program    *
 * DATE CREATED: 05/05/2021                             *
 * LAST MODIFIED:
 ********************************************************/
import java.util.*;

public class droneCollector
{
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            usage();
        }
        else if(args[0].equals("-i"))
        {
            modes.InteractiveMode();
        }
        else if(args[0].equals("-r"))
        {
            // ensures there is correct amount of arguments for report mode
            if((args.length == 1) || (args.length == 2) ||
                (args.length == 3) || (args.length == 4))
            {
                usage();
            }
            else
            {
                modes.ReportMode(args[1], args[2], args[3], args[4]);
            }
        }
        else
        {
            usage();
        }
    }

    /**************************************************************************
     * NAME: usage
     * PURPOSE: to display usage information when necessary
     * IMPORT: none
     * EXPORT: none
     **************************************************************************/
    private static void usage() 
    {
        System.out.println();
        System.out.println("\u001b[1m" + "DroneCollector" + "\u001b[0m");
        System.out.println("\nusage:");
        System.out.println("    Interactive mode");
        System.out.println("    run with -i\n");
        System.out.println("    Report mode");
        System.out.println("    run with -r <location_file> <product_file>"
                                + " <inventory_file> <order_file>\n");
    }
}
