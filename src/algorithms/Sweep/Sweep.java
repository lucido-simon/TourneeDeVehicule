package algorithms.Sweep;

import common.Edge.BasicEdge;
import common.Edge.SweepEdge;
import common.Vertex.SweepVertex;
import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sweep
{
    public static int CAR_LIMIT = 40;
    public static double[][] distances;
    private static SweepVertex[] vertices;

    public static void loadData(Graph<SweepVertex, SweepEdge> g)
    {
        ArrayList<SweepVertex> al = new ArrayList<>(g.vertexSet());
        vertices = al.toArray(new SweepVertex[g.vertexSet().size()]);

        ArrayList<SweepEdge> edges = new ArrayList<>(g.edgeSet());


        distances = new double[vertices.length][vertices.length];

        for ( SweepEdge be : edges )
        {
            distances[be.v1.index][be.v2.index] = be.cost;
            System.out.println(be.cost);
        }
    }


    public static String sweep()
    {
        ArrayList<SweepVertex> verticesWithoutDepot = new ArrayList<SweepVertex>(Arrays.asList(Arrays.copyOfRange(vertices, 1, vertices.length)));
        Collections.sort(verticesWithoutDepot);

        //Cluster
        Cluster actualCluster = new Cluster();

        ArrayList<Cluster> clusters = new ArrayList<>();

        // ajoute 0 au cluster
        actualCluster.add(vertices[0]);
        for (int i = 0; i < verticesWithoutDepot.size(); i++)
        {
            SweepVertex n = verticesWithoutDepot.get(i);

            // si la capacité est dépassée on fait un nouveau cluster
            if (actualCluster.weight + n.weight > CAR_LIMIT)
            {
                clusters.add(actualCluster);
                actualCluster = new Cluster();
                // ajoute le dépôt à chaque cluster
                actualCluster.add(vertices[0]);
            }

            // ajoute le nœud au cluster
            // ajoute tous les bords que ya avec des nœuds qui sont déjà dans le cluster
            actualCluster.add(n);
            for (int j = 0; j < actualCluster.nodes.size(); j++)
            {
                SweepVertex nIn = actualCluster.nodes.get(j);
                SweepEdge e = new SweepEdge(nIn, n, distances[nIn.index][n.index]);

                SweepEdge eReverse = new SweepEdge(n, nIn, distances[n.index][nIn.index]);

                actualCluster.edges.add(e);
                actualCluster.edges.add(eReverse);
            }

            //dans le cas du dernier élément, je dois ajouter un cluster.
            if (i == verticesWithoutDepot.size() - 1)
            {
                clusters.add(actualCluster);
            }
        }

        int totalCost = 0;
        int clusterCount = clusters.size();

        StringBuilder sb = new StringBuilder();
        sb.append(clusterCount + "\r\n");

        for (int i = 0; i < clusterCount; i++)
        {
            //System.out.println("Cluster: " + clusters.get(i).weight);
            clusters.get(i).mst();
            //clusters.get(i).printMST();
            clusters.get(i).dfsONMST();
            clusters.get(i).printTSP(sb);
            sb.append("");
            sb.append("\r\n");
            totalCost += compClusterCost(clusters.get(i));
        }

        for (int i = 0; i < clusterCount; i++)
        {
            clusters.get(i).printTSPAdds(sb);
            sb.append("\r\n");
        }
        sb.append("TOTAL COST OF THE ROUTES:" + totalCost);
        return sb.toString();
    }

    public static int compClusterCost(Cluster cl)
    {
        int cost = 0;
        for (int i = 0; i < cl.tsp.size() - 1; i++)
        {
            SweepVertex n = cl.tsp.get(i);
            SweepVertex n1 = cl.tsp.get(i + 1);

            cost += distances[n.index][n1.index];
        }
        return cost;
    }


}
