package Respositories.Category;

import Models.Category;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class CategoryRepository implements CategoryRepositoryInterface {

    Firestore db;

    public CategoryRepository(Firestore db) {
        this.db = db;
    }

    @Override
    public void createCategory() {
        Category newCategory = new Category();
        Scanner input = new Scanner(System.in);

        System.out.println("Input category name:");
        newCategory.setName(input.nextLine());
        System.out.println("Add unique category identifier:");
        newCategory.setId(input.nextLine());

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_CATEGORY).get();
        List<QueryDocumentSnapshot> categories = null;
        try {
            categories = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert categories != null;
        for(QueryDocumentSnapshot category : categories)
            while(Objects.equals(category.get(CATEGORY_ID), newCategory.getId()))
            {
                System.out.println("That category identifier already exists! Add unique category identifier:");
                newCategory.setId(input.nextLine());
            }

        db.collection(COLLECTION_CATEGORY).document(newCategory.getId()).set(newCategory);
    }

    @Override
    public void updateCategory(String categoryID) {

        Category category;
        Scanner input = new Scanner(System.in);

        DocumentReference docRef = db.collection(COLLECTION_CATEGORY).document(categoryID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert document != null;
        if (document.exists()) {
            category = document.toObject(Category.class);
            assert category != null;
            System.out.println("Category information:");
            System.out.println("Name: " + category.getName());
            System.out.println("ID: " + category.getId());
            System.out.println("What do you want to change?");
            switch (input.nextLine())
            {
                case "Name":
                    System.out.println("New Name:");
                    category.setName(input.nextLine());
                    break;
                case "ID":
                    System.out.println("New ID:");
                    category.setId(input.nextLine());
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }

        } else {
            System.out.println("Wrong category ID!");
        }

    }

    @Override
    public void deleteCategory(String categoryID) {

        DocumentReference docRef = db.collection(COLLECTION_CATEGORY).document(categoryID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot category = null;
        try {
            category = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert category != null;
        if (category.exists()) {
            db.collection(COLLECTION_CATEGORY).document(categoryID).delete();
            System.out.println("Category deleted!");
        } else {
            System.out.println("Wrong category ID!");
        }

    }

    @Override
    public void searchCategory(String categoryID) {

        DocumentReference docRef = db.collection(COLLECTION_CATEGORY).document(categoryID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot category = null;
        try {
            category = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert category != null;
        if (category.exists()) {
            System.out.println("Category information:");
            System.out.println("Name: " + category.get(CATEGORY_NAME));
            System.out.println("ID: " + category.get(CATEGORY_ID));
        } else {
            System.out.println("Wrong category ID!");
        }

    }

    @Override
    public void getAllCategories() {

        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_CATEGORY).get();
        List<QueryDocumentSnapshot> categories = null;
        try {
            categories = future.get().getDocuments();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("List of categories:");
        assert categories != null;
        for(QueryDocumentSnapshot category : categories)
            System.out.println(category.get(CATEGORY_NAME) + " - " + category.get(CATEGORY_ID));

    }


}
