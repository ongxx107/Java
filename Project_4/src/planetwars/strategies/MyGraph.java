package planetwars.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyGraph {
    private List<Integer> vertex ;
    private HashMap<Integer, HashMap<Integer, Integer>> edges;

    // Initialize the vertex in ArrayList and edges in HashMap
    MyGraph(){
        vertex = new ArrayList<>();
        edges = new HashMap<Integer, HashMap<Integer, Integer>>();
    }

    // Add the edge from starting planet to destination planet with given length into HashMap
    public void addEdge(Integer srcPlanet, Integer destPlanet, Integer weight){
        HashMap<Integer, Integer> edgeMap = edges.get(srcPlanet);

        if(edgeMap == null)
            edgeMap = new HashMap<>();
        edgeMap.put(destPlanet, weight);
        edges.put(srcPlanet, edgeMap);
    }

    // Add the planet into vertex List
    public void addVertex(Integer id){
        if(vertex.contains(id)==false)
            vertex.add(id);
    }

    // Get the neighbouring planets by using the connected edges
    public HashMap<Integer, Integer> getNeighbours(Integer srcPlanetId){
        return edges.get(srcPlanetId);
    }
}

