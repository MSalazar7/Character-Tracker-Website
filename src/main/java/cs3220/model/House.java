package cs3220.model;

import java.util.ArrayList;
import java.util.List;

public class House {

    private String name;

    private List<Character> characters;

    public House( String name )
    {
        this.name = name;
        this.characters = new ArrayList<Character>();
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public List<Character> getCharacters()
    {
        return characters;
    }

    public void setCharacters( List<Character> characters )
    {
        this.characters = characters;
    }
}
