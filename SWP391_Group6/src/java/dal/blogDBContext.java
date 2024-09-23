/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Blog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Employee_User;

/**
 *
 * @author admin
 */
public class blogDBContext extends DBContext {

    public List<Blog> getBlogTop3Date() {
        List<Blog> list = new ArrayList<>();

        String sql = "SELECT TOP  3 * FROM Blog ORDER BY date DESC";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setShortContent(rs.getString(3));
                b.setContent(rs.getString(4));
                b.setDate(rs.getDate(5));
                employeeDBContext eDB = new employeeDBContext();
                int id = rs.getInt(6);
                Employee_User e = eDB.getEmployeeByIdForBlog(id);
                b.setEmployee(e);
                list.add(b);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Blog> getAll(int pageNumber, int pageSize) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM Blog ORDER BY blog_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            int offset = (pageNumber - 1) * pageSize;
            st.setInt(1, offset);
            st.setInt(2, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setShortContent(rs.getString(3));
                b.setContent(rs.getString(4));
                b.setDate(rs.getDate(5));
                employeeDBContext eDB = new employeeDBContext();
                int id = rs.getInt(6);
                Employee_User e = eDB.getEmployeeByIdForBlog(id);
                b.setEmployee(e);
                list.add(b);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public Blog getContentByBlogId(int blogid) {
        String sql = "select * from Blog where blog_id = ?";
        Blog b = new Blog();
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setInt(1, blogid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                b.setBlog_id(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setDate(rs.getDate(5));
                employeeDBContext eDB = new employeeDBContext();
                int id = rs.getInt(6);
                Employee_User e = eDB.getEmployeeByIdForBlog(id);
                b.setEmployee(e);
                b.setContent(rs.getString(4));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return b;

    }

    public List<Blog> getAllSearchByTittle(String search, int pageNumber, int pageSize) {
        String sql = "SELECT * \n"
                + "FROM Blog \n"
                + "WHERE title LIKE '%' + ? + '%' \n"
                + "ORDER BY blog_id \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        List<Blog> list = new ArrayList<>();

        try {
            PreparedStatement st = connect.prepareStatement(sql);
            int offset = (pageNumber - 1) * pageSize;
            st.setString(1, search);
            st.setInt(2, offset);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt(1));
                b.setTitle(rs.getString(2));
                b.setShortContent(rs.getString(3));
                b.setContent(rs.getString(4));
                b.setDate(rs.getDate(5));
                employeeDBContext eDB = new employeeDBContext();
                int id = rs.getInt(6);
                Employee_User e = eDB.getEmployeeByIdForBlog(id);
                b.setEmployee(e);
                list.add(b);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getTotalBlogsBySearch(String search) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Blog WHERE title LIKE '%' + ? + '%' ";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            st.setString(1, search);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalBlogs() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Blog";
        try {
            PreparedStatement st = connect.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        blogDBContext l = new blogDBContext();

        List<Blog> ldb = l.getBlogTop3Date();
        for (Blog x : ldb) {
            System.out.println(x.getBlog_id());
        }

    }
}
