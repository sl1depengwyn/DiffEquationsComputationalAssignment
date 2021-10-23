package com.deca.diffequationscomputationalassignment;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.abs;
import static java.lang.Math.max;

abstract public class NumericalSolution extends Solution {

    NumericalSolution(double x0, double X, double y0, int N) {
        super(x0, X, y0, N);
    }

    public List<XYChart.Series<Number, Number>> getLocalErrors() {
        List<XYChart.Series<Number, Number>> listOfSeries = getSolution();
        ExactSolution exactSolution = new ExactSolution(x0, X, y0, 1);

        for (XYChart.Series<Number, Number> series : listOfSeries) {
            for (XYChart.Data<Number, Number> point : series.getData()) {
                double exactAtThisPoint = exactSolution.solution(point.getXValue().doubleValue());
                point.setYValue(abs(point.getYValue().doubleValue() - exactAtThisPoint));
            }
        }

        return listOfSeries;
    }

    public List<XYChart.Series<Number, Number>> getGlobalErrors(int n0, int N) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        Class[] constructorParameters = {double.class, double.class, double.class, int.class};

        for (int n = n0; n < N; ++n) {
            NumericalSolution eulerMethod = this.getClass().getConstructor(constructorParameters).newInstance(x0, X, y0, n);
            double maxError = 0;
            for (XYChart.Series<Number, Number> seriesOfLErrors : eulerMethod.getLocalErrors()) {
                Optional<XYChart.Data<Number, Number>> maxLError = seriesOfLErrors.getData().stream().max((first, second) -> Double.compare(first.getYValue().doubleValue(), second.getYValue().doubleValue()));
                maxError = max(maxError, maxLError.get().getYValue().doubleValue());
            }
            series.getData().add(new XYChart.Data<>(n, maxError));
        }

        List<XYChart.Series<Number, Number>> listOfSeries = new ArrayList<>();
        listOfSeries.add(series);
        return listOfSeries;
    }

    public void drawLocalErrorsOnGraph(LineChart<Number, Number> chart) {
        List<XYChart.Series<Number, Number>> series = getLocalErrors();

        for (XYChart.Series<Number, Number> segment : series) {
            chart.getData().add(segment);
        }

        for (XYChart.Series<Number, Number> segment : series) {
            paintLine(segment);
        }
    }

    public void drawGlobalErrorsOnGraph(int n0, int N, LineChart<Number, Number> chart) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<XYChart.Series<Number, Number>> series = getGlobalErrors(n0, N);

        for (XYChart.Series<Number, Number> segment : series) {
            chart.getData().add(segment);
        }

        for (XYChart.Series<Number, Number> segment : series) {
            paintLine(segment);
        }
    }
}
