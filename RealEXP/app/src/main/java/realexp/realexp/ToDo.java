package realexp.realexp;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDo implements Parcelable {
    private int ID;
    private String title;
    private String description;
    private int month;
    private int date;
    private int year;
    private int hourTime;
    private int minuteTime;
    private int diffiulty; //1 Easy, 2 Medium, 3 Hard
    private int experience;
    private int gold;
    private int priority;


    protected ToDo(Parcel in) {
        ID = in.readInt();
        title = in.readString();
        description = in.readString();
        month = in.readInt();
        date = in.readInt();
        year = in.readInt();
        hourTime = in.readInt();
        minuteTime = in.readInt();
        diffiulty = in.readInt();
        experience = in.readInt();
        gold = in.readInt();
        priority = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(month);
        dest.writeInt(date);
        dest.writeInt(year);
        dest.writeInt(hourTime);
        dest.writeInt(minuteTime);
        dest.writeInt(diffiulty);
        dest.writeInt(experience);
        dest.writeInt(gold);
        dest.writeInt(priority);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ToDo> CREATOR = new Parcelable.Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };
}
/*
    //maybe make it so that later on, users can just say a time period and we set the date for them
    public ToDo(String t, String d, int m, int date, int y, int htime, int mtime, int p, int diff)
    {
        title = t;
        description = d;
        month = m;
        this.date = date;
        year = y;
        hourTime = htime;
        minuteTime = mtime;
        priority = p; //Range is 1 to 3 for now, 3 being the highest priority
        diffiulty = diff;
        initEXP();
        initGold();
    }

    public void setID(int id)
    {
        ID = id;
    }
    public int getID()
    {
        return ID;
    }
    private void initEXP()
    {

    }

    private void initGold()
    {

    }

    public void setTitle(String t)
    {
        title = t;
    }
    public void setDescriptio(String d)
    {
        description = d;
    }
    public void setMonth(int m)
    {
        month = m;
    }
    public void setDate(int d)
    {
        date = d;
    }
    public void setYear(int y)
    {
        year = y;
    }
    public void setTime(int t)
    {

    }

    public int getEXP()
    {
        return experience;
    }
    public void setEXP(int exp)
    {
        experience = exp;
    }

    public int getGold()
    {
        return gold;
    }
    public void setGold(int g)
    {
        gold = g;
    }
 */