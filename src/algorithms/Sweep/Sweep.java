package algorithms.Sweep;

import common.Edge.BasicEdge;
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

    public static void loadData(Graph<SweepVertex, BasicEdge<SweepVertex>> g)
    {
        ArrayList<SweepVertex> al = new ArrayList<>(g.vertexSet());
        vertices = al.toArray(new SweepVertex[g.vertexSet().size()]);

        ArrayList<BasicEdge<SweepVertex>> edges = new ArrayList<>(g.edgeSet());


        distances = new double[vertices.length][vertices.length];

        for ( BasicEdge be : edges )
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

        ArrayList<Cluster> clusters = new ArrayList<Cluster>();

        //pridam 0 do clusteru
        actualCluster.add(vertices[0]);
        for (int i = 0; i < verticesWithoutDepot.size(); i++)
        {
            SweepVertex n = verticesWithoutDepot.get(i);

            //pokud by byla prekrocena kapacita vytvorim novy cluster
            if (actualCluster.weight + n.weight > CAR_LIMIT)
            {
                clusters.add(actualCluster);
                actualCluster = new Cluster();
                //pridam depot uzel do kazdeho clusteru
                actualCluster.add(vertices[0]);
            }

            //pridam uzel do clusteru
            //pridam vsechny hrany ktere inciduji s uzly ktere jiz jsou v clusteru
            actualCluster.add(n);
            for (int j = 0; j < actualCluster.nodes.size(); j++)
            {
                SweepVertex nIn = actualCluster.nodes.get(j);
                BasicEdge<SweepVertex> e = new BasicEdge<>(nIn, n, distances[nIn.index][n.index]);

                BasicEdge<SweepVertex> eReverse = new BasicEdge<>(n, nIn, distances[n.index][nIn.index]);

                actualCluster.edges.add(e);
                actualCluster.edges.add(eReverse);
            }

            //v pripade posledni polozky musim pridat i cluster.
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
