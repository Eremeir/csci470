/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 4           Summer 2023 *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Due Date: July 21th, 2023                             *
 *                                                        *
 *  Purpose: Calculate eligible destinations for an       *
 *           airline mileage points redemption app.       *
 *                                                        *
 **********************************************************/
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MileRedeemerApp
{
    //Main subroutine to open console and loop for user input.
    public static void main(String[] args) throws IOException 
    {
        Scanner scnr = new Scanner(System.in);
        MileRedeemer mileRedeemer = new MileRedeemer();

        System.out.print("Please enter the name of the file: ");
        String fileName = scnr.nextLine();

        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
        mileRedeemer.readDestinations(fileScanner);
        fileScanner.close();
    
        System.out.println("\n----------------------------------------------------------------");
        System.out.printf("%56s%n","WELCOME TO THE JAVA AIRLINES MILES REDEEMER APP");
        System.out.println("----------------------------------------------------------------\n");

        String[] cityNames = mileRedeemer.getCityNames();
        System.out.println("List of destination cities your client can travel to:\n");
        for (String cityName : cityNames)
        {
            System.out.println(cityName);
        }
        
        do
        {
            System.out.println("\n----------------------------------------------------------------\n");
            System.out.print("Please enter your client's accumulated Frequent Flyer Miles: ");
            int miles = Integer.parseInt(scnr.nextLine());

            System.out.print("\nPlease enter your client's month of departure (1-12): ");
            int month = Integer.parseInt(scnr.nextLine());

            String[] tickets = mileRedeemer.redeemMiles(miles, month);
            if (tickets.length != 0) //As long as there is at least one redeemed ticket
            {
                System.out.println("\nYour client's Frequent Flyer Miles can be used to redeem the following tickets:\n");
                for (String ticket : tickets)
                {
                    System.out.println(ticket);
                }
            }
            else
            {
                System.out.println("\n*** Your client has not accumulated enough Frequent Flyer Miles ***");
            }

            System.out.println("\nYour client's remaining Frequent Flyer Miles: " + mileRedeemer.getRemainingMiles());
            System.out.println("\n----------------------------------------------------------------\n");
            System.out.print("Do you want to continue (y/n)? ");
        }
        while (scnr.nextLine().equalsIgnoreCase("y"));

        System.out.println("\n----------------------------------------------------------------");
        System.out.printf("%60s%n","THANK YOU FOR USING THE JAVA AIRLINES MILES REDEEMER APP");
        System.out.println("----------------------------------------------------------------\n");
        scnr.close();
    }
}
