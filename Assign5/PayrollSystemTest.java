// PayrollSystemTest.java
// Employee hierarchy test program.

/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 5           Summer 2023 *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Due Date: July 28th, 2023                             *
 *                                                        *
 *  Purpose: Calculate payroll for a company              *
 *           polymorphically.                             *
 *                                                        *
 **********************************************************/
import java.util.Scanner;

public class PayrollSystemTest { 

   public static void main(String[] args) {
   
      // create subclass objects
      SalariedEmployee salariedEmployee = 
         new SalariedEmployee("John", "Smith", "111-11-1111", new Date(6, 15, 1944), 800.00);
      HourlyEmployee hourlyEmployee = 
         new HourlyEmployee("Karen", "Price", "222-22-2222", new Date(12, 29, 1960), 16.75, 40);
      CommissionEmployee commissionEmployee = 
         new CommissionEmployee(
         "Sue", "Jones", "333-33-3333", new Date(9, 8, 1954), 10000, .06);
      BasePlusCommissionEmployee basePlusCommissionEmployee = 
         new BasePlusCommissionEmployee(
         "Bob", "Lewis", "444-44-4444", new Date(3, 2, 1965), 5000, .04, 300);

      System.out.println("Employees processed individually:");
      
      System.out.printf("%n%s%n%s: $%,.2f%n%n", 
         salariedEmployee, "earned", salariedEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n",
         hourlyEmployee, "earned", hourlyEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n",
         commissionEmployee, "earned", commissionEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n", 
         basePlusCommissionEmployee, 
         "earned", basePlusCommissionEmployee.earnings());

      // create four-element Employee array
      Employee[] employees = new Employee[4]; 

      // initialize array with Employees
      employees[0] = salariedEmployee;
      employees[1] = hourlyEmployee;
      employees[2] = commissionEmployee; 
      employees[3] = basePlusCommissionEmployee;

      Scanner scnr = new Scanner(System.in);
      System.out.print("Enter the current month (1 - 12): ");
      int currentMonth = scnr.nextInt();
      scnr.close();

      System.out.printf("\nEmployees processed polymorphically:%n%n");
      
      // generically process each element in array employees
      for (Employee currentEmployee : employees) {
      
         System.out.println(currentEmployee); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if (currentEmployee instanceof BasePlusCommissionEmployee) { 
         
            // downcast Employee reference to 
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee = 
               (BasePlusCommissionEmployee) currentEmployee;

            employee.setBaseSalary(1.10 * employee.getBaseSalary());

            System.out.printf(
               "new base salary with 10%% increase is: $%,.2f%n", employee.getBaseSalary());
         } 

         int birthMonth = currentEmployee.getBirthDate().getMonth();
         if (currentMonth == birthMonth) {
            System.out.printf("earned $%,.2f plus $100.00 birthday bonus%n%n", currentEmployee.earnings());
         }
         else
         {
            System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
         }
         
      } 

      // get type name of each object in employees array
      for (int j = 0; j < employees.length; j++)
         System.out.printf("Employee %d is a %s%n", j, employees[j].getClass().getName()); 
   }
}
