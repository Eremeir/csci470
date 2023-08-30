/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 3           Summer 2023 *
 *                                                        *
 *  Class Name: TipCalculator                             *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Purpose: Calculate tips for group orders through      *
 *           a console app.                               *
 *                                                        *
 **********************************************************/

public class TipCalculator
{
    //Member Vars
    private double billAmt; //Dollar total of the bill
    private int tipPercent; //Desired percentage of bill to tip
    private int partySize;  //Number of people in the group

    public TipCalculator() //Default Constructor
    {
        billAmt = 0;
        tipPercent = 20;
        partySize = 1;
    }

    //Retrieve bill ammount instance variable.
    public double getBillAmt()
    {
        return billAmt;
    }

    //Assign bill ammount instance variable.
    public void setBillAmt(double billAmt)
    {
        this.billAmt = billAmt;
    }

    //Retrieve tip percentage instance variable.
    public int getTipPercent()
    {
        return tipPercent;
    }

    //Assign tip percentage instance variable.
    public void setTipPercent(int tipPercent)
    {
        this.tipPercent = tipPercent;
    }

    //Retrieve party size instance variable.
    public int getPartySize()
    {
        return partySize;
    }

    //Assign party size instance variable.
    public void setPartySize(int partySize)
    {
        this.partySize = partySize;
    }

    //Calculate total bill ammount including tip.
    public double getTotalBill() 
    {
        return billAmt * ((tipPercent / 100.0) + 1);
    }

    //Calculate total cost for even split payment.
    public double getIndividualShare()
    {
        return getTotalBill() / partySize;
    }
}
