<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Navigation Bar -->
<link rel="stylesheet" href="header.css">
<style>
    /* Navigation Bar Styles */
    .navbar {
        background-color: #fff;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        padding: 1rem 0;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        z-index: 1000;
    }

    .nav-container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .nav-left,
    .nav-right {
        display: flex;
        align-items: center;
        gap: 2rem;
    }

    .logo-link {
        text-decoration: none !important;
        transition: transform 0.3s ease;
        display: block;
    }

    .logo-link:hover {
        transform: scale(1.05);
    }

    .logo-link h1 {
        color: #3498db;
        font-size: 24px;
        font-weight: bold;
        margin: 0;
    }

    .nav-left a:not(.logo-link),
    .nav-right a {
        text-decoration: none;
        color: #2f3640;
        padding: 8px 16px;
        border-radius: 5px;
        transition: all 0.3s ease;
        display: flex;
        align-items: center;
        gap: 0.5rem;
    }

    .nav-left a i,
    .nav-right a i {
        font-size: 1.1rem;
    }

    .login-btn {
        background-color: #3498db;
        color: white !important;
    }

    .logout-btn {
        background-color: #e74c3c;
        color: white !important;
    }

    .nav-left a:hover:not(.logo-link),
    .nav-right a:hover {
        opacity: 0.9;
        transform: translateY(-2px);
    }

    /* Add margin to main content to prevent overlap with fixed navbar */
    .main-content {
        margin-top: 80px;
        min-height: calc(100vh - 80px);
        padding: 20px;
    }

    /* Responsive Design */
    @media (max-width: 768px) {

        .nav-left,
        .nav-right {
            gap: 1rem;
        }

        .nav-left a:not(.logo-link),
        .nav-right a {
            padding: 6px 12px;
            font-size: 14px;
        }

        .logo-link h1 {
            font-size: 20px;
        }
    }
</style>
<nav class="navbar">
    <div class="nav-container">
        <div class="nav-left">
            <a href="index" class="logo-link" style="text-decoration: none;">
                <h1>EMS</h1>
            </a>
            <!--                        <a href="manage-employees.html"><i class="fas fa-users"></i> Employees</a>-->
        </div>
        <div class="nav-right">
            <a href="login" class="login-btn" id="loginBtn"><i class="fas fa-sign-in-alt"></i> Login</a>
        </div>
    </div>
</nav>

<!--<script>
// Function to check login status and update navigation
function updateNavigation() {
const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
const loginBtn = document.getElementById('loginBtn');
const logoutBtn = document.getElementById('logoutBtn');

if (isLoggedIn) {
loginBtn.style.display = 'none';
logoutBtn.style.display = 'flex';
} else {
loginBtn.style.display = 'flex';
logoutBtn.style.display = 'none';
}
}

// Handle logout
document.getElementById('logoutBtn').addEventListener('click', function (e) {
e.preventDefault();
localStorage.removeItem('isLoggedIn');
window.location.href = 'login.html';
});

// Update navigation when page loads
document.addEventListener('DOMContentLoaded', updateNavigation);
</script>-->