package com.mathpartner.generator.chapters.chapter5;

import com.mathpartner.generator.model.Task;
import java.util.Locale;
import java.util.Random;

public class DecimalAverageGen {
    private final Random random = new Random();

    /**
     * Генерує задачу на середнє арифметичне або середню швидкість руху
     * @param taskId Порядковий номер задачі ("01")
     */
    public Task generateTask(String taskId) {
        int type = random.nextInt(2);

        if (type == 0) {
            // Тип 0: Середнє арифметичне трьох чисел
            double n1 = 5.2 + random.nextInt(5);
            double n2 = 6.4 + random.nextInt(4);
            double n3 = 7.3 + random.nextInt(6);
            
            double sum = n1 + n2 + n3;
            double avg = sum / 3.0;

            // Визначаємо точну кількість знаків після коми для відповіді
            String answer = String.format(Locale.US, "%.2f", avg);
            // Якщо число закінчується на нуль, можна використовувати TASK-D1, інакше TASK-D2
            String taskType = answer.endsWith("0") ? "TASK-D1" : "TASK-D2";
            if (taskType.equals("TASK-D1")) {
                answer = String.format(Locale.US, "%.1f", avg);
            }

            String condition = String.format(Locale.US, "Знайдіть середнє арифметичне трьох чисел:\n%.1f, %.1f та %.1f.", n1, n2, n3);

            Task task = new Task(taskId, taskType, condition, answer);
            task.addTextStep("Щоб знайти середнє арифметичне кількох чисел, потрібно їхню суму поділити на їхню кількість.");
            task.addTextStep("Знайдемо суму заданих чисел:");
            task.addTextStep(String.format(Locale.US, "%.1f + %.1f + %.1f = %.1f", n1, n2, n3, sum));
            task.addTextStep("Тепер поділимо отриману суму на 3:");
            task.addTextStep(String.format(Locale.US, "%.1f / 3 = %s", sum, answer));
            return task;

        } else {
            // Тип 1: Середня швидкість руху катера чи автомобіля (гарантуємо красивий результат)
            int t1 = 2; // час першої ділянки (години)
            int t2 = 3; // час другої ділянки (години)
            int totalTime = t1 + t2; // 5 годин

            int v1 = 60 + random.nextInt(5) * 4; // наприклад, 60, 64, 68...
            int v2 = 70 + random.nextInt(5) * 4; // наприклад, 70, 74, 78...

            int s1 = v1 * t1;
            int s2 = v2 * t2;
            int totalDistance = s1 + s2;
            
            double avgSpeed = (double) totalDistance / totalTime;
            String answer = String.format(Locale.US, "%.1f", avgSpeed);
            String taskType = answer.endsWith(".0") ? "TASK-D0" : "TASK-D1";
            if (taskType.equals("TASK-D0")) {
                answer = String.format(Locale.US, "%.0f", avgSpeed);
            }

            String condition = String.format("Автомобіль рухався %d год зі швидкістю %d км/год\n" +
                    "і %d god зі швидкістю %d км/год. Знайдіть середню швидкість руху автомобіля.", t1, v1, t2, v2);

            Task task = new Task(taskId, taskType, condition, answer);
            task.addTextStep("Щоб знайти середню швидкість руху, потрібно весь пройдений шлях поділити на весь час у дорозі.");
            task.addTextStep("Обчислимо відстань першої та другої ділянки маршруту:");
            task.addTextStep(String.format("%d * %d = %d", v1, t1, s1));
            task.addTextStep(String.format("%d * %d = %d", v2, t2, s2));
            task.addTextStep("Знайдемо загальний шлях та загальний час:");
            task.addTextStep(String.format("%d + %d = %d", s1, s2, totalDistance));
            task.addTextStep(String.format("%d + %d = %d", t1, t2, totalTime));
            task.addTextStep("Поділимо весь шлях на весь час для обчислення середньої швидкості:");
            task.addTextStep(String.format(Locale.US, "%d / %d = %s", totalDistance, totalTime, answer));
            return task;
        }
    }
}
 
