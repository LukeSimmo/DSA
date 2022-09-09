/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025                        *
 * PURPOSE: Implement Hash table                        *
 * DATE CREATED: 03/05/2021                             *
 * LAST MODIFIED: 03/05/2021                            *
 ********************************************************
 * 
 * REFERENCES:
 * 
 * File DSAHashTable
 * comprised of previously submitted code by Luke Simpson in
 * practical 7 - Hash Tables - DSA COMP1002
 * 
 */
import java.io.Serializable;
import java.util.*;

public class DSAHashTable implements Serializable
{
    //CLASSFIELDS
    private DSAHashEntry[] hashArray;
    private int count;

    public DSAHashTable(int tableSize)
    {
        tableSize = nextPrime(tableSize);

        hashArray = new DSAHashEntry[tableSize];
        count = 0;

        for(int ii = 0; ii < tableSize; ii++)
        {
            hashArray[ii] = new DSAHashEntry();
        }
    }

    public boolean isEmpty()
    {
        return (hashArray.length == 0);
    }

    /****************************************************
     * NAME: put
     * PURPOSE: add item to table
     * IMPORT: inKey (String), inValue (Object)
     * EXPORT: none
     ***************************************************/
    public void put(String inKey, Object inValue)
    {
        int index;
        int originalIdx;
        boolean found, abort;
        int ii = 1;

        index = hash(inKey);
        originalIdx = index;
        found = false;
        abort = false;

        while((!found) && (!abort))
        {
            if((hashArray[index].state == 0) || (hashArray[index].state == -1))
            {
                found = true;
            }
            else if(hashArray[index].state == 1)
            {
                if(hashArray[index].key.equals(inKey))
                {
                    throw new IllegalArgumentException("Key " + inKey + " already exists!");
                }

                index = ( index + stepHash(originalIdx)) % hashArray.length;

                if(index == originalIdx)
                {
                    abort = true;
                }
            }
        }

        if(!found)
        {
            throw new NoSuchElementException("No space for key " + inKey);
        }

        hashArray[index] = new DSAHashEntry(inKey, inValue);
        count++;

        if(getLoadFactor() > 0.7)
        {
            resize(hashArray.length * 2);
        }
    }

    /****************************************************
     * NAME: get
     * PURPOSE: retrive value of imported key
     * IMPORT: inKey (String)
     * EXPORT: Object
     ****************************************************/
    public Object get(String inKey)
    {
        int index;
        int originalIdx;
        boolean found, abort;

        index = hash(inKey);
        originalIdx = index;
        found = false;
        abort = false;

        while((!found) && (!abort))
        {
            if(hashArray[index].state == 0)
            {
                abort = true;
            }
            else if(hashArray[index].key.equals(inKey))
            {
                found = true;
            }
            else
            {
                index = (index + stepHash(originalIdx) % hashArray.length);
                if(index == originalIdx)
                {
                    abort = true;
                }
            }
        }

        if(!found)
        {
            throw new NoSuchElementException("No value found for key " + inKey);
        }
        
        return hashArray[index].value;
    }

    /****************************************************
     * NAME: remove
     * PURPOSE: remove item from table
     * IMPORT: inKey (String)
     * EXPORT: none
     ****************************************************/
    public void remove(String inKey)
    {
        int index;
        int originalIdx;
        boolean found, abort;
        index = hash(inKey);
        originalIdx = index;
        found = false;
        abort = false;

        while((!found) && (!abort))
        {
            if(hashArray[index].state == 0)
            {
                abort = true;
            }
            else if(hashArray[index].key == inKey)
            {
                found = true;
                count--;
            }
            else
            {
                index = (index + stepHash(originalIdx) % hashArray.length);
                if(index == originalIdx)
                {
                    abort = true;
                }
            }
        }

        if(!found)
        {
            throw new NoSuchElementException("No value found for deletion for key " + inKey);
        }

        hashArray[index].state = -1;
        hashArray[index].key = "";
        hashArray[index].value = null;

        if(getLoadFactor() < 0.4)
        {
            resize(hashArray.length / 2);
        }
    }

