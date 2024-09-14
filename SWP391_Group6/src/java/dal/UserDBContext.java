/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author KEISHA
 */
public class UserDBContext extends DBContext<User> {

    public User getUserByMailAndPass(String username, String password) {
        PreparedStatement stm = null;
        User user = null;

        try {
            String sql = "Select u.id, u.fullName, u.role FROM [User] u\n"
                    + " WHERE u.email = ? and password = ?";

            stm = connect.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getString("role"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    @Override
    public void insert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        PreparedStatement stm = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "Select u.id, u.fullName, u.role FROM [User] u\n"
                    + " WHERE role = 'user'";
            stm = connect.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setFullName(rs.getString("fullName"));
                u.setRole(rs.getString("role"));
                
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connect.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    
    
}
