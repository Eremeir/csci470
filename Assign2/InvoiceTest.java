/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 2           Summer 2023 *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Due Date: July 3rd, 2023                              *
 *                                                        *
 *  Purpose: Test methods of Invoice class by             *
 *           creating and printing dummy invoices.        *
 *                                                        *
 **********************************************************/

public class InvoiceTest {

    //Main subroutine to create hardcoded invoices and print out their contents
    public static void main(String[] args)
    {
        Invoice invoice0 = new Invoice();
        System.out.println("Invoice #0");
        invoice0.printInvoice();

        Invoice invoice1 = new Invoice("AB-23-4312", "Cordless Drill", 10, 189.00);
        System.out.println("Invoice #1");
        invoice1.printInvoice();

        Invoice invoice2 = new Invoice("BC-34-5423", "Hammer", 11, 18.00);
        System.out.println("Invoice #2");
        invoice2.printInvoice();

        Invoice invoice3 = new Invoice("CD-45-6534", "Philips Head Screwdriver", 12, 9.00);
        System.out.println("Invoice #3");
        invoice3.printInvoice();

        Invoice invoice4 = new Invoice("DE-56-7645", "Light Switch", 13, 25.00);
        System.out.println("Invoice #4");
        invoice4.printInvoice();

        Invoice invoice5 = new Invoice("EF-67-8756", "Carpenter's Square", 14, 30.00);
        System.out.println("Invoice #5");
        invoice5.printInvoice();
    }
}
