package App;

import App.Model.Services;
import App.Model.Widgets;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * App.MainClass in order to laucnh Spring boot app
 */
@SpringBootApplication(scanBasePackages={
        "App.controller", "App.Model"})
@ComponentScan({"App.controller"})
public class MainClass {

    /**
     * main in orde to launch app
     * @param args
     * args are parameters passed to the application
     */
    public static void main(String[] args){
        System.setProperty("java.net.preferIPv4Stack" , "true");

        InputStream fis = MainClass.class.getClassLoader().getResourceAsStream("ServiceAccountKey.json");
        if (fis != null) {
            try {
                FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(fis))
                        .setDatabaseUrl("https://dashboard-eb9b3.firebaseio.com")
                        .build();
                FirebaseApp.initializeApp(firebaseOptions);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error when reading the file");
        }
        SpringApplication.run(MainClass.class, args);
    }
}
