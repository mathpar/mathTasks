   /*
5. Реалізація генератора розділу 4.4: FractionTextProblemsGen.java
Текстові задачі на дві класичні теми: знаходження дробу від числа та знаходження числа за його дробом. Числа підбираються так, щоб ділення на знаменник виконувалося без остачі (TASK-Z).
 */
package com.mathpartner.generator.chapters.chapter4;

import com.mathpartner.generator.model.Task;
import java.util.Random;

public class FractionTextProblemsGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        int denom = 4 + random.nextInt(5); // 4..8
        int part = 12 + random.nextInt(10); // ціла проміжна частка
        int total = denom * part;           // загальна кількість
        int num = 2 + random.nextInt(denom - 2); // чисельник

        if (type == 0) {
            // Знаходження дробу від числа
            String condition = String.format("У книжці %d сторінок. Оксанка прочитала %d/%d книжки.\n" +
                    "Скільки сторінок прочитала дівчинка?", total, num, denom);
            int answer = (total / denom) * num;

            Task task = new Task(taskId, "TASK-Z", condition, String.valueOf(answer));
            task.addTextStep("Щоб знайти дріб від числа, потрібно це число поділити на знаменник і помножити на чисельник:");
            task.addTextStep(String.format("%d / %d * %d = %d", total, denom, num, answer));
            return task;
        } else {
            // Знаходження числа за його дробом
            int knownValue = part * num;
            String condition = String.format("Турист пройшов %d км, що становить %d/%d всього шляху.\n" +
                    "Яка довжина всього маршруту туриста?", knownValue, num, denom);

            Task task = new Task(taskId, "TASK-Z", condition, String.valueOf(total));
            task.addTextStep("Щоб знайти число за його дробом, потрібно відому частину поділити на чисельник і помножити на знаменник:");
            task.addTextStep(String.format("%d / %d * %d = %d", knownValue, num, denom, total));
            return task;
        }
    }
}
 
