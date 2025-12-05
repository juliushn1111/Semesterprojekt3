package model;

import java.io.Serializable;

public class CriticalInfo implements Serializable {

    private String kritik;

    public CriticalInfo(String kritik) {
        this.kritik = kritik;
    }

    public String getKritik() {
        return kritik;
    }

    public void setKritik(String kritik) {
        this.kritik = kritik;
    }

    @Override
    public String toString() {
        return kritik;
    }
}
