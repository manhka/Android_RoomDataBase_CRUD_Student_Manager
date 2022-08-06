package manhnguyen.studentmanagement_testing_level.com.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import manhnguyen.studentmanagement_testing_level.com.Student;

@Database(entities = {Student.class}, version = 3)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase instance;

    public static synchronized StudentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "studentDB")
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract StudentDAO studentDAO();
}
