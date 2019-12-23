package lilee.hd.anotterredditapp.subreddits;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubredditRepository {
    // Write a message to the database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("message");
//    myRef.setValue("Hello, World!");

    public SubredditRepository() {
        super();
    }
}
