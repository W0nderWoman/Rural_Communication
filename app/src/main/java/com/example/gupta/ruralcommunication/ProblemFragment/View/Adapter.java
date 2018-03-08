package com.example.gupta.ruralcommunication.ProblemFragment.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gupta.ruralcommunication.ProblemFragment.Model.ProblemObject;
import com.example.gupta.ruralcommunication.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Vasudev on 3/8/2018.
 */

public class Adapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<ProblemObject> problems;

    public Adapter(Context context, ArrayList<ProblemObject> problems) {
        this.context = context;
        this.problems = problems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.problem_list_view,parent,false);
        return new ProblemItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProblemItemHolder prob= (ProblemItemHolder) holder;
        prob.bindView(context,problems.get(position));
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }
}
class ProblemItemHolder extends RecyclerView.ViewHolder{

    Boolean descriptionToggle=false;
    public ProblemItemHolder(View itemView) {
        super(itemView);
    }
    void bindView(Context context,ProblemObject problem){
        TextView titleTextView=itemView.findViewById(R.id.problem_title_text_view);
        titleTextView.setText(problem.getTitle());
        TextView dateTextView=itemView.findViewById(R.id.problem_date_text_view);
        dateTextView.setText(problem.getDate());
        final TextView descriptionTextView=itemView.findViewById(R.id.problem_description_text_view);
        descriptionTextView.setText(problem.getDescription());
        TextView statusTextView=itemView.findViewById(R.id.problem_status_text_view);
        statusTextView.setText(problem.getStatus());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descriptionToggle=!descriptionToggle;
                if(descriptionToggle){
                    descriptionTextView.setVisibility(View.VISIBLE);
                }else {
                    descriptionTextView.setVisibility(View.GONE);
                }
            }
        });
    }
}
