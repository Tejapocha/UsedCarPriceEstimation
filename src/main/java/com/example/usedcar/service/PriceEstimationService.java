package com.example.usedcar.service;

import com.example.usedcar.model.Car;
import com.example.usedcar.util.CsvLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceEstimationService {

    private List<Car> cars;
    private double[] coefficients; // [intercept, mileage, year, engineSize]

    public PriceEstimationService() {
        this.cars = CsvLoader.loadCarsFromCsv("cars.csv");
        trainModel();
    }

    private void trainModel() {
        int n = cars.size();
        int m = 3; // number of features

        // Build X matrix with intercept
        double[][] X = new double[n][m + 1];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            Car c = cars.get(i);
            X[i][0] = 1.0;           // intercept
            X[i][1] = c.getMileage();
            X[i][2] = c.getYear();
            X[i][3] = c.getEngineSize();
            y[i] = c.getPrice();
        }

        // Simple OLS using normal equation: β = (X^T X)^-1 X^T y
        coefficients = simpleOLS(X, y);

        System.out.println("✅ Model trained with " + n + " cars");
    }

    // Simple OLS implementation using Java arrays
    private double[] simpleOLS(double[][] X, double[] y) {
        int n = X.length;
        int m = X[0].length;

        // Compute X^T * X
        double[][] XtX = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += X[k][i] * X[k][j];
                }
                XtX[i][j] = sum;
            }
        }

        // Compute X^T * y
        double[] Xty = new double[m];
        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int k = 0; k < n; k++) {
                sum += X[k][i] * y[k];
            }
            Xty[i] = sum;
        }

        // Solve XtX * beta = Xty using Gauss-Jordan
        return gaussJordan(XtX, Xty);
    }

    // Gauss-Jordan elimination to solve linear system
    private double[] gaussJordan(double[][] A, double[] b) {
        int n = b.length;
        for (int i = 0; i < n; i++) {
            // Pivot
            double max = Math.abs(A[i][i]);
            int row = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(A[k][i]) > max) {
                    max = Math.abs(A[k][i]);
                    row = k;
                }
            }
            // Swap rows
            double[] temp = A[i]; A[i] = A[row]; A[row] = temp;
            double t = b[i]; b[i] = b[row]; b[row] = t;

            // Normalize
            double div = A[i][i];
            for (int j = 0; j < n; j++) A[i][j] /= div;
            b[i] /= div;

            // Eliminate
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = A[k][i];
                    for (int j = 0; j < n; j++) A[k][j] -= factor * A[i][j];
                    b[k] -= factor * b[i];
                }
            }
        }
        return b;
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public double estimatePrice(Car inputCar) {
        return coefficients[0] 
               + coefficients[1] * inputCar.getMileage()
               + coefficients[2] * inputCar.getYear()
               + coefficients[3] * inputCar.getEngineSize();
    }
}
