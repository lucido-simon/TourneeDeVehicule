/*
 * (C) Copyright 2003-2020, by Barak Naveh and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * See the CONTRIBUTORS.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the
 * GNU Lesser General Public License v2.1 or later
 * which is available at
 * http://www.gnu.org/licenses/old-licenses/lgpl-2.1-standalone.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR LGPL-2.1-or-later
 */

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


/**
 * A simple introduction to using JGraphT.
 *
 * @author Barak Naveh
 */
public final class HelloJGraphT
{
    private HelloJGraphT()
    {
    } // ensure non-instantiability.


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


    private static Graph<String, DefaultEdge> createStringGraph()
    {
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
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