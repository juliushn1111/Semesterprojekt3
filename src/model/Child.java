package model;

import java.io.Serializable;
import java.util.Objects;

public class Child extends Person implements Serializable
{
    // Barnets køn (f.eks. "Boy", "Girl").
    private String sex;
    // Barnets alder i hele år.
    private int age;
    // Constructor: kalder super for at sætte navnet

    public Child(String name, String sex, int age){
        super(name);
        this.sex = sex;
        this.age = age;
        setAge(age);
    }
    public String getSex()
    {
        return sex;
    }
    public int getAge()
    {
        return age;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    // Setter for alder.

    public void setAge(int age)
    {
        if (age < 0)
        {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    @Override public String toString()
    {
        return "Child{" +
                "name='" + getName() + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }

    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o)) // tjek Person's felter (name)
            return false;
        Child child = (Child) o;
        // sammenligner Child-specifikke felter
        return age == child.age && Objects.equals(sex, child.sex);
    }

    @Override public int hashCode()
    {
        return Objects.hash(super.hashCode(), sex, age);
    }

}
