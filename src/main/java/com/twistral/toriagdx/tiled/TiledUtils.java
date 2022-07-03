package com.twistral.toriagdx.tiled;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;


public class TiledUtils {



    /**
     * You need to run this function once in a method like create() or show().
     * <br><br> Example: TiledUtils.addAnimatedTileToMap(map, 0.5f, "myCoolTileset", "tile-layer", "blueFlowerAnimation", "heyyo");
     * <br><br> IMPORTANT: YOU NEED TO EXECUTE {@link #updateTiledAnimation()} IN YOUR UPDATE METHOD BEFORE YOU DRAW ANYTHING, IF YOU DON'T
     * DO THIS THE ANIMATION WILL NOT BE PLAYED AND YOU WILL BE STUCK ON THE FIRST FRAME ALL THE TIME.
     * @param map Any tiled map
     * @param durationOfEachFrame How much time is one frame going to take in the animation
     * @param tilesetNameWithoutExt What is the name of the tileset that these animation frames are in? (without extension)
     * @param layerNameForAnim The tile layer in your map that will have this animation
     * @param animPropName You need to set a property in Tiled editor to all the animation tiles (name and value of that property
     *                     should be a string) this is the name of that property
     * @param animPropValue This is the value of that property (the property you set on Tiled)
     */
    public static void addAnimatedTileToMap(TiledMap map, float durationOfEachFrame,
                                            String tilesetNameWithoutExt, String layerNameForAnim,
                                            String animPropName, String animPropValue) {
        Array<StaticTiledMapTile> frameTiles = new Array<>();

        Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet(tilesetNameWithoutExt).iterator();
        while (tiles.hasNext()) {
            TiledMapTile tile = tiles.next();
            if (tile.getProperties().containsKey(animPropName) && tile.getProperties().get(animPropName, String.class).equals(animPropValue))
                frameTiles.add((StaticTiledMapTile) tile);
        }

        AnimatedTiledMapTile animatedTile = new AnimatedTiledMapTile(durationOfEachFrame, frameTiles);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layerNameForAnim);

        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell.getTile().getProperties().containsKey(animPropName)
                        && cell.getTile().getProperties().get(animPropName, String.class).equals(animPropValue)) {
                    cell.setTile(animatedTile);
                }
            }
        }
    }



    /**
     * You need to run this function in your update() method before any drawing happens.
     * @see #addAnimatedTileToMap(TiledMap, float, String, String, String, String)
     */
    public static void updateTiledAnimation() {
        AnimatedTiledMapTile.updateAnimationBaseTime();
    }



    /**
     * If map has n tiles horizontally and m tiles vertically, this method returns n.
     * @param map any tiled map
     * @return how many tiles are placed horizontally in the tiled map as an integer
     */
    public static int getHorizontalTileCount(TiledMap map) {
        return map.getProperties().get("width", Integer.class);
    }



    /**
     * If map has n tiles horizontally and m tiles vertically, this method returns m.
     * @param map any tiled map
     * @return how many tiles are placed vertically in the tiled map as an integer
     */
    public static int getVerticalTileCount(TiledMap map) {
        return map.getProperties().get("height", Integer.class);
    }



    /**
     * @param map and tiled map
     * @return How many pixels does one tile take vertically (height of one tile)
     */
    public static int getTileWidth(TiledMap map) {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getTileWidth();
    }



    /**
     * @param map and tiled map
     * @return How many pixels does one tile take horizontally (width of one tile)
     */
    public static int getTileHeight(TiledMap map) {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getTileWidth();
    }



}
