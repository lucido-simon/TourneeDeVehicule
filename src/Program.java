import algorithms.BranchAndBound.BranchAndBound;
import algorithms.ClarkAndWright.ClarkAndWright;
import algorithms.Sweep.Sweep;
import common.Edge.BasicEdge;
import common.Edge.SweepEdge;
import common.GraphReader.LoadFromFile;
import common.Vertex.BasicVertex;
import common.Vertex.SweepVertex;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;

import java.lang.reflect.Array;
import java.net.*;
import java.util.*;


public final class Program
{

    public static void main(String[] args) throws ExportException
    {
/*        *//*
        Pour lancer le programme avec des arguments : Run -> Edit Configurations -> case "Program Arguments"
        Le format est le suivant : "arg1 arg2"
        Exemple : "chemin.dot Sweep"
        *//*

        // Vérifie qu'il y ait bien 2 arguments (ni plus, ni moins) et envoie un message d'erreur si ce n'est pas le cas.
        if (args.length != 2){
            System.out.println("Le nombre d'arguments est incorrect. Veuillez entrer le chemin du fichier d'extension \"dot\" contenant le graphe ainsi que la méthode voulant être utilisée (CW, BB où Sweep).");
        }

        // S'il y a bien 2 arguments
        else {
            System.out.println("Chemin vers le fichier : " + args[0]); // Chemin vers le fichier = args[0]
            switch (args[1]){
                case "CW" :
                    System.out.println("Clark and Wright");
                    break;
                case "BB" :
                    System.out.println("Breach and Bound");
                    break;
                case "Sweep" :
                    System.out.println("Sweep");
                    break;
                default :
                    System.out.println("La méthode choisie (" + args[1] + ") n'a pas été reconnue.");
            }
        }*/


/*        Graph<BasicVertex, BasicEdge> weightedBasicGraph = new SimpleWeightedGraph<>(BasicEdge.class);
        LoadFromFile.importFromFile("C:\\Users\\Simon\\Desktop\\Java\\TourneeDeVehicule2\\src\\common\\GraphReader\\test.dot", weightedBasicGraph);

        System.out.println("-- toString output");
        System.out.println(weightedBasicGraph.toString());
        System.out.println();

        Set<BasicVertex> vertices = weightedBasicGraph.vertexSet();
        Set<BasicEdge> edges = weightedBasicGraph.edgeSet();

        for ( BasicVertex bv : vertices )
            System.out.println(bv.index + " " + bv.name + " " + bv.weight);

        for ( BasicEdge be : edges )
        {
            System.out.println(be.v1.name + "->" + be.v2.name + be.cost + " " + weightedBasicGraph.getEdgeWeight(be));
        }*/

        /*ClarkAndWright vrp = new ClarkAndWright();
        vrp.loadDataBasic(weightedBasicGraph);
        System.out.println(vrp.clarkWright());*/

        Graph<SweepVertex, SweepEdge> sweepGraph = new DefaultUndirectedWeightedGraph<>(SweepEdge.class);
        LoadFromFile.importFromFileSweep("C:\\Users\\Simon\\Desktop\\Java\\TourneeDeVehicule2\\src\\common\\GraphReader\\test.dot", sweepGraph);

        ArrayList<SweepVertex> vertices = new ArrayList<>(sweepGraph.vertexSet());
        ArrayList<SweepEdge> edges = new ArrayList<>(sweepGraph.edgeSet());

        for ( SweepVertex bv : vertices )
            System.out.println(bv.index + " " + bv.name + " " + bv.weight);

        for ( SweepEdge be : edges )
        {
            System.out.println(be.v1.name + "->" + be.v2.name + be.cost + " " + sweepGraph.getEdgeWeight(be));
        }


        Sweep sweep = new Sweep();
        Sweep.loadData(sweepGraph);
        System.out.println(Sweep.sweep());


        /*System.out.println("-- Branch and Bound --");
        BranchAndBound test = new BranchAndBound();
        test.loadDataBasic(weightedBasicGraph);
        test.BranchBound();*/

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
                    //System.out.println("Distance du point " + vertices.get(i) + " au point " + vertices.get(j) + " = " + (int)weight + " ");
                }

            }
            System.out.println("");
        }

        return g;

    }

}