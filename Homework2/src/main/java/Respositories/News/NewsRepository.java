package Respositories.News;

import Models.News;
import Respositories.Author.AuthorRepository;
import Respositories.Category.CategoryRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static Respositories.Author.AuthorRepositoryInterface.COLLECTION_AUTHORS;
import static Respositories.Category.CategoryRepositoryInterface.COLLECTION_CATEGORY;

public class NewsRepository implements NewsRepositoryInterface {

    private Firestore db;

    public NewsRepository(Firestore db) {
        this.db = db;
    }

    @Override
    public void createNews() {

        SimpleDateFormat formatFirestore = new SimpleDateFormat(FIRESTORE_FORMAT, Locale.ENGLISH);
        SimpleDateFormat formatShow = new SimpleDateFormat(VIEW_FORMAT);
        Date newsDate = null;
        String newsDateFormated;

        AuthorRepository authorRepository = new AuthorRepository(db);
        CategoryRepository categoryRepository = new CategoryRepository(db);
        News newNews = new News();
        Scanner input = new Scanner(System.in);
        List<String> categoryIDs = new ArrayList<>();

        System.out.println("Input date:");
        try {
            newsDate = formatShow.parse(input.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newsDateFormated = formatFirestore.format(newsDate);
        try {
            newsDate = formatFirestore.parse(newsDateFormated);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newNews.setDate(newsDate);
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
                System.out.println("That author ID doesn't exist! Input author ID of the news: ");
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
                    System.out.println("That category ID doesn't exist! Input category ID of the news: ");
                    categoryIDs.set(i, input.nextLine());
                }
            }
        }
        newNews.setCategories(categoryIDs);
        System.out.println("Add unique news identifier: ");
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
                System.out.println("That news identifier already exists! Add unique news identifier: ");
                newNews.setId(input.nextLine());
            }
        db.collection(COLLECTION_NEWS).document(newNews.getId()).set(newNews);
    }


    @Override
    public void updateNews(String newsID) {

        SimpleDateFormat formatFirestore = new SimpleDateFormat(FIRESTORE_FORMAT, Locale.ENGLISH);
        SimpleDateFormat formatShow = new SimpleDateFormat(VIEW_FORMAT);
        Date newsDate = null;
        String newsDateFormated;

        List<String> categoryIDs = new ArrayList<>();
        AuthorRepository authorRepository = new AuthorRepository(db);
        CategoryRepository categoryRepository = new CategoryRepository(db);
        Scanner input = new Scanner(System.in);
        News mNews;
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
            mNews = news.toObject(News.class);
            assert mNews != null;
            System.out.println("News information:");
            System.out.println("Date: " + mNews.getDate());
            System.out.println("Title: " +mNews.getTitle());
            System.out.println("Subtitle: " +mNews.getSubtitle());
            System.out.println("Article: " + mNews.getArticle());
            System.out.println("Author ID: " + mNews.getAuthorID());
            System.out.print("Category ID: ");
            for(int i = 0; i < mNews.getCategories().size(); i++) {
                if(i+1 == mNews.getCategories().size()) {
                    System.out.print(mNews.getCategories().get(i));
                    break;
                }
                System.out.print(mNews.getCategories().get(i) + ", ");

            }
            System.out.println("\nID: " +mNews.getId());

            switch (input.nextLine())
            {
                case "Date":
                    System.out.println("New date:");
                    try {
                        newsDate = formatFirestore.parse(input.nextLine());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newsDateFormated = formatShow.format(newsDate);
                    try {
                        newsDate = formatShow.parse(newsDateFormated);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    mNews.setDate(newsDate);
                    break;
                case "Title":
                    System.out.println("New title:");
                    mNews.setTitle(input.nextLine());
                    break;
                case "Subtitle":
                    System.out.println("New subtitle:");
                    mNews.setSubtitle(input.nextLine());
                    break;
                case "Article":
                    System.out.println("New article:");
                    mNews.setArticle(input.nextLine());
                    break;
                case "Author ID":
                    authorRepository.getAllAuthors();
                    System.out.println("New author ID:");
                    mNews.setAuthorID(input.nextLine());
                    break;
                case "Category ID":
                    categoryRepository.getAllCategories();
                    System.out.println("New categories:");
                    System.out.println("Input number of categories: ");
                    int numberOfCategories = Integer.parseInt(input.nextLine());
                    for(int i = 0; i < numberOfCategories; i++) {
                        System.out.println("Input category ID of the news: ");
                        categoryIDs.add(i, input.nextLine());

                        while(true) {
                            DocumentReference categoryRef = db.collection(COLLECTION_CATEGORY).document(categoryIDs.get(i));
                            ApiFuture<DocumentSnapshot> apiFuture = categoryRef.get();
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
                                System.out.println("That category ID doesn't exist! Input category ID of the news: ");
                                categoryIDs.set(i, input.nextLine());
                            }
                        }
                    }
                    mNews.setCategories(categoryIDs);
                    break;
                case "ID":
                    System.out.println("New ID:");
                    mNews.setId(input.nextLine());
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }

            db.collection(COLLECTION_NEWS).document(newsID).set(mNews, SetOptions.merge());
        } else {
            System.out.println("Wrong news ID!");
        }


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
        SimpleDateFormat formatFirestore = new SimpleDateFormat(FIRESTORE_FORMAT, Locale.ENGLISH);
        SimpleDateFormat formatShow = new SimpleDateFormat(VIEW_FORMAT);
        Date newsDate = null;
        String newsDateFormated;

        News mNews;
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
            mNews = news.toObject(News.class);
            assert mNews != null;
            System.out.println("News information:");
            try {
                newsDate = formatFirestore.parse(mNews.getDate().toString());
                newsDateFormated = formatShow.format(newsDate);
                newsDate = formatShow.parse(newsDateFormated);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mNews.setDate(newsDate);
            System.out.println("Date: " + mNews.getDate());
            System.out.println("Title: " +mNews.getTitle());
            System.out.println("Subtitle: " +mNews.getSubtitle());
            System.out.println("Article: " + mNews.getArticle());
            System.out.println("Author ID: " + mNews.getAuthorID());
            System.out.print("Category ID: ");
            for(int i = 0; i < mNews.getCategories().size(); i++) {
                if(i+1 == mNews.getCategories().size()) {
                    System.out.print(mNews.getCategories().get(i));
                    break;
                }
                System.out.print(mNews.getCategories().get(i) + ", ");

            }
            System.out.println("\nID: " +mNews.getId());
        } else {
            System.out.println("Wrong news ID!");
        }
    }

    @Override
    public void getAllNews() {

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NEWS).get();
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
            System.out.println();
        }
    }

    @Override
    public void getByAuthor(String authorID) {

        News mNews ;
        ApiFuture<QuerySnapshot> newsList = db.collection(COLLECTION_NEWS).get();
        List<QueryDocumentSnapshot> dates = null;
        try {
            dates = newsList.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert dates != null;
        for (QueryDocumentSnapshot news : dates) {
            mNews = news.toObject(News.class);
            if(mNews.getAuthorID().equals(authorID)) {
                System.out.println(mNews.getTitle() + " - " + mNews.getId());
                System.out.println(mNews.getSubtitle());
                System.out.println();
            }
        }

    }

    @Override
    public void orderByDate() {

        News mNews ;
        SimpleDateFormat formatFirestore = new SimpleDateFormat(FIRESTORE_FORMAT, Locale.ENGLISH);
        SimpleDateFormat formatShow=new SimpleDateFormat(VIEW_FORMAT);
        Date newsDate = null;
        String newsDateFormated;

        ApiFuture<QuerySnapshot> newsList = db.collection(COLLECTION_NEWS).orderBy(NEWS_DATE, Query.Direction.DESCENDING).get();
        List<QueryDocumentSnapshot> dates = null;
        try {
            dates = newsList.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert dates != null;
        for (QueryDocumentSnapshot news : dates) {
            mNews = news.toObject(News.class);
            try {
                newsDate = formatFirestore.parse(mNews.getDate().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            newsDateFormated = formatShow.format(newsDate);

            System.out.println(newsDateFormated);
            System.out.println(mNews.getTitle() + " - " + mNews.getId());
            System.out.println(mNews.getSubtitle());
            System.out.println();

        }

    }

    @Override
    public void orderByAuthor() {
        News mNews ;
        ApiFuture<QuerySnapshot> newsList = db.collection(COLLECTION_NEWS).orderBy(NEWS_AUTHOR_ID, Query.Direction.DESCENDING).get();
        List<QueryDocumentSnapshot> dates = null;
        try {
            dates = newsList.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert dates != null;
        for (QueryDocumentSnapshot news : dates) {
            mNews = news.toObject(News.class);
            System.out.println(mNews.getAuthorID());
            System.out.println(mNews.getTitle() + " - " + mNews.getId());
            System.out.println(mNews.getSubtitle());
            System.out.println();
        }
    }

    @Override
    public void getByCategory(String categoryID) {
        News mNews ;
        ApiFuture<QuerySnapshot> newsList = db.collection(COLLECTION_NEWS).get();
        List<QueryDocumentSnapshot> dates = null;
        try {
            dates = newsList.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert dates != null;
        for (QueryDocumentSnapshot news : dates) {
            mNews = news.toObject(News.class);
            for(int i=0; i < mNews.getCategories().size(); i++) {
                if(mNews.getCategories().get(i).contains(categoryID)) {
                    System.out.println(mNews.getTitle() + " - " + mNews.getId());
                    System.out.println(mNews.getSubtitle());
                    System.out.println();
                }
            }
        }
    }
}
