package model;

import java.util.Objects;
// Child nedarver fra Person og udvider med information
// om barnets køn og alder.
public class Child extends Person
{
    // Barnets køn (f.eks. "male", "female", "other").
    private String sex;
    // Barnets alder i hele år.
    private int age;
    // Constructor: kalder super for at sætte navnet
    // og initialiserer Child-specifikke felter.
    // setAge() bruges for at genbruge valideringen.
    public Child(String name, String sex, int age){
        super(name);
        this.sex = sex;
        setAge(age); // bruger samme validering
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
    // Indeholder simpel validering for at sikre gyldige værdier.
    public void setAge(int age)
    {
        if (age < 0)
        {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }
    // toString: giver en letforståelig tekstrepræsentation
    // af objektet til debugging/logging.
    @Override public String toString()
    {
        return "Child{" +
                "name='" + getName() + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
    // equals: to Child-objekter betragtes som ens hvis både Person-delen
    // (via super.equals) og Child-felterne matcher.
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
    // hashCode: matcher equals. Kombinerer Person's hashCode
    // med Child-felterne, så objektet fungerer korrekt i hash-baserede datastrukturer.
    @Override public int hashCode()
    {
        return Objects.hash(super.hashCode(), sex, age);
    }

}
