package manhnguyen.studentmanagement_testing_level.com;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import manhnguyen.studentmanagement_testing_level.com.database.StudentDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView viewStudent;
    StudentAdapter adapter;
    List<Student> studentList;
    Button btnAddNewStudent, btnSearchByName;
    EditText nameSearch;
    String nameStudent;
    String classStudent;
    private static final int REQUEST_CODE_ADD = 123;
    private static final int REQUEST_CODE_EDIT = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        StudentDatabase.getInstance(this).studentDAO().getListStudent();
        adapter = new StudentAdapter(new StudentAdapter.ClickItemStudent() {
            @Override
            public void UpdateStudent(Student student) {
                clickUpdateStudent(student);
            }

            @Override
            public void DeleteStudent(Student student) {
                clickDeleteStudent( student);
            }

        });
        studentList = new ArrayList<>();
        adapter.SetData(studentList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        viewStudent.setLayoutManager(linearLayoutManager);
        viewStudent.setAdapter(adapter);
        GetListStudent();
        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewStudent();
            }
        });
        // search
        btnSearchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchByName();
            }
        });

    }

    // delete Student
    private void clickDeleteStudent(Student student) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to remove student : '"+student.getName()+"'?").
        setTitle("Delete Student")
      .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StudentDatabase.getInstance(MainActivity.this).studentDAO().deleteStudent(student);
                Toast.makeText(MainActivity.this, "Delete successful!", Toast.LENGTH_SHORT).show();
                GetListStudent();
            }
        })
        .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        })
.show();
    }

    // update STUDENT
    private void clickUpdateStudent(Student student) {
        Intent intent=new Intent(MainActivity.this, EditStudentActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("object student",student);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    // get List Student
    private void GetListStudent() {
        studentList = StudentDatabase.getInstance(this).studentDAO().getListStudent();
        adapter.SetData(studentList);
    }

    // mapping
    private void Mapping() {
        viewStudent = (RecyclerView) findViewById(R.id.recycleviewStudent);
        btnAddNewStudent = (Button) findViewById(R.id.btnAddNewStudent);
        btnSearchByName = (Button) findViewById(R.id.btnSearch);
        nameSearch = (EditText) findViewById(R.id.editTextSearch);
    }

    // add new student
    private void AddNewStudent() {
        startActivityForResult(new Intent(MainActivity.this, AddNewStudentActivity.class), REQUEST_CODE_ADD);
    }

    private void InsertToStudentTB() {
        Student student = new Student(nameStudent, classStudent, R.drawable.student1);
        StudentDatabase.getInstance(this).studentDAO().insert(student);
        GetListStudent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            nameStudent = data.getStringExtra("name");
            classStudent = data.getStringExtra("clazz");
            InsertToStudentTB();
        } else {
            Toast.makeText(this, "DATA null", Toast.LENGTH_SHORT).show();
        }
        if (requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK && data!=null){
            GetListStudent();
        }
    }

    // search by name
    private void SearchByName() {
        String nameStudentSearch = nameSearch.getText().toString().trim();
        if (nameStudentSearch.isEmpty()) {
            Toast.makeText(this, "Please , Enter the name to search", Toast.LENGTH_SHORT).show();
        }
        studentList = new ArrayList<>();
        studentList = StudentDatabase.getInstance(this).studentDAO().searchStudent(nameStudentSearch);
        if (studentList.size() == 0) {
            Toast.makeText(this, "Do not have this Student !", Toast.LENGTH_SHORT).show();
        } else
            adapter.SetData(studentList);
    }
}