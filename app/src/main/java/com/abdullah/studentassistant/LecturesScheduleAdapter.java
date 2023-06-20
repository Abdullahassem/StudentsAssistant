package com.abdullah.studentassistant;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class LecturesScheduleAdapter extends RecyclerView.Adapter<LecturesScheduleAdapter.ViewHolder> {

    private final Map<String, List<Lecture>> lecturesByDay;

    public LecturesScheduleAdapter(Map<String, List<Lecture>> lecturesByDay) {
        this.lecturesByDay = lecturesByDay;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_schedule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String day = (String) lecturesByDay.keySet().toArray()[position];
        List<Lecture> lectures = lecturesByDay.get(day);

        holder.tvDay.setText(day.toUpperCase());
        StringBuilder sb = new StringBuilder();

        for (Lecture lecture : lectures) {
            sb.append(lecture.getName())
                    .append(" (")
                    .append(lecture.getStartTime())
                    .append(" - ")
                    .append(lecture.getEndTime())
                    .append(")\n");
        }

        holder.tvLectures.setText(sb.toString());
    }

    @Override
    public int getItemCount() {
        return lecturesByDay.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDay, tvLectures;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvLectures = itemView.findViewById(R.id.tvLectures);
        }
    }
}