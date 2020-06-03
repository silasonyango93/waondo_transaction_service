package com.silasonyango.transactionservice.entity_classes.calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "actual_weeks")
public class ActualWeeksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ActualWeekId")
    private int actualWeekId;

    @Column(name = "TermId")
    private int termId;

    @Column(name = "WeekIterationId")
    private int weekIterationId;

    @Column(name = "WeekStartDate")
    private String weekStartDate;

    @Column(name = "WeekEndDate")
    private String weekEndDate;

    public ActualWeeksEntity() {
    }

    public ActualWeeksEntity(int termId, int weekIterationId, String weekStartDate, String weekEndDate) {
        this.termId = termId;
        this.weekIterationId = weekIterationId;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
    }

    public int getActualWeekId() {
        return actualWeekId;
    }

    public void setActualWeekId(int actualWeekId) {
        this.actualWeekId = actualWeekId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getWeekIterationId() {
        return weekIterationId;
    }

    public void setWeekIterationId(int weekIterationId) {
        this.weekIterationId = weekIterationId;
    }

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public String getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(String weekEndDate) {
        this.weekEndDate = weekEndDate;
    }
}
