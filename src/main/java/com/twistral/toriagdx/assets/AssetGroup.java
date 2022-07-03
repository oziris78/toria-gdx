package com.twistral.toriagdx.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

import java.util.Collection;
import java.util.HashMap;


final class AssetGroup {

    private HashMap<String, AssetDescriptor> descriptors; // asset keys and assets
    private boolean isQueued;


    public AssetGroup() {
        this.descriptors = new HashMap<>();
        this.isQueued = false;
    }


    public void addAsset(String assetKey, AssetDescriptor assetDescriptor) {
        if (this.descriptors.containsKey(assetKey))
            throw new RuntimeException("Invalid key: " + assetKey);
        if (assetDescriptor == null)
            throw new RuntimeException("The assetDescriptor is null for this key: " + assetKey);
        this.descriptors.put(assetKey, assetDescriptor);
    }


    public AssetDescriptor getAsset(String key) {
        AssetDescriptor temp = this.descriptors.get(key);
        if (temp == null)
            throw new RuntimeException("An asset for this key doesn't exist, key: " + key);
        return temp;
    }


    public void queue(AssetManager assetManager) {
        this.isQueued = true;
        Collection<AssetDescriptor> assets = this.descriptors.values();
        for (AssetDescriptor asset : assets)
            assetManager.load(asset);
    }



    public boolean isQueued() {return this.isQueued;}


}
