/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 2           Summer 2023 *
 *                                                        *
 *  Class Name: Invoice                                   *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Purpose: Test methods of Invoice class by             *
 *           creating and printing dummy invoices.        *
 *                                                        *
 **********************************************************/

import java.text.DecimalFormat; //Import decimal format class for $sign formatting.

public class Invoice {

    //Member Vars
    private String partNumber;  //Internal SKU for uniquely identifying each part
    private String description; //Description of part details
    private int quantity;       //Quantity of part ordered
    private double itemPrice;   //Price per item of part
    
    public Invoice(String partNumber, String description, int quantity, double itemPrice) //Constructor
    {
        //Call setter functions to handle assignment.
        setPartNumber(partNumber);
        setDescription(description);
        setQuantity(quantity);
        setItemPrice(itemPrice);
    }

    public Invoice() //Default Constructor
    {
        //Call setter functions to handle assignment with default values.
        setPartNumber("");
        setDescription("");
        setQuantity(0);
        setItemPrice(0.0);
    }

    //Retrieve part number instance variable.
    public String getPartNumber()
    {
        return partNumber;
    }

    //Assign part number instance variable.
    public void setPartNumber(String partNumber)
    {
        this.partNumber = partNumber;
    }

    //Retrieve description instance variable.
    public String getDescription()
    {
        return description;
    }

    //Assign description instance variable.
    public void setDescription(String description)
    {
        this.description = description;
    }

    //Retrieve quantity instance variable.
    public int getQuantity()
    {
        return quantity;
    }

    //Assign quantity instance variable.
    public void setQuantity(int quantity)
    {
        if (quantity > 0)
        {
            this.quantity = quantity;
        }
        else
        {
            this.quantity = 0;
        }
    }

    //Retrieve item price instance variable.
    public double getItemPrice()
    {
        return itemPrice;
    }

    //Assign item price instance variable.
    public void setItemPrice(double itemPrice)
    {
        if (itemPrice > 0)
        {
            this.itemPrice = itemPrice;
        }
        else
        {
            this.itemPrice = 0.0;
        }
    }

    //Calculate the invoice amount by multiplying quantity by item price.
    public double getInvoiceAmount()
    {
        return quantity * itemPrice;
    }

    //Print formatted invoice contents
    public void printInvoice()
    {
        DecimalFormat decimalFormat = new DecimalFormat("$##,###,##0.00");

        System.out.printf("%16s: %s%n", "Part No.", getPartNumber());
        System.out.printf("%16s: %s%n", "Item Desc.", getDescription());
        System.out.printf("%16s: %d%n", "Quantity", getQuantity());
        System.out.printf("%16s: %.2f%n", "Item Price", getItemPrice());
        System.out.printf("%16s: %14s%n", "Invoice Subtotal", decimalFormat.format(getInvoiceAmount()));
        System.out.println("*********************************");
    }
}
