package model;

import java.io.Serializable;

public class Information implements Serializable {

    private String info;
    private String name;

    public Information(String info, String name) {
        this.info = info;
        this.name = name;
    }

    // ---------- GETTERS ----------
    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    // ---------- SETTERS ----------
    public void setInfo(String info) {
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Information{" +
                "info='" + info + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}