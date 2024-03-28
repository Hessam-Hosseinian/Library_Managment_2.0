import java.util.ArrayList;

public class Category {

    private String categoryId;
    private String categoryName;

    private ArrayList<Category> subCategory;

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        subCategory = new ArrayList<>();
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Category> getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(ArrayList<Category> subCategory) {
        this.subCategory = subCategory;
    }

    public void setSubs(Category sub) {
        this.subCategory.add(sub);
    }

}