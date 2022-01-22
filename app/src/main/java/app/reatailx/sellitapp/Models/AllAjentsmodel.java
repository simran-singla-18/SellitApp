package app.reatailx.sellitapp.Models;

import java.io.Serializable;

/**
 * Created by IBRANDOX-4 on 08-08-2017.
 */

public class AllAjentsmodel implements Serializable {
    private String vendorid;
    private String id;
    private String srno;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String address;

    public String getAddress() {
        return address;
    }

    public AllAjentsmodel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getVendorid() {
        return vendorid;
    }

    public AllAjentsmodel setVendorid(String vendorid) {
        this.vendorid = vendorid;
        return this;
    }

    public String getId() {
        return id;
    }

    public AllAjentsmodel setId(String id) {
        this.id = id;
        return this;
    }

    public String getAjentimage() {
        return ajentimage;
    }

    public AllAjentsmodel setAjentimage(String ajentimage) {
        this.ajentimage = ajentimage;
        return this;
    }

    private String ajentimage;

    public String getSrno() {
        return srno;
    }

    public AllAjentsmodel setSrno(String srno) {
        this.srno = srno;
        return this;
    }

    public String getName() {
        return name;
    }

    public AllAjentsmodel setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AllAjentsmodel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AllAjentsmodel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public AllAjentsmodel setStatus(String status) {
        this.status = status;
        return this;
    }
}
