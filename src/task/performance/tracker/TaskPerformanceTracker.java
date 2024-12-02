
package task.performance.tracker;

import java.util.Scanner;


public class TaskPerformanceTracker {

   
    public static void main(String[] args) {
      
    Scanner sc = new Scanner(System.in);
    boolean exit = true;
     do{  
        System.out.print("\n-----------------------------------------------");
        System.out.print("\n| WELCOME TO TASK AND PERFORMANCE TRACKER APP |");
        System.out.println("\n-----------------------------------------------");
        System.out.println("1. EMPLOYEE");
        System.out.println("2. TASK ASSIGNMENT");
        System.out.println("3. REPORTS");
        System.out.println("4. EXIT");
        
        int opt = -1;
        boolean validOption = false;
        
        while (!validOption){
           System.out.print("Enter option: ");
                if (sc.hasNextInt()) {
                    opt = sc.nextInt();
                    if (opt >= 1 && opt <= 4) {
                       validOption = true;
                    }else {
                        System.out.println("Wrong input!, Maximum is 4");
                    }
                }else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next();
                }
            } 

        switch(opt){
            
            case 1:
            Employee emp = new Employee();
            emp.empTransac();
                break;
                
            case 2:
            TaskAssignment task = new TaskAssignment();    
            task.taskTransac();
                break;
                
            case 3:
             Reports rp = new Reports();
             rp.empReports();
                break; 
           
            case 4:
                System.out.print("Exiting... You want to continue? yes/no: "); 
                String res = sc.next();
                if(res.equalsIgnoreCase("yes")){exit = false;
                }
                break;    
               
        }
     }while(exit);
       
    }
    
}

    

