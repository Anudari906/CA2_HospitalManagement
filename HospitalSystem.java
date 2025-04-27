/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @aut2hor anudari
 */
import java.util.*;


public class HospitalSystem {
    private static List<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Dummy Managers 
        Manager headSurgeon = new Manager ("Dr. Alice", "Chief Surgeon");
        Manager nurseManager = new Manager("Nurse Bob", "Head Nurse");
        
        // Dummy Departments
        Department emergency =new Department ("Emergency Room");
        Department surgery = new Department ("Surgery");
        
        // Dummy Employees
        employees.add(new Employee("Nurse John", nurseManager, emergency));
        employees.add(new Employee("Dr. Emily", headSurgeon, surgery));
        employees.add(new Employee("Nurse Steve", nurseManager, surgery));
        employees.add(new Employee("Dr. Kate" , headSurgeon, emergency));
        
        // SORT the employees before menu starts
        employees = mergeSort(employees);  // <--- ADD THIS LINE

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getUserChoice();
            MenuOption option = MenuOption.values()[choice - 1];

            switch (option) {
                case SORT:
                    sortEmployees();
                    // We will add sorting code here
                    break;
                case SEARCH:
                    searchEmployee();
                    // We will add searching code here
                    break;
                case ADD_EMPLOYEE:
                    addNewEmployee();
                    // We will add employee adding code here
                    break;
                case GENERATE_RANDOM:
                    generateRandomEmployees();
                    // We will add random generation code here
                    break;
                case EXIT:
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
            }
        }
    }
    private static void sortEmployees() {
    if (employees.size() <= 1) {
        return;
    }
    employees = mergeSort(employees);
    System.out.println("Employees sorted successfully!\nShowing first 20 employees:");
    for (int i = 0; i < Math.min(20, employees.size()); i++) {
        System.out.println((i + 1) + ". " + employees.get(i).getEmployeeName());
    }
}
    private static void searchEmployee() {
    System.out.print("Enter the employee name to search: ");
    String searchName = scanner.nextLine().replaceAll("\\s+", " ").trim();

    int left = 0;
    int right = employees.size() - 1;
    boolean found = false;

    while (left <= right) {
        int mid = left + (right - left) / 2;
        String midName = employees.get(mid).getEmployeeName();

        int result = searchName.compareToIgnoreCase(midName);

        if (result == 0) {
            // Found!
            Employee foundEmployee = employees.get(mid);
            System.out.println("Employee Found:");
            System.out.println("Name: " + foundEmployee.getEmployeeName());
            System.out.println("Manager: " + foundEmployee.getManager().getName() + " (" + foundEmployee.getManager().getType() + ")");
            System.out.println("Department: " + foundEmployee.getDepartment().getDepartmentName());
            found = true;
            break;
        } else if (result < 0) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    if (!found) {
        System.out.println("Employee NOT found in the system.");
    }
}
    private static void addNewEmployee() {
    System.out.print("Enter the employee name: ");
    String name = scanner.nextLine().trim();
    
    System.out.println("Select a Manager Type:");
    System.out.println("1. Chief Surgeon");
    System.out.println("2. Head Nurse");
    System.out.println("3. Admin Manager");
    int managerChoice = getValidatedInput(1, 3);
    
    Manager manager = null;
    switch (managerChoice) {
        case 1:
            manager = new Manager("Dr. Smith", "Chief Surgeon");
            break;
        case 2:
            manager = new Manager("Nurse Adams", "Head Nurse");
            break;
        case 3:
            manager = new Manager("Mr. Brown", "Admin Manager");
            break;
    }
    System.out.println("Select a Department:");
    System.out.println("1. Emergency Room");
    System.out.println("2. Surgery");
    System.out.println("3. Administration");
    int departmentChoice = getValidatedInput(1, 3);
    
    Department department = null;
    switch (departmentChoice) {
        case 1:
            department = new Department("Emergency Room");
            break;
        case 2:
            department = new Department("Surgery");
            break;
        case 3:
            department = new Department("Administration");
            break;
    }

    Employee newEmployee = new Employee(name, manager, department);
    employees.add(newEmployee);

    System.out.println("New employee added successfully!");
}
    private static int getValidatedInput(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
            System.out.print("Enter choice (" + min + "-" + max + "): ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    return choice;
            
}
    private static void generateRandomEmployees() {
    String[] randomNames = {"Alex", "Taylor", "Jordan", "Casey", "Morgan", "Chris", "Sam", "Robin", "Jamie", "Pat"};
    String[] managerTypes = {"Chief Surgeon", "Head Nurse", "Admin Manager"};
    String[] managerNames = {"Dr. Smith", "Nurse Adams", "Mr. Brown"};
    String[] departments = {"Emergency Room", "Surgery", "Administration"};
    
    Random rand = new Random();
    int numberToGenerate = rand.nextInt(5) + 1; //Generate between 1 to 5 random employees
    
    for (int u = 0; i < numberToGenerate; i++) {
        String name = randomNames[rand.nextInt(randomNames.length)];
        String managerName = managerNames[rand.nextInt(managerNames.length)];
        String managerType = managerTypes[rand.nextInt(managerTypes.length)];
        String departmentName = departments[rand.nextInt(departments.length)];
        
        Manager manager = new Manager(managerName, managerType);
        Department department = new Department (departmentName);
        Employee employee = new Employee (name, manager, department);
        
        employees.add(employee);
    }
    System.out.println(numberToGenerate + " random employee generated succesfully!");
    }
        
    private static void showMenu() {
        System.out.println("\n=== Hospital Management Menu ===");
        for (int i = 0; i < MenuOption.values().length; i++) {
            System.out.println((i + 1) + ". " + MenuOption.values()[i]);
        }
    }

    private static int getUserChoice() {
        int choice = -1;
        while (choice < 1 || choice > MenuOption.values().length) {
            System.out.print("Enter your choice (1-" + MenuOption.values().length + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
        return choice;
    }

    private static List<Employee> mergeSort(List<Employee> list) {
    if (list.size() <= 1) {
        return list;
    }

    int middle = list.size() / 2;
    List<Employee> left = mergeSort(list.subList(0, middle));
    List<Employee> right = mergeSort(list.subList(middle, list.size()));

    return merge(left, right);
}

private static List<Employee> merge(List<Employee> left, List<Employee> right) {
    List<Employee> merged = new ArrayList<>();
    int i = 0, j = 0;

    while (i < left.size() && j < right.size()) {
        if (left.get(i).getEmployeeName().compareToIgnoreCase(right.get(j).getEmployeeName()) <= 0) {
            merged.add(left.get(i));
            i++;
        } else {
            merged.add(right.get(j));
            j++;
        }
    }

    while (i < left.size()) {
        merged.add(left.get(i));
        i++;
    }

    while (j < right.size()) {
        merged.add(right.get(j));
        j++;
    }

    return merged;
}

}
// This is the Manager class
// This is the Manager class
// Displaying the hospital main menu
//"Set up basic project structure"
//"Implemented Merge Sort for employee names"
//"Implemented Merge Sort for employee names"
//"Implemented Merge Sort for employee names"
//"Created Manager and Department classes"