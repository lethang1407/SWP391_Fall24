/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Employee_User;

/**
 *
 * @author admin
 */
public class employeeDBContext extends DBContext {

    public Employee_User getEmployeeByIdForBlog(int id) {
        String sql = "SELECT * FROM Employee where emp_id=?";
        

        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Employee_User e = new Employee_User();
                e.setEmp_id(id);
                e.setName_emp(rs.getString(2));
                 return e;

            }
           

        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        employeeDBContext eDb = new employeeDBContext();
        Employee_User e = eDb.getEmployeeByIdForBlog(1);
        System.out.println(e.getName_emp());
    }
}
