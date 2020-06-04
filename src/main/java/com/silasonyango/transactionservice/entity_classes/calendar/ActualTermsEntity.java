package com.silasonyango.transactionservice.entity_classes.calendar;


import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "actual_terms")

@javax.persistence.SqlResultSetMapping(
        name = "actual_terms", entities =
@javax.persistence.EntityResult(entityClass = ActualTermsEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="ActualTermsEntity.getTheLatestRegisteredTerm",
                query="SELECT * FROM actual_terms ORDER BY TermEndDate DESC",
                resultSetMapping = "actual_terms" )
})
public class ActualTermsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TermId")
    private int termId;

    @Column(name = "TermIterationId")
    private int termIterationId;

    @Column(name = "TermStartDate")
    private String termStartDate;

    @Column(name = "TermEndDate")
    private String termEndDate;

    @Column(name = "Year")
    private String year;

    public ActualTermsEntity() {}

    public ActualTermsEntity(int termIterationId,String termStartDate,String termEndDate,String year) {
        this.termIterationId = termIterationId;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
        this.year = year;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getTermIterationId() {
        return termIterationId;
    }

    public void setTermIterationId(int termIterationId) {
        this.termIterationId = termIterationId;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
