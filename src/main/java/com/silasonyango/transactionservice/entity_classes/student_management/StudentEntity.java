package com.silasonyango.transactionservice.entity_classes.student_management;

import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "students")

@javax.persistence.SqlResultSetMapping(
        name = "students", entities =
@javax.persistence.EntityResult(entityClass = StudentEntity.class)
)

@NamedNativeQueries({
        @NamedNativeQuery(name="StudentEntity.getAllNoneAdminStudents",
                query="SELECT * FROM students WHERE students.IsAnAdminStudent = 0",
                resultSetMapping = "students" )
})
public class StudentEntity implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentId")
    private int studentId;

    @Column(name = "AdmissionNo")
    private String admissionNo;

    @Column(name = "StudentName")
    private String studentName;

    @Column(name = "GenderId")
    private int genderId;

    @Column(name = "StudentDOB")
    private String studentDob;

    @Column(name = "StudentResidenceId")
    private int studentResidenceId;

    @Column(name = "ClassId")
    private int classId;

    @Column(name = "AdmissionDate")
    private String admissionDate;

    @Column(name = "ProfPicName")
    private String profPicName;

    @Column(name = "IsAnAdminStudent")
    private int isAnAdminStudent;

    @Column(name = "ParentPhoneNumber")
    private String parentPhoneNumber;

    @OneToMany(mappedBy = "studentId",fetch = FetchType.LAZY)
    private List<FeeStatementEntity> feeStatementEntities;

    public StudentEntity() {
    }

    public StudentEntity(String admissionNo, String studentName, int genderId, String studentDob, int studentResidenceId, int classId, String admissionDate, String profPicName, int isAnAdminStudent) {
        this.admissionNo = admissionNo;
        this.studentName = studentName;
        this.genderId = genderId;
        this.studentDob = studentDob;
        this.studentResidenceId = studentResidenceId;
        this.classId = classId;
        this.admissionDate = admissionDate;
        this.profPicName = profPicName;
        this.isAnAdminStudent = isAnAdminStudent;
    }

    public StudentEntity(int studentId, String admissionNo, String studentName, int genderId, String studentDob, int studentResidenceId, int classId, String admissionDate, String profPicName, int isAnAdminStudent) {
        this.studentId = studentId;
        this.admissionNo = admissionNo;
        this.studentName = studentName;
        this.genderId = genderId;
        this.studentDob = studentDob;
        this.studentResidenceId = studentResidenceId;
        this.classId = classId;
        this.admissionDate = admissionDate;
        this.profPicName = profPicName;
        this.isAnAdminStudent = isAnAdminStudent;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(String studentDob) {
        this.studentDob = studentDob;
    }

    public int getStudentResidenceId() {
        return studentResidenceId;
    }

    public void setStudentResidenceId(int studentResidenceId) {
        this.studentResidenceId = studentResidenceId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getProfPicName() {
        return profPicName;
    }

    public void setProfPicName(String profPicName) {
        this.profPicName = profPicName;
    }

    public int getIsAnAdminStudent() {
        return isAnAdminStudent;
    }

    public void setIsAnAdminStudent(int isAnAdminStudent) {
        this.isAnAdminStudent = isAnAdminStudent;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public List<FeeStatementEntity> getFeeStatementEntities() {
        return feeStatementEntities;
    }

    public void setFeeStatementEntities(List<FeeStatementEntity> feeStatementEntities) {
        this.feeStatementEntities = feeStatementEntities;
    }
}
