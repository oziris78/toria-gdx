package com.twistral.toriagdx;

import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ToriaGDX {

    /*  IMAGES  */

    public static TextureRegion getRepeatedTexture(Texture texture, int row, int col) {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0, 0, texture.getWidth() * row, texture.getHeight() * col);
        return textureRegion;
    }


    public static Vector2 imageDimensions(FileHandle file) {
        Vector2 vector = new Vector2();
        Pixmap pixmap = new Pixmap(file);
        vector.x = pixmap.getWidth();
        vector.y = pixmap.getHeight();
        if (file.name().matches("(?i).*\\.9\\.png$")) {
            vector.x = MathUtils.clamp(vector.x - 2, 0.0f, vector.x);
            vector.y = MathUtils.clamp(vector.y - 2, 0.0f, vector.y);
        }
        pixmap.dispose();
        return vector;
    }


    /*  FILES  */

    public static void openFileExplorer(FileHandle startDirectory) throws IOException {
        if (startDirectory.exists()) {
            File file = startDirectory.file();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } else {
            throw new IOException("Directory doesn't exist: " + startDirectory.path());
        }
    }

    public static void writeWarningsToFile(Array<String> warnings, FileHandle file) {
        for (String warn : warnings)
            file.writeString(warn.replaceAll("(?<!\\[)\\[(?!\\[).*?\\]", "") + "\n", true);
    }



    /*  FONTS  */

    public static boolean fontHasAllChars(BitmapFont.BitmapFontData bitmapFontData, String text) {
        for (int i = 0; i < text.length(); i++) {
            if (bitmapFontData.getGlyph(text.charAt(i)) == null) return false;
        }
        return true;
    }


    /*  COLORS  */

    public static float brightness(Color color) {
        return (float) (Math.sqrt(0.299f * Math.pow(color.r, 2) + 0.587f * Math.pow(color.g, 2) + 0.114f * Math.pow(color.b, 2)));
    }

    public static Color inverseColor(Color color) {
        return new Color(1f - color.r, 1f - color.g, 1f - color.b, color.a);
    }


    /*  LOGIC  */

    public static boolean twoSidedEquals(Object obj1, Object obj2, Object val1, Object val2) {
        return (obj1.equals(val1) && obj2.equals(val2)) || (obj1.equals(val2) && obj2.equals(val1));
    }



    /*  OS STUFF  */

    public static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    public static boolean isLinux() {
        return System.getProperty("os.name").startsWith("Linux");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").startsWith("Mac");
    }




}
