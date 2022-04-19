package com.example.microstreaminganalytics.entity;

public class Calculations {

    private String mean;
    private String median;
    private String mode;
    private String standardDeviation;
    private Quartiles quartiles;
    private String maximum;
    private String minimum;

    public Calculations(String mean, String median, String mode, String standardDeviation, Quartiles quartiles, String maximum, String minimum) {
        this.mean = mean;
        this.median = median;
        this.mode = mode;
        this.standardDeviation = standardDeviation;
        this.quartiles = quartiles;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(String standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Quartiles getQuartiles() {
        return quartiles;
    }

    public void setQuartiles(Quartiles quartiles) {
        this.quartiles = quartiles;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }
}
