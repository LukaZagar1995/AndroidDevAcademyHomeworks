package Respositories.Database;

import Respositories.Database.Database;
import com.google.cloud.firestore.Firestore;

public interface DatabaseRepositoryInterface {

    String databaseAccessKeyPath = "D:\\Users\\Luka\\IdeaProjects\\Homework2\\src\\main\\resources\\homework2-499db-firebase-adminsdk-4zqfq-69e45865c8.json";
    Database db = new Database();
    Firestore DATABASE = db.getDb() ;

}
