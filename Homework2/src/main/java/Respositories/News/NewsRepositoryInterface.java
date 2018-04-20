package Respositories.News;

public interface NewsRepositoryInterface {

    void createNews();
    void updateNews(String newsID);
    void deleteNews(String newsID);
    void searchNews(String newsID);
    void orderByDate();
    void orderByAuthor();
    void getByCategory(String categoryID);
    void getByAuthor(String authorID);
    void getAllNews();

    String COLLECTION_NEWS = "news";
    String NEWS_ID = "id";
    String NEWS_TITLE = "title";
    String NEWS_SUBTITLE = "subtitle";
    String NEWS_ARTICLE = "article";
    String NEWS_AUTHOR_ID = "authorID";
    String NEWS_CATEGORIES = "categories";
    String NEWS_DATE = "date";
    String FIRESTORE_FORMAT =  "EEE MMM dd HH:mm:ss ZZZ yyyy";
    String VIEW_FORMAT = "dd.MM.yyyy";

}
