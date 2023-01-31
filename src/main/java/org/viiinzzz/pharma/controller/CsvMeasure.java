package org.viiinzzz.pharma.controller;

import org.viiinzzz.pharma.help.csv.CsvBean;

import com.opencsv.bean.CsvBindByPosition;


public class CsvMeasure extends CsvBean {

    @CsvBindByPosition(position = 0)
    private String time;

    @CsvBindByPosition(position = 1)
    private String value;

    public String getTime() {
        return time;
    }

    private void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "| " + time + " | " + value + " |";
    }
}
