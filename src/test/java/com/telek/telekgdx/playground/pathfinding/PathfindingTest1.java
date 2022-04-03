package com.telek.telekgdx.playground.pathfinding;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class PathfindingTest1 extends ApplicationAdapter {

    // https://happycoding.io/tutorials/libgdx/pathfinding

    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    BitmapFont font;

    CityGraph cityGraph;
    GraphPath<City> cityPath;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();

        cityGraph = new CityGraph();

        City startCity = new City(300, 250, "S");
        City bCity = new City(300, 350, "B");
        City aCity = new City(200, 350, "A");
        City cCity = new City(400, 350, "C");
        City dCity = new City(200, 250, "D");
        City fCity = new City(100, 250, "F");
        City eCity = new City(400, 250, "E");
        City hCity = new City(300, 150, "H");
        City gCity = new City(200, 150, "G");
        City iCity = new City(200, 50, "I");
        City jCity = new City(300, 50, "J");
        City kCity = new City(400, 50, "K");
        City goalCity = new City(400, 150, "Z");

        cityGraph.addCity(startCity);
        cityGraph.addCity(bCity);
        cityGraph.addCity(aCity);
        cityGraph.addCity(cCity);
        cityGraph.addCity(dCity);
        cityGraph.addCity(fCity);
        cityGraph.addCity(eCity);
        cityGraph.addCity(hCity);
        cityGraph.addCity(gCity);
        cityGraph.addCity(iCity);
        cityGraph.addCity(jCity);
        cityGraph.addCity(kCity);
        cityGraph.addCity(goalCity);

        cityGraph.connectCities(startCity, bCity);
        cityGraph.connectCities(bCity, aCity);
        cityGraph.connectCities(bCity, cCity);
        cityGraph.connectCities(startCity, dCity);
        cityGraph.connectCities(dCity, fCity);
        cityGraph.connectCities(startCity, hCity);
        cityGraph.connectCities(hCity, gCity);
        cityGraph.connectCities(gCity, iCity);
        cityGraph.connectCities(iCity, jCity);
        cityGraph.connectCities(jCity, kCity);
        cityGraph.connectCities(kCity, goalCity);
        cityGraph.connectCities(eCity, goalCity);
        // comment below to see the change
        cityGraph.connectCities(startCity, eCity);

        cityPath = cityGraph.findPath(startCity, goalCity);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Street street : cityGraph.streets) {
            street.render(shapeRenderer);
        }

        // Draw all cities blue
        for (City city : cityGraph.cities) {
            city.render(shapeRenderer, batch, font, false);
        }

        // Draw cities in path green
        for (City city : cityPath) {
            city.render(shapeRenderer, batch, font, true);
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}