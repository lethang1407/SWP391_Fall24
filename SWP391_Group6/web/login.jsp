<%-- 
    Document   : login
    Created on : Sep 12, 2024, 12:36:47 PM
    Author     : KEISHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
            margin: 0;
        }
        .container {
            display: flex;
            width: 80%;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }
        .form-section {
            width: 50%;
            padding: 40px;
            border-right: 1px solid #f0f0f0;
            box-sizing: border-box;
        }
        .form-section:last-child {
            border-right: none;
        }
        .form-section h2 {
            margin-bottom: 20px;
        }
        .form-option-buttons {
            margin-bottom: 20px;
        }
        .form-option-buttons button {
            padding: 10px;
            margin-right: 10px;
            cursor: pointer;
        }
        .login-form {
            display: none; /* Hide all forms initially */
        }
        .login-form.active {
            display: block; /* Show active form */
        }
        input[type="text"], input[type="password"], input[type="email"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .links {
            margin-top: 20px;
        }
        .links a {
            display: block;
            margin: 5px 0;
            color: #0066cc;
            text-decoration: none;
        }
        .links a:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        function showForm(section, formId) {
            const forms = document.querySelectorAll(`#${section} .login-form`);
            forms.forEach(form => form.classList.remove('active')); // Hide all forms
            document.getElementById(formId).classList.add('active'); // Show selected form
        }
    </script>
</head>
<body>
    <div class="container">
        <!-- Employee Login Section -->
        <div class="form-section" id="employee-section">
            <h2>Employee Login</h2>
            <div class="form-option-buttons">
                <button onclick="showForm('employee-section', 'employee-username-form')">Login with Username</button>
                <button onclick="showForm('employee-section', 'employee-email-form')">Login with Email</button>
            </div>
            <!-- Login with Username Form -->
            <div class="login-form active" id="employee-username-form">
                <form action="loginEmployeeByUsername" method="POST">
                    Username: <input type="text" name="username" required/> <br/>
                    Password: <input type="password" name="password" required/> <br/>
                    <input type="submit" value="Login as Employee"/>
                </form>
            </div>
            <!-- Login with Email Form -->
            <div class="login-form" id="employee-email-form">
                <form action="loginEmployeeByEmail" method="POST">
                    Email: <input type="email" name="email" required/> <br/>
                    Password: <input type="password" name="password" required/> <br/>
                    <input type="submit" value="Login as Employee"/>
                </form>
            </div>
            
        </div>
        
        <!-- Customer Login Section -->
        <div class="form-section" id="customer-section">
            <h2>Customer Login</h2>
            <div class="form-option-buttons">
                <button onclick="showForm('customer-section', 'customer-username-form')">Login with Username</button>
                <button onclick="showForm('customer-section', 'customer-email-form')">Login with Email</button>
            </div>
            <!-- Login with Username Form -->
            <div class="login-form active" id="customer-username-form">
                <form action="loginCustomerByUsername" method="POST">
                    Username: <input type="text" name="username" required/> <br/>
                    Password: <input type="password" name="password" required/> <br/>
                    <input type="submit" value="Login as Customer"/>
                </form>
            </div>
            <!-- Login with Email Form -->
            <div class="login-form" id="customer-email-form">
                <form action="loginCustomerByEmail" method="POST">
                    Email: <input type="email" name="email" required/> <br/>
                    Password: <input type="password" name="password" required/> <br/>
                    <input type="submit" value="Login as Customer"/>
                </form>
            </div>
            <!-- Additional Links for Customer -->
            <div class="links">
                <a href="registerCustomer">Register a Customer Account</a>
                <a href="changePasswordCustomer">Change Customer Password</a>
                <a href="resetPasswordCustomer">Reset Customer Password</a>
            </div>
        </div>
    </div>
</body>
</html>
