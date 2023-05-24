package internal.core.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    
    protected Integer stuCode;
    protected String stuName;
    protected Float stuScore;
    protected String stuImagPath;
    protected String stuAddress;
    protected String stuNote;

    // Setter
    public void setterStuCode(Integer stuCode) {
        this.stuCode = stuCode;
    }

    public void setterStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setterStuScore(Float stuScore) {
        this.stuScore = stuScore;
    }

    public void setterStuImagPath(String stuImagPath) {
        this.stuImagPath = stuImagPath;
    }

    public void setterStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public void setterStuNote(String stuNote) {
        this.stuNote = stuNote;
    }

    // Getter
    public Integer getterStuCode() {
        return stuCode;
    }
    
    public String getterStuName() {
        return stuName;
    }

    public Float getterStuScore() {
        return stuScore;
    }

    public String getterStuImagByte() {
        return stuImagPath;
    }

    public String getterStuAddress() {
        return stuAddress;
    }

    public String getterStuNote() {
        return stuNote;
    }
}