package algorithms.ClarkAndWright.model;

import common.Edge.BasicEdge;
import common.Vertex.BasicVertex;

import java.util.ArrayList;
import java.util.Collections;

public class Cluster
{

    public int amount;
    public ArrayList<BasicVertex> nodes;
    public ArrayList<BasicEdge> edges;
    public ArrayList<BasicVertex> tsp;
    public ArrayList<BasicEdge> mstE;

    public int cost;

    public Cluster()
    {
        nodes = new ArrayList<BasicVertex>();
        edges = new ArrayList<BasicEdge>();
    }

    public void add(BasicVertex n)
    {
        nodes.add(n);
        amount += n.amount;
    }

    public Cluster copy()
    {
        Cluster n = new Cluster();
        n.amount = this.amount;
        n.nodes = this.nodes;
        return n;
    }

    public void printMST(StringBuilder sb)
    {
        System.out.println("CLUSTER:");
        for (BasicEdge e : mstE)
        {
            sb.append("From: " + e.n1.index + " To: " + e.n2.index + " Val: " + e.val);
        }
    }

    public void mst()
    {
        mstE = new ArrayList<BasicEdge>();

        nodes.get(0).visited = true;
        nodes.get(0).mstEdges = new ArrayList<BasicEdge>();
        int visitedNodes = 1;
        Collections.sort(edges);

        while (visitedNodes != nodes.size())
        {
            boolean added = false;
            int counter = 0;
            while (!added)
            {

                BasicEdge e = edges.get(counter);
                if ((e.n1.visited == true && e.n2.visited == false))
                {
                    //||(e.n2.visited == true && e.n1.visited == false)){
                    mstE.add(e);
                    //e.n1.visited = true;
                    e.n2.visited = true;
                    //I add to the MST the edges that make up the skeleton
                    if (e.n1.mstEdges == null)
                    {
                        e.n1.mstEdges = new ArrayList<BasicEdge>();
                    }
                    e.n1.mstEdges.add(e);
                    added = true;
                    visitedNodes++;
                }
                counter++;
            }
        }
    }

    public void dfsONMST()
    {
        tsp = new ArrayList<BasicVertex>();
        BasicVertex start = nodes.get(0);
        dfsProjdi(start);
        tsp.add(start);
    }

    public void dfsProjdi(BasicVertex n)
    {
        tsp.add(n);
        if (n.mstEdges != null)
        {
            for (BasicEdge e : n.mstEdges)
            {
                BasicVertex n2 = e.n2;
                dfsProjdi(n2);
            }
        }
    }

    public void printTSP(StringBuilder sb)
    {
        //System.out.println("CLUSTER TSP:");
        for (BasicVertex n : tsp)
        {
            sb.append(n.index + " ");
        }
    }

    public void printTSPAdds(StringBuilder sb)
    {
        int nodeCount = tsp.size();
        for (int i = 0; i < nodeCount; i++)
        {
            BasicVertex n = tsp.get(i);
            sb.append(n.add);
            if (i < nodeCount - 1)
            {
                sb.append("->");
            }
        }
    }
}
