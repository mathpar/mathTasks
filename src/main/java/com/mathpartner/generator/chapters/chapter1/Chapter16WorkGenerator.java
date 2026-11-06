 
package com.mathpartner.generator.chapters.chapter1;

import java.util.Random;

/**
 * Генератор текстових задач на роботу (Chapter 1, Section 1.6) для MathLearning.
 * Генерує умови задач, приховані рішення та відповіді у форматі TASK-Z / TASK-Q.
 */
public class Chapter16WorkGenerator {

    private final Random random = new Random();

    public static class WorkTask {
        public String taskType; // TASK-Z або TASK-Q
        public String textEn;
        public String textUa;
        public String solution;
        public String answer;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(taskType).append(" \"").append(textUa).append("\"\n");
            sb.append("---\n");
            sb.append("? \"\"\n");
            sb.append("===\n");
            sb.append("_ \"Рішення.\"\n").append(solution).append("\n");
            sb.append("===\n");
            sb.append("---\n");
            sb.append(answer).append("\n");
            return sb.toString();
        }
    }

    /**
     * Генерує випадкову задачу на роботу.
     */
    public WorkTask generateRandomTask(int taskNumber) {
        int type = random.nextInt(2); // Два різних шаблони задач
        if (type == 0) {
            return generateTwoWorkersTask(taskNumber);
        } else {
            return generatePoolPipesTask(taskNumber);
        }
    }

    /**
     * Шаблон 1: Два робітники виконують роботу разом. 
     * Підбираються цілі числа (TASK-Z), щоб спільний час ділився без остачі.
     */
    private WorkTask generateTwoWorkersTask(int taskNumber) {
        WorkTask task = new WorkTask();
        task.taskType = "TASK-Z";

        int p1 = 3; int p2 = 2; int jointRate = 5; int hours = 6; int totalWork = 30;
        int days1 = 10; int days2 = 15;

        // Іноді випадково змінюємо параметри на інші красиві набори
        if (random.nextBoolean()) {
            p1 = 1; p2 = 1; jointRate = 2; hours = 4; totalWork = 8;
            days1 = 8; days2 = 8;
        }

        String numStr = String.format("%02d", taskNumber);
        task.textUa = String.format("TASK-Z %s. Один робітник може виконати замовлення за %d днів, а другий — за %d днів. За скільки днів вони виконають це замовлення, працюючи разом?", numStr, days1, days2);
        
        StringBuilder sol = new StringBuilder();
        sol.append(String.format("  \"1) Приймемо всю роботу за 1.\"\n"));
        sol.append(String.format("  \"Продуктивність першого: 1/%d, другого: 1/%d.\"\n", days1, days2));
        sol.append(String.format("  \"2) Спільна продуктивність: 1/%d + 1/%d = %d/%d = 1/%d.\"\n", days1, days2, jointRate, totalWork, hours));
        sol.append(String.format("  \"3) Час спільної роботи: 1 / (1/%d) = %d.\"\n", hours, hours));
        
        task.solution = sol.toString();
        task.answer = String.valueOf(hours);

        return task;
    }

    /**
     * Шаблон 2: Дві труби наповнюють басейн.
     * Відповідь формується у вигляді стандартного математичного дробу.
     */
    private WorkTask generatePoolPipesTask(int taskNumber) {
        WorkTask task = new WorkTask();
        task.taskType = "TASK-Q";
        
        int t1 = random.nextInt(4) + 4;  // 4..7 годин
        int t2 = random.nextInt(4) + 8;  // 8..11 годин

        String numStr = String.format("%02d", taskNumber);
        task.textUa = String.format("TASK-Q %s. Перша труба може наповнити басейн за %d годин, а друга — за %d годин. Яка частина басейну залишиться порожньою після 2 годин одночасної роботи обох труб?", numStr, t1, t2);

        int lcm = calculateLCM(t1, t2);
        int r1 = lcm / t1;
        int r2 = lcm / t2;
        int jointRateNum = r1 + r2; 
        
        int workDoneNum = jointRateNum * 2;
        int remainingNum = lcm - workDoneNum;
        
        // Якщо раптом вийшло від'ємне або нуль через випадкові числа, коригуємо на ходу
        if (remainingNum <= 0) {
            t1 = 6; t2 = 8; lcm = 24;
            remainingNum = 10; // 1 - 2*(1/6 + 1/8) = 1 - 2*(7/24) = 1 - 14/24 = 10/24
        }

        int gcd = calculateGCD(remainingNum, lcm);
        int finalNum = remainingNum / gcd;
        int finalDen = lcm / gcd;

        StringBuilder sol = new StringBuilder();
        sol.append(String.format("  \"1) Продуктивність першої труби: 1/%d, другої: 1/%d за годину.\"\n", t1, t2));
        sol.append(String.format("  \"2) За 2 години разом вони наповнять: 2 * (1/%d + 1/%d) = %d/%d.\"\n", t1, t2, workDoneNum, lcm));
        sol.append(String.format("  \"3) Залишкова порожня частина: 1 - %d/%d = %d/%d.\"\n", workDoneNum, lcm, finalNum, finalDen));
        
        task.solution = sol.toString();
        
        if (finalDen == 1) {
            task.answer = String.valueOf(finalNum);
        } else {
            // Змінено на стандартний математичний запис дробу (наприклад, 5/12 замість)
            task.answer = String.format("%d/%d", finalNum, finalDen);
        }

        return task;
    }

    private int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int calculateLCM(int a, int b) {
        return (a * b) / calculateGCD(a, b);
    }
}
 

