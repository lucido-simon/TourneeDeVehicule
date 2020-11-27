package common.Vertex;

import common.Route.Route;

import java.util.BitSet;

public class BasicVertex
{
    public int index;
    public String name;
    public int weight;
    public Route route;

    public BasicVertex(){}

    public BasicVertex(int i, String name, int weight)
    {
        index = i;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return name + " " + index;
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        return (o instanceof BasicVertex) && (toString().equals(o.toString()));
    }

}
