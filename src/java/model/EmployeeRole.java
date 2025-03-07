/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.lang.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Vu Thi Huong Giang
 */

public class EmployeeRole {

    private int roleId;
    private  int employeeId;

    public EmployeeRole() {
    }

    public EmployeeRole(int roleId, int employeeId) {
        this.roleId = roleId;
        this.employeeId = employeeId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "EmployeeRole{" + "roleId=" + roleId + ", employeeId=" + employeeId + '}';
    }
    
    
}
