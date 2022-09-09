/********************************************************
 * AUTHOR: LUKE SIMPSON 20171025                        *
 * PURPOSE: Handles all user input and input errors     *
 * DATE CREATED: 09/05/2021                             *
 * LAST MODIFIED:                                       *
 ********************************************************/
import java.util.*;

public class userInput
{
    // file uses method overloading 

    // exception handling for entering an int

    /****************************************************
     * NAME: input
     * PURPOSE: handles all integer user input
     * IMPORT: prompt (String), lower (int), upper (int)
     * EXPORT: num (int)
     *****************************************************/
    public static int input(String prompt, int lower, int upper)
    {
        int num;
        Scanner sc = new Scanner(System.in);

        String outputPrompt;
        outputPrompt = prompt;

        do
        {
            try
            {
                System.out.print(outputPrompt);
                num = sc.nextInt();
            }
            catch(InputMismatchException e)
            {
                sc.next();
                num = lower - 1;
            }
            outputPrompt = ("error, please enter a number in the valid range" + "\n" + prompt);
        } while((num < lower) || (num > upper));

        return num;
    }

    /****************************************************
     * NAME: input
     * PURPOSE: handle string user input
     * IMPORT: prompt (String)
     * EXPORT: output (String)
     ****************************************************/
    public static String input(String prompt)
    {
        String output;
        Scanner sc = new Scanner(System.in);

        System.out.print(prompt);
        output = sc.nextLine();

        return output;
    }
}
