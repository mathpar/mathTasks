 /*
2. Реалізація генератора розділу 4.1: FractionConceptGen.java

Генерує базові поняття: знаходження чисельника, знаменника чи визначення дробу за малюнком/умовою. Усі відповіді тут є цілими числами або простими дробами.
 */
 
 
package com.mathpartner.generator.chapters.chapter4;

import com.mathpartner.generator.model.Task;
import java.util.Random;

public class FractionConceptGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        int denominator = 5 + random.nextInt(8); // 5..12
        int numerator = 1 + random.nextInt(denominator - 1);

        if (type == 0) {
            String condition = String.format("Кавун розрізали на %d рівних частин\n" +
                    "і %d частини з'їли. Яку частину кавуна з'їли?", denominator, numerator);
            String answer = numerator + "/" + denominator;
            Task task = new Task(taskId, "TASK-Q", condition, answer);
            task.addTextStep("Знаменник дробу показує, на скільки частин розділили ціле, а чисельник — скільки частин взяли:");
            return task;
        } else {
            String condition = String.format("Укажіть знаменник дробу %d/%d.", numerator, denominator);
            Task task = new Task(taskId, "TASK-Z", condition, String.valueOf(denominator));
            task.addTextStep("Знаменник дробу записується під рискою дробу. У даному випадку це:");
            task.addTextStep(String.valueOf(denominator));
            return task;
        }
    }
}

