 
package com.mathpartner.generator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель одного завдання на тиждень (8 завдань на тиждень, кожне містить 10 задач)
 */
public class Assignment {
    private final String pageHeader; // Наприклад, "PAGE UA-SM-05-Week01-Task01..."
    private final List<Task> tasks = new ArrayList<>();

    public Assignment(String pageHeader) {
        this.pageHeader = pageHeader;
    }

    public void addTask(Task task) {
        if (tasks.size() >= 10) {
            throw new IllegalStateException("Завдання не може містити більше 10 задач!");
        }
        tasks.add(task);
    }

    public String getPageHeader() { return pageHeader; }
    public List<Task> getTasks() { return tasks; }
}

