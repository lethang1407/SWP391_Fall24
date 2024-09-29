
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Employee;


public class EmployeeDBContext extends DBContext {

    public Employee getEmployeeByIdForBlog(int id) {
        String sql = "SELECT * FROM Employee where emp_id=?";

        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Employee e = new Employee();
                e.setEmp_id(id);
                e.setName_emp(rs.getString(2));
                return e;

            }

        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeDBContext eDb = new EmployeeDBContext();
        Employee e = eDb.getEmployeeByIdForBlog(1);
        System.out.println(e.getName_emp());
    }
}
