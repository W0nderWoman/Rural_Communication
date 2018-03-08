package com.example.gupta.ruralcommunication.ProblemFragment.Model;

/**
 * Created by Vasudev on 3/8/2018.
 */

public class ProblemObject {
    private String Title;
    private String Status;
    private String Description;
    private String Date;

    public ProblemObject(String title, String status, String description, String date) {
        Title = title;
        Status = status;
        Description = description;
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
