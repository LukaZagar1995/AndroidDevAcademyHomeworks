package Respositories.Author;

public interface AuthorRepositoryInterface {

    void createAuthor();
    void updateAuthor(String authorID);
    void deleteAuthor(String authorID);
    void searchAuthor(String authorID);
    void getAllAuthors();

    String COLLECTION_AUTHORS = "authors";
    String AUTHOR_ID = "id";
    String AUTHOR_FULL_NAME = "fullName";
    String AUTHOR_SHORT_NAME = "shortName";
    String AUTHOR_SHORT_CV = "shortCV";
}
