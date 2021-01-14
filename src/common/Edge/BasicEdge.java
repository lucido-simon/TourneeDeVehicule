package common.Edge;

import common.Vertex.BasicVertex;
import common.Vertex.SweepVertex;

public class BasicEdge<T extends BasicVertex> implements Comparable<BasicEdge>
{
    public T v1;
    public T v2;

    public double cost;

    public BasicEdge()
    {
    }

    public BasicEdge(T ln1, T ln2, double dist)
    {
        this.v1 = ln1;
        this.v2 = ln2;
        this.cost = dist;
    }

    public void reverse()
    {
        T swap = this.v2;
        this.v2 = v1;
        this.v1 = swap;
    }

    @Override
    public String toString()
    {
        if ( v1 == null || v2 == null )
            return "null";
        return v1.name + v2.name;
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        return (o instanceof BasicEdge) && (toString().equals(o.toString()));
    }

    @Override
    public int compareTo(BasicEdge o)
    {
        if (this.cost < o.cost)
            return -1;
        else if (o.cost == this.cost)
            return 0;
        else
            return 1;
    }

}
