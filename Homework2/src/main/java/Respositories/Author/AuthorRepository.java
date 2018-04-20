package Respositories.Author;

import Models.Author;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class AuthorRepository implements AuthorRepositoryInterface {

    private Firestore db;

    public AuthorRepository(Firestore db) {
        this.db = db;
    }

    @Override
    public void createAuthor() {

        Author newAuthor = new Author();
        Scanner input = new Scanner(System.in);

        System.out.println("Input authors full name:");
        newAuthor.setFullName(input.nextLine());
        System.out.println("Input authors short name:");
        newAuthor.setShortName(input.nextLine());
        System.out.println("Input authors short CV: ");
        newAuthor.setShortCV(input.nextLine());
        System.out.println("Add unique author identifier:");
        newAuthor.setId(input.nextLine());

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_AUTHORS).get();
        List<QueryDocumentSnapshot> authors = null;
        try {
            authors = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert authors != null;
        for(QueryDocumentSnapshot author : authors)
            while(Objects.equals(author.get(AUTHOR_ID), newAuthor.getId()))
            {
                System.out.println("That author identifier already exists! Add unique author identifier:");
                newAuthor.setId(input.nextLine());
            }

            db.collection(COLLECTION_AUTHORS).document(newAuthor.getId()).set(newAuthor);

    }

    @Override
    public void updateAuthor(String authorID) {

        Author author;
        Scanner input = new Scanner(System.in);

        DocumentReference docRef = db.collection(COLLECTION_AUTHORS).document(authorID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert document != null;
        if (document.exists()) {
            author = document.toObject(Author.class);
            assert author != null;
            System.out.println("Author information:");
            System.out.println("Full Name: " + author.getFullName());
            System.out.println("Short Name: " + author.getShortName());
            System.out.println("Short CV: " + author.getShortCV());
            System.out.println("ID: " + author.getId());
            System.out.println("What do you want to change?");
            switch (input.nextLine())
            {
                case "Full Name":
                    System.out.println("New Full Name:");
                    author.setFullName(input.nextLine());
                    break;
                case "Short Name":
                    System.out.println("New Short Name:");
                    author.setShortName(input.nextLine());
                    break;
                case "Short CV":
                    System.out.println("Short CV:");
                    author.setShortCV(input.nextLine());
                    break;
                case "ID":
                    System.out.println("New ID:");
                    author.setId(input.nextLine());
                    break;
                    default:
                        System.out.println("Wrong input!");
                        break;
            }
            db.collection(COLLECTION_AUTHORS).document(authorID).set(author, SetOptions.merge());
        } else {
            System.out.println("Wrong author ID!");
        }

    }

    @Override
    public void deleteAuthor(String authorID) {
        DocumentReference docRef = db.collection(COLLECTION_AUTHORS).document(authorID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot author = null;
        try {
            author = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert author != null;
        if (author.exists()) {
            db.collection(COLLECTION_AUTHORS).document(authorID).delete();
            System.out.println("Author deleted!");
        } else {
            System.out.println("Wrong author ID!");
        }
    }

    @Override
    public void searchAuthor(String authorID) {

        DocumentReference docRef = db.collection(COLLECTION_AUTHORS).document(authorID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot author = null;
        try {
            author = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert author != null;
        if (author.exists()) {
            System.out.println("Author information:");
            System.out.println("Full Name: " + author.get(AUTHOR_FULL_NAME));
            System.out.println("Short Name: " + author.get(AUTHOR_SHORT_NAME));
            System.out.println("Short CV: " + author.get(AUTHOR_SHORT_CV));
            System.out.println("ID: " + author.get(AUTHOR_ID));
        } else {
            System.out.println("Wrong author ID!");
        }

    }

    @Override
    public void getAllAuthors() {

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_AUTHORS).get();
        List<QueryDocumentSnapshot> authors = null;
        try {
            authors = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("List of authors:");
        assert authors != null;
        for(QueryDocumentSnapshot author : authors)
            System.out.println(author.get(AUTHOR_SHORT_NAME) + " - " + author.get(AUTHOR_ID));

    }

}
