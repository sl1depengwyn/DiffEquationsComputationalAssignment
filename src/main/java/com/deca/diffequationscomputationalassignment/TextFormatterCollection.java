package com.deca.diffequationscomputationalassignment;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public abstract class TextFormatterCollection {

    static public class DoubleFormatter {

        static Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        static UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            } else {
                return null;
            }
        };

        static StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };

        public static TextFormatter<Double> getTextFormatter(double defaultValue) {
            return new TextFormatter<>(converter, defaultValue, filter);
        }
    }

    static public class NaturalFormatter {

        static Pattern validEditingState = Pattern.compile("([1-9][0-9]*)?");

        static UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            } else {
                return null;
            }
        };

        static StringConverter<Integer> converter = new StringConverter<Integer>() {

            @Override
            public Integer fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 1;
                } else {
                    return Integer.valueOf(s);
                }
            }


            @Override
            public String toString(Integer d) {
                return d.toString();
            }
        };

        public static TextFormatter<Integer> getTextFormatter(Integer defaultValue) {
            return new TextFormatter<>(converter, defaultValue, filter);
        }
    }
}
