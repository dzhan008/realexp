package realexp.realexp;
public class ToDo
{
    private int ID;
    private String title;
    private String description;
    private int month;
    private int date;
    private int year;
    private int time;
    private int experience;
    private int gold;
    private int priority;

    //maybe make it so that later on, users can just say a time period and we set the date for them
    public ToDo(String t, String d, int m, int date, int y, int time, int p)
    {
        title = t;
        description = d;
        month = m;
        this.date = date;
        year = y;
        this.time = time;
        priority = p; //Range is 1 to 3 for now, 3 being the highest priority
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
        time = t;
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


}
