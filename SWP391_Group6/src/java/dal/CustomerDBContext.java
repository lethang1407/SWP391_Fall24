
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer_User;
import java.sql.*;
import java.time.LocalDateTime;
import model.Cart;
import model.PasswordResetToken;
import model.Role;

/**
 *
 * @author KEISHA
 */
public class CustomerDBContext extends DBContext<Customer_User> {

    public void insertCustomer(Customer_User customer) throws SQLException {
        PreparedStatement stm_insert = null;
        PreparedStatement stm_query = null;
        try {
            // Tắt chế độ tự commit
            connect.setAutoCommit(false);

            // Câu lệnh SQL để lấy cus_id lớn nhất hiện tại
            String sql_get_max_cus_id = "SELECT MAX(cus_id) AS max_cus_id FROM [dbo].[Customer]";
            stm_query = connect.prepareStatement(sql_get_max_cus_id);
            ResultSet rs = stm_query.executeQuery();
            int new_cus_id = 1;

            // Nếu có giá trị cus_id lớn nhất, tăng lên 1 để tạo cus_id mới
            if (rs.next() && rs.getInt("max_cus_id") > 0) {
                new_cus_id = rs.getInt("max_cus_id") + 1;
            }
            customer.setCus_id(new_cus_id);  // Đặt giá trị cus_id mới cho đối tượng customer

            // Câu lệnh SQL để chèn thông tin vào bảng Customer
            String sql_insert_cus = "INSERT INTO [dbo].[Customer]\n"
                    + "           ([cus_id]\n"
                    + "           ,[name_cus]\n"
                    + "           ,[password]\n"
                    + "           ,[email]\n"
                    + "           ,[c_phone]\n"
                    + "           ,[display_name]\n"
                    + "           ,[status]\n"
                    + "           ,[role_id]\n"
                    + "           ,[gender]\n"
                    + "           ,[username]\n"
                    + "           ,[birth_date]\n"
                    + "           ,[verification_code])\n"
                    + "     VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Chuẩn bị câu lệnh SQL chèn
            stm_insert = connect.prepareStatement(sql_insert_cus);
            stm_insert.setInt(1, customer.getCus_id());                       // cus_id
            stm_insert.setString(2, customer.getName_cus());               // name_cus
            stm_insert.setString(3, customer.getPassword());               // password
            stm_insert.setString(4, customer.getEmail());                  // email
            stm_insert.setString(5, customer.getC_phone());                // c_phone 
            stm_insert.setString(6, customer.getDisplay_name());           // display_name
            stm_insert.setBoolean(7, false);                                // status (giả sử là active - true)
            stm_insert.setInt(8, customer.getRole().getRole_id());         // role_id
            stm_insert.setInt(9, customer.isGender() ? 1 : 0);
            stm_insert.setString(10, customer.getUsername());
            stm_insert.setDate(11, customer.getDob());                     // dob (ngày sinh)
            stm_insert.setString(12, customer.getVerificationCode());

            // Thực thi câu lệnh chèn
            stm_insert.executeUpdate();

            // Commit các thay đổi
            connect.commit();
        } catch (SQLException ex) {
            // In ra lỗi chi tiết để dễ dàng debug
//            System.err.println("SQL Error: " + ex.getMessage());

            if (connect != null) {
                try {
                    connect.rollback();
                } catch (SQLException ex1) {
//                    System.err.println("Rollback Error: " + ex1.getMessage());
                }
            }

            // Ném lỗi ra để xử lý ở nơi gọi hàm
            throw ex;
        } finally {
            try {
                connect.setAutoCommit(true);
                if (stm_insert != null) {
                    stm_insert.close();
                }
                if (stm_query != null) {
                    stm_query.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isPhoneExists(String phone) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM [dbo].[Customer] WHERE c_phone = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connect.prepareStatement(sql);
            stm.setString(1, phone);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public boolean isUsernameExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM [dbo].[Customer] WHERE username = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connect.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public boolean isEmailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM [dbo].[Customer] WHERE email = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connect.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public boolean verifyCustomer(String verificationCode) throws SQLException {
        String sql = "UPDATE [dbo].[Customer] SET status = 1 WHERE verification_code = ?";
        PreparedStatement stm = null;
        try {
            stm = connect.prepareStatement(sql);
            stm.setString(1, verificationCode);
            int rowsUpdated = stm.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
    }

    // Lấy thông tin tài khoản khách hàng bằng email và mật khẩu
    public Customer_User getCustomerAccountByEmail(String email, String password) {
        String sql = """
                     SELECT cus_id, name_cus, email, status, avartar, role_id, cart_id
                     FROM Customer 
                     WHERE email = ? AND [password] = ?""";

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Customer_User customer = new Customer_User();
                    customer.setCus_id(rs.getInt("cus_id"));
                    customer.setName_cus(rs.getString("name_cus"));
                    customer.setEmail(rs.getString("email"));
                    customer.setStatus(rs.getBoolean("status"));
                    customer.setAvatar(rs.getString("avartar"));

                    Role role = new Role();
                    role.setRole_id(rs.getInt("role_id"));
                    customer.setRole(role);

                    return customer;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi lấy thông tin tài khoản", ex);
        }
        return null;
    }

    // Đổi mật khẩu khách hàng
    public boolean changePassword(int cus_id, String newPassword) {
        String sql = "UPDATE Customer SET password = ? WHERE cus_id = ?";
        boolean updated = false;

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setString(1, newPassword);
            stm.setInt(2, cus_id);
            int rowsAffected = stm.executeUpdate();
            updated = (rowsAffected > 0);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi thay đổi mật khẩu", ex);
        }
        return updated;
    }

    public void updateCustomer(Customer_User customer) {
        String sql = """
                     UPDATE Customer 
                     SET name_cus = ?, gender = ?, c_phone = ?, avartar = ?
                     WHERE cus_id = ?""";

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setString(1, customer.getName_cus());
            stm.setInt(2, customer.isGender() ? 1 : 0);
            stm.setString(3, customer.getC_phone());
            stm.setString(4, customer.getAvatar());
            stm.setInt(5, customer.getCus_id());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi cập nhật thông tin khách hàng", ex);
        }
    }

    // Lấy thông tin khách hàng bằng ID
    public Customer_User getCustomerById(int cus_id) {
        String sql = "SELECT * FROM Customer WHERE cus_id = ?";

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setInt(1, cus_id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Customer_User customer = new Customer_User();
                    customer.setCus_id(rs.getInt("cus_id"));
                    customer.setName_cus(rs.getString("name_cus"));
                    customer.setEmail(rs.getString("email"));
                    customer.setGender(rs.getBoolean("gender"));
                    customer.setPassword(rs.getString("password"));
                    customer.setC_phone(rs.getString("c_phone"));
                    customer.setStatus(rs.getBoolean("status"));
                    customer.setAvatar(rs.getString("avartar"));

                    // Thiết lập Role
                    Role role = new Role();
                    role.setRole_id(rs.getInt("role_id"));
                    customer.setRole(role);

                    // Thiết lập Cart
                    Cart cart = new Cart();
                    cart.setCart_id(rs.getInt("cart_id"));
                    customer.setCart(cart);

                    return customer;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi lấy thông tin khách hàng theo ID", ex);
        }
        return null;
    }

    // Lấy thông tin khách hàng bằng email
    public Customer_User getCustomerByEmail(String email) {
        String sql = "SELECT * FROM Customer WHERE email = ?";

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setString(1, email);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Customer_User customer = new Customer_User();
                    customer.setCus_id(rs.getInt("cus_id"));
                    customer.setEmail(rs.getString("email"));
                    customer.setName_cus(rs.getString("name_cus"));

                    return customer;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi lấy thông tin khách hàng theo email", ex);
        }
        return null;
    }

    // Tạo token đặt lại mật khẩu
    public void createPasswordResetToken(int cus_id, String token, LocalDateTime expirationTime) {
        String sql = "INSERT INTO PasswordResetToken (cus_id, token, expiration_time) VALUES (?, ?, ?)";

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setInt(1, cus_id);
            stm.setString(2, token);
            stm.setTimestamp(3, Timestamp.valueOf(expirationTime));
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi tạo token đặt lại mật khẩu", ex);
        }
    }

    // Lấy token đặt lại mật khẩu
    public PasswordResetToken getToken(String token) {
        String sql = "SELECT * FROM PasswordResetToken WHERE token = ?";

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setString(1, token);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    PasswordResetToken passwordResetToken = new PasswordResetToken();
                    passwordResetToken.setCus_id(rs.getInt("cus_id"));
                    passwordResetToken.setToken(rs.getString("token"));
                    passwordResetToken.setExpirationTime(rs.getTimestamp("expiration_time").toLocalDateTime());

                    return passwordResetToken;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi lấy token", ex);
        }
        return null;
    }

    // Xóa token đặt lại mật khẩu
    public void deleteToken(String token) {
        String sql = "DELETE FROM PasswordResetToken WHERE token = ?";

        try (PreparedStatement stm = connect.prepareStatement(sql)) {
            stm.setString(1, token);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, "Lỗi khi xóa token", ex);
        }
    }

}
