 package com.mathpartner.generator.chapters.chapter5;

import com.mathpartner.generator.model.Task;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Random;

public class DecimalRoundingGen {
    private final Random random = new Random();

    /**
     * Генерує задачу на округлення чисел із динамічним встановленням типу TASK-D
     * @param taskId Порядковий номер задачі (наприклад, "01")
     */
    public Task generateTask(String taskId) {
        int type = random.nextInt(3); // 3 типи розрядів для округлення
        
        // Генеруємо випадкове число з трьома або чотирма знаками після коми
        double rawValue = 10 + random.nextInt(150) + (1000 + random.nextInt(9000)) / 10000.0;
        BigDecimal bd = new BigDecimal(Double.toString(rawValue));
        
        String condition;
        String answer;
        String taskType;
        String roundTargetText;
        
        int scale; // Кількість знаків після коми для фінальної відповіді

        switch (type) {
            case 0: // Округлення до десятих (1 знак після коми -> TASK-D1)
                scale = 1;
                roundTargetText = "десятих";
                taskType = "TASK-D1";
                BigDecimal res1 = bd.setScale(scale, RoundingMode.HALF_UP);
                answer = String.format(Locale.US, "%.1f", res1.doubleValue());
                break;
                
            case 1: // Округлення до сотих (2 знаки після коми -> TASK-D2)
                scale = 2;
                roundTargetText = "сотих";
                taskType = "TASK-D2";
                BigDecimal res2 = bd.setScale(scale, RoundingMode.HALF_UP);
                answer = String.format(Locale.US, "%.2f", res2.doubleValue());
                break;
                
            default: // Округлення до цілих (0 знаків після коми -> TASK-D0 або TASK-Z)
                scale = 0;
                roundTargetText = "одиниць (цілих)";
                taskType = "TASK-D0"; // або TASK-Z, але використовуємо TASK-D0 для узгодженості модуля
                BigDecimal res3 = bd.setScale(scale, RoundingMode.HALF_UP);
                answer = String.format(Locale.US, "%.0f", res3.doubleValue());
                break;
        }

        condition = String.format(Locale.US, "Округліть число %.4f до %s.", bd.doubleValue(), roundTargetText);

        Task task = new Task(taskId, taskType, condition, answer);
        task.addTextStep(String.format(Locale.US, "Знайдемо розряд %s у числі %.4f.", roundTargetText, bd.doubleValue()));
        task.addTextStep("Якщо наступна за цим розрядом цифра дорівнює 5, 6, 7, 8 або 9, ");
        task.addTextStep("то цифру в даному розряді збільшуємо на одиницю, а всі наступні цифри відкидаємо.");
        task.addTextStep("Запишемо отриманий результат округлення:");
        task.addTextStep(answer);

        return task;
    }
}
