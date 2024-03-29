import java.util.ArrayList;

/**
 * The Category class represents a category in the library system.
 * 
 * @author Hessam Hosseinian
 */
public class Category {

    private String categoryId;
    private String categoryName;
    private ArrayList<Category> subCategory;

    /**
     * Constructs a new Category object with the specified attributes.
     *
     * @param categoryId   The unique identifier of the category.
     * @param categoryName The name of the category.
     */
    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        subCategory = new ArrayList<>();
    }
    // !-------------------------------------------------CATEGORY_ID

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    // !-------------------------------------------------CATEGORY_NAME

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    // !-------------------------------------------------SUB_CATEGORY

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