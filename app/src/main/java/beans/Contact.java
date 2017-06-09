package beans;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by marcio on 04/06/17.
 */
public class Contact implements Serializable {

    private Integer id;
    private String name;
    private String address;
    private String city;
    private String phone1;
    private String phone2;
    private byte[] pic;

    public Contact() {
    }

    public Contact(String name, String address, String city, String phone1, String phone2, byte[] pic) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return name + " - " + phone1 + " - " + phone2;
    }
}
