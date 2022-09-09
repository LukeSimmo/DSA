/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025
 * PURPOSE: test harness for hash table
 * DATE CREATED: 17/05/2021
 * LAST MODIFIED: 17/05/2021 
 ********************************************************/
import java.util.*;

public class UnitTestHashTable
{
    public static void main(String[] args)
    {
        int passed, tests;
        DSAHashTable table = null;
        String[] array = new String[1];
        int num;

        passed = 0;
        tests = 0;

//-------------------------------------------------------

        bold("\n\nTesting constructor");
        System.out.println("---------------------------------------------------");

        // constructor (isEmpty())
        try
        {
            tests++;
            table = new DSAHashTable(1);
            System.out.print("Testing creation of DSAHashTable (isEmpty()): ");
            if(table.isEmpty())
            {
                throw new IllegalArgumentException("table is empty");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-------------------------------------------------------

        bold("\nAdding and removing from table");
        System.out.println("---------------------------------------------------");

        // put()
        try
        {
            tests++;
            System.out.print("Testing put(): ");
            table.put("key", 2);
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // put() (with resize)
        try
        {
            tests++;
            System.out.print("Testing put() (with resize): ");
            table.put("next", 3);
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // get()
        try
        {
            tests++;
            System.out.print("Testing get(): ");
            num = (int)table.get("key");
            if(num != 2)
            {
                throw new IllegalArgumentException("value mismatch");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // remove()
        try
        {
            tests++;
            System.out.print("Testing remove(): ");
            table.remove("key");
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-------------------------------------------------------

        bold("\nExporting table");
        System.out.println("---------------------------------------------------");

        // export()
        try
        {
            tests++;
            System.out.print("Testing export(): ");
            array = table.export();
            if(!array[0].equals("next,3"))
            {
                throw new IllegalArgumentException("export incorrect");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-------------------------------------------------------

        bold("\nTesting contains key");
        System.out.println("---------------------------------------------------");

        // containsKey()
        try
        {
            tests++;
            System.out.print("Testing containsKey(): ");
            if(!table.containsKey("next"))
            {
                throw new IllegalArgumentException("does not contain key");
            }
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-------------------------------------------------------

        bold("\nTesting load factor");
        System.out.println("---------------------------------------------------");

        // getLoadFactor()
        try
        {
            tests++;
            System.out.print("Testing getLoadFactor(): ");
            table.getLoadFactor();
            passed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//-------------------------------------------------------

        System.out.print("\nNumber PASSED: " + passed + "/" + tests);
        System.out.print(" -> " + (double)passed/tests*100 + "%\n");

//-------------------------------------------------------
    }

    public static void bold(String str)
    {
        System.out.println("\u001b[1m" + str + "\u001b[0m");
    }
}