
package planetwars.strategies;

import planetwars.publicapi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class MyStrategy implements IStrategy {
    private static List<IPlanet> myListOfPlanets;
    private Map<Integer, IPlanet> mappingPlanets = new HashMap<>();
    /**
     * Method where students can observe the state of the system and schedule events to be executed.
     *
     * @param planets          The current state of the system.
     * @param planetOperations Helper methods students can use to interact with the system.
     * @param eventsToExecute  Queue students will add to in order to schedule events.
     */
    @Override
    public void takeTurn(List<IPlanet> planets, IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute) {
        myListOfPlanets = planets;

        // Create the graph and add all vertices as our planets and add all weighted edge in HashMap
        MyGraph planetGraph = createPlanetGraph();

        // Add the key as planet ID and value as planet into a mappingPlanets HashMap
        for (IPlanet planet : planets) {
            mappingPlanets.put(planet.getId(), planet);
        }

        // Get the our planets and store them in Array List
        List<IVisiblePlanet> conqueredVisiblePlanets = new ArrayList<>();
        for (IPlanet planet : planets) {
            if (planet instanceof IVisiblePlanet && ((IVisiblePlanet) planet).getOwner() == Owner.SELF) {
                conqueredVisiblePlanets.add((IVisiblePlanet) planet);
            }
        }

        // Iterate through our conquered planets only
        for (IVisiblePlanet conq : conqueredVisiblePlanets) {

            HashMap<Integer, Integer> neighbours = planetGraph.getNeighbours(conq.getId());

            // Check if our neighbouring planets are neutral planets
            // If yes, send one person to these neutral planets
            boolean isNeighbourNeutral = sendNeighbouringNeutralPlanet(planetOperations, eventsToExecute, mappingPlanets, conq, neighbours);

            // Check if our neighbouring planets are fully conquered
            boolean isAllConquered = checkIfNeighboursConquered(mappingPlanets, neighbours);

            // If our neighbouring planets are conquered, send population to enemy planets among neighbours
            if (isAllConquered == true) {
                if (conq.getPopulation() > 1) {
                    List<IVisiblePlanet> interestedNeighbours = getNeighboursWithAttackingEnemy(mappingPlanets, planetGraph, conq, neighbours);

                    if(conq.getPopulation() > 1) {
                        if(interestedNeighbours.size() > 0){
                            sendReinforcement(planetOperations, eventsToExecute, conq, interestedNeighbours);
                        }
                    }

                    if(interestedNeighbours.size() == 0 && (conq.getPopulation() > 0.9*conq.getSize())){
                        sendReinforcement(planetOperations, eventsToExecute, conq, neighbours);
                    }
                }
            }

            // Attack the strongest(highest habitability) enemy among neighbours
            if (isNeighbourNeutral==false && isAllConquered == false) {
                IVisiblePlanet toAttack = getStrongestEnemy(neighbours);
                eventsToExecute.offer(planetOperations.transferPeople(conq, toAttack, (long) (conq.getPopulation() - 1) / 2));
            }
        }
    }

    // Helper methods
    private IVisiblePlanet getStrongestEnemy(HashMap<Integer, Integer> neighbours) {
        int maxGrowthRateConquerBy = -1;
        IVisiblePlanet toAttack = null;
        for (Integer neighbourId : neighbours.keySet()) {
            IVisiblePlanet neighbourPlanet = (IVisiblePlanet) mappingPlanets.get(neighbourId);
            if (neighbourPlanet.getOwner() != Owner.SELF && neighbourPlanet.getHabitability() > maxGrowthRateConquerBy) {
                toAttack = neighbourPlanet;
                maxGrowthRateConquerBy = neighbourPlanet.getHabitability();
            }
        }
        return toAttack;
    }

    private void sendReinforcement(IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute, IVisiblePlanet conq, List<IVisiblePlanet> interestedNeighbours) {
        for (IVisiblePlanet interestPlanet : interestedNeighbours) {
            eventsToExecute.offer(planetOperations.transferPeople(conq, interestPlanet, (long) (conq.getPopulation() - 1) / interestedNeighbours.size()));
        }
    }

    private void sendReinforcement(IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute, IVisiblePlanet conq, HashMap<Integer, Integer> neighbours) {
        for (Integer neighbourId : neighbours.keySet()) {
            IVisiblePlanet neighbourPlanet = (IVisiblePlanet) mappingPlanets.get(neighbourId);
            eventsToExecute.offer(planetOperations.transferPeople(conq, neighbourPlanet, (long) (conq.getPopulation() - 1) / neighbours.size()));
        }
    }

    private List<IVisiblePlanet> getNeighboursWithAttackingEnemy(Map<Integer, IPlanet> mappingPlanets, MyGraph planetGraph, IVisiblePlanet conq, HashMap<Integer, Integer> neighbours) {
        List<IVisiblePlanet> interestedNeighbours = new ArrayList<>();
        for (Integer neighbourId : neighbours.keySet()) {
            IVisiblePlanet neighbourPlanet = (IVisiblePlanet) mappingPlanets.get(neighbourId);
            HashMap<Integer, Integer> checkPlanets = planetGraph.getNeighbours(conq.getId());
            for(Integer checkPlanetId : checkPlanets.keySet()){
                IVisiblePlanet checkPlanet = (IVisiblePlanet)mappingPlanets.get(checkPlanetId);
                if(checkPlanet.getOwner() == Owner.OPPONENT){
                    interestedNeighbours.add(neighbourPlanet);
                    break;
                }
            }
        }
        return interestedNeighbours;
    }

    private boolean checkIfNeighboursConquered(Map<Integer, IPlanet> mappingPlanets, HashMap<Integer, Integer> neighbours) {
        boolean isAllConquered = true;
        for (Integer neighbourId : neighbours.keySet()) {
            IVisiblePlanet neighbourPlanet = (IVisiblePlanet) mappingPlanets.get(neighbourId);
            if (neighbourPlanet.getOwner() != Owner.SELF) {
                isAllConquered = false;
                break;
            }
        }
        return isAllConquered;
    }

    private boolean sendNeighbouringNeutralPlanet(IPlanetOperations planetOperations, Queue<IEvent> eventsToExecute, Map<Integer, IPlanet> mappingPlanets, IVisiblePlanet conq, HashMap<Integer, Integer> neighbours) {
        boolean isNeighbourNeutral = false;
        long maxPop = conq.getPopulation();
        long curPop = 1;
        for (Integer neighbourId : neighbours.keySet()) {
            if (curPop < maxPop - 1) {
                IVisiblePlanet neighbourPlanet = (IVisiblePlanet) mappingPlanets.get(neighbourId);
                if (neighbourPlanet.getOwner() == Owner.NEUTRAL && conq.getPopulation() > conq.getEdges().size()) {
                    eventsToExecute.offer(planetOperations.transferPeople(conq, neighbourPlanet, 1));
                    isNeighbourNeutral = true;
                    curPop++;
                }
            }
        }
        return isNeighbourNeutral;
    }


    private static MyGraph createPlanetGraph()
    {
        MyGraph graph = new MyGraph();
        // add the planets
        for(IPlanet planet : myListOfPlanets){
            graph.addVertex(planet.getId());
            for(IEdge edge : planet.getEdges()){
                graph.addEdge(edge.getSourcePlanetId(), edge.getDestinationPlanetId(), edge.getLength());
            }
        }
        return graph;
    }


    @Override
    public String getName() {
        return "Chaitanya and Ren Jeik";
    }

    @Override
    public boolean compete() {
        return true;
    }

}
