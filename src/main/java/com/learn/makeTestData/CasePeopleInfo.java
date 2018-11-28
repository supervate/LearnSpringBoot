package cn.rq.es.makeTestData;

import lombok.Data;

@Data
public class CasePeopleInfo {
    public String caseId;
    public String caseName;
    public String peopleName;
    public int age;
    public String f1;
    public String f2;
    public String f3;
    public String f4;
    public String f5;
    public String f6;
    public String f7;
    public String f8;
    public String f9;
    public String f10;

    public CasePeopleInfo(String caseId, String caseName, String peopleName, int age, String f1, String f2, String f3, String f4, String f5, String f6, String f7, String f8, String f9, String f10) {
        this.caseId = caseId;
        this.caseName = caseName;
        this.peopleName = peopleName;
        this.age = age;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
        this.f5 = f5;
        this.f6 = f6;
        this.f7 = f7;
        this.f8 = f8;
        this.f9 = f9;
        this.f10 = f10;
    }

    public CasePeopleInfo(String caseId, String caseName, String peopleName, int age) {
        this.caseId = caseId;
        this.caseName = caseName;
        this.peopleName = peopleName;
        this.age = age;
    }
}
