
 /*
3. Реалізація генератора розділу 4.2: FractionTypesAndMixedGen.java
Фокусується на перетворенні неправильного дробу в мішане число і навпаки. Застосовуємо векторний формат відповіді MathPartner [цела, чисельник/знаменник].
  */

package com.mathpartner.generator.chapters.chapter4;

import com.mathpartner.generator.model.Task;
import java.util.Random;

public class FractionTypesAndMixedGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        int whole = 2 + random.nextInt(5);       // Цела частина 2..6
        int denom = 3 + random.nextInt(6);       // Знаменник 3..8
        int num = 1 + random.nextInt(denom - 1); // Чисельник < знаменника

        if (type == 0) {
            // Перетворення неправильного дробу в мішане число
            int improperNumerator = whole * denom + num;
            String condition = String.format("Виділіть цілу частину з неправильного дробу %d/%d.", improperNumerator, denom);
            String answer = String.format("[%d, %d/%d]", whole, num, denom);
            
            Task task = new Task(taskId, "TASK-Q", condition, answer);
            task.addTextStep("Поділимо чисельник на знаменник з остачею. Неповна частка буде цілою частиною, а остача — чисельником:");
            task.addTextStep(String.format("%d / %d = %d (остача %d)", improperNumerator, denom, whole, num));
            return task;
        } else {
            // Перетворення мішаного числа в неправильний дріб
            String condition = String.format("Запишіть мішане число [%d, %d/%d]\nу вигляді неправильного дробу.", whole, num, denom);
            int improperNumerator = whole * denom + num;
            String answer = improperNumerator + "/" + denom;

            Task task = new Task(taskId, "TASK-Q", condition, answer);
            task.addTextStep("Помножимо цілу частину на знаменник і додамо чисельник, результат запишемо в чисельник:");
            task.addTextStep(String.format("%d * %d + %d = %d", whole, denom, num, improperNumerator));
            return task;
        }
    }
}

