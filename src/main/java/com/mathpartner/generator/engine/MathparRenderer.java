// 3. Оновлений двигун рендерингу MathparRenderer.java

 
package com.mathpartner.generator.engine;

import com.mathpartner.generator.model.Assignment;
import com.mathpartner.generator.model.Task;
import com.mathpartner.generator.model.SolutionStep;

public class MathparRenderer {

    public String renderAssignment(Assignment assignment) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"").append(assignment.getPageHeader()).append("\"\n");
        
        for (Task task : assignment.getTasks()) {
            sb.append("===\n");
            // Динамічний префікс TASK-Z або TASK-Q залежно від типу обчислень
            sb.append("\"").append(task.getTaskType()).append(" ").append(task.getTaskId()).append(". ").append(task.getConditionText()).append("\"\n");
            sb.append("---\n?\n===\n");
            
            // Блок розв'язання з обов'язковим префіксом "_ Рішення XX."
            sb.append("\"_ Рішення ").append(task.getTaskId()).append(".\n");
            
            for (SolutionStep step : task.getSolutionSteps()) {
                sb.append(step.getContent()).append("\n");
            }
            sb.append("\"\n");
            
            // Фінальна відповідь (без крапки з комою та дужок для поодиноких чисел/векторів)
            sb.append("---\n").append(task.getFinalAnswer()).append("\n");
        }
        sb.append("===\n");
        return sb.toString();
    }
}

