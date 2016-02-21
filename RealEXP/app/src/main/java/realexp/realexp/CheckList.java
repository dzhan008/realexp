package realexp.realexp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.row_layout, null);
            holder = new ViewHolder();
            holder.questTitle = (TextView) convertView.findViewById(R.id.q_title);
            holder.questDescription = (TextView) convertView.findViewById(R.id.q_description);
            holder.questDate= (TextView) convertView.findViewById(R.id.q_date);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.questTitle.setText(myList.get(position).get_title());
        holder.questDescription.setText(myList.get(position).get_description());
        holder.questDate.setText(myList.get(position).get_date());
        return convertView;
    }

    static class ViewHolder
    {
        TextView questTitle;
        TextView questDescription;
        TextView questDate;
    }

    public boolean isEmpty()
    {
        if (myList.isEmpty()) return true;
        else return false;
    }
}
