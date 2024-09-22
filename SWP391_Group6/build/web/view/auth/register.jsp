<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Registration</title>
        <style>
            /* Đặt kiểu cho phần tùy chọn đã có tài khoản và liên kết đăng nhập */
            .register-options {
                margin-top: 20px;
                text-align: center;
            }

            .register-options p {
                margin: 5px 0;
            }

            .register-options a {
                color: #007bff;
                text-decoration: none;
            }

            .register-options a:hover {
                text-decoration: underline;
            }

            /* Đặt kiểu chung cho body */
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            /* Đặt kiểu cho container của form đăng ký */
            .register-container {
                background-color: #fff;
                padding: 40px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 400px;
                text-align: center;
            }

            /* Tiêu đề đăng ký */
            .register-container h2 {
                margin-bottom: 20px;
                color: #333;
            }

            /* Đặt kiểu cho các form group (mỗi hàng họ tên, email, password, v.v.) */
            .form-group {
                margin-bottom: 15px;
                text-align: left;
            }

            /* Đặt kiểu cho nhãn của form */
            .form-group label {
                display: block;
                font-size: 14px;
                margin-bottom: 5px;
                color: #333;
            }

            /* Đặt kiểu cho các input (họ tên, email, password, v.v.) */
            .form-group input,
            .form-group select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }

            /* Kiểu cho nút đăng ký */
            button[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
            }

            /* Hiệu ứng khi hover lên nút đăng ký */
            button[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Đặt kiểu cho thông báo lỗi */
            .error-message {
                color: red;
                margin-top: 10px;
                font-size: 14px;
            }

            /* Đặt kiểu cho thông báo thành công */
            .success-message {
                color: green;
                margin-top: 10px;
                font-size: 14px;
            }

            /* Responsive cho màn hình nhỏ */
            @media (max-width: 480px) {
                .register-container {
                    width: 90%;
                    padding: 20px;
                }
            }

        </style>
    </head>
    <body>
        <div class="register-container">
            <h2>Register to the System</h2>

            <form action="register" method="post">
                <div class="form-group">
                    <label for="name">Full Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender" required>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="mobile">Mobile:</label>
                    <input type="tel" id="mobile" name="mobile" required pattern="[0-9]{10}" title="Enter a valid 10-digit mobile number">
                </div>

                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" required>
                </div>

                <!-- Error message display -->
                <c:if test="${not empty errorMessage}">
                    <p class="error-message">${errorMessage}</p>
                </c:if>

                <!-- Success message display -->
                <c:if test="${not empty successMessage}">
                    <p class="success-message">${successMessage}</p>
                </c:if>

                <button type="submit">Register</button>
            </form>

            <!-- Link to Login -->
            <div class="register-options">
                <p>Already have an account? <a href="login">Login here</a></p>
            </div>
        </div>
    </body>
</html>
