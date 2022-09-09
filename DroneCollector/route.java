/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025                        *
 * PURPOSE: Handle routing for droneCollector           *
 * DATE CREATED: 15/05/2021                             *
 * LAST MODIFIED: 15/05/2021                            * 
 ********************************************************/
import java.util.*;

public class route
{
    /********************************************************
     * METHOD: findRoute                                    
     * PURPOSE: handle route for imported order
     * IMPORTS: graph(DSAGraph), order(Object[][])
     * EXPORTS: none
     ********************************************************/
    public static void findRoute(DSAGraph graph, Object[][] order)
    {
        DSALinkedList stores = new DSALinkedList();
        DSALinkedList items = new DSALinkedList();
        double totalDistance;
        String temp, item;

        int i = 3;

        totalDistance = 0;
        
        for(int ii = 3; ii < order.length; ii++)
        {
            stores.insertLast(order[ii][0].toString());
        }

        stores.insertFirst("Home");
        stores.insertLast("Home");

        Iterator itr = stores.iterator();

        String str = itr.next().toString();

        System.out.println("Route:");

        while(itr.hasNext())
        {
            temp = itr.next().toString();
            item = temp;

            if(!temp.equals("Home"))
            {
                temp = graph.findInventory(temp);
            }

            System.out.print(str + "---(" + graph.getDistance(str, temp) + ")-->"); /*+ temp + " " + item);*/
            totalDistance = totalDistance + graph.getDistance(str, temp);

            items.insertLast(item);

            str = temp;
        }
        System.out.println("Home");
        System.out.println("Distance: " + totalDistance);
        System.out.println();

        Iterator itrI = items.iterator();

        System.out.println("Items collected: ");

        while(itrI.hasNext())
        {
            String out = itrI.next().toString();
            if(!out.equals("Home"))
            {
                System.out.print(out);
                System.out.println(", Qt: " + order[i][1]);
                i++;
            }
        }
    }
}
