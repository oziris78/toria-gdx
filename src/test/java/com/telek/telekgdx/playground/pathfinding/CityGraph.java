package com.telek.telekgdx.playground.pathfinding;


import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


public class CityGraph implements IndexedGraph<City> {

    CityHeuristic cityHeuristic = new CityHeuristic();
    Array<City> cities = new Array<>();
    Array<Street> streets = new Array<>();

    /**
     * Map of Cities to Streets starting in that City.
     */
    ObjectMap<City, Array<Connection<City>>> streetsMap = new ObjectMap<>();

    private int lastNodeIndex = 0;

    public void addCity(City city) {
        city.index = lastNodeIndex;
        lastNodeIndex++;

        cities.add(city);
    }

    public void connectCities(City fromCity, City toCity) {
        Street street = new Street(fromCity, toCity);
        if (!streetsMap.containsKey(fromCity)) {
            streetsMap.put(fromCity, new Array<Connection<City>>());
        }
        streetsMap.get(fromCity).add(street);
        streets.add(street);
    }

    public GraphPath<City> findPath(City startCity, City goalCity) {
        GraphPath<City> cityPath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<>(this).searchNodePath(startCity, goalCity, cityHeuristic, cityPath);
        return cityPath;
    }

    @Override
    public int getIndex(City node) {
        return node.index;
    }

    @Override
    public int getNodeCount() {
        return lastNodeIndex;
    }

    @Override
    public Array<Connection<City>> getConnections(City fromNode) {
        if (streetsMap.containsKey(fromNode)) {
            return streetsMap.get(fromNode);
        }

        return new Array<>(0);
    }
}