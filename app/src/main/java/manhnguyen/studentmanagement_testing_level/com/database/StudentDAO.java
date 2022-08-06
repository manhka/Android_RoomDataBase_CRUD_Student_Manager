package manhnguyen.studentmanagement_testing_level.com.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import manhnguyen.studentmanagement_testing_level.com.Student;

@Dao
public interface StudentDAO {
    @Insert
    void insert(Student student);
    @Query("SELECT * FROM student")
    List<Student> getListStudent();
    @Query("SELECT * FROM student WHERE name LIKE '%' || :studentName || '%'")
    List<Student>searchStudent(String studentName);
    @Update
    void updateStudent(Student student);
    @Delete
    void deleteStudent(Student student);
}
