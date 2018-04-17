package Models;

import com.google.cloud.firestore.annotation.PropertyName;
import com.google.gson.annotations.SerializedName;

import static Respositories.Author.AuthorRepositoryInterface.*;

public class Author {

    @SerializedName(AUTHOR_ID)
    @PropertyName(AUTHOR_ID)
    private String id;

    @SerializedName(AUTHOR_FULL_NAME)
    @PropertyName(AUTHOR_FULL_NAME)
    private String fullName;

    @SerializedName(AUTHOR_SHORT_NAME)
    @PropertyName(AUTHOR_SHORT_NAME)
    private String shortName;

    @SerializedName(AUTHOR_SHORT_CV)
    @PropertyName(AUTHOR_SHORT_CV)
    private String shortCV;

    public Author() {
    }

    public Author(String id, String fullName, String shortName, String shortCV) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.shortCV = shortCV;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortCV() {
        return shortCV;
    }

    public void setShortCV(String shortCV) {
        this.shortCV = shortCV;
    }
}
