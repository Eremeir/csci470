/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 3           Summer 2023 *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Due Date: July 14th, 2023                             *
 *                                                        *
 *  Purpose: Calculate tips for group orders through      *
 *           a console app.                               *
 *                                                        *
 **********************************************************/
import java.util.Scanner;
import java.text.DecimalFormat;

public class TipApp
{
    //Main subroutine to open console and loop for user input.
    public static void main(String[] args)
    {
        TipApp app = new TipApp();
        app.calculateTips();
    }

    //Accept input and return formatted output of calculated tip amounts.
    public void calculateTips()
    {
        Scanner scnr = new Scanner(System.in);
        String userInput;

        System.out.println("*** Tip Calculator ***");
        do
        {
            TipCalculator tipCalculator = new TipCalculator();

            System.out.print("\n");
            tipCalculator.setBillAmt(sanitizeDoubles(scnr, "Enter the bill amount: "));
            tipCalculator.setTipPercent(sanitizeInts(scnr, "Enter your desired tip percentage (20 = 20%): ", "tip percentage", false));
            tipCalculator.setPartySize(sanitizeInts(scnr, "Enter the size of your party: ", "party size", true));

            System.out.println("\n*** Your Bill ***\n");
            System.out.println("Bill Amount: $" + formatDouble(tipCalculator.getBillAmt()));
            System.out.println("Tip Percentage: " + tipCalculator.getTipPercent() + "%");
            System.out.println("Party Size: " + tipCalculator.getPartySize());
            System.out.println("\nTotal Bill (with Tip): $" + formatDouble(tipCalculator.getTotalBill()));
            System.out.println("Share for Each Individual: $" + formatDouble(tipCalculator.getIndividualShare()));

            System.out.print("\nAnother bill? (y/n): ");
            userInput = scnr.next();

        }
        while (userInput.equalsIgnoreCase("y"));

        System.out.println("\nGoodbye!");
    }

    //Accept double user input from the scanner and validate it.
    private double sanitizeDoubles(Scanner scnr, String input)
    {
        while (true)
        {
            System.out.print(input);
            try
            {
                double val = Double.parseDouble(scnr.next());
                if (val <= 0)
                {
                    System.out.println("Please enter a bill total greater than 0.\n");
                    continue;
                }
                return val;
            } 
            catch (NumberFormatException e)
            {
                System.out.println("Pleae enter a valid bill amount.\n");
            }
        }
    }

    //Accept int user input from the scanner and validate it.
    private int sanitizeInts(Scanner scnr, String input, String context, boolean enforceNonZero)
    {
        while (true)
        {
            System.out.print(input);
            try
            {
                int val = Integer.parseInt(scnr.next());
                if (enforceNonZero && val <= 0) //Only check condition in case of party size.
                {
                    System.out.println("Please enter a " + context + " greater than 0.\n");
                    continue;
                }
                else if (val < 0) //Only check condition in case of tip percentage.
                {
                    System.out.println("Please enter a " + context + " of at least 0.\n");
                    continue;
                }
                return val;
            }
            catch (NumberFormatException e) //Error case for non-numeric input.
            {
                System.out.println("Pleae enter a valid " + context + ".\n");
            }
        }
    }

    //Return formatted string of decimal value.
    public String formatDouble(double val)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(val);
    }
}
