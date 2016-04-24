package realexp.realexp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by PenQuynh on 12/30/2015.
 */
public class CheckList extends BaseAdapter
{
    private ArrayList<ToDo> myList;
    private LayoutInflater layoutInflater;

    public CheckList(Context aContext, ArrayList<ToDo> myList)
    {
        this.myList = myList;
        layoutInflater = LayoutInflater.from(aContext);
    }


    public void addToDo(ToDo quest)
    {
        myList.add(quest);
    }

    public void removeToDo()
    {

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.row_layout, null);
            holder = new ViewHolder();
            holder.questTitle = (TextView) convertView.findViewById(R.id.q_title);
            holder.questDescription = (TextView) convertView.findViewById(R.id.q_description);
            holder.questType = (TextView) convertView.findViewById(R.id.q_type);
            holder.questDeadline = (TextView) convertView.findViewById(R.id.q_deadline);
            holder.questDifficulty = (TextView )convertView.findViewById(R.id.q_difficulty);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        /*Set the buttons to do something*/
        Button deleteButt = (Button) convertView.findViewById(R.id.delete_btn);
        Button editButt = (Button) convertView.findViewById(R.id.edit_btn);
        Button completeButt = (Button) convertView.findViewById(R.id.complete_btn);

        //If the delete button is pressed, remove it from the list
        deleteButt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                myList.remove(position); //Removes the specific item from the list
                notifyDataSetChanged(); //Notfiy the adapter that your dateset has changed
            }
        });

        //If the edit button is pressed, edit the quest
        editButt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                //Edit the quest
            }
        });

        completeButt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                /*Completed Quest*/
                //Calculate the amount of exp/gold give to the player

                //Give the exp and gold to the player

                //Delete the item from the list
                //myList.remove(position); //Removes the specific item from the list
                //notifyDataSetChanged(); //Notfiy the adapter that your dateset has changed
            }
        });
        /*Set the text views with information*/
        //Set Title
        holder.questTitle.setText("Quest: " + myList.get(position).get_title());
        //Set Description (if there is one)
        if (!(myList.get(position).get_description().matches(""))) {
            holder.questDescription.setText("Description: " + myList.get(position).get_description());
        }
        //Set Type
        holder.questType.setText("Type: " + myList.get(position).get_type());
        //Set Deadline (Date and Time)
        if (myList.get(position).get_time().matches("")){
            holder.questDeadline.setText("Deadline: " + myList.get(position).get_date());
        }
        else {
            holder.questDeadline.setText("Deadline: " + myList.get(position).get_date() + " at " +
                    myList.get(position).get_time());
        }
        //Set Difficulty
        holder.questDifficulty.setText("Difficulty: " + myList.get(position).get_difficulty());

        return convertView;
    }

    static class ViewHolder
    {
        TextView questTitle;
        TextView questDescription;
        TextView questType;
        TextView questDeadline;
        TextView questDifficulty;
    }

    public boolean isEmpty()
    {
        if (myList.isEmpty()) return true;
        else return false;
    }
}
