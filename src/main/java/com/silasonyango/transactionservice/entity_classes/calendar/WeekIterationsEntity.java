package com.silasonyango.transactionservice.entity_classes.calendar;

import javax.persistence.*;
@javax.persistence.Entity
@Table(name = "week_iterations")
public class WeekIterationsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WeekIterationId")
    private int weekIterationId;

    @Column(name = "WeekIterationDescription")
    private String weekIterationDescription;

    @Column(name = "WeekIterationCode")
    private int weekIterationCode;

    public int getWeekIterationId() {
        return weekIterationId;
    }

    public void setWeekIterationId(int weekIterationId) {
        this.weekIterationId = weekIterationId;
    }

    public String getWeekIterationDescription() {
        return weekIterationDescription;
    }

    public void setWeekIterationDescription(String weekIterationDescription) {
        this.weekIterationDescription = weekIterationDescription;
    }

    public int getWeekIterationCode() {
        return weekIterationCode;
    }

    public void setWeekIterationCode(int weekIterationCode) {
        this.weekIterationCode = weekIterationCode;
    }
}
