package algorithms.ClarkAndWright.model;

import common.Edge.BasicEdge;

import java.util.ArrayList;

public class Route
{

    public int allowed;
    public int actual;
    public int totalCost;


    public int[] nodes;
    public BasicEdge[] inEdges;
    public BasicEdge[] outEdges;


    ArrayList<BasicEdge> edges;

    public Route(int nodesNumber)
    {
        edges = new ArrayList<>();

        nodes = new int[nodesNumber];
        inEdges = new BasicEdge[nodesNumber];
        outEdges = new BasicEdge[nodesNumber];
    }

    public void add(BasicEdge e)
    {
        edges.add(e);

        outEdges[e.n1.index] = e;
        inEdges[e.n2.index] = e;

        e.n1.route = this;
        e.n2.route = this;

        totalCost += e.val;
    }

    public void removeEdgeToNode(int index)
    {
        BasicEdge e = inEdges[index];
        outEdges[e.n1.index] = null;

        totalCost -= e.val;

        edges.remove(e);
        inEdges[index] = null;
    }

    public void removeEdgeFromNode(int index)
    {
        BasicEdge e = outEdges[index];
        inEdges[e.n2.index] = null;

        totalCost -= e.val;
        edges.remove(e);
        outEdges[index] = null;
    }

    public int predecessor(int nodeIndex)
    {
        return inEdges[nodeIndex].n1.index;
    }


    public int successor(int nodeIndex)
    {
        return outEdges[nodeIndex].n2.index;
    }

    public boolean merge(Route r2, BasicEdge mergingEdge)
    {

        int from = mergingEdge.n1.index;
        int to = mergingEdge.n2.index;

        int predecessorI = this.predecessor(from);
        int predecessorJ = r2.predecessor(to);

        int successorI = this.successor(from);
        int successorJ = r2.successor(to);

        // option one
        // the edge goes from node ze, of which in the first Route we return back to node 0
        // in the second route, on the other hand, the predecessor of node J is warehouse = 0

        if (successorI == 0 && predecessorJ == 0)
        {
            this.removeEdgeToNode(0);
            r2.removeEdgeFromNode(0);
            for (BasicEdge e : r2.edges)
            {
                this.add(e);
            }
            this.actual += r2.actual;
            this.add(mergingEdge);
            return true;
            // option two
            // the edge goes in the opposite direction
            // node i is in the first route of the type just behind the stock
            // we have to turn the edge
        } else if (successorJ == 0 && predecessorI == 0)
        {
            mergingEdge.reverse();
            this.removeEdgeFromNode(0);
            r2.removeEdgeToNode(0);
            for (BasicEdge e : r2.edges)
            {
                this.add(e);
            }
            this.actual += r2.actual;
            this.add(mergingEdge);
            return true;
        }

        return false;
    }
}
