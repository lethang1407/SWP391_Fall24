/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Capacity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DINH SON
 */
public class CapacityDBContext extends DBContext {
    public Capacity getCapacityFindById(int cid) {
        String sql = "Select * from Capacity WHERE cap_id=?";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Capacity c = new Capacity(rs.getInt(1), rs.getInt(2));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
        
    }
     public List<Capacity> getAll() {
        List<Capacity> list = new ArrayList<>();
        String sql = "Select * from Capacity";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Capacity c = new Capacity(rs.getInt(1), rs.getInt(2));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }
    
}
