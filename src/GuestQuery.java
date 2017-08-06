/**
 * Created by chinatsu on 8/4/2017.
 * Modified  : 8/6/2017
 */

//set import
import sun.security.util.Password;
import java.sql.*;
import java.util.*;
import java.lang.*;

/*
 * Program : GuestQuery.java
 * Date: 4th August 2017
 * Author : Chinatsu Kawakami
 * Description: this is the file to execute query to show the data in database
 */
public class GuestQuery {
    private static final String URL = "jdbc:derby:Membership";
    private static final String USERNAME = "chinatsu";
    private static final String PASSWORD = "";

    private Connection connection;
    private PreparedStatement selectAllGuest;
    private PreparedStatement selectAllGuestByLastName;
    private PreparedStatement insertNewPerson;

    //constructor

    public GuestQuery()
    {
        try
        {
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);

            //create query to show all data in database
            selectAllGuest = connection.prepareStatement("SELECT * FORM Membership");
            //create query that selects entries with a specific last name
            selectAllGuestByLastName= connection.prepareStatement("SELECT * FROM Membership WHERE LastName=?");
            //create insert that adds a new guest info into database
            insertNewPerson = connection.prepareStatement("Insert INTO Membership"+"(FirstName,LastName,Age,Email,Address,Phone)"+"Values (?,?,?,?,?,?)");


        }//close try
        catch(SQLException sqlException){
              sqlException.printStackTrace();
              System.exit(1);
        }//close catch
    }//close constructor

    //select all of the data in the database
    public List<Guest> getAllGuest()
    {
        List<Guest> results =null;
        ResultSet resultSet = null;
        try
        {
             resultSet = selectAllGuest.executeQuery();
             results = new ArrayList<Guest>();

             while(resultSet.next())
             {
                 results.add(new Guest(
                         resultSet.getInt("Id"),
                         resultSet.getString("FirstName"),
                         resultSet.getString("LastName"),
                         resultSet.getInt("Age"),
                         resultSet.getString("Email"),
                         resultSet.getString("Address"),
                         resultSet.getString("Phone")));
             }//close while loop


        }//close try
        catch(SQLException sqlException)
        {
             sqlException.printStackTrace();

        }//end of catch
        finally
        {
            try{
                  resultSet.close();
            }catch(SQLException sqlException)
            {
                sqlException.printStackTrace();
                close();
            }
        }
             return results;

    }//end of method

    public List<Guest>getPeopleByLastName(String name)
    {
        List<Guest> results= null;
        ResultSet resuleSet = null;
        try{
            selectAllGuestByLastName.setString(1,name);
            resuleSet = selectAllGuestByLastName.executeQuery();
            results =new ArrayList<Guest>();
            while(resuleSet.next())
            {
                results.add(new Guest(
                        resuleSet.getInt("Id"),
                        resuleSet.getString("FirstName"),
                        resuleSet.getString("LastName"),
                        resuleSet.getInt("Age"),
                        resuleSet.getString("Email"),
                        resuleSet.getString("Address"),
                        resuleSet.getString("Phone")));
            }
        }//end try
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();

        }//end catch
        finally
        {
            try{
                resuleSet.close();
            }catch(SQLException sqlException)
            {
                sqlException.printStackTrace();
                close();

            }//end catch
        }//end finally
        return results;
    }//end this method

    public int addGuest( int id,String fname,String lname, int age,String email,String address,String phone)
    {
        int result = 0;

        try{
            insertNewPerson.setString(1,fname);
            insertNewPerson.setString(2,lname);
            insertNewPerson.setInt(3,age);
            insertNewPerson.setString(4,email);
            insertNewPerson.setString(5,address);
            insertNewPerson.setString(6,phone);

        }//end try
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }//end this method

    public void close()
    {
        try
        {
            connection.close();

        }catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

}//end of this class
