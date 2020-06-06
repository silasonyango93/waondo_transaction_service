package com.silasonyango.transactionservice.entity_classes.calendar;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "term_iterations")

@javax.persistence.SqlResultSetMapping(
        name = "term_iterations", entities =
@javax.persistence.EntityResult(entityClass = TermIterationsEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="TermIterationsEntity.getAllTermIterationsInAscendingOrder",
                query="SELECT * FROM term_iterations ORDER BY TermIterationId ASC",
                resultSetMapping = "term_iterations" ),
        @NamedNativeQuery(
                name="TermIterationsEntity.findActualTermByTermIterationId",
                query="SELECT * FROM term_iterations INNER JOIN actual_terms ON term_iterations.TermIterationId = actual_terms.TermIterationId WHERE term_iterations.TermIterationId = ?",
                resultSetMapping = "term_iterations")
})
public class TermIterationsEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TermIterationId")
    private int termIterationId;

    @Column(name = "TermIterationDescription")
    private String termIterationDescription;

    @OneToMany(mappedBy = "termIterationId",fetch = FetchType.EAGER)
    private List<ActualTermsEntity> actualTermsEntityList;

    public int getTermIterationId() {
        return termIterationId;
    }

    public void setTermIterationId(int termIterationId) {
        this.termIterationId = termIterationId;
    }

    public String getTermIterationDescription() {
        return termIterationDescription;
    }

    public void setTermIterationDescription(String termIterationDescription) {
        this.termIterationDescription = termIterationDescription;
    }

    public List<ActualTermsEntity> getPhones() {
        return actualTermsEntityList;
    }

    public void setPhones(List<ActualTermsEntity> actualTermsEntityList) {
        this.actualTermsEntityList = actualTermsEntityList;
    }
}
