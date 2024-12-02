package task.performance.tracker;

import java.util.Map;
import java.util.Scanner;

public class Reports {
    public void empReports() {
        Scanner sc = new Scanner(System.in);
        String res;
        do {
            System.out.print("\n-----------------------------");
            System.out.print("\n| WELCOME TO REPORT SECTION |");
            System.out.println("\n-----------------------------");
            System.out.println("1. VIEW SPECIFIC REPORT");
            System.out.println("2. VIEW GENERAL REPORT");
            System.out.println("3. VIEW COMPLETED TASKS OF EMPLOYEE");
            System.out.println("4. EXIT");

            int opt = -1;
            boolean validChoice = false;

            while (!validChoice) {
                System.out.print("Enter option: ");
                if (sc.hasNextInt()) {
                    opt = sc.nextInt();
                    if (opt >= 1 && opt <= 4) {
                        validChoice = true;
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 and 4.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next();
                }
            }
            Reports rp = new Reports();

            switch (opt) {
                case 1:
                    rp.viewSpecificReport();
                    break;
                case 2:
                    rp.viewGeneralReport();
                    break;
                case 3:
                    rp.empCompletedTasks();
                    break;
                case 4:
                    System.out.println("Exiting to main menu...");
                    break;
            }

            System.out.print("Do you want to continue? yes/no: ");
            res = sc.next();
        } while (res.equalsIgnoreCase("yes"));
    }

   public void viewSpecificReport() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();

    System.out.print("Enter Employee ID: ");
    int empId = sc.nextInt();

    
    String empDetailsQuery = "SELECT emp_id, emp_fname, emp_lname, emp_email, emp_dept " +
                             "FROM tbl_employee " +
                             "WHERE emp_id = " + empId;

   
    Map<String, String> employeeDetails = conf.getSingleRecord(empDetailsQuery, 
        new String[]{"emp_id", "emp_fname", "emp_lname", "emp_email", "emp_dept"});

    if (employeeDetails == null || employeeDetails.isEmpty()) {
        System.out.println("No employee found with ID: " + empId);
        return;
    }

    
    System.out.println("\n--- Employee Details ---");
    System.out.println("ID: " + employeeDetails.get("emp_id"));
    System.out.println("First Name: " + employeeDetails.get("emp_fname"));
    System.out.println("Last Name: " + employeeDetails.get("emp_lname"));
    System.out.println("Email: " + employeeDetails.get("emp_email"));
    System.out.println("Department: " + employeeDetails.get("emp_dept"));

    System.out.println("\n--- Task Details ---");
    
    String empTasksQuery = "SELECT task_id, task_name, task_assigned, task_deadline, task_status " +
                           "FROM tbl_task " +
                           "WHERE task_assigned = " + empId;
    String[] taskHeaders = {"Task ID", "Task Name", "Assigned Employee ID", "Deadline", "Status"};
    String[] taskColumns = {"task_id", "task_name", "task_assigned", "task_deadline", "task_status"};
    conf.viewRecords(empTasksQuery, taskHeaders, taskColumns);
}




    public void viewGeneralReport() {
        config conf = new config();

        String genQuery = "SELECT task_id, task_name, task_assigned, emp_lname, task_deadline, task_status " +
                "FROM tbl_task " +
                "JOIN tbl_employee ON task_assigned = emp_id";
        String[] genHeaders = {"Task ID", "Task Name", "Assigned Employee ID", "Employee Last Name", "Deadline", "Status"};
        String[] genColumns = {"task_id", "task_name", "task_assigned", "emp_lname", "task_deadline", "task_status"};
        conf.viewRecords(genQuery, genHeaders, genColumns);
    }

    public void empCompletedTasks() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        String completedTasksQuery = "SELECT emp_id, emp_lname, COUNT(task_id) AS completed_tasks " +
                "FROM tbl_employee " +
                "LEFT JOIN tbl_task ON emp_id = task_assigned " +
                "WHERE emp_id = " + empId + " AND task_status = 'Completed' " +
                "GROUP BY emp_id, emp_lname";

        config conf = new config();
        String[] headers = {"Employee ID", "Employee Last Name", "Completed Tasks Count"};
        String[] columns = {"emp_id", "emp_lname", "completed_tasks"};

        conf.viewRecords(completedTasksQuery, headers, columns);
    }
}
