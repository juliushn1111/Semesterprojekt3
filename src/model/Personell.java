package model;

import java.util.Objects;
// Personell nedarver fra Person og udvider med et ID og en jobtitel.
// Klassen repræsenterer ansatte i institutionen.
public class Personell extends Person
{
    // Unikt ID for medarbejderen.
    private int id;
    // Titel/jobbet personen har (fx "Pædagog" eller "Vikar").
    private String title;
    public Personell(String name, int id, String title){
        super(name);
        this.id=id;
        this.title=title;
    }
    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }
    public void setId(int id)
    {
        if (id < 0)
        {
            throw new IllegalArgumentException("ID cannot be negative");
        }

        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "Personell{" +
                "name='" + getName() + '\'' +     // arvet fra Person
                ", id=" + id +                    // eget felt
                ", title='" + title + '\'' +      // eget felt
                '}';
    }

    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Personell personell = (Personell) o;
        return id == personell.id && Objects.equals(title, personell.title);
    }
    // hashCode: matcher equals. Kombinerer Person's hashCode
    // med Personell-felterne, så objektet fungerer korrekt i hash-baserede datastrukturer.
    @Override public int hashCode()
    {
        return Objects.hash(super.hashCode(), id, title);
    }
}
