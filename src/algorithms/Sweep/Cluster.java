package algorithms.Sweep;

import common.Edge.BasicEdge;
import common.Vertex.SweepVertex;

import java.util.ArrayList;
import java.util.Collections;

public class Cluster
{

    public int weight;
    public ArrayList<SweepVertex> nodes;
    public ArrayList<BasicEdge<SweepVertex>> edges;
    public ArrayList<SweepVertex> tsp;
    public ArrayList<BasicEdge<SweepVertex>> mstE;

    public int cost;

    public Cluster()
    {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void add(SweepVertex n)
    {
        nodes.add(n);
        weight += n.weight;
    }

    public Cluster copy()
    {
        Cluster n = new Cluster();
        n.weight = this.weight;
        n.nodes = this.nodes;
        return n;
    }

    public void printMST(StringBuilder sb)
    {
        System.out.println("CLUSTER:");
        for (BasicEdge e : mstE)
        {
            sb.append("From: " + e.v1.index + " To: " + e.v2.index + " Val: " + e.cost);
        }
    }

    public void mst()
    {
        mstE = new ArrayList<>();

        nodes.get(0).visited = true;
        nodes.get(0).mstEdges = new ArrayList<>();
        int visitedSweepVertexs = 1;
        Collections.sort(edges);

        while (visitedSweepVertexs != nodes.size() )
        {
            boolean added = false;
            int counter = 0;
            while (!added )
            {

                BasicEdge<SweepVertex> e = edges.get(counter);
                if (e.v1.visited && e.v2.visited)
                {
                    //||(e.v2.visited == true && e.v1.visited == false)){
                    mstE.add(e);
                    //e.v1.visited = true;
                    e.v2.visited = true;
                    //pridam do MST hran, ktere tvori kostru
                    if (e.v1.mstEdges == null)
                    {
                        e.v1.mstEdges = new ArrayList<>();
                    }
                    e.v1.mstEdges.add(e);
                    added = true;
                    visitedSweepVertexs++;
                }
                counter++;
            }
        }
    }

    public void dfsONMST()
    {
        tsp = new ArrayList<>();
        SweepVertex start = nodes.get(0);
        dfsProjdi(start);
        tsp.add(start);
    }

    public void dfsProjdi(SweepVertex n)
    {
        tsp.add(n);
        if (n.mstEdges != null)
        {
            for (BasicEdge<SweepVertex> e : n.mstEdges)
            {
                SweepVertex v2 = e.v2;
                dfsProjdi(v2);
            }
        }
    }

    public void printTSP(StringBuilder sb)
    {
        //System.out.println("CLUSTER TSP:");
        for (SweepVertex n : tsp)
        {
            sb.append(n.index + " ");
        }
    }

    public void printTSPAdds(StringBuilder sb)
    {
        int nodeCount = tsp.size();
        for (int i = 0; i < nodeCount; i++)
        {
            SweepVertex n = tsp.get(i);
            sb.append(n.name);

            if (i < nodeCount - 1)
            {
                sb.append("->");
            }
        }
    }
}
