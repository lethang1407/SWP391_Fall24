/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.Brand;
import model.Capacity;
import model.Gender;
import model.Image;
import model.Product;

/**
 *
 * @author DINH SON
 */
public class productListDBContext extends DBContext {

    public List<Product> getAll(int pageNumber, int pageSize) {
        List<Product> pList = new ArrayList<>();
        BrandDBContext br = new BrandDBContext();
        CapacityDBContext cap = new CapacityDBContext();
        GenderDBContext gen = new GenderDBContext();
        ImageDBContext image = new ImageDBContext();
        String sql = "SELECT * FROM Product Order By product_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connect.prepareStatement(sql);
            int offset = (pageNumber - 1) * pageSize;
            st.setInt(1, offset);
            st.setInt(2, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                Brand b = br.getBrandFindById(rs.getInt(1));
                Capacity c = cap.getCapacityFindById(rs.getInt(1));
                Gender g = gen.getGenderFindById(rs.getInt(1));
                Image ig = image.getImageById(rs.getInt(1));
                p.setProduct_id(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setDate(rs.getDate("date"));
                p.setPrice(rs.getBigDecimal("price"));
                
                p.setBrand(b);
              
            
                pList.add(p);
            }
            return pList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public int getTotalProduct() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Product";
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

    public List<Product> getAllSearchByTittle(String search, int pageNumber, int pageSize) {
        String sql = "SELECT * \n"
                + "FROM Product \n"
                + "WHERE name LIKE '%' + ? + '%' \n"
                + "ORDER BY product_id \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement st = connect.prepareStatement(sql);
            int offset = (pageNumber - 1) * pageSize;
            st.setString(1, search);
            st.setInt(2, offset);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getBigDecimal(3));
                p.setDate(rs.getDate(4));

                list.add(p);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getTotalProductBySearch(String search) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Product WHERE title LIKE '%' + ? + '%' ";
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

    public static void main(String[] args) {
        productListDBContext pd = new productListDBContext();
        List<Product> p = pd.getAllSearchByTittle("c",1, 10);
        for (Product x : p) {
            System.out.println(x.getProduct_id());
        }
        System.out.println(pd.getTotalProduct());
        
    }

}
