package realexp.realexp;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestRecyclerInfo implements Parcelable {
    private int ID;
    private String title;
    private String description;
    private String type;
    private int month;
    private int date;
    private int year;
    private int hourTime;
    private int minuteTime;
    private String suffixTime;
    private String difficulty; //1 Easy, 2 Medium, 3 Hard
    private int experience;
    private int gold;
    private int priority;



    protected QuestRecyclerInfo(int id, String title, String description, String type, int month,
                                int date, int year, int hourTime, int minuteTime, String suffixTime,
                                String difficulty, int experience, int gold, int priority)
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
        this.suffixTime = suffixTime;
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
        dest.writeString(suffixTime);
        dest.writeString(difficulty);
        dest.writeInt(experience);
        dest.writeInt(gold);
        dest.writeInt(priority);
    }
    //De Parcel object
    protected QuestRecyclerInfo(Parcel in) {
        ID = in.readInt();
        title = in.readString();
        description = in.readString();
        type = in.readString();
        month = in.readInt();
        date = in.readInt();
        year = in.readInt();
        hourTime = in.readInt();
        minuteTime = in.readInt();
        suffixTime = in.readString();
        difficulty = in.readString();
        experience = in.readInt();
        gold = in.readInt();
        priority = in.readInt();
    }
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<QuestRecyclerInfo> CREATOR = new Parcelable.Creator<QuestRecyclerInfo>() {
        @Override
        public QuestRecyclerInfo createFromParcel(Parcel in) {
            return new QuestRecyclerInfo(in);
        }

        @Override
        public QuestRecyclerInfo[] newArray(int size) {
            return new QuestRecyclerInfo[size];
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
    public String get_type() {return type;}
    public String get_date()
    {
        String deadline = month + "/" + date + "/" + year;
        return deadline;
    }
    public String get_time()
    {
        if (suffixTime.matches("")) return "";
        String time = hourTime + ":" + minuteTime + " " + suffixTime;
        if (suffixTime == "AM/PM") return " ";
        return time;
    }
    public String get_difficulty() {return difficulty;}
}