package realexp.realexp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Penquynh on 5/1/2016.
 */
public class QuestRecyclerAdapter extends RecyclerView.Adapter<QuestRecyclerAdapter.MyViewHolder> {
    LayoutInflater inflater;
    Context context;
    List<QuestRecyclerInfo> data = Collections.emptyList();

    public QuestRecyclerAdapter(Context context, List<QuestRecyclerInfo> data){
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    public void addQuest(QuestRecyclerInfo quest)
    {
        data.add(quest);
    }
    public void deleteQuest(int position){data.remove(position);}
    public void updateQuest(QuestRecyclerInfo quest, int position){
        data.get(position).setEverything(quest);
    }


        @Override
    public QuestRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, new MyViewHolder.MyViewHolderClicks(){

            @Override
            public void rowClick(View caller, int position) {
                Toast.makeText(context, "Row number clicked: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void questClick(View caller, int position) {
                Toast.makeText(context, "Quest clicked. ", Toast.LENGTH_SHORT).show();

            }

            public void viewButtonClick(View caller, int position){
                Toast.makeText(context, "View coming soon near you! ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, QuestViewInfo_Activity.class);
                i.putExtra("quest", data.get(position));
                i.putExtra("position", position);
                ((Activity) context).startActivityForResult(i, 20);
            }
            public void completeButtonClick(View caller, int position){
                Toast.makeText(context, "Quest completed!!! ", Toast.LENGTH_SHORT).show();
                //data.remove(position); //Removes the specific item from the list
                //notifyDataSetChanged(); //Notfiy the adapter that your dateset has changed
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(QuestRecyclerAdapter.MyViewHolder holder, int position) {

        //Note to whoever read this: Need to add string resources for the hardcoded string literals
        holder.questTitle.setText("Quest: " + data.get(position).get_title());
        if (data.get(position).get_time().matches("")) {
            holder.questDeadline.setText("Deadline: " + data.get(position).get_deadline());
        }
        else {
            holder.questDeadline.setText("Deadline: " + data.get(position).get_deadline() + " at " +
                data.get(position).get_time());
        }
        //holder.questDeadline.setText("Deadline: " + data.get(position).get_date());

    }

    @Override
    public int getItemCount() {return data.size();} //Returns amount of items in the data set
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public interface MyViewHolderClicks{
            void rowClick(View caller, int position);
            void questClick(View caller, int position);
            void viewButtonClick(View caller, int position);
            void completeButtonClick(View caller, int position);
        }

        //Data Members
        TextView questTitle;
        TextView questDeadline;
        Button completeButt;
        Button viewButt;
        public MyViewHolderClicks mListener;

        public MyViewHolder(View itemView, MyViewHolderClicks listener)//Constructor
        {
            super(itemView);
            mListener = listener;

            questTitle = (TextView) itemView.findViewById(R.id.q_title);
            questDeadline = (TextView) itemView.findViewById(R.id.q_deadline);

            completeButt = (Button) itemView.findViewById(R.id.complete_btn);
            viewButt = (Button) itemView.findViewById(R.id.view_btn);

            itemView.setOnClickListener(this);
            questTitle.setOnClickListener(this);
            viewButt.setOnClickListener(this);
            completeButt.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.q_title:
                    mListener.questClick(v, getAdapterPosition());
                    break;
                case R.id.complete_btn:
                    mListener.completeButtonClick(v, getAdapterPosition());
                    break;
                case R.id.view_btn:
                    mListener.viewButtonClick(v, getAdapterPosition());
                    break;
                default:
                    mListener.rowClick(v, getAdapterPosition());
                    break;
            }
        }

    }
}
