/*
 *  
Java18-OOJ
 */
package banksystem.model;

import java.util.List;


public class Employee {
    
    private int id;
    
    private int number;
    
    private String name;
    
    private List<Employee> employees;

    public Employee(int id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
        
    }

    public Employee() {
    }

    

    

    public Employee(int number) {
        this.number = number;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees=employees;
    }

   
    

}
