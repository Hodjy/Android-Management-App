package com.example.managementapp;

import java.util.Date;

public class UserEvent {

    private String m_Title;
    private String m_Description;
    private String m_Date;

    public UserEvent(String i_Title, String i_Description, String i_Date) {
        m_Title = i_Title;
        m_Description = i_Description;
        m_Date = i_Date;
    }

    public String getTitle() {
        return m_Title;
    }

    public void setTitle(String i_Title) {
        m_Title = i_Title;
    }

    public String getDescription() {
        return m_Description;
    }

    public void setDescription(String i_Description) {
        m_Description = i_Description;
    }

    public String getDate() {
        return m_Date;
    }

    public void setDate(String i_Date) {
        m_Date = i_Date;
    }
}
