package common.Route;

import common.Edge.BasicEdge;
import common.Vertex.BasicVertex;

import java.util.ArrayList;

public class Route
{

    public int weightLimit;
    public int currentWeight;
    public int totalCost;


    public BasicVertex[] nodes;
    public BasicEdge[] inEdges;
    public BasicEdge[] outEdges;


    ArrayList<BasicEdge> edges;

    public Route(int numberOfVertices)
    {
        edges = new ArrayList<>();

        nodes = new BasicVertex[numberOfVertices];
        inEdges = new BasicEdge[numberOfVertices];
        outEdges = new BasicEdge[numberOfVertices];
    }

    public void add(BasicEdge e)
    {
        edges.add(e);

        outEdges[e.v1.index] = e;
        inEdges[e.v2.index] = e;

        e.v1.route = this;
        e.v2.route = this;

        totalCost += e.cost;
    }

    public void removeEdgeToVertex(int index)
    {
        BasicEdge e = inEdges[index];
        outEdges[e.v1.index] = null;

        totalCost -= e.cost;

        edges.remove(e);
        inEdges[index] = null;
    }

    public void removeEdgeFromVertex(int index)
    {
        BasicEdge e = outEdges[index];
        inEdges[e.v2.index] = null;

        totalCost -= e.cost;
        edges.remove(e);
        outEdges[index] = null;
    }

    public int predecessor(int vertexIndex)
    {
        return inEdges[vertexIndex].v1.index;
    }


    public int successor(int vertexIndex)
    {
        return outEdges[vertexIndex].v2.index;
    }

    public boolean merge(Route r2, BasicEdge mergingEdge)
    {

        int from = mergingEdge.v1.index;
        int to = mergingEdge.v2.index;

        int predecessorI = this.predecessor(from);
        int predecessorJ = r2.predecessor(to);

        int successorI = this.successor(from);
        int successorJ = r2.successor(to);

        // option one
        // the edge goes from node ze, of which in the first Route we return back to node 0
        // in the second route, on the other hand, the predecessor of node J is warehouse = 0

        if (successorI == 0 && predecessorJ == 0)
        {
            this.removeEdgeToVertex(0);
            r2.removeEdgeFromVertex(0);
            for (BasicEdge e : r2.edges)
            {
                this.add(e);
            }
            this.currentWeight += r2.currentWeight;
            this.add(mergingEdge);
            return true;
            // option two
            // the edge goes in the opposite direction
            // node i is in the first route of the type just behind the stock
            // we have to turn the edge
        }
        else if (successorJ == 0 && predecessorI == 0)
        {
            mergingEdge.reverse();
            this.removeEdgeFromVertex(0);
            r2.removeEdgeToVertex(0);
            for (BasicEdge e : r2.edges)
            {
                this.add(e);
            }
            this.currentWeight += r2.currentWeight;
            this.add(mergingEdge);
            return true;
        }

        return false;
    }
}
