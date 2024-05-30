package cs3220.model;

public class Character {

    private String name;

    private String father;

    private String mother;

    public Character()
    {
    }

    public Character( String name, String father, String mother )
    {
        this.name = name;
        this.father = father;
        this.mother = mother;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getFather()
    {
        return father;
    }

    public void setFather( String father )
    {
        this.father = father;
    }

    public String getMother()
    {
        return mother;
    }

    public void setMother( String mother )
    {
        this.mother = mother;
    }

}
