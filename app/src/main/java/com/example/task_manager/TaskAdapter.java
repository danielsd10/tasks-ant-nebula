package com.example.task_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private List<Task> listTasks;

    public TaskAdapter(Context context, int textViewResourceId, List<Task> items) {
        super(context, textViewResourceId, items);
        listTasks = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_task, null);
        }

        Task item = getItem(position);

        TextView textTask = (TextView) view.findViewById(R.id.textTask);
        textTask.setText(item.getName());

        return view;
    }
}
