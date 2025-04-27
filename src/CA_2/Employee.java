/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author anudari
 */
public class Employee {
    private String employeeName;
    private Manager manager;
    private Department department;

    public Employee(String employeeName, Manager manager, Department department) {
        this.employeeName = employeeName;
        this.manager = manager;
        this.department = department;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Manager getManager() {
        return manager;
    }

    public Department getDepartment() {
        return department;
    }
}