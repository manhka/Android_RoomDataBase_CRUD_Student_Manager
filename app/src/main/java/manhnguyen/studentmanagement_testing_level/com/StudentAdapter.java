package manhnguyen.studentmanagement_testing_level.com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {


    private List<Student> studentList;
private  ClickItemStudent clickItemStudent;
    public void SetData(List<Student> students) {
        this.studentList = students;
        notifyDataSetChanged();
    }
public interface ClickItemStudent{
        void UpdateStudent(Student student);
        void DeleteStudent(Student student);
}

    public StudentAdapter(ClickItemStudent clickItemStudent) {
        this.clickItemStudent = clickItemStudent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        if (student == null) {
            return;
        }
        holder.name.setText(student.getName());
        holder.clazz.setText(student.getClazz());
        holder.image.setImageResource(R.drawable.student2);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemStudent.UpdateStudent(student);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
clickItemStudent.DeleteStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (studentList != null) {
            return studentList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, clazz;
        private ImageView image;
        CircleImageView btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textviewName);
            clazz = (TextView) itemView.findViewById(R.id.textviewClass);
            image = (ImageView) itemView.findViewById(R.id.image_student);
            btnEdit=(CircleImageView) itemView.findViewById(R.id.btnEdit);
            btnDelete=(CircleImageView) itemView.findViewById(R.id.btnDelete);
            Animation animation =AnimationUtils.loadAnimation(itemView.getContext(), R.anim.animation);
            itemView.startAnimation(animation);
        }
    }
}
