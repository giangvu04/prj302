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

public class Department {
    private int departmentId; 
    private String name;

    public Department() {
    }

    public Department(int departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", name=" + name + '}';
    }
    
    
    
    
    
    
    
    
}
