<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Leave Request - EMS</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            min-height: 100vh;
            padding-top: 60px;
        }

        .main-content {
            max-width: 800px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .form-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 2rem;
        }

        .form-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
        }

        .back-button {
            color: #3498db;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .form-title {
            font-size: 1.5rem;
            color: #2c3e50;
            margin: 0;
        }

        .form-grid {
            display: grid;
            gap: 1.5rem;
            margin-bottom: 2rem;
        }

        .form-group {
            display: flex;
            flex-direction: column;
        }

        .form-group label {
            font-size: 0.9rem;
            color: #7f8c8d;
            margin-bottom: 0.5rem;
            font-weight: 500;
        }

        .form-group input,
        .form-group select {
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            transition: border-color 0.3s;
        }

        .form-group input:focus,
        .form-group select:focus {
            outline: none;
            border-color: #3498db;
        }

        .form-group input[readonly] {
            background-color: #f8f9fa;
            cursor: not-allowed;
        }

        .date-range {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1rem;
        }

        .reason-box {
            margin-top: 1rem;
        }

        .reason-box textarea {
            width: 100%;
            min-height: 120px;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            resize: vertical;
            transition: border-color 0.3s;
        }

        .reason-box textarea:focus {
            outline: none;
            border-color: #3498db;
        }

        .form-actions {
            display: flex;
            justify-content: flex-end;
            gap: 1rem;
            margin-top: 2rem;
        }

        .btn {
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 0.5rem;
            transition: all 0.3s;
        }

        .btn-cancel {
            background-color: #f8f9fa;
            color: #7f8c8d;
        }

        .btn-cancel:hover {
            background-color: #e9ecef;
        }

        .btn-submit {
            background-color: #3498db;
            color: white;
        }

        .btn-submit:hover {
            background-color: #2980b9;
        }

        .error-message {
            background-color: #ffebee;
            color: #c62828;
            padding: 1rem;
            border-radius: 4px;
            margin-bottom: 1rem;
            text-align: center;
        }

        @media (max-width: 768px) {
            .date-range {
                grid-template-columns: 1fr;
            }

            .form-actions {
                flex-direction: column;
            }

            .btn {
                width: 100%;
                justify-content: center;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="main-content">
        <div class="form-container">
            <div class="form-header">
                <a href="viewApplications" class="back-button">
                    <i class="fas fa-arrow-left"></i>
                    Back to Applications
                </a>
                <h1 class="form-title">Leave Request Form</h1>
            </div>

            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="applicationRequest" method="POST">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="name">Employee Name</label>
                        <input type="text" id="name" name="name" 
                               value="${sessionScope.employee.name}" readonly>
                    </div>

                    <div class="form-group">
                        <label for="department">Department</label>
                        <input type="text" id="name" name="name" 
                               value="${sessionScope.employee.departmentName}" readonly>
                    </div>

                    <div class="form-group">
                        <label for="role">Role</label>
                        <input type="text" id="role" name="role" 
                               value="${sessionScope.employee.role}" readonly>
                    </div>

                    <div class="form-group">
                        <label for="title">Title</label>
                        <input type="text" id="title" name="title" required 
                               placeholder="Enter leave request title">
                    </div>

                    <div class="date-range">
                        <div class="form-group">
                            <label for="startDate">Start Date</label>
                            <input type="date" id="startDate" name="startDate" required>
                        </div>

                        <div class="form-group">
                            <label for="endDate">End Date</label>
                            <input type="date" id="endDate" name="endDate" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="leaveOnPerson">Leave On Person</label>
                        <input type="text" id="leaveOnPerson" name="leaveOnPerson" required 
                               placeholder="Enter person to leave work on">
                    </div>

                    <div class="form-group reason-box">
                        <label for="reason">Reason for Leave</label>
                        <textarea id="reason" name="reason" required 
                                  placeholder="Please provide a detailed reason for your leave request"></textarea>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="viewApplications" class="btn btn-cancel">
                        <i class="fas fa-times"></i>
                        Cancel
                    </a>
                    <button type="submit" class="btn btn-submit">
                        <i class="fas fa-paper-plane"></i>
                        Submit Request
                    </button>
                </div>
            </form>
        </div>
    </div>

    <script>
        // Set minimum date for startDate and endDate to today
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('startDate').min = today;
        document.getElementById('endDate').min = today;

        // Update endDate minimum when startDate changes
        document.getElementById('startDate').addEventListener('change', function() {
            document.getElementById('endDate').min = this.value;
        });

        // Validate dates when form is submitted
        document.querySelector('form').addEventListener('submit', function(e) {
            const startDate = new Date(document.getElementById('startDate').value);
            const endDate = new Date(document.getElementById('endDate').value);
            const today = new Date();
            today.setHours(0, 0, 0, 0);

            if (startDate < today) {
                e.preventDefault();
                alert('Start date cannot be in the past');
            } else if (endDate < startDate) {
                e.preventDefault();
                alert('End date must be after start date');
            }
        });
    </script>
</body>
</html> 