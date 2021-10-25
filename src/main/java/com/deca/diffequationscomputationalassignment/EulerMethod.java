package com.deca.diffequationscomputationalassignment;

import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class EulerMethod extends NumericalSolution {

    double lastY;
    double lastX;

    public EulerMethod(double x0, double X, double y0, int N) {
        super(x0, X, y0, N);
        color = Color.RED;
    }

    @Override
    public List<XYChart.Series<Number, Number>> getSolution() {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(x0, y0));
        lastX = x0;
        lastY = y0;

        for (double x = x0 + h; x < X + h; x += h) {
            double newY = lastY + h * f(lastX, lastY);
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

