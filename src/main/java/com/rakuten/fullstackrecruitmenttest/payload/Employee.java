package com.rakuten.fullstackrecruitmenttest.payload;

public class Employee {
    private String name;
    private String department;
    private String designation;
    private double salary;
    private long joiningDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(long joiningDate) {
        this.joiningDate = joiningDate;
    }
}
