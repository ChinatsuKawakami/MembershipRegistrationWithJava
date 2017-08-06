/**
 * Created by chinatsu on 8/3/2017.
 * Project Description : This is the program to register, Delete, Search membership information.
 * Class name : Guest.jva
 */
import javax.swing.*;
import java.lang.*;
import javax.swing.JOptionPane;
import java.util.InputMismatchException;

public class Guest {


    //field
    private String firstName;
    private String lastName;
    private int age;
    private int id;
    private String email;
    private String address;
    private String phone;

    //getter read-only
    public String getfirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public String getAddress() {
        return address;
    }

   public String getPhone() {
        return phone;
    }

    //Constructor

    public Guest(int id,String firstName, String lastName, int age,String email,String address,String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        if (age >= 0) {
            this.age = age;
        } else {

            JOptionPane.showMessageDialog(null, "Age should not be -1");
        }



        this.email = email;
        this.address = address;
        if((phone.length()==10)&&(phone.substring(0,2).equals("416")|| phone.substring(0,2).equals("647")))
        {
            this.phone = phone;
        }else
        {
            JOptionPane.showMessageDialog(null, "Phone number should be 10 digits or your phone number should start with 416 or 647");
        }
    }//end of this method


}//end of this class
