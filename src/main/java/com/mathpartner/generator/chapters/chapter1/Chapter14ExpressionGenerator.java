/* 1. Реалізація генератора для підрозділу 1.4 (Спрощення буквених виразів)
Цей генератор створює буквені вирази кількох типів (наприклад, \(a \cdot x + b \cdot x\) або \(a \cdot (b \cdot x)\)), автоматично розраховує підсумковий коефіцієнт, підставляє числове значення змінної та обчислює кінцеву відповідь. */
 
package com.mathpartner.generator.chapters.chapter1;

import com.mathpartner.generator.model.Task;
import java.util.Random;

public class Chapter14ExpressionGenerator {
    private final Random random = new Random();

    public Task generateExpressionTask(String taskId) {
        int type = random.nextInt(3);
        String var = "x";
        
        int a = 2 + random.nextInt(15);
        int b = 2 + random.nextInt(15);
        int valX = 3 + random.nextInt(8); // значення змінної

        String condition;
        int finalCoeff;
        int finalResult;
        
        switch (type) {
            case 0: // a * x + b * x
                condition = String.format("Спростіть вираз %d * %s + %d * %s\nта обчисліть його значення, якщо %s = %d.", a, var, b, var, var, valX);
                finalCoeff = a + b;
                finalResult = finalCoeff * valX;
                
                Task task0 = new Task(taskId, "TASK-Z", condition, String.valueOf(finalResult));
                task0.addTextStep("Винесемо спільний множник за дужки та додамо коефіцієнти:");
                task0.addTextStep(String.format("(%d + %d) * %s = %d * %s", a, b, var, finalCoeff, var));
                task0.addTextStep("Підставимо значення змінної у спрощений вираз:");
                task0.addTextStep(String.format("%d * %d = %d", finalCoeff, valX, finalResult));
                return task0;

            case 1: // a * (b * x)
                condition = String.format("Спростіть вираз %d * (%d * %s)\nта обчисліть його значення, якщо %s = %d.", a, b, var, var, valX);
                finalCoeff = a * b;
                finalResult = finalCoeff * valX;
                
                Task task1 = new Task(taskId, "TASK-Z", condition, String.valueOf(finalResult));
                task1.addTextStep("Використовуючи сполучну властивість множення, перемножимо числа:");
                task1.addTextStep(String.format("(%d * %d) * %s = %d * %s", a, b, var, finalCoeff, var));
                task1.addTextStep("Підставимо значення змінної у спрощений вираз:");
                task1.addTextStep(String.format("%d * %d = %d", finalCoeff, valX, finalResult));
                return task1;

            default: // a * x - b * x (де a > b)
                if (a <= b) { a += b; }
                condition = String.format("Спростіть вираз %d * %s - %d * %s\nта обчисліть його значення, якщо %s = %d.", a, var, b, var, var, valX);
                finalCoeff = a - b;
                finalResult = finalCoeff * valX;
                
                Task task2 = new Task(taskId, "TASK-Z", condition, String.valueOf(finalResult));
                task2.addTextStep("Винесемо спільний множник за дужки та виконаємо віднімання:");
                task2.addTextStep(String.format("(%d - %d) * %s = %d * %s", a, b, var, finalCoeff, var));
                task2.addTextStep("Підставимо значення змінної у спрощений вираз:");
                task2.addTextStep(String.format("%d * %d = %d", finalCoeff, valX, finalResult));
                return task2;
        }
    }
}
