package com.example.managementapp;

import java.util.Date;

public class UserEvent {

    private String m_Title;
    private String m_Description;
    private String m_Date;

    public UserEvent(String m_Title, String m_Description, String m_Date) {
        this.m_Title = m_Title;
        this.m_Description = m_Description;
        this.m_Date = m_Date;
    }

    public String getTitle() {
        return m_Title;
    }

    public void setTitle(String m_Title) {
        this.m_Title = m_Title;
    }

    public String getDescription() {
        return m_Description;
    }

    public void setDescription(String m_Description) {
        this.m_Description = m_Description;
    }

    public String getDate() {
        return m_Date;
    }

    public void setDate(String m_Date) {
        this.m_Date = m_Date;
    }
}
