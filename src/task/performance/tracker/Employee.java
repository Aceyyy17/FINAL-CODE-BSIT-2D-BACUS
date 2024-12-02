
package task.performance.tracker;

import java.util.Scanner;


public class Employee {
    
public void empTransac(){
        Scanner sc = new Scanner(System.in);
        String res;
        
        
     do{  
        System.out.println("\n---------------------------");
        System.out.print("|WELCOME TO EMPLOYEE PANEL|");
        System.out.println("\n---------------------------");
        System.out.println("1. ADD EMPLOYEE");
        System.out.println("2. VIEW EMPLOYEE");
        System.out.println("3. UPDATE EMPLOYEE");
        System.out.println("4. DELETE EMPLOYEE");
        System.out.println("5. EXIT");
        
        int opt = -1;
        boolean validOption = false;
        
        while (!validOption){
           System.out.print("Enter option: ");
                if (sc.hasNextInt()) {
                    opt = sc.nextInt();
                    if (opt >= 1 && opt <= 5) {
                       validOption = true;
                    }else {
                        System.out.println("Wrong input!, Maximum is 5");
                    }
                }else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next();
                }
            } 
        
        Employee emp = new Employee();
        switch(opt){
            
            case 1:
            emp.addEmployee();
            emp.viewEmployee();
            
                break;
                
            case 2:
            emp.viewEmployee();    
            
                break;
            case 3:
            emp.viewEmployee(); 
            emp.updateEmployee(); 
            emp.viewEmployee(); 
            
                break;
            case 4:
             emp.viewEmployee();   
             emp.deleteEmployee();
             emp.viewEmployee(); 
                break;
            case 5:
            System.out.println("Existing...");    
               
                break;    
        }
        System.out.print("Do you want to continue? yes/no: ");
        res = sc.next();
     }while(res.equalsIgnoreCase("yes"));
    }
    
    public void addEmployee(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Employee First name: ");
        String fname = sc.nextLine();
        System.out.print("Employee Last name: ");
        String lname = sc.next();
        System.out.print("Email: ");
        String eml = sc.next();
        System.out.print("Department: ");
        String dept = sc.next();
        
        
        String qry = "INSERT INTO tbl_employee (emp_fname, emp_lname, emp_email, emp_dept )VALUES (?, ?, ?, ?)";
        config conf = new config();
        conf.addRecord(qry, fname, lname, eml, dept);
    }
    
    public void viewEmployee() {
        String qry = "SELECT * FROM tbl_employee";
        String[] Headers = {"ID", "First name", "Last name", "Email", "Department"};
        String[] Columns = {"emp_id", "emp_fname", "emp_lname", "emp_email", "emp_dept"};
        config conf = new config();
        conf.viewRecords(qry, Headers, Columns);
    }
    
    private void updateEmployee(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to update: ");   
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT emp_id FROM tbl_employee WHERE emp_id = ?",id)==0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Please select employee id again: ");
            id = sc.nextInt();
            
        }
        
        System.out.print("New First name: ");
        String fname = sc.next();
        System.out.print("New Last name: ");
        String lname = sc.next();
        System.out.print("New Email: ");
        String eml = sc.next();
        System.out.print("Department: ");
        String dept = sc.next();
        
        String qry = "UPDATE tbl_employee SET emp_fname = ?, emp_lname = ?, emp_email = ?, emp_dept = ? WHERE emp_id = ?";
        conf.updateRecord(qry,fname, lname, eml, dept, id );
    }
    public void deleteEmployee(){
        Scanner sc = new Scanner(System.in);    
        config conf = new config();
        
        System.out.print("Enter Id to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT emp_id FROM tbl_employee WHERE emp_id = ?",id)==0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Please select employee id again: ");
            id = sc.nextInt();
        }
        String qry = "DELETE  FROM tbl_employee WHERE emp_id = ?";
        conf.deleteRecord(qry, id  );
    
    }
}


