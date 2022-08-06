package manhnguyen.studentmanagement_testing_level.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNewStudentActivity extends AppCompatActivity {
    EditText nameAdd, classAdd;
    Button btnAdd;
    ImageView imageAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        Mapping();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nST = nameAdd.getText().toString().trim();
                String cST = classAdd.getText().toString().trim();
                if (nST.isEmpty() || cST.isEmpty()) {
                    Toast.makeText(AddNewStudentActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AddNewStudentActivity.this, MainActivity.class);
                    intent.putExtra("name", nST);
                    intent.putExtra("clazz", cST);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private void Mapping() {
        nameAdd = (EditText) findViewById(R.id.editTextNameAdd);
        classAdd = (EditText) findViewById(R.id.editTextClassAdd);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        imageAdd = (ImageView) findViewById(R.id.imageStudentAdd);
    }
}