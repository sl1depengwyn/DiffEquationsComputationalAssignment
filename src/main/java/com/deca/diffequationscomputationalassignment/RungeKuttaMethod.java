package com.deca.diffequationscomputationalassignment;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class RungeKuttaMethod extends NumericalSolution {
    double lastY;
    double lastX;
    double k1;
    double k2;
    double k3;
    double k4;

    public RungeKuttaMethod(double x0, double X, double y0, int N) {
        super(x0, X, y0, N);
        color = Color.BLUE;
    }

    public double f(double x, double y) {
        return (y * y - y) / x;
    }

    @Override
    public List<XYChart.Series<Number, Number>> getSolution() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(x0, y0));
        lastX = x0;
        lastY = y0;

        for (double x = x0 + h; x < X + h; x += h) {
            k1 = h * f(lastX, lastY);
            k2 = h * f(lastX + h / 2, lastY + k1 / 2);
            k3 = h * f(lastX + h / 2, lastY + k2 / 2);
            k4 = h * f(lastX + h, lastY + k3);
            double newY = lastY + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
            if (!Double.isFinite(newY)) {
                break;
            }
            series.getData().add(new XYChart.Data<>(x, newY));
            lastX = x;
            lastY = newY;
        }

        List<XYChart.Series<Number, Number>> listOfSeries = new ArrayList<>();
        listOfSeries.add(series);
        return listOfSeries;
    }
}
