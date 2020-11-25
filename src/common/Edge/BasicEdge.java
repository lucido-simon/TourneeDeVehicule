package common.Edge;

import common.Vertex.BasicVertex;

public class BasicEdge implements Comparable<BasicEdge>
{
    public BasicVertex n1;
    public BasicVertex n2;

    public double val;


    public BasicEdge(BasicVertex ln1, BasicVertex ln2, double dist)
    {
        this.n1 = ln1;
        this.n2 = ln2;
        this.val = dist;
    }

    public void reverse()
    {
        BasicVertex swap = this.n2;
        this.n2 = n1;
        this.n1 = swap;
    }

    @Override
    public String toString()
    {
        return n1.add + n2.add;
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
        if (this.val < o.val)
            return -1;
        else if (o.val == this.val)
            return 0;
        else
            return 1;
    }

}
