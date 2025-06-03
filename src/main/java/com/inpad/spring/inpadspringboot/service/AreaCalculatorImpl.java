package com.inpad.spring.inpadspringboot.service;

import org.springframework.stereotype.Service;

@Service
public class AreaCalculatorImpl implements AreaCalculator{
    private static final double EARTH_RADIUS = 6371008.8;
    private static final double FACTOR = (EARTH_RADIUS * EARTH_RADIUS) / 2;
    private static final double PI_OVER_180 = Math.PI / 180;

    @Override
    public double polygonArea(double[][][] coordinates) {
        double total = 0;
        if (coordinates.length > 0) {
            total += Math.abs(ringArea(coordinates[0]));
            for (int i = 1; i < coordinates.length; i++) {
                total -= Math.abs(ringArea(coordinates[i]));
            }
        }
        return total;
    }

    private double ringArea(double[][] coords) {
        int coordsLength = coords.length - 1;
        if (coordsLength <= 2) return 0;

        double total = 0;
        for (int i = 0; i < coordsLength; i++) {
            double[] lower = coords[i];
            double[] middle = coords[(i + 1) % coordsLength];
            double[] upper = coords[(i + 2) % coordsLength];

            double lowerX = lower[0] * PI_OVER_180;
            double middleY = middle[1] * PI_OVER_180;
            double upperX = upper[0] * PI_OVER_180;

            total += (upperX - lowerX) * Math.sin(middleY);
        }
        return total * FACTOR;
    }

}
