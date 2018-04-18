package Respositories.News;

public interface NewsRepositoryInterface {

    void createNews();
    void updateNews(String newsID);
    void deleteNews(String newsID);
    void searchNews(String newsID);
    void getAllNews();

    String COLLECTION_NEWS = "news";
    String NEWS_ID = "id";
    String NEWS_TITLE = "title";
    String NEWS_SUBTITLE = "subtitle";
    String NEWS_ARTICLE = "article";
    String NEWS_AUTHOR_ID = "authorID";
    String NEWS_CATEGORIES = "categories";

}
