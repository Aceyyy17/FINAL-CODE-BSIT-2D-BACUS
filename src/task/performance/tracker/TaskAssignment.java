
package task.performance.tracker;

import java.util.Scanner;


public class TaskAssignment {
    
    public void taskTransac(){
        
        Scanner sc = new Scanner(System.in);
        String res;
        
        
     do{  
        System.out.println("\n----------------------------");
        System.out.print("|WELCOME TO TASK OPERATIONS|");
        System.out.println("\n----------------------------");
        System.out.println("1. ADD TASK");
        System.out.println("2. VIEW TASK");
        System.out.println("3. UPDATE TASK");
        System.out.println("4. DELETE TASK");
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
        
        TaskAssignment task = new TaskAssignment();
        switch(opt){
            
            case 1:
            task.addTask();
            task.viewTask();
            
                          break;
                
            case 2:
            task.viewTask();
                           break;
                           
            case 3:
            task.viewTask();
            task.updateTask();
            task.viewTask();
            
                          break;
                          
            case 4:
             task.viewTask();
            task.deleteTask();
            task.viewTask(); 
                          break;
                          
            case 5:
            System.out.println("Existing...");     
               
                          break;    
        }
        System.out.print("Do you want to continue? yes/no: ");
        res = sc.next();
       }while(res.equalsIgnoreCase("yes"));
       }
    
      public void addTask() {
      Scanner sc = new Scanner(System.in);
      
      config conf = new config();
      Employee emp = new Employee();
      emp.viewEmployee();

   
       System.out.print("Enter ID of the Employee to assign the task: ");
       int empID = sc.nextInt();
       sc.nextLine();

       String Esql = "SELECT emp_id FROM tbl_employee WHERE emp_id = ?";
       while (conf.getSingleValue(Esql, empID) == 0) {
        System.out.print("Employee ID doesn't exist, please select again: ");
        empID = sc.nextInt();
        sc.nextLine();
    }

    
       System.out.print("Task Name: ");
       String tName = sc.nextLine();
   
      System.out.print("Deadline (yyyy-mm-dd): "); 
      String deadline = sc.nextLine();

       System.out.print("Status: ");
       String stat = sc.nextLine();

   
       String qry = "INSERT INTO tbl_task (task_name, task_assigned, task_deadline, task_status) VALUES (?, ?, ?, ?)";
        conf.addRecord(qry, tName, empID, deadline, stat);

        System.out.println("Task successfully added and assigned to Employee ID: " + empID);
}

        private void viewTask() {
       String qry = "SELECT task_id, task_name, task_assigned, task_deadline, task_status "
            + "FROM tbl_task  JOIN tbl_employee  ON task_assigned = emp_id";
   
      String[] Headers = {"Task ID", "Task Name", "Assigned Employee ID",  "Deadline", "Status"};
      String[] Columns = {"task_id", "task_name",  "task_assigned", "task_deadline", "task_status"};
    
      config conf = new config();
      conf.viewRecords(qry, Headers, Columns);
}

        
        private void updateTask(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to update: ");   
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT task_id FROM tbl_task WHERE task_id = ?",id)==0){
           System.out.println("Selected ID doesn't exist!");
           System.out.print("Please select employee id again: ");
            id = sc.nextInt();
            
        }
        
        System.out.print("New task name: ");
        String taskname = sc.nextLine();
        System.out.print("New Assigned ID to: ");
        String assigned = sc.nextLine();
        System.out.print("New Deadline: ");
        String deadline = sc.nextLine();
        System.out.print("New Status: ");
        String stat = sc.nextLine();
        
        String qry = "UPDATE tbl_task SET task_name = ?, task_assigned = ?, task_deadline = ?, task_status = ? WHERE task_id = ?";
        conf.updateRecord(qry,taskname, assigned, deadline, stat, id );
    }
     
      public void deleteTask(){
         Scanner sc = new Scanner(System.in);    
        config conf = new config();
        
        System.out.print("Enter Id to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT task_id FROM tbl_task WHERE task_id = ?", id)==0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Please select task id again: ");
            id = sc.nextInt();
        }
        String qry = "DELETE  FROM tbl_task WHERE task_id = ?";
        conf.deleteRecord(qry, id  );
      }
    
    }

