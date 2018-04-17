package Models;

import com.google.cloud.firestore.annotation.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static Respositories.News.NewsRepositoryInterface.*;


public class News {

    @SerializedName(NEWS_ID)
    @PropertyName(NEWS_ID)
    private String id;

    @SerializedName(NEWS_TITLE)
    @PropertyName(NEWS_TITLE)
    private String title;

    @SerializedName(NEWS_SUBTITLE)
    @PropertyName(NEWS_SUBTITLE)
    private String subtitle;

    @SerializedName(NEWS_ARTICLE)
    @PropertyName(NEWS_ARTICLE)
    private String article;

    @SerializedName(NEWS_AUTHOR_ID)
    @PropertyName(NEWS_AUTHOR_ID)
    private String authorID;

    @SerializedName(NEWS_CATEGORIES)
    @PropertyName(NEWS_CATEGORIES)
    private List<String> categories;

    public News() {
    }

    public News(String id, String title, String subtitle, String article, String authorID, List<String> categories) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.article = article;
        this.authorID = authorID;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
