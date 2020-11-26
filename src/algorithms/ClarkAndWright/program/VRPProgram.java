package algorithms.ClarkAndWright.program;

import java.util.ArrayList;
import java.util.Collections;

import algorithms.ClarkAndWright.model.Route;
import algorithms.ClarkAndWright.model.Saving;
import common.Edge.BasicEdge;
import common.Vertex.BasicVertex;
import org.jgrapht.Graph;


public class VRPProgram
{

    public static int CAR_LIMIT = 10;
    private static double[][] savings;
    public static double[][] distances;
    private static BasicVertex[] vertices;
    private static ArrayList<Route> routes;
    private static int nCount;

    public static void loadDataBasic(Graph<BasicVertex, BasicEdge> g)
    {
        ArrayList<BasicVertex> vertices = new ArrayList<>(g.vertexSet());
        VRPProgram.vertices = vertices.toArray(new BasicVertex[g.vertexSet().size()]);

        ArrayList<BasicEdge> edges = new ArrayList<>(g.edgeSet());


        nCount = vertices.size();

        distances = new double[nCount][nCount];

        for ( BasicEdge be : edges )
        {
            distances[be.n1.index][be.n2.index] = be.val;
            System.out.println(be.val);
        }




    }

    public static String clarkWright()
    {
        routes = new ArrayList<>();

        //I create N vertices. Each node will be inserted into a route.
        //each route will contain 2 edges - from the depo to the edge and back
        for (int i = 0; i < nCount; i++)
        {

            BasicVertex n = vertices[i];


            if (i != 0)
            {
                //creating the two edges
                BasicEdge e = new BasicEdge(vertices[0], n, distances[0][n.index]);
                BasicEdge e2 = new BasicEdge(n, vertices[0], distances[0][n.index]);

                Route r = new Route(nCount);
                r.allowed = CAR_LIMIT;
                r.add(e);
                r.add(e2);
                r.actual += n.amount;

                routes.add(r);
            }
        }


        MyUtils.printRoutes(routes);
        //Computing the savings - the values which made be saved by optimization
        ArrayList<Saving> sList = computeSaving(distances, nCount, savings, vertices);
        //sorting the savings
        Collections.sort(sList);

        //and use the savings until the list is not empty
        while (!sList.isEmpty())
        {
            Saving actualS = sList.get(0);

            BasicVertex n1 = actualS.from;
            BasicVertex n2 = actualS.to;

            Route r1 = n1.route;
            Route r2 = n2.route;

            int from = n1.index;
            int to = n2.index;

            //MyUtils.printSaving(actualS);

            if (actualS.val > 0 && r1.actual + r2.actual < r1.allowed && !r1.equals(r2))
            {

                //moznozt jedna z uzlu do kteryho se de se de do cile

                BasicEdge outgoingR2 = r2.outEdges[to];
                BasicEdge incommingR1 = r1.inEdges[from];


                if (outgoingR2 != null && incommingR1 != null)
                {
                    boolean succ = r1.merge(r2, new BasicEdge(n1, n2, distances[n1.index][n2.index]));
                    if (succ)
                    {
                        routes.remove(r2);
                    }
                }

                else
                {
                    System.out.println("Problem");
                }

            }

            sList.remove(0);
            //MyUtils.printRoutes(routes);

        }
        StringBuilder sb = new StringBuilder();
        sb.append(routes.size() + "\r\n");


        MyUtils.printRoutesCities(routes, sb);
        MyUtils.printAdds(routes, vertices, sb);
        return sb.toString();
    }


    /**
     * Computation of savings. The value which could be saved if we would not return to the depo, but instead pass directly from one node to other.
     *
     * @param dist
     * @param n
     * @param sav
     * @param nodesField
     * @return
     */
    public static ArrayList<Saving> computeSaving(double[][] dist, int n, double[][] sav, BasicVertex[] nodesField)
    {
        sav = new double[n][n];
        ArrayList<Saving> sList = new ArrayList<Saving>();
        for (int i = 1; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                sav[i][j] = dist[0][i] + dist[j][0] - dist[i][j];
                BasicVertex n1 = nodesField[i];
                BasicVertex n2 = nodesField[j];
                Saving s = new Saving(sav[i][j], n1, n2);
                sList.add(s);
            }
        }
        return sList;
    }
}
