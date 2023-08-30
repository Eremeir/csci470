/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 4           Summer 2023 *
 *                                                        *
 *  Class Name: Destination                               *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Purpose: Calculate eligible destinations for an       *
 *           airline mileage points redemption app.       *
 *                                                        *
 **********************************************************/

public class Destination
{
    //Member Vars
    private String city;           //City name of destination airport
    private int regMiles;          //Number of miles to travel to a destination during regular travel periods
    private int superMiles;        //Number of miles to travel to a destination during supersaver travel periods
    private int firstClassUpgrade; //Number of additional miles required to upgrade to first class.
    private int startMonth;        //Number corresponding to first motnh of the year during supersaver season
    private int endMonth;          //Number corresponding to last motnh of the year during supersaver season

    public Destination(String city, int regMiles, int superMiles, int firstClassUpgrade, int startMonth, int endMonth) 
    {
        this.city = city;
        this.regMiles = regMiles;
        this.superMiles = superMiles;
        this.firstClassUpgrade = firstClassUpgrade;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    //Retrieve destination city instance variable.
    public String getCity()
    {
        return city;
    }

    //Retrieve regular miles instance variable.
    public int getRegMiles()
    {
        return regMiles;
    }

    //Retrieve supersaver miles instance variable.
    public int getSuperMiles()
    {
        return superMiles;
    }

    //Retrieve first class upgrade miles instance variable.
    public int getFirstClassUpgrade()
    {
        return firstClassUpgrade;
    }

    //Retrieve supersaver miles instance variable.
    public int getStartMonth()
    {
        return startMonth;
    }

    //Retrieve supersaver miles instance variable.
    public int getEndMonth()
    {
        return endMonth;
    }
}
