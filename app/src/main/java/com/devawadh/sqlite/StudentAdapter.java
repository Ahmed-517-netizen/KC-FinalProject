package com.devawadh.sqlite;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Studentdb> {
    private List<Studentdb> list;
    private Context context;

    TextView txtcurrentstudent,
            evalstudent,
            quantityText,
            addMeal,
            subtractMeal,
            removeMeal;

    public StudentAdapter(Context context, List<Studentdb> mystudent) {
        super(context, 0, mystudent);
        this.list = mystudent;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.singlestudent,parent,false
            );
        }
        final Studentdb currentstudent = getItem(position);
        txtcurrentstudent = (TextView)listItemView.findViewById(R.id.selected_student_name);
        evalstudent = (TextView)listItemView.findViewById(R.id.eval_item);
        /*subtractMeal = (TextView)listItemView.findViewById(R.id.minus_meal);
        quantityText = (TextView)listItemView.findViewById(R.id.quantity);
        addMeal = (TextView)listItemView.findViewById(R.id.plus_meal);
        removeMeal = (TextView)listItemView.findViewById(R.id.delete_item);*/

        return listItemView;
}

}
