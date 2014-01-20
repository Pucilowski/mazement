package com.pucilowski.navigation.logic.grid.misc;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 28/11/13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class Lerp {
    final float[] range;

    public Lerp(float[] range) {
        this.range = range;
    }

    public float interpolate(float ratio) {
        if (ratio == 1) return range[range.length - 1];

        int intervals = range.length - 1;

        int a = (int) Math.floor((float) (range.length - 1) * ratio);
        int b = a + 1;
        float intervalSize = 1f / (float) intervals;
        float newr = (ratio - a * intervalSize) / intervalSize;

        return range[a] + (range[b] - range[a]) * newr;
    }

    static final Color VIOLET = new Color(143, 0, 255);
    static final Color INDIGO = new Color(75, 0, 130);
    static final Color ORANGE = new Color(255, 165, 130);

    public static final Lerp[] rainbow = new Lerp[3];

    static {
        Color[] range = {
                VIOLET,
                INDIGO,
                Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                ORANGE,
                Color.RED,
        };

        float[][] colors = new float[3][range.length];

        for (int r = 0; r < range.length; r++) {
            float[] rgb = range[r].getRGBColorComponents(null);

            for (int c = 0; c < 3; c++) {
                colors[c][r] = rgb[c];
            }
        }

        for (int c = 0; c < 3; c++) {
            rainbow[c] = new Lerp(colors[c]);
        }
    }
}