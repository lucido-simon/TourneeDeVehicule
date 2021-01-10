import algorithms.BranchAndBound.BranchAndBound;
import algorithms.ClarkAndWright.ClarkAndWright;
import common.Edge.BasicEdge;
import common.Vertex.BasicVertex;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;

import java.net.*;
import java.util.*;


public final class Program
{

    public static void main(String[] args) throws ExportException
    {
        Graph<BasicVertex, BasicEdge> weightedBasicGraph = createWeightedBasicGraph();

        System.out.println("-- toString output");
        System.out.println(weightedBasicGraph.toString());
        System.out.println();

        ClarkAndWright vrp = new ClarkAndWright();
            vrp.loadDataBasic(weightedBasicGraph);
        System.out.println(vrp.clarkWright());

   /*     PrimMinimumSpanningTree<String, DefaultWeightedEdge> tree = new PrimMinimumSpanningTree<>(stringWeightedGraph);
        SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> tree2 = tree.getSpanningTree();
        System.out.println(tree2.toString());*/
        System.out.println("-- Branch and Bound --");
        BranchAndBound test = new BranchAndBound();
        test.loadDataBasic(weightedBasicGraph);
        test.BranchBound();

    }

    private static Graph<BasicVertex, BasicEdge> createWeightedBasicGraph()
    {
        Graph<BasicVertex, BasicEdge> g = new SimpleWeightedGraph<>(BasicEdge.class);

        String[] c = {"a", "b", "c", "d", "e", "f", "g"};

        g.addVertex(new BasicVertex( 0,"a", 5));
        g.addVertex(new BasicVertex( 1,"b", 8));
        g.addVertex(new BasicVertex( 2,"c", 1));
        g.addVertex(new BasicVertex( 3,"d", 9));
        g.addVertex(new BasicVertex( 4,"e", 9));
        g.addVertex(new BasicVertex( 5,"f", 4));
        g.addVertex(new BasicVertex( 6,"g", 2));

        ArrayList<BasicVertex> vertices = new ArrayList<>(g.vertexSet());
        vertices.sort((BasicVertex b1, BasicVertex b2) -> Integer.compare(b1.index, b2.index));

        for ( int i = 0; i < 7 ; i++)
        {
            for ( int j = 0; j < 7 ; j++)
            {
                if (i != j)
                {
                    Random r = new Random();
                    double weight = 1.d + r.nextDouble() * 9.d;
                    g.addEdge(vertices.get(i), vertices.get(j), new BasicEdge(vertices.get(i), vertices.get(j), weight));
                    g.setEdgeWeight(vertices.get(i), vertices.get(j), weight );
                    System.out.println("Distance du point " + vertices.get(i) + " au point " + vertices.get(j) + " = " + (int)weight + " ");
                }

            }
            System.out.println("");
        }

        return g;

    }

    private static Graph<String, DefaultWeightedEdge> createWeightedStringGraph()
    {
        Graph<String, DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        String[] c = {"a", "b", "c", "d", "e", "f", "g"};

        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");
        g.addVertex("f");
        g.addVertex("g");



        for ( int i = 0; i < 7 ; i++)
        {
            for ( int j = 0; j < 7 ; j++)
            {
                if (i != j)
                {
                    Random r = new Random();
                    g.addEdge(c[i], c[j]);
                    g.setEdgeWeight(c[i], c[j], 1.d + r.nextDouble() * (9.d));
                }

            }

        }


        return g;
    }
}