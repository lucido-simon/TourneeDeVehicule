package algorithms.Sweep;

import algorithms.ClarkAndWright.Saving;
import common.Edge.BasicEdge;
import common.Route.Route;
import common.Vertex.BasicVertex;
import common.Vertex.SweepVertex;

import java.util.ArrayList;

public class MyUtils
{

    public static void printSaving(Saving s)
    {
        int from = s.from.index;
        int to = s.to.index;
        System.out.println("Saving - From: " + from + " To: " + to + " Val: " + s.cost);
    }

    public static void printRoutesCities(ArrayList<Route> routes, StringBuilder sb)
    {
        for (Route r : routes)
        {
            printCities(r, sb);
            sb.append("\r\n");
        }
    }

    public static void printNames(ArrayList<Route> routes, BasicVertex[] names, StringBuilder sb)
    {
        int totalCost = 0;
        for (Route r : routes)
        {
            printNames(r, names, sb);
            sb.append("\r\n");
            totalCost += r.totalCost;
        }
        sb.append("TOTAL COST OF THE ROUTES: " + totalCost);
    }

    /**
     * Prints city addresses from one route
     *
     * @param r
     * @param names
     */
    public static void printNames(Route r, BasicVertex[] names, StringBuilder sb)
    {
        sb.append(names[0].name);
        BasicEdge edge = r.outEdges[0];
        sb.append(" -> " + names[edge.v2.index].name);
        do
        {
            edge = r.outEdges[edge.v2.index];
            sb.append(" -> " + names[edge.v2.index].name);
        } while (edge.v2.index != 0);
    }


    public static void printRoute(Route r)
    {
        System.out.print("Route: ");
        BasicEdge edge = r.outEdges[0];

        System.out.print("(" + edge.v1.index + "->" + edge.v2.index + ")");

        do
        {
            edge = r.outEdges[edge.v2.index];
            System.out.print("(" + edge.v1.index + "->" + edge.v2.index + ")");
        } while (edge.v2.index != 0);


        System.out.print(" Amount: " + r.currentWeight + " Cost: " + r.totalCost);

        System.out.println();
    }

    /**
     * Prints cities from one route
     *
     * @param r
     */
    public static void printCities(Route r, StringBuilder sb)
    {
        sb.append(0 + " ");
        BasicEdge edge = r.outEdges[0];
        sb.append(edge.v2.index + " ");
        do
        {
            edge = r.outEdges[edge.v2.index];
            sb.append(edge.v2.index + " ");
        } while (edge.v2.index != 0);
    }


    /**
     * Prints edges from all routes
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
