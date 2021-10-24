package com.deca.diffequationscomputationalassignment;

import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ExactSolution extends Solution {

    double pointOfDiscontinuity;

    ExactSolution(double x0, double X, double y0, int N) {
        super(x0, X, y0, N);
        color = Color.GREEN;
        pointOfDiscontinuity = -1 / c;
        h = 0.1;
    }

    public double solution(double x) {
        return 1 / (x * c + 1);
    }

    @Override
    public List<XYChart.Series<Number, Number>> getSolution() {

        XYChart.Series<Number, Number> seriesBeforePointOfDiscontinuity = new XYChart.Series<>();
        XYChart.Series<Number, Number> seriesAfterPointOfDiscontinuity = new XYChart.Series<>();
        double newY;
        if (x0 < pointOfDiscontinuity && pointOfDiscontinuity < X) {
            for (double x = x0; x < pointOfDiscontinuity; x += h) {
                newY = solution(x);
                if (!Double.isFinite(newY)) {
                    continue;
                }
                seriesBeforePointOfDiscontinuity.getData().add(new XYChart.Data<>(x, newY));
            }
            for (double x = pointOfDiscontinuity + h; x <= X + h; x += h) {
                newY = solution(x);
                if (!Double.isFinite(newY)) {
                    continue;
                }
                seriesAfterPointOfDiscontinuity.getData().add(new XYChart.Data<>(x, newY));
            }
        } else {
            for (double x = x0; x <= X + h; x += h) {
                newY = solution(x);
                if (!Double.isFinite(newY)) {
                    continue;
                }
                seriesBeforePointOfDiscontinuity.getData().add(new XYChart.Data<>(x, newY));
            }
        }
        List<XYChart.Series<Number, Number>> series = new ArrayList<>();
        series.add(seriesBeforePointOfDiscontinuity);
        series.add(seriesAfterPointOfDiscontinuity);
        return series;
    }
}

