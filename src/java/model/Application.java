/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.EmployeeDAO;
import java.lang.*;
import java.io.*;
import java.sql.Date;

/**
 *
 * @author Vu Thi Huong Giang
 */
public class Application {

    private int applicationId;
    private String title;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private int createdBy;
    private Integer processedBy;
    private String note;
    private int leaveOnPerson;

    private String createdByName;
    private String processedByName;
    private String createByRole;
    private String leaveOnPersonName;

    public Application(String title, Date startDate, Date endDate, String reason, String status, int createdBy, Integer processedBy, String note, int leaveOnPerson) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.createdBy = createdBy;
        this.processedBy = processedBy;
        this.note = note;
        this.leaveOnPerson = leaveOnPerson;
    }

    public Application() {
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(Integer processedBy) {
        this.processedBy = processedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedByName() {
        EmployeeDAO edao = new EmployeeDAO();
        return edao.getByEmployeeId(createdBy).getName();
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getProcessedByName() {
        EmployeeDAO edao = new EmployeeDAO();
        if (processedBy == 0) {
            return " ";
        } else {
            return edao.getByEmployeeId(processedBy).getName();
        }
    }

    public void setProcessedByName(String processedByName) {
        this.processedByName = processedByName;
    }

    public String getCreateByRole() {
        return createByRole;
    }

    public void setCreateByRole(String createByRole) {
        this.createByRole = createByRole;
    }

    public int getLeaveOnPerson() {
        return leaveOnPerson;
    }

    public void setLeaveOnPerson(int leaveOnPerson) {
        this.leaveOnPerson = leaveOnPerson;
    }

    public String getLeaveOnPersonName() {
        EmployeeDAO edao = new EmployeeDAO();
        return edao.getByEmployeeId(leaveOnPerson).getName();
    }

    @Override
    public String toString() {
        return "Application{" + "applicationId=" + applicationId + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", reason=" + reason + ", status=" + status + ", createdBy=" + createdBy + ", processedBy=" + processedBy + ", note=" + note + ", leaveOnPerson=" + leaveOnPerson + '}';
    }

}
