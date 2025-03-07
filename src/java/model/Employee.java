/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.lang.*;
import java.sql.Date;
/**
 *
 * @author Vu Thi Huong Giang
 */

public class Employee {
    
    private int employeeId;
    private String name;
    private boolean gender;
    private int phoneNumber;
    private String address;
    private int departmentId;
    private Integer managerId; // có thể null
    private String email;
    private String password;
    private Date startWorkDate;
    private String departmentName;
    private String manageName;
    private String role;
    private int roleId;

    public Employee() {
    }

    public Employee(int employeeId, String name, boolean gender, int phoneNumber, String address, int departmentId, Integer managerId, String email, String password, Date startWorkDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.departmentId = departmentId;
        this.managerId = managerId;
        this.email = email;
        this.password = password;
        this.startWorkDate = startWorkDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(Date startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", address=" + address + ", departmentId=" + departmentId + ", managerId=" + managerId + ", email=" + email + ", password=" + password + ", startWorkDate=" + startWorkDate + '}';
    }
    

    
}
