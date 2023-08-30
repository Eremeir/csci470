/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 6           Summer 2023 *
 *                                                        *
 *  Class Name: Mile Redeemer                             *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Purpose: Calculate eligible destinations for an       *
 *           airline mileage points redemption app        *
 *           in a friendly graphical user interface.      *
 *                                                        *
 **********************************************************/
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

public class MileRedeemer
{
    private Destination[] destinationArray; //Array to hold destination objects
    private int remainingMiles;             //Int to hold remaining miles after ticket redemption

    //Read information from the destinations file, create destination objects and put them into an array to sort by descending mileage.
    public void readDestinations(Scanner fileScanner)
    {
        ArrayList<Destination> destinationList = new ArrayList<>();

        while (fileScanner.hasNextLine()) //While not at end of file
        {
            String[] varStrings = fileScanner.nextLine().split(";"); //Split line at delimiter to extract parts
            String city = varStrings[0];
            int regMiles = Integer.parseInt(varStrings[1]);
            int superMiles = Integer.parseInt(varStrings[2]);
            int firstClassUpgrade = Integer.parseInt(varStrings[3]);
            String[] monthSplit = varStrings[4].split("-"); //Split month at hyphen
            int startMonth = Integer.parseInt(monthSplit[0]);
            int endMonth = Integer.parseInt(monthSplit[1]);

            Destination destination = new Destination(city, regMiles, superMiles, firstClassUpgrade, startMonth, endMonth);
            destinationList.add(destination);
        }

        destinationArray = destinationList.toArray(new Destination[0]);
        Arrays.sort(destinationArray, Comparator.comparingInt(Destination::getRegMiles).reversed()); //Sort the array based on comparing the ints given from getRegMiles, then reverse it
    }

    //Return an array of city names in alphabetical order. 
    public String[] getCityNames()
    {
        String[] cityNames = new String[destinationArray.length];
        for (int i = 0; i < destinationArray.length; i++)
        {
            cityNames[i] = destinationArray[i].getCity();
        }
        Arrays.sort(cityNames);
        return cityNames;
    }

    //Maximally redeem miles using a given number of credits and a month to calcualte discount periods, then circle back to upgrade to first class.
    public String[] redeemMiles(int miles, int month)
    {
        remainingMiles = miles;
        ArrayList<String> tickets = new ArrayList<>();

        for (Destination destination : destinationArray)
        {
            if (isDiscounted(month, destination))
            {
                redeemSuperTicket(destination, tickets);
            }
            else
            {
                redeemRegTicket(destination, tickets);
            }
        }

        upgradeTickets(tickets); //Pass the list of redeemed tickets to see if there are any which can be further upgraded to first class.

        return tickets.toArray(new String[0]);
    }

    //Check if a given month is within the supersaver period for a given destination.
    private boolean isDiscounted(int month, Destination destination)
    {
        return month >= destination.getStartMonth() && month <= destination.getEndMonth();
    }

    //Redeem miles for a supersaver ticket for a given destination, add it to the list of redeemed tickets and adjust remaining miles.
    private void redeemSuperTicket(Destination destination, ArrayList<String> tickets)
    {
        if (remainingMiles >= destination.getSuperMiles())
        {
            tickets.add(createTicketDesc(destination, "Economy Class"));
            remainingMiles -= destination.getSuperMiles();
        }
    }

    //Redeem miles for a regular ticket for a given destination, add it to the list of redeemed tickets and adjust remaining miles.
    private void redeemRegTicket(Destination destination, ArrayList<String> tickets)
    {
        if (remainingMiles >= destination.getRegMiles())
        {
            tickets.add(createTicketDesc(destination, "Economy Class"));
            remainingMiles -= destination.getRegMiles();
        }
    }

    //Write ticket description dynamically based on seat class and destination.
    private String createTicketDesc(Destination destination, String seatClass)
    {
        return "* A trip to " + destination.getCity() + " in " + seatClass;
    }

    //Loop through redeemed tickets to upgrade any eligible tickets to first class.
    private void upgradeTickets(ArrayList<String> tickets)
    {
        ArrayList<Integer> destIndecies = new ArrayList<>(); //stores list of destination indecies for each ticket
        for (String ticket : tickets)
        {
            int j = getCityIndex(ticket);
            destIndecies.add(j);
        }

        for (int i = 0; i < destIndecies.size(); i++)
        {
            int j = destIndecies.get(i);
            Destination destination = destinationArray[j];

            if (remainingMiles >= destination.getFirstClassUpgrade()) //if enough earned miles
            {
                tickets.set(i, createTicketDesc(destination, "First Class"));
                remainingMiles -= destination.getFirstClassUpgrade();
            }
        }
    }

    //Retrieve the index of a destination's city from within the ticket string.
    private int getCityIndex(String ticket)
    {
        String destinationCity = ticket.substring(ticket.indexOf("to ") + 3, ticket.indexOf(" in"));
        for (int i = 0; i < destinationArray.length; i++)
        {
            if (destinationArray[i].getCity().equals(destinationCity)) //if destination city matches within the destination array
            {
                return i; //return index of destination in the destination array
            }
        }
        return -1; //destination city not found in array
    }

    //Retrieve the number of remaining mile credits.
    public int getRemainingMiles()
    {
        return remainingMiles;
    }

    //Return alphabetical array of destinations
    public Destination[] getAlphaDest()
    {
        Destination[] alphabeticalArray = Arrays.copyOf(destinationArray, destinationArray.length); //copy original array to not change original data
        Arrays.sort(alphabeticalArray, Comparator.comparing(Destination::getCity)); //sort by comparing result of getCity
        return alphabeticalArray;
    }
}
