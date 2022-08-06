package manhnguyen.studentmanagement_testing_level.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import manhnguyen.studentmanagement_testing_level.com.database.StudentDatabase;

public class EditStudentActivity extends AppCompatActivity {
  EditText nameEditStudent, classEditStudent;
    ImageView image;
    Button btnEdit;
   Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        Mapping();
        student= (Student) getIntent().getExtras().get("object student");
        if (student!=null){
            nameEditStudent.setText(student.getName());
            classEditStudent.setText(student.getClazz());
            image.setImageResource(R.drawable.student2);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateStudent();
                }
            });
        }
    }

    private void updateStudent() {
        String nameStudentEdit=nameEditStudent.getText().toString().trim();
        String clazzStudentEdit=classEditStudent.getText().toString().trim();
        if (nameStudentEdit.isEmpty()||clazzStudentEdit.isEmpty()){
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
        }
        else {
            student.setName(nameStudentEdit.trim());
            student.setClazz(clazzStudentEdit.trim());
            StudentDatabase.getInstance(this).studentDAO().updateStudent(student);
            Toast.makeText(this, "Edit Student successful", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent();
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    private void Mapping() {
            nameEditStudent = (EditText) findViewById(R.id.editTextNameEdit);
        classEditStudent = (EditText) findViewById(R.id.editTextClassEdit);
        btnEdit = (Button) findViewById(R.id.btnEditStudent);
        image=(ImageView) findViewById(R.id.imageStudentEdit);

    }
}