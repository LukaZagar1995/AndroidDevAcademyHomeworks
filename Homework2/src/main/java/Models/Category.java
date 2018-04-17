package Models;

import com.google.cloud.firestore.annotation.PropertyName;
import com.google.gson.annotations.SerializedName;

import static Respositories.Category.CategoryRepositoryInterface.*;


public class Category {

    @SerializedName(CATEGORY_ID)
    @PropertyName(CATEGORY_ID)
    private String id;

    @SerializedName(CATEGORY_NAME)
    @PropertyName(CATEGORY_NAME)
    private String name;

    public Category() {
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
