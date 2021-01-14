package common.GraphReader;

import common.Edge.BasicEdge;
import common.Edge.SweepEdge;
import common.Vertex.BasicVertex;
import common.Vertex.SweepVertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFromFile
{
    public static void importFromFile(String filepath, Graph<BasicVertex, BasicEdge> g)
    {

        try
        {
            File f = new File(filepath);
            Scanner scan = new Scanner(f);

            ArrayList<BasicVertex> vertices = new ArrayList<>();


            int index = 0;

            while( scan.hasNextLine() )
            {
                String nextLine = scan.nextLine();
                String avantBracket = null;
                String apresBracket = null;


                if ( nextLine.contains("{") || nextLine.contains("}") || nextLine.length() < 3)
                    continue;

                if ( nextLine.contains("["))
                {
                    avantBracket = nextLine.substring(0, nextLine.indexOf("["));
                    apresBracket = nextLine.substring(nextLine.indexOf("["));
                }

                if ( !avantBracket.contains("->"))
                {
                    String nom;
                    String cout;

                    nom = avantBracket;
                    nom = nom.replaceAll("\\s","");


                    if ( apresBracket.contains("xpos"))
                    {
                        String guillemet = apresBracket.substring(apresBracket.indexOf("cost=") + 6);
                        cout = guillemet.substring(0, guillemet.indexOf("\""));
                    }

                    else cout = apresBracket.substring(apresBracket.indexOf("cost=") + 6, apresBracket.length() - 3 );


                    BasicVertex bv = new BasicVertex(index, nom, Integer.parseInt(cout));


                    vertices.add(bv);
                    g.addVertex(bv);

                }

                else if ( avantBracket.contains("->"))
                {
                    String vertexSourceString;
                    String vertexTargetString;

                    vertexSourceString = avantBracket.substring(0, avantBracket.indexOf("->"));
                    vertexTargetString = avantBracket.substring(avantBracket.indexOf("->") + 2);

                    vertexSourceString = vertexSourceString.replaceAll("\\s","");
                    vertexTargetString = vertexTargetString.replaceAll("\\s","");

                    BasicVertex source = null;
                    BasicVertex target = null;

                    for (BasicVertex b: vertices )
                    {
                        if ( b.name.equals(vertexSourceString) )
                            source = b;
                        if ( b.name.equals(vertexTargetString) )
                            target = b;
                    }

                    if ( source == null || target == null )
                    {
                        System.out.println("Vertex inconnu :" + vertexSourceString + "->" + vertexTargetString + source + " " + target);
                    }

                    String poids;

                    poids = apresBracket.substring(apresBracket.indexOf("weight=") + 8, apresBracket.length() - 3);

                    BasicEdge be = new BasicEdge<>(source, target, Double.parseDouble(poids));
                    g.addEdge(source, target, be);
                    g.setEdgeWeight(source, target, Double.parseDouble(poids));

                }

                index++;
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("Erreur lors de la lecture du fichier");
            e.printStackTrace();
        }



    }

    public static void importFromFileSweep(String filepath, Graph<SweepVertex, SweepEdge> g)
    {

        try
        {
            File f = new File(filepath);
            Scanner scan = new Scanner(f);
            SweepVertex depot = null;

            ArrayList<SweepVertex> vertices = new ArrayList<>();


            int index = 0;

            while( scan.hasNextLine() )
            {
                String nextLine = scan.nextLine();
                String avantBracket = null;
                String apresBracket = null;


                if ( nextLine.contains("{") || nextLine.contains("}") || nextLine.length() < 3)
                    continue;

                if ( nextLine.contains("["))
                {
                    avantBracket = nextLine.substring(0, nextLine.indexOf("["));
                    apresBracket = nextLine.substring(nextLine.indexOf("["));
                }

                if ( !avantBracket.contains("->"))
                {
                    if ( !apresBracket.contains("xpos") || !apresBracket.contains("ypos"))
                    {
                        System.out.println(avantBracket + apresBracket + " ne contient pas de xpos ou ypos");
                        continue;
                    }


                    String nom;
                    String cout;
                    String x;
                    String y;


                    nom = avantBracket;
                    nom = nom.replaceAll("\\s","");

                    String guillemet = apresBracket.substring(apresBracket.indexOf("cost=") + 6);
                    cout = guillemet.substring(0, guillemet.indexOf("\""));

                    guillemet = apresBracket.substring(apresBracket.indexOf("xpos=") + 6);
                    x = guillemet.substring(0, guillemet.indexOf("\""));

                    guillemet = apresBracket.substring(apresBracket.indexOf("xpos=") + 6);
                    y = guillemet.substring(0, guillemet.indexOf("\""));

                    SweepVertex bv = new SweepVertex(index, nom, Integer.parseInt(cout), Double.parseDouble(x), Double.parseDouble(y), depot);
                    if ( depot == null ) depot = bv;

                    //System.out.println("nom:" + bv.name );

                    vertices.add(bv);
                    g.addVertex(bv);

                }

                else if ( avantBracket.contains("->"))
                {
                    String vertexSourceString;
                    String vertexTargetString;

                    vertexSourceString = avantBracket.substring(0, avantBracket.indexOf("->"));
                    vertexTargetString = avantBracket.substring(avantBracket.indexOf("->") + 2);

                    vertexSourceString = vertexSourceString.replaceAll("\\s","");
                    vertexTargetString = vertexTargetString.replaceAll("\\s","");

                    SweepVertex source = null;
                    SweepVertex target = null;

                    for (SweepVertex b: vertices )
                    {
                        if ( b.name.equals(vertexSourceString) )
                            source = b;
                        if ( b.name.equals(vertexTargetString) )
                            target = b;
                    }

                    if ( source == null || target == null )
                    {
                        System.out.println("Vertex inconnu :" + vertexSourceString + "->" + vertexTargetString + source + " " + target);
                    }

                    String poids;

                    poids = apresBracket.substring(apresBracket.indexOf("weight=") + 8, apresBracket.length() - 3);

                    SweepEdge be = new SweepEdge(source, target, Double.parseDouble(poids));
                    SweepEdge beReversed = new SweepEdge(target, source, Double.parseDouble(poids));
                    g.addEdge(source, target, be);
                    if ( ! g.addEdge(target, source, beReversed ))
                    {
                       // System.out.println(source + " " + target);
                    }
                    g.setEdgeWeight(source, target, Double.parseDouble(poids));
                    g.setEdgeWeight(target, source, Double.parseDouble(poids));

                }

                index++;
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("Erreur lors de la lecture du fichier");
            e.printStackTrace();
        }



    }
}
