package com.dorm.muro.dormitory.presentation.todo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem implements Serializable {
    private String title, commentary;
    private Date deadline;
    private boolean isPinned;
    private String key;

    public TodoItem(String title, String commentary, Date deadline) {
        this.title = title;
        if (commentary != null && !commentary.isEmpty())
            this.commentary = commentary;
        else
            this.commentary = "";
        this.deadline = deadline;
    }

    public TodoItem() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }

    public String getDeadlineString(SimpleDateFormat formatter) {
        return formatter.format(deadline);
    }
}
