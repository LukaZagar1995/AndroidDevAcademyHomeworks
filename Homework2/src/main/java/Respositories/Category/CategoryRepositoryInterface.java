package Respositories.Category;

public interface CategoryRepositoryInterface {

    void createCategory();
    void updateCategory(String categoryID);
    void deleteCategory(String categoryID);
    void searchCategory(String categoryID);
    void getAllCategories();

    String COLLECTION_CATEGORY = "categories";
    String CATEGORY_ID = "id";
    String CATEGORY_NAME = "name";

}
