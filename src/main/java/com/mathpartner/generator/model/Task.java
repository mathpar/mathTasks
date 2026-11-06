//   2. Модернізована модель Task.java
// Додано поле taskType для динамічного виведення TASK-Z або TASK-Q.
 
package com.mathpartner.generator.model;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String taskId;
    private final String taskType; // "TASK-Z" або "TASK-Q"
    private final String conditionText;
    private final List<SolutionStep> solutionSteps = new ArrayList<>();
    private final String finalAnswer;

    public Task(String taskId, String taskType, String conditionText, String finalAnswer) {
        this.taskId = taskId;
        this.taskType = taskType;
        this.conditionText = conditionText;
        this.finalAnswer = finalAnswer;
    }

    public void addTextStep(String text) {
        solutionSteps.add(new SolutionStep(text, true));
    }

    public String getTaskId() { return taskId; }
    public String getTaskType() { return taskType; }
    public String getConditionText() { return conditionText; }
    public List<SolutionStep> getSolutionSteps() { return solutionSteps; }
    public String getFinalAnswer() { return finalAnswer; }
}
 
 
