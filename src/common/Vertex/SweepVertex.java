package common.Vertex;

import common.Edge.BasicEdge;

import java.util.ArrayList;

public class SweepVertex extends BasicVertex implements Comparable<SweepVertex>
{

    public double x;
    public double y;
    public double angleFromDepot;

    public int state;
    public boolean visited;
    public ArrayList<BasicEdge<SweepVertex>> mstEdges;

    public int cluster;

    /**
     * @param depot Donner null en paramètre si la Vertex qu'on essaie de créer est le dépot
     */
    public SweepVertex()
    {
        super();
    }

    public SweepVertex(int i, String name, int weight, double x, double y, SweepVertex depot)
    {
        super(i, name, weight);
        this.x = x;
        this.y = y;

        if (depot != null) // Si on instancies pas le dépot
        {
            // Calcul du cluster
            if (x >= depot.x)
            {
                if (y >= depot.y)
                    cluster = 1;
                else cluster = 4;

            }
            else
            {
                if (y >= depot.y)
                    cluster = 2;
                else cluster = 3;
            }

            // Calcul de l'angle par rapport au dépot

            double difx = Math.abs(x - depot.x);
            double dify = Math.abs(y - depot.y);

            if (dify != 0)
            {
                double tangA = (double) dify / difx;

                if (cluster == 2 || cluster == 4)
                    tangA = 1 / tangA;

                angleFromDepot += Math.atan(tangA);
            }
        }


    }

    @Override
    public int compareTo(SweepVertex o)
    {
        if (this.angleFromDepot < o.angleFromDepot)
        {
            return -1;
        }
        else if (o.angleFromDepot == this.angleFromDepot)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

}
