package algorithms.ClarkAndWright;

import common.Vertex.BasicVertex;

public class Saving implements Comparable<Saving>
{
    public double cost;
    public BasicVertex from;
    public BasicVertex to;

    public Saving(double v, BasicVertex f, BasicVertex t)
    {
        cost = v;
        from = f;
        to = t;
    }

    @Override
    public int compareTo(Saving o)
    {
        if (o.cost < this.cost)
        {
            return -1;
        }

        else if (o.cost == this.cost)
        {
            return 0;
        }

        else
        {
            return 1;
        }
    }
}
