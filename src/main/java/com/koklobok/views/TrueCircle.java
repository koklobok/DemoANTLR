package com.koklobok.views;

import griffon.javafx.formatters.ColorFormatter;
import javafx.scene.paint.Color;

/**
 * Created by krunch on 4/29/2017.
 */
public class TrueCircle extends ResultCircle {

    private static final Color COLOR_CENTER = ColorFormatter.parseColor("#33c915");
    private static final Color COLOR_END = ColorFormatter.parseColor("#20ee3b");

    public TrueCircle(int textLineNumber) {
        super(textLineNumber, COLOR_CENTER, COLOR_END);
    }
}
