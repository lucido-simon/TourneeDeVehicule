package common.Edge;

import common.Vertex.BasicVertex;

public class BasicEdge implements Comparable<BasicEdge>
{
    public BasicVertex v1;
    public BasicVertex v2;

    public double cost;


    public BasicEdge(BasicVertex ln1, BasicVertex ln2, double dist)
    {
        this.v1 = ln1;
        this.v2 = ln2;
        this.cost = dist;
    }

    public void reverse()
    {
        BasicVertex swap = this.v2;
        this.v2 = v1;
        this.v1 = swap;
    }

    @Override
    public String toString()
    {
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
