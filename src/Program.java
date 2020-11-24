import algorithms.ClarkAndWright.program.VRPProgram;
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


public final class Program
{

    public static void main(String[] args) throws URISyntaxException, ExportException
    {
        Graph<String, DefaultWeightedEdge> stringWeightedGraph = createWeightedStringGraph();

        System.out.println("-- toString output");
        System.out.println(stringWeightedGraph.toString());
        System.out.println();

        VRPProgram vrp = new VRPProgram();
        vrp.loadData(stringWeightedGraph);
        vrp.clarkWright();

        PrimMinimumSpanningTree<String, DefaultWeightedEdge> tree = new PrimMinimumSpanningTree<>(stringWeightedGraph);
        SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> tree2 = tree.getSpanningTree();
        System.out.println(tree2.toString());

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