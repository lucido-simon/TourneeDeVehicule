package common.Vertex;

import algorithms.ClarkAndWright.model.Route;
import common.Edge.BasicEdge;

import java.util.ArrayList;

public class BasicVertex
{
    public int index;
    public Route route;

    public String add;

    public int amount;


    public BasicVertex(int i)
    {
        index = i;
    }

    public BasicVertex(int i, String add)
    {
        index = i;
        this.add = add;
    }

    public BasicVertex(int i, String add, int amount)
    {
        index = i;
        this.add = add;
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return add + " " + index;
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
