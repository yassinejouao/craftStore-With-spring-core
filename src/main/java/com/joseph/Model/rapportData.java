package com.joseph.Model;

import java.time.LocalDate;

public class rapportData {
    private LocalDate date;
    private Double total;

    public rapportData(LocalDate date, Double total) {
        this.date = date;
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}