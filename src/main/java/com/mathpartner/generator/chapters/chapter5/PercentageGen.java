 /*
2. Клас PercentageGen.java
Генерує задачі на відсотки з обов'язковим екрануванням символу \%.
*/
package com.mathpartner.generator.chapters.chapter5;

import com.mathpartner.generator.model.Task;
import java.util.Locale;
import java.util.Random;

public class PercentageGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        
        int total = 100 + random.nextInt(8) * 50; // 100, 150, 200...
        int percent = 5 + random.nextInt(4) * 5;   // 5\%, 10\%, 15\%, 20\%

        if (type == 0) {
            // Знаходження відсотків від числа
            String condition = String.format("Знайдіть %d\\%% від числа %d.", percent, total);
            double result = (total * percent) / 100.0;
            String answer = String.format(Locale.US, "%.2f", result);

            Task task = new Task(taskId, "TASK-D2", condition, answer);
            task.addTextStep(String.format("Перетворимо %d\\%% у десятковий дріб:", percent));
            task.addTextStep(String.format(Locale.US, "%d\\%% = %.2f", percent, percent / 100.0));
            task.addTextStep("Помножимо число на отриманий десятковий дріб:");
            task.addTextStep(String.format(Locale.US, "%d * %.2f = %.2f", total, percent / 100.0, result));
            return task;
        } else {
            // Текстова задача на відсотки
            int price = 200 + random.nextInt(5) * 100; // 200..600
            int discountPercent = 10;
            double discountVal = (price * discountPercent) / 100.0;
            double finalPrice = price - discountVal;

            String condition = String.format("Ціна товару становила %d грн. Під час розпродажу її знизили на %d\\%%.\n" +
                    "Яка нова ціна товару?", price, discountPercent);
            String answer = String.format(Locale.US, "%.2f", finalPrice);

            Task task = new Task(taskId, "TASK-D2", condition, answer);
            task.addTextStep(String.format("Знайдемо суму знижки в гривнях, що становить %d\\%% від початкової ціни:", discountPercent));
            task.addTextStep(String.format(Locale.US, "%d * %.2f = %.2f", price, discountPercent / 100.0, discountVal));
            task.addTextStep("Обчислимо нову вартість товару:");
            task.addTextStep(String.format(Locale.US, "%d - %.2f = %.2f", price, discountVal, finalPrice));
            return task;
        }
    }
}
