 
/* 3. Інтерфейси для генерації та рендерингу */ 
package com.mathpartner.generator.engine;

import com.mathpartner.generator.model.Assignment;

/**
 * Стратегія генерації конкретного типу завдання (наприклад, для певної теми/тижня)
 */
public interface AssignmentGenerator {
    Assignment generate(int weekNumber, int assignmentNumber);
}

