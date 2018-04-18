package Respositories.News;

import Models.Category;
import Models.News;
import Respositories.Author.AuthorRepository;
import Respositories.Category.CategoryRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static Respositories.Author.AuthorRepositoryInterface.COLLECTION_AUTHORS;
import static Respositories.Category.CategoryRepositoryInterface.COLLECTION_CATEGORY;

public class NewsRepository implements NewsRepositoryInterface {

    Firestore db;

    public NewsRepository(Firestore db) {
        this.db = db;
    }

    @Override
    public void createNews() {

        AuthorRepository authorRepository = new AuthorRepository(db);
        CategoryRepository categoryRepository = new CategoryRepository(db);
        News newNews = new News();
        Scanner input = new Scanner(System.in);
        List<String> categoryIDs = new ArrayList<>();

        System.out.println("Input title:");
        newNews.setTitle(input.nextLine());
        System.out.println("Input subtitle:");
        newNews.setSubtitle(input.nextLine());
        System.out.println("Input article: ");
        newNews.setArticle(input.nextLine());
        System.out.println("Input author ID of the news: ");
        authorRepository.getAllAuthors();
        newNews.setAuthorID(input.nextLine());

        while(true) {
        DocumentReference docRef = db.collection(COLLECTION_AUTHORS).document(newNews.getAuthorID());
        ApiFuture<DocumentSnapshot> apiFuture = docRef.get();
        DocumentSnapshot author = null;
        try {
            author = apiFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert author != null;

            if (author.exists()) {
                break;
            } else {
                System.out.println("That author ID doesn't exist!Input author ID of the news: ");
                newNews.setAuthorID(input.nextLine());
            }
        }
        categoryRepository.getAllCategories();
        System.out.println("Input number of categories: ");
        int numberOfCategories = Integer.parseInt(input.nextLine());
        for(int i = 0; i < numberOfCategories; i++) {
            System.out.println("Input category ID of the news: ");
            categoryIDs.add(i, input.nextLine());

            while(true) {
                DocumentReference docRef = db.collection(COLLECTION_CATEGORY).document(categoryIDs.get(i));
                ApiFuture<DocumentSnapshot> apiFuture = docRef.get();
                DocumentSnapshot category = null;
                try {
                    category = apiFuture.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                assert category != null;

                if (category.exists()) {
                    break;
                } else {
                    System.out.println("That category ID doesn't exist!Input category ID of the news: ");
                    categoryIDs.set(i, input.nextLine());
                }
            }

        }

        newNews.setCategories(categoryIDs);
        System.out.println("Add unique news identifier:");
        newNews.setId(input.nextLine());

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NEWS).get();
        List<QueryDocumentSnapshot> news = null;
        try {
            news = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert news != null;
        for(QueryDocumentSnapshot document : news)
            while(Objects.equals(document.get(NEWS_ID), newNews.getId()))
            {
                System.out.println("That news identifier already exists! Add unique news identifier:");
                newNews.setId(input.nextLine());
            }

        db.collection(COLLECTION_NEWS).document(newNews.getId()).set(newNews);

    }


    @Override
    public void updateNews(String newsID) {

    }

    @Override
    public void deleteNews(String newsID) {

        DocumentReference docRef = db.collection(COLLECTION_NEWS).document(newsID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot news = null;
        try {
            news = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert news != null;
        if (news.exists()) {
            db.collection(COLLECTION_NEWS).document(newsID).delete();
            System.out.println("News deleted!");
        } else {
            System.out.println("Wrong news ID!");
        }

    }

    @Override
    public void searchNews(String newsID) {

        List<String> categories = new ArrayList<>();
        DocumentReference docRef = db.collection(COLLECTION_NEWS).document(newsID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot news = null;
        try {
            news = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert news != null;
        if (news.exists()) {
            System.out.println("News information:");
            System.out.println("Title: " + news.get(NEWS_TITLE));
            System.out.println("Subtitle: " + news.get(NEWS_SUBTITLE));
            System.out.println("Article: " + news.get(NEWS_ARTICLE));
            System.out.println("Author ID: " + news.get(NEWS_AUTHOR_ID));
            System.out.println("Category ID: " + news.get(NEWS_CATEGORIES));
            System.out.println("ID: " + news.get(NEWS_ID));
        } else {
            System.out.println("Wrong news ID!");
        }

    }

    @Override
    public void getAllNews() {

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_AUTHORS).get();
        List<QueryDocumentSnapshot> news = null;
        try {
            news = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("List of news:");
        assert news != null;
        for(QueryDocumentSnapshot document : news) {
            System.out.println(document.get(NEWS_TITLE) + " - " + document.get(NEWS_ID));
            System.out.println(document.get(NEWS_SUBTITLE));

        }
    }
}
