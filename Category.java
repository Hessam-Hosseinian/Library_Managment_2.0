public class Category {

    private String categoryId;
    private String categoryName;
    private String subCategory;

    public Category(String categoryId, String categoryName, String subCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategory = subCategory;
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

    public String getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

}