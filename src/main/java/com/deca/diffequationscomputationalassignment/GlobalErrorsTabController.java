package com.deca.diffequationscomputationalassignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class GlobalErrorsTabController {

    private double x0 = 1;
    private double X = 10;
    private double y0 = 0.5;
    private int N = 10;

    @FXML
    private CheckBox checkEuler;

    @FXML
    private CheckBox checkIE;

    @FXML
    private CheckBox checkRK;

    @FXML
    private TextField n0TextField;

    @FXML
    private TextField NTextField;

    @FXML
    private Label errorText;

    @FXML
    private LineChart<Number, Number> globalErrorsChart;

    @FXML
    public void initialize() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        applyFormatters();
        recalculate();
    }

    public void applyFormatters() {
        n0TextField.setTextFormatter(TextFormatterCollection.NaturalFormatter.getTextFormatter(5));
        NTextField.setTextFormatter(TextFormatterCollection.NaturalFormatter.getTextFormatter(15));
    }

    @FXML
    private void recalculate() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            int n0 = Integer.parseInt(n0TextField.getText());
            int N = Integer.parseInt(NTextField.getText());

            ObservableList<XYChart.Series<Number, Number>> graphs = FXCollections.observableArrayList();
            globalErrorsChart.setData(graphs);


            if (n0 > N) {
                errorText.setText("N must be greater than n0!");
                return;
            } else {
                errorText.setText("");
            }


            if (checkEuler.isSelected()) {
                EulerMethod eulerMethod = new EulerMethod(x0, X, y0, N);
                eulerMethod.drawGlobalErrorsOnGraph(n0, N, globalErrorsChart);
            }

            if (checkIE.isSelected()) {
                ImprovedEulerMethod IEMethod = new ImprovedEulerMethod(x0, X, y0, N);
                IEMethod.drawGlobalErrorsOnGraph(n0, N, globalErrorsChart);
            }

            if (checkRK.isSelected()) {
                RungeKuttaMethod RKMethod = new RungeKuttaMethod(x0, X, y0, N);
                RKMethod.drawGlobalErrorsOnGraph(n0, N, globalErrorsChart);
            }
    }
}
