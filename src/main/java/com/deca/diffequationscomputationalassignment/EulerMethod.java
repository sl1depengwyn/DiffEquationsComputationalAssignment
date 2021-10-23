package com.deca.diffequationscomputationalassignment;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.*;

public class EulerMethod extends NumericalSolution {

    double lastY;
    double lastX;

    public EulerMethod(double x0, double X, double y0, int N) {
        super(x0, X, y0, N);
        color = Color.RED;
    }

    public double f(double x, double y) {
        if (x == 0) {
            x += h;
        }
        return (y * y - y) / x;
    }

    public List<XYChart.Series<Number, Number>> getSolution() {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(x0, y0));
        lastX = x0;
        lastY = y0;

        for (double x = x0 + h; x < X + h; x += h) {
            double newY = lastY + h * f(lastX, lastY);
//            if (!Double.isFinite(newY)) {
//                break;
//            }
            // System.out.printf("x=%f y=%f\n", x, newY);
            series.getData().add(new XYChart.Data<>(x, newY));
            lastX = x;
            lastY = newY;
        }

        List<XYChart.Series<Number, Number>> listOfSeries = new ArrayList<>();
        listOfSeries.add(series);

        return listOfSeries;
    }

}

