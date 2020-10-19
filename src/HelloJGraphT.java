import org.jgrapht.*;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;
import org.jgrapht.alg.spanning.*;

import java.io.*;
import java.net.*;
import java.util.*;


public final class HelloJGraphT
{
    private HelloJGraphT() { } // ensure non-instantiability.


    public static void main(String[] args) throws URISyntaxException, ExportException
    {
        Graph<String, DefaultWeightedEdge> stringWeightedGraph = createWeightedStringGraph();

        System.out.println("-- toString output");
        System.out.println(stringWeightedGraph.toString());
        System.out.println();

        PrimMinimumSpanningTree<String, DefaultWeightedEdge> tree = new PrimMinimumSpanningTree<>(stringWeightedGraph);
        SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> tree2 = tree.getSpanningTree();
        System.out.println(tree2.toString());

    }

    private static Graph<String, DefaultWeightedEdge> createWeightedStringGraph()
    {
        Graph<String, DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");

        g.addEdge("a", "b");
        g.addEdge("a", "e");
        g.addEdge("b", "c");
        g.addEdge("c", "e");
        g.addEdge("b", "d");
        g.addEdge("e", "d");

        g.setEdgeWeight("e", "d", 2.d);
        g.setEdgeWeight("e", "c", 2.d);
        g.setEdgeWeight("e", "a", 8.d);

        return g;
    }
}