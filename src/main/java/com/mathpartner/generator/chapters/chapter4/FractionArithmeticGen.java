  
/*
4. Реалізація генератора розділу 4.3: FractionArithmeticGen.java
Додавання та віднімання дробів з однаковими знаменниками, а також мішаних чисел.
 */

package com.mathpartner.generator.chapters.chapter4;

import com.mathpartner.generator.model.Task;
import java.util.Random;

public class FractionArithmeticGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        int denom = 5 + random.nextInt(10); // 5..14

        if (type == 0) {
            // Додавання простих дробів
            int n1 = 1 + random.nextInt(denom / 2);
            int n2 = 1 + random.nextInt(denom / 2);
            String condition = String.format("Обчисліть значення виразу:\n%d/%d + %d/%d = ?", n1, denom, n2, denom);
            String answer = (n1 + n2) + "/" + denom;

            Task task = new Task(taskId, "TASK-Q", condition, answer);
            task.addTextStep("При додаванні дробів з однаковими знаменниками додаємо їхні чисельники, а знаменник залишаємо без змін:");
            task.addTextStep(String.format("(%d + %d) / %d = %d/%d", n1, n2, denom, n1 + n2, denom));
            return task;
        } else {
            // Віднімання мішаних чисел (без позичання одиниці для простоти 5 класу)
            int w1 = 5 + random.nextInt(4);
            int w2 = 1 + random.nextInt(3);
            int n1 = 3 + random.nextInt(3);
            int n2 = 1 + random.nextInt(2);
            
            String condition = String.format("Обчисліть різницю мішаних чисел:\n[%d, %d/%d] - [%d, %d/%d] = ?", w1, n1, denom, w2, n2, denom);
            String answer = String.format("[%d, %d/%d]", (w1 - w2), (n1 - n2), denom);

            Task task = new Task(taskId, "TASK-Q", condition, answer);
            task.addTextStep("Окремо виконаємо віднімання цілих частин та окремо — дробових частин:");
            task.addTextStep(String.format("Ціла частина: %d - %d = %d", w1, w2, w1 - w2));
            task.addTextStep(String.format("Дробова частина: %d/%d - %d/%d = %d/%d", n1, denom, n2, denom, n1 - n2, denom));
            return task;
        }
    }
}

