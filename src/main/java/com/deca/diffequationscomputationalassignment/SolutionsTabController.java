package com.deca.diffequationscomputationalassignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;


public class SolutionsTabController {

    @FXML
    private CheckBox checkExact;

    @FXML
    private CheckBox checkEuler;

    @FXML
    private CheckBox checkIE;

    @FXML
    private CheckBox checkRK;

    @FXML
    private TextField x0TextField;

    @FXML
    private TextField XTextField;

    @FXML
    private TextField y0TextField;

    @FXML
    private TextField NTextField;

    @FXML
    private Label errorText;

    @FXML
    private LineChart<Number, Number> solutionsChart;

    @FXML
    public void initialize() {
        applyFormatters();
        recalculate();
    }

    public void applyFormatters() {
        x0TextField.setTextFormatter(TextFormatterCollection.DoubleFormatter.getTextFormatter(1));
        XTextField.setTextFormatter(TextFormatterCollection.DoubleFormatter.getTextFormatter(9));
        y0TextField.setTextFormatter(TextFormatterCollection.DoubleFormatter.getTextFormatter(0.5));
        NTextField.setTextFormatter(TextFormatterCollection.NaturalFormatter.getTextFormatter(10));
    }

    @FXML
    private void recalculate() {

        try {
            double x0 = Double.parseDouble(x0TextField.getText());
            double X = Double.parseDouble(XTextField.getText());
            double y0 = Double.parseDouble(y0TextField.getText());
            int N = Integer.parseInt(NTextField.getText());

            ObservableList<XYChart.Series<Number, Number>> graphs = FXCollections.observableArrayList();
            solutionsChart.setData(graphs);

            errorText.setText("");

            if (x0 >= X) {
                errorText.setText("X must be greater than x0!");
                return;
            }

            if (x0 < 0 && 0 < X) {
                errorText.setText("Numerical methods can give unstable solutions if there is a point of discontinuity in the range");
            }

            if ((X - x0) / N >= 1) {
                errorText.setText(errorText.getText().concat("\nNumerical methods are not so precise if the step is greater than 1"));
            }

            if (checkExact.isSelected()) {
                ExactSolution exactMethod = new ExactSolution(x0, X, y0, 1);
                exactMethod.drawSolutionOnGraph(solutionsChart);
            }

            if (checkEuler.isSelected()) {
                EulerMethod eulerMethod = new EulerMethod(x0, X, y0, N);
                eulerMethod.drawSolutionOnGraph(solutionsChart);
            }

            if (checkIE.isSelected()) {
                ImprovedEulerMethod IEMethod = new ImprovedEulerMethod(x0, X, y0, N);
                IEMethod.drawSolutionOnGraph(solutionsChart);
            }

            if (checkRK.isSelected()) {
                RungeKuttaMethod RKMethod = new RungeKuttaMethod(x0, X, y0, N);
                RKMethod.drawSolutionOnGraph(solutionsChart);
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
