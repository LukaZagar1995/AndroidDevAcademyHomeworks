package Respositories.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Database implements DatabaseRepositoryInterface {


    public Firestore getDb() {

        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream(databaseAccessKeyPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions optionsDatabase = null;
        try {
            optionsDatabase = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://homework2-499db.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(optionsDatabase);
        Firestore db = FirestoreClient.getFirestore();

        return db;
    }

}
