package com.dvh.lesson03.DvhEntity;

public class DvhStudent {
    Long Dvhid;
    String Dvhname;
    int Dvhage;
    String Dvhaddress;
    String Dvhemail;
    String Dvhphone;
    String Dvhgender;

    public DvhStudent() {

    }
    public DvhStudent(Long Dvhid, String Dvhname, int Dvhage, String Dvhaddress, String Dvhemail, String Dvhphone, String Dvhgender) {
        this.Dvhid = Dvhid;
        this.Dvhname = Dvhname;
        this.Dvhage = Dvhage;
        this.Dvhaddress = Dvhaddress;
        this.Dvhemail = Dvhemail;
        this.Dvhphone = Dvhphone;
        this.Dvhgender = Dvhgender;
    }
    public Long getDvhid() {
        return Dvhid;
    }
    public void setDvhid(Long Dvhid) {
        this.Dvhid = Dvhid;
    }
    public String getDvhname() {
        return Dvhname;
    }
    public void setDvhname(String Dvhname) {
        this.Dvhname = Dvhname;
    }
    public int getDvhage() {
        return Dvhage;
    }
    public void setDvhage(int Dvhage) {
        this.Dvhage = Dvhage;
    }
    public String getDvhaddress() {
        return Dvhaddress;
    }
    public void setDvhaddress(String Dvhaddress) {
        this.Dvhaddress = Dvhaddress;
    }
    public String getDvhemail() {
        return Dvhemail;
    }
    public void setDvhemail(String Dvhemail) {
        this.Dvhemail = Dvhemail;
    }
    public String getDvhphone() {
        return Dvhphone;
    }
    public void setDvhphone(String Dvhphone) {
        this.Dvhphone = Dvhphone;
    }
    public String getDvhgender() {
        return Dvhgender;
    }
    public void setDvhgender(String Dvhgender) {
        this.Dvhgender = Dvhgender;
    }
}
