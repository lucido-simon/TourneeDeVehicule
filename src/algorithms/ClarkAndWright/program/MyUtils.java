package algorithms.ClarkAndWright.program;

import algorithms.ClarkAndWright.model.*;
import common.Edge.*;
import common.Vertex.*;

import java.util.ArrayList;

public class MyUtils
{

    public static void printSaving(Saving s)
    {
        int from = s.from.index;
        int to = s.to.index;
        System.out.println("Saving - From: " + from + " To: " + to + " Val: " + s.val);
    }

    public static void printRoutesCities(ArrayList<Route> routes, StringBuilder sb)
    {
        for (Route r : routes)
        {
            printCities(r, sb);
            sb.append("\r\n");
        }
    }

    public static void printAdds(ArrayList<Route> routes, BasicVertex[] adds, StringBuilder sb)
    {
        int totalCost = 0;
        for (Route r : routes)
        {
            printAdds(r, adds, sb);
            sb.append("\r\n");
            totalCost += r.totalCost;
        }
        sb.append("TOTAL COST OF THE ROUTES: " + totalCost);
    }

    public static void printRoute(Route r)
    {
        System.out.print("Route: ");
        BasicEdge edge = r.outEdges[0];

        System.out.print("(" + edge.n1.index + "->" + edge.n2.index + ")");

        do
        {
            edge = r.outEdges[edge.n2.index];
            System.out.print("(" + edge.n1.index + "->" + edge.n2.index + ")");
        } while (edge.n2.index != 0);


        System.out.print(" Amount: " + r.actual + " Cost: " + r.totalCost);

        System.out.println("");
    }

    /**
     * Vytiskne mesta z jedne cesty
     *
     * @param r
     */
    public static void printCities(Route r, StringBuilder sb)
    {
        sb.append(0 + " ");
        BasicEdge edge = r.outEdges[0];
        sb.append(edge.n2.index + " ");
        do
        {
            edge = r.outEdges[edge.n2.index];
            sb.append(edge.n2.index + " ");
        } while (edge.n2.index != 0);
    }

    /**
     * Vytiskne adresy mest z jedne cesty
     *
     * @param r
     * @param adds
     */
    public static void printAdds(Route r, BasicVertex[] adds, StringBuilder sb)
    {
        sb.append(adds[0].add);
        BasicEdge edge = r.outEdges[0];
        sb.append(" -> " + adds[edge.n2.index].add);
        do
        {
            edge = r.outEdges[edge.n2.index];
            sb.append(" -> " + adds[edge.n2.index].add);
        } while (edge.n2.index != 0);
    }

    /**
     * Vytiskne hrany ze vsech cest
     *
     * @param routes
     */
    public static void printRoutes(ArrayList<Route> routes)
    {
        int totalCost = 0;
        for (Route r : routes)
        {
            printRoute(r);
            totalCost += r.totalCost;
        }

        System.out.println("Total cost of the routes: " + totalCost);
    }
}
