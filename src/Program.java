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
        /*
        Pour lancer le programme avec des arguments : Run -> Edit Configurations -> case "Program Arguments"
        Le format est le suivant : "arg1 arg2"
        Exemple : "chemin.dot Sweep"
        */

        // Vérifie qu'il y ait bien 2 arguments (ni plus, ni moins) et envoie un message d'erreur si ce n'est pas le cas.
        if (args.length != 2){
            System.out.println("Le nombre d'arguments est incorrect. Veuillez entrer le chemin du fichier d'extension \"dot\" contenant le graphe ainsi que la méthode voulant être utilisée (CW, BB où Sweep).");
        }

        // S'il y a bien 2 arguments
        else {
            switch (args[1]){
                case "CW" :
                    Graph<BasicVertex, BasicEdge> weightedBasicGraph = new SimpleWeightedGraph<>(BasicEdge.class);
                    LoadFromFile.importFromFile(args[0], weightedBasicGraph);
                    ClarkAndWright.loadDataBasic(weightedBasicGraph);
                    System.out.println(ClarkAndWright.clarkWright());
                    break;
                case "BB" :
                    Graph<BasicVertex, BasicEdge> basicGraph = new SimpleWeightedGraph<>(BasicEdge.class);
                    LoadFromFile.importFromFile(args[0], basicGraph);
                    BranchAndBound.loadDataBasic(basicGraph);
                    BranchAndBound.BranchBound();
                    break;
                case "Sweep" :
                    Graph<SweepVertex, SweepEdge> sweepGraph = new DefaultUndirectedWeightedGraph<>(SweepEdge.class);
                    LoadFromFile.importFromFileSweep(args[0], sweepGraph);
                    Sweep.loadData(sweepGraph);
                    System.out.println(Sweep.sweep());
                    break;
                default :
                    System.out.println("La méthode choisie (" + args[1] + ") n'a pas été reconnue.");
            }
        }


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
                }

            }
            System.out.println("");
        }

        return g;

    }

}