    /****************************************************
     * NAME: export
     * PURPOSE: return String array of entire table
     * IMPORT: none
     * EXPORT: str (String[])
     ****************************************************/
    public String[] export()
    {
        String[] str = new String[count];
        int jj = 0;

        for(int ii = 0; ii < hashArray.length; ii++)
        {
            if(hashArray[ii].state == 1)
            {
                str[jj] = (hashArray[ii].key + "," + hashArray[ii].value);
                jj++;
            }
        }

        return str;
    }

    /****************************************************
     * NAME: containsKey
     * PURPOSE: checks if table contains certain key
     * IMPORT: key (String)
     * EXPORT: contains (boolean)
     ****************************************************/
    public boolean containsKey(String key)
    {
        boolean contains = false;
        int index = hash(key);

        for(int ii = 0; ii < hashArray.length; ii++)
        {
            if(hashArray[ii].key.equals(key))
            {
                contains = true;
            }
        }

        return contains;
    }

    public double getLoadFactor()
    {
        return ((double)count / (double)hashArray.length);
    }

    /****************************************************
     * NAME: resize
     * PURPOSE: resize hash array
     * IMPORT: size (int)
     * EXPORT: none
     ****************************************************/
    private void resize(int size)
    {
        DSAHashEntry[] oldArray = hashArray;

        size = nextPrime(size);
        int inCount = 0;

        hashArray = new DSAHashEntry[size];

        for(int ii = 0; ii < size; ii++)
        {
            hashArray[ii] = new DSAHashEntry();
        }

        for(int ii = 0; ii < oldArray.length; ii++)
        {
            if(oldArray[ii].state == 1)
            {
                put(oldArray[ii].key, oldArray[ii].value);
                inCount++;
            }
        }

        count = inCount;
    }

    /****************************************************
     * NAME: hash
     * PURPOSE: assign hash index to key
     * IMPORT: inKey (String)
     * EXPORT: index (int)
     ***************************************************/
    private int hash(String inKey)
    {
        int a = 63689;
        int b = 378551;
        int index = 0;

        for(int ii = 0; ii < inKey.length(); ii++)
        {
            index = (index * a) + inKey.charAt(ii);
            a *= b;
        }

        return Math.abs(index % hashArray.length);
    }

    private int stepHash(int inKey)
    {
        return 5 - (inKey % 5);
    }

    /****************************************************
     * NAME: nextPrime
     * PURPOSE: find next prime value
     * IMPORT: startVal (int)
     * EXPORT: primeVal (int)
     ***************************************************/
    private int nextPrime(int startVal)
    {
        int primeVal;
        boolean isPrime = false;
        int ii;
        double rootVal;

        if(startVal % 2 == 0)
        {
            primeVal = startVal - 1;
        }
        else
        {
            primeVal = startVal;
        }

        do
        {
            primeVal += 2;
            ii = 3;
            isPrime = true;
            rootVal = Math.sqrt(primeVal);
            do
            {
                if(primeVal % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii += 2;
                }
            } while((ii <= rootVal) && (isPrime));
        } while(!isPrime);

        return primeVal;
    }


    //---------------PRIVATE INNER CLASS (HASH ENTRY)-----------------
    private class DSAHashEntry implements Serializable
    {
        //CLASSFIELDS
        private String key;
        private Object value;
        private int state;

        //DEFAULT CONSTRUCTOR
        private DSAHashEntry()
        {
            key = "";
            value = null;
            state = 0;
        }

        //ALTERNATE CONSTRUCTOR
        private DSAHashEntry(String inKey, Object inValue)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("Key cannot be null!");
            }
            key = inKey;
            value = inValue;
            state = 1;
        }
    }
}
