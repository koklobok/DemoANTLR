package com.koklobok.views;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * Created by krunch on 4/29/2017.
 */
public abstract class ResultCircle extends Circle {

    public ResultCircle(int textLineNumber, Color colorCenter, Color colorEnd) {
        double centerY = 17.0 * textLineNumber + (textLineNumber - 1) * 3;
        RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 0.7, true
                , CycleMethod.NO_CYCLE, new Stop(0.0, colorCenter), new Stop(1.0, colorEnd));

        setCenterX(8.0);
        setCenterY(centerY);
        setRadius(8.0);
        setFill(gradient);
        setStrokeType(StrokeType.INSIDE);
        setSmooth(true);
        setStrokeWidth(0.0);
    }

}
