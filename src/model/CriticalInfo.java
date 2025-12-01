package model;

public class CriticalInfo extends Information
{

    private int id;

    public CriticalInfo(String info, String name, int id)
    {
        super(info, name);
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String toString() {
        return "CriticalInfo[id=" + id + ", name=" + getName() + ", info=" + getInfo() + "]";
    }
}
