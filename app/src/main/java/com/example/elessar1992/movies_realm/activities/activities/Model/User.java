package com.example.elessar1992.movies_realm.activities.activities.Model;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by elessar1992 on 3/8/18.
 */

@RealmClass
public class User extends RealmObject
{
    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String eventTitle;
    private String eventScore;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String name)
    {
        this.firstname = name;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String name)
    {
        this.lastname = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String name)
    {
        this.username = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventScore() {
        return eventScore;
    }

    public void setEventScore(String eventScore) {
        this.eventScore = eventScore;
    }
}
