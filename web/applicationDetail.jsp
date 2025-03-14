<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application Detail - EMS</title>
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

        .application-container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 2rem;
        }

        .application-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 1px solid #eee;
        }

        .back-button {
            color: #3498db;
            text-decoration: none;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .application-title {
            font-size: 1.5rem;
            color: #2c3e50;
            margin: 0;
        }

        .status-badge {
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-size: 0.9rem;
            font-weight: 500;
        }

        .status-pending {
            background-color: #fff3cd;
            color: #856404;
        }

        .status-approved {
            background-color: #d4edda;
            color: #155724;
        }

        .status-rejected {
            background-color: #f8d7da;
            color: #721c24;
        }

        .application-details {
            display: grid;
            gap: 1.5rem;
        }

        .detail-group {
            display: grid;
            grid-template-columns: 150px 1fr;
            gap: 1rem;
            align-items: start;
        }

        .detail-label {
            font-weight: 500;
            color: #7f8c8d;
        }

        .detail-value {
            color: #2c3e50;
        }

        .reason-box {
            margin-top: 1rem;
        }

        .reason-box .detail-value {
            background-color: #f8f9fa;
            padding: 1rem;
            border-radius: 4px;
            white-space: pre-wrap;
        }

        .approval-section {
            margin-top: 2rem;
            padding-top: 2rem;
            border-top: 1px solid #eee;
        }

        .approval-form {
            display: grid;
            gap: 1.5rem;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .form-group label {
            font-weight: 500;
            color: #7f8c8d;
        }

        .form-group textarea {
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            resize: vertical;
            min-height: 100px;
        }

        .form-group textarea:focus {
            outline: none;
            border-color: #3498db;
        }

        .approval-actions {
            display: flex;
            justify-content: flex-end;
            gap: 1rem;
            margin-top: 1.5rem;
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
            text-decoration: none;
        }

        .btn-cancel {
            background-color: #f8f9fa;
            color: #7f8c8d;
        }

        .btn-cancel:hover {
            background-color: #e9ecef;
        }

        .btn-approve {
            background-color: #28a745;
            color: white;
        }

        .btn-approve:hover {
            background-color: #218838;
        }

        .btn-reject {
            background-color: #dc3545;
            color: white;
        }

        .btn-reject:hover {
            background-color: #c82333;
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
            .detail-group {
                grid-template-columns: 1fr;
            }

            .approval-actions {
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
        <div class="application-container">
            <div class="application-header">
                <a href="viewApplications" class="back-button">
                    <i class="fas fa-arrow-left"></i>
                    Back to Applications
                </a>
                <h1 class="application-title">Application Details</h1>
                <span class="status-badge status-${application.status.toLowerCase()}">
                    ${application.status}
                </span>
            </div>

            <div class="application-details">
                <div class="detail-group">
                    <div class="detail-label">Title</div>
                    <div class="detail-value">${application.title}</div>
                </div>

                <div class="detail-group">
                    <div class="detail-label">Created By</div>
                    <div class="detail-value">${application.createdBy}</div>
                </div>

                <div class="detail-group">
                    <div class="detail-label">Created By Role</div>
                    <div class="detail-value">${application.createdByRole}</div>
                </div>

                <div class="detail-group">
                    <div class="detail-label">Start Date</div>
                    <div class="detail-value">
                        <fmt:formatDate value="${application.startDate}" pattern="dd/MM/yyyy"/>
                    </div>
                </div>

                <div class="detail-group">
                    <div class="detail-label">End Date</div>
                    <div class="detail-value">
                        <fmt:formatDate value="${application.endDate}" pattern="dd/MM/yyyy"/>
                    </div>
                </div>

                <div class="detail-group">
                    <div class="detail-label">Leave On Person</div>
                    <div class="detail-value">${application.leaveOnPerson}</div>
                </div>

                <div class="detail-group">
                    <div class="detail-label">Processed By</div>
                    <div class="detail-value">
                        ${application.processedBy != null ? application.processedBy : 'Not processed yet'}
                    </div>
                </div>

                <div class="detail-group reason-box">
                    <div class="detail-label">Reason</div>
                    <div class="detail-value">${application.reason}</div>
                </div>
            </div>

            <c:if test="${application.status == 'Pending'}">
                <div class="approval-section">
                    <h2>Process Application</h2>
                    <form action="processApplication" method="POST" class="approval-form">
                        <input type="hidden" name="applicationId" value="${application.id}">
                        
                        <div class="form-group">
                            <label for="note">Note</label>
                            <textarea id="note" name="note" required 
                                    placeholder="Enter your note about this application"></textarea>
                        </div>

                        <div class="approval-actions">
                            <a href="viewApplications" class="btn btn-cancel">
                                <i class="fas fa-times"></i>
                                Cancel
                            </a>
                            <button type="submit" name="action" value="reject" class="btn btn-reject">
                                <i class="fas fa-times-circle"></i>
                                Reject
                            </button>
                            <button type="submit" name="action" value="approve" class="btn btn-approve">
                                <i class="fas fa-check-circle"></i>
                                Approve
                            </button>
                        </div>
                    </form>
                </div>
            </c:if>

            <c:if test="${application.status != 'Pending'}">
                <div class="application-actions">
                    <a href="viewApplications" class="btn btn-cancel">
                        <i class="fas fa-times"></i>
                        Back
                    </a>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html> 