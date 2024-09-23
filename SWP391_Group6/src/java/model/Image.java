package model;

import java.util.ArrayList;


/**
 *
 * @author KEISHA
 */
public class Image {

    private int img_id;
    private String img_url;
    private Product product;
    private Feedback feedback;
    private ArrayList<Slider> slides = new ArrayList<>();
    private ArrayList<Blog> blog = new ArrayList<>();

    public Image() {
    }
    
    

    public Image(int img_id, String img_url) {
        this.img_id = img_id;
        this.img_url = img_url;
    }
    
    

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public ArrayList<Slider> getSlides() {
        return slides;
    }

    public void setSlides(ArrayList<Slider> slides) {
        this.slides = slides;
    }

    public ArrayList<Blog> getBlog() {
        return blog;
    }

    public void setBlog(ArrayList<Blog> blog) {
        this.blog = blog;
    }

}
