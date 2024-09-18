/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Blog;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;

/**
 *
 * @author KEISHA
 */
public class BlogDBContext extends DBContext<Blog> {

    

    public ArrayList<Blog> getBlogForHomepage() {
        PreparedStatement stm = null;
        ArrayList<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT top 3\n"
                    + "    b.blog_id,\n"
                    + "    b.title,\n"
                    + "    b.shortContent,\n"
                    + "    b.content,\n"
                    + "    b.date,\n"
                    + "    e.name_emp,\n"
                    + "    i.img_url\n"
                    + "FROM \n"
                    + "    dbo.Blog b\n"
                    + "JOIN \n"
                    + "    dbo.Employee e ON b.emp_id = e.emp_id\n"
                    + "LEFT JOIN \n"
                    + "    dbo.Blog_IMG bi ON b.blog_id = bi.blog_id\n"
                    + "LEFT JOIN \n"
                    + "    dbo.Image i ON bi.img_id = i.img_id\n"
                    + "WHERE \n"
                    + "    bi.img_id = (\n"
                    + "        SELECT TOP 1 img_id \n"
                    + "        FROM dbo.Blog_IMG \n"
                    + "        WHERE blog_id = b.blog_id\n"
                    + "        ORDER BY img_id ASC\n"
                    + "    )";

            stm = connect.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Blog b = new Blog();
                b.setBlog_id(rs.getInt("blog_id"));
                b.setTitle(rs.getString("title"));
                b.setShortContent(rs.getString("shortContent"));

                Image i = new Image();
                i.setImg_url(rs.getString("img_url"));

                b.setImage(i);

                blogs.add(b);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BlogDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return blogs;

    }

}
