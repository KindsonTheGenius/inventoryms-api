package com.kindsonthegenius.inventoryms_springboot_api.models;

public class CategoryStats {
    private Long currentMonthCount;
    private Long previousMonthCount;
    private double percentageDifference;

    public CategoryStats(Long currentMonthCount, Long previousMonthCount, double percentageDifference) {
        this.currentMonthCount = currentMonthCount;
        this.previousMonthCount = previousMonthCount;
        this.percentageDifference = percentageDifference;
    }

    public Long getCurrentMonthCount() { return currentMonthCount; }
    public Long getPreviousMonthCount() { return previousMonthCount; }
    public double getPercentageDifference() { return percentageDifference; }

}
