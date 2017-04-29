package com.koklobok.views;

import griffon.javafx.formatters.ColorFormatter;
import javafx.scene.paint.Color;

/**
 * Created by krunch on 4/29/2017.
 */
public class FalseCircle extends ResultCircle {

    private static final Color COLOR_CENTER = ColorFormatter.parseColor("#e53939");
    private static final Color COLOR_END = ColorFormatter.parseColor("#dbb3b1");

    public FalseCircle(int textLineNumber) {
        super(textLineNumber, COLOR_CENTER, COLOR_END);
    }
}
