package com.kiranam.android;


import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kiranam.android.databinding.ItemStudentBinding;
import com.kiranam.android.models.Student;
import com.kiranam.android.utils.Utility;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Student> list;

    public StudentsAdapter(List<Student> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder) {
            StudentViewHolder studentViewHolder = (StudentViewHolder) holder;
            studentViewHolder.bindData(list.get(position));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(
                    Utility.dpSize(holder.itemView.getContext(), 15),
                    Utility.dpSize(holder.itemView.getContext(), 15),
                    Utility.dpSize(holder.itemView.getContext(), 15),
                    position == list.size() - 1 ? Utility.dpSize(holder.itemView.getContext(), 15) : 0
            );
            holder.itemView.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addStudents(List<Student> students) {
        int positionStart = getItemCount() + 1;
        list.addAll(students);
        notifyItemRangeInserted(positionStart, students.size());
    }


    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private ItemStudentBinding binding;

        public StudentViewHolder(@NonNull ItemStudentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bindData(Student student) {
            binding.tvName.setText(capitizeString(student.getFirstName()) + " " + student.getLastName());

            if (student.getDes() != null && !student.getDes().isEmpty()) {
                binding.tvClassName.setText(student.getDes());
            } else {
                binding.tvClassName.setText("");
            }

            if (student.getPhoto() != null && !student.getPhoto().isEmpty()) {
                binding.imgUser.setImageURI(Uri.parse(Constants.AVATAR_IMAGE_URL) + student.getPhoto() + "");
            } else {
                binding.imgUser.setImageURI(Uri.parse(Constants.AVATAR_IMAGE_DUMMY_URL));
            }

        }

    }

    private String capitizeString(String name) {
        String captilizedString = "";
        if (!name.trim().equals("")) {
            captilizedString = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return captilizedString;
    }


}
