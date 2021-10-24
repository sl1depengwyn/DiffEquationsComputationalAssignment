package com.deca.diffequationscomputationalassignment;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class Solution {
    protected double x0;
    protected double X;
    protected double y0;
    protected double N;
    protected double c;
    protected double h;
    protected Color color;

    Solution(double x0, double X, double y0, int N) {
        this.x0 = x0;
        this.X = X;
        this.y0 = y0;
        this.N = N;
        c = (y0 - 1) / (y0 * x0);
        h = (X - x0) / this.N;
    }

    public void paintLine(XYChart.Series<Number, Number> series) {
        Node line = series.getNode().lookup(".chart-series-line");

        String rgb = String.format("%d, %d, %d",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
        line.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");
    }

    public void drawSolutionOnGraph(LineChart<Number, Number> chart) {
        List<XYChart.Series<Number, Number>> series = getSolution();

        for (XYChart.Series<Number, Number> segment : series) {
            chart.getData().add(segment);
        }

        for (XYChart.Series<Number, Number> segment : series) {
            paintLine(segment);
        }
    }

    abstract public List<XYChart.Series<Number, Number>> getSolution();
}
