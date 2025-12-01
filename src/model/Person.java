package model;

import java.util.Objects;

public class Person
{
    private String name;
    public Person(String name){
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    // toString: giver en tekstrepræsentation af objektet (bruges fx i print/debug).
    @Override public String toString()
    {
        return "Person{" + "name='" + name + '\'' + '}';
    }
    // equals: bestemmer om to Person-objekter er "ens".
    // Vi tjekker om det er samme objekt først, derefter om klasserne matcher,
    // og til sidst om navnene er ens.
    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }
    // hashCode: bruges sammen med equals i fx HashMap / HashSet.
    // Ens navn = ens hashværdi.
    @Override public int hashCode()
    {
        return Objects.hash(name);
    }
}
