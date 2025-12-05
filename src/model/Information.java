package model;

import java.io.Serializable;

public class Information implements Serializable
{
    private String info;
    private String name;

    public Information(String info, String name)
    {
        this.info = info;
        this.name = name;
    }

    public String getInfo()
    {
        return info;
    }

    public String getName()
    {
        return name;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String toString() {
        return "Information[name=" + name + ", info=" + info + "]";
    }
}
