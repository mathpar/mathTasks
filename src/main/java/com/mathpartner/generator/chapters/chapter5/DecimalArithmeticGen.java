/* 
1. Клас DecimalArithmeticGen.java
Генерує арифметичні завдання з динамічним налаштуванням точності TASK-D2 та TASK-D4.
*/

package com.mathpartner.generator.chapters.chapter5;

import com.mathpartner.generator.model.Task;
import java.util.Locale;
import java.util.Random;

public class DecimalArithmeticGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        
        if (type == 0) {
            // Додавання десяткових дробів з точністю 2 знаки (TASK-D2)
            double a = 1.5 + random.nextInt(5) + (random.nextInt(10) / 10.0);
            double b = 2.1 + random.nextInt(4) + (random.nextInt(10) / 10.0);
            double res = a + b;

            String condition = String.format(Locale.US, "Обчисліть значення виразу:\n%.1f + %.1f = ?", a, b);
            String answer = String.format(Locale.US, "%.2f", res);

            Task task = new Task(taskId, "TASK-D2", condition, answer);
            task.addTextStep("Виконаємо додавання десяткових дробів, підписуючи розряд під розрядом:");
            task.addTextStep(String.format(Locale.US, "%.1f + %.1f = %.1f", a, b, res));
            return task;
        } else {
            // Множення десяткових дробів з точністю 4 знаки (TASK-D4)
            double a = 0.12 + (random.nextInt(5) / 100.0);
            double b = 0.3 + (random.nextInt(3) / 10.0);
            double res = a * b;

            String condition = String.format(Locale.US, "Обчисліть добуток дійсних чисел:\n%.2f * %.1f = ?", a, b);
            String answer = String.format(Locale.US, "%.4f", res);

            Task task = new Task(taskId, "TASK-D4", condition, answer);
            task.addTextStep("Перемножимо числа, не звертаючи уваги на коми, а потім відокремимо справа стільки знаків, скільки їх в обох множниках разом:");
            task.addTextStep(String.format(Locale.US, "%.2f * %.1f = %.4f", a, b, res));
            return task;
        }
    }
}
