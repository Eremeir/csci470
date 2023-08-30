// Employee.java
// Employee abstract superclass.

/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 5           Summer 2023 *
 *                                                        *
 *  Class Name: Employee                                  *
 *                                                        *
 *  Developer: Matt Borek                                 *
 *                                                        *
 *  Purpose: Calculate payroll for a company              *
 *           polymorphically.                             *
 *                                                        *
 **********************************************************/

public abstract class Employee {

   private final String firstName;
   private final String lastName;
   private final String socialSecurityNumber;
   private final Date birthDate;

   // constructor
   public Employee(String firstName, String lastName, String socialSecurityNumber, Date birthDate) {
   
      this.firstName = firstName;                                    
      this.lastName = lastName;                                    
      this.socialSecurityNumber = socialSecurityNumber;
      this.birthDate = birthDate;         
   } 

   // return first name
   public String getFirstName() {
   
      return firstName;
   } 

   // return last name
   public String getLastName() {
   
      return lastName;
   } 

   // return social security number
   public String getSocialSecurityNumber() {
   
      return socialSecurityNumber;
   }

   // return birth date
   public Date getBirthDate() {
       return birthDate;
   }

   // return String representation of Employee object
   @Override
   public String toString() {
   
      return String.format("%s %s%nsocial security number: %s %nbirth date: %s", 
         getFirstName(), getLastName(), getSocialSecurityNumber(), getBirthDate());
   } 

   // abstract method must be overridden by concrete subclasses
   public abstract double earnings(); 
     // no implementation here
}
