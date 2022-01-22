package app.reatailx.sellitapp.Models;

import java.io.Serializable;

/**
 * Created by dheeraj.
 */

public class Inprogressmodel implements Serializable {

    private String lead_id;
    private String vendorname;
    private String ajentname;
    private String model_name;
    private String imageurl;
    private String price;
    private String lead_pick_status;
    private String lead_pick_date;
    private String lead_pick_time;
    private String modify_date;
    private String city;
    private String leadstatus;
    private String lead_pick_month;
    private String lead_pick_year;
    private String role;
    private String datecustome;

    public String getDatecustome() {
        return datecustome;
    }

    public Inprogressmodel setDatecustome(String datecustome) {
        this.datecustome = datecustome;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Inprogressmodel setRole(String role) {
        this.role = role;
        return this;
    }

    public String getVarientname() {
        return varientname;
    }

    public Inprogressmodel setVarientname(String varientname) {
        this.varientname = varientname;
        return this;
    }

    private String varientname;


    public String getLead_pick_month() {
        return lead_pick_month;
    }

    public Inprogressmodel setLead_pick_month(String lead_pick_month) {
        this.lead_pick_month = lead_pick_month;
        return this;
    }

    public String getLead_pick_year() {
        return lead_pick_year;
    }

    public Inprogressmodel setLead_pick_year(String lead_pick_year) {
        this.lead_pick_year = lead_pick_year;
        return this;
    }

    public String getVendorname() {
        return vendorname;
    }

    public Inprogressmodel setVendorname(String vendorname) {
        this.vendorname = vendorname;
        return this;
    }

    public String getAjentname() {
        return ajentname;
    }

    public Inprogressmodel setAjentname(String ajentname) {
        this.ajentname = ajentname;
        return this;
    }

    public String getLead_id() {
        return lead_id;
    }

    public Inprogressmodel setLead_id(String lead_id) {
        this.lead_id = lead_id;
        return this;
    }

    public String getModel_name() {
        return model_name;
    }

    public Inprogressmodel setModel_name(String model_name) {
        this.model_name = model_name;
        return this;
    }

    public String getImageurl() {
        return imageurl;
    }

    public Inprogressmodel setImageurl(String imageurl) {
        this.imageurl = imageurl;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Inprogressmodel setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getLead_pick_status() {
        return lead_pick_status;
    }

    public Inprogressmodel setLead_pick_status(String lead_pick_status) {
        this.lead_pick_status = lead_pick_status;
        return this;
    }

    public String getLead_pick_date() {
        return lead_pick_date;
    }

    public Inprogressmodel setLead_pick_date(String lead_pick_date) {
        this.lead_pick_date = lead_pick_date;
        return this;
    }

    public String getLead_pick_time() {
        return lead_pick_time;
    }

    public Inprogressmodel setLead_pick_time(String lead_pick_time) {
        this.lead_pick_time = lead_pick_time;
        return this;
    }

    public String getModify_date() {
        return modify_date;
    }

    public Inprogressmodel setModify_date(String modify_date) {
        this.modify_date = modify_date;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Inprogressmodel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLeadstatus() {
        return leadstatus;
    }

    public Inprogressmodel setLeadstatus(String leadstatus) {
        this.leadstatus = leadstatus;
        return this;
    }
}
