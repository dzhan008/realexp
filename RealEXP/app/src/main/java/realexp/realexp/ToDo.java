package realexp.realexp;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDo implements Parcelable {
    private int ID;
    private String title;
    private String description;
    private String type;
    private int month;
    private int date;
    private int year;
    private int hourTime;
    private int minuteTime;
    private int difficulty; //1 Easy, 2 Medium, 3 Hard
    private int experience;
    private int gold;
    private int priority;



    protected ToDo(int id, String title, String description, String type, int month, int date,
                   int year, int hourTime, int minuteTime, int difficulty, int experience, int gold,
                    int priority)
    {
        ID = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.month = month;
        this.date = date;
        this.year = year;
        this.hourTime = hourTime;
        this.minuteTime = minuteTime;
        this.difficulty = difficulty;
        this.experience = experience;
        this.gold = gold;
        this.priority = priority;

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
        dest.writeString(type);
        dest.writeInt(month);
        dest.writeInt(date);
        dest.writeInt(year);
        dest.writeInt(hourTime);
        dest.writeInt(minuteTime);
        dest.writeInt(difficulty);
        dest.writeInt(experience);
        dest.writeInt(gold);
        dest.writeInt(priority);
    }
    //De Parcel object
    protected ToDo(Parcel in) {
        ID = in.readInt();
        title = in.readString();
        description = in.readString();
        type = in.readString();
        month = in.readInt();
        date = in.readInt();
        year = in.readInt();
        hourTime = in.readInt();
        minuteTime = in.readInt();
        difficulty = in.readInt();
        experience = in.readInt();
        gold = in.readInt();
        priority = in.readInt();
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

    public String get_title()
    {
        return title;
    }
    public String get_description()
    {
        return description;
    }
    public String get_date()
    {
        String deadline = month + "/" + date + "/" + year;
        return deadline;
    }
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