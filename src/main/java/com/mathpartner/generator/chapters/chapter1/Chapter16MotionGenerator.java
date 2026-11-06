 
package com.mathpartner.generator.chapters.chapter1;

import java.util.Random;

/**
 * Генератор текстових задач на рух (Chapter 1, Section 1.6) для MathLearning.
 * Забезпечує виключно лінійний формат відповіді виду a/b для дробових результатів.
 */
public class Chapter16MotionGenerator {

    private final Random random = new Random();

    public static class MotionTask {
        public String taskType; // TASK-Z або TASK-Q
        public String textRu;
        public String solution;
        public String answer;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(taskType).append(" \"").append(textRu).append("\"\n");
            sb.append("---\n");
            sb.append("? \"\"\n");
            sb.append("===\n");
            sb.append("_ \"Решение.\"\n").append(solution).append("\n");
            sb.append("===\n");
            sb.append("---\n");
            sb.append(answer).append("\n");
            return sb.toString();
        }
    }

    public MotionTask generateRandomTask(int taskNumber) {
        int type = random.nextInt(2); 
        if (type == 0) {
            return generateTowardsEachOtherTask(taskNumber);
        } else {
            return generateRiverFlowTask(taskNumber);
        }
    }

    /**
     * Шаблон 1: Рух назустріч один одному.
     */
    private MotionTask generateTowardsEachOtherTask(int taskNumber) {
        MotionTask task = new MotionTask();
        
        int v1 = 60 + random.nextInt(31); // 60..90 км/год
        int v2 = 70 + random.nextInt(31); // 70..100 км/год
        int vSum = v1 + v2;
        
        // Генеруємо час так, щоб іноді виходив дріб
        int tNumerator = vSum * 2 + random.nextInt(50);
        int gcd = calculateGCD(tNumerator, vSum);
        
        int finalNum = tNumerator / gcd;
        int finalDen = vSum / gcd;
        
        String numStr = String.format("%02d", taskNumber);
        task.textRu = String.format("Из двух городов навстречу друг другу одновременно выехали два автомобиля. Скорость первого %d км/ч, а второго — %d км/ч. Через сколько часов они встретятся, если расстояние между городами %d км?", v1, v2, tNumerator);
        
        task.solution = String.format("  \"1) Скорость сближения: %d + %d = %d км/ч.\"\n  \"2) Время до встречи: %d / %d = %d/%d ч.\"", v1, v2, vSum, tNumerator, vSum, finalNum, finalDen);

        if (finalDen == 1) {
            task.taskType = "TASK-Z";
            task.answer = String.valueOf(finalNum);
        } else {
            task.taskType = "TASK-Q";
            task.answer = String.format("%d/%d", finalNum, finalDen);
        }
        
        return task;
    }

    /**
     * Шаблон 2: Рух за течією та проти течії річки.
     */
    private MotionTask generateRiverFlowTask(int taskNumber) {
        MotionTask task = new MotionTask();
        
        int vOwn = 15 + random.nextInt(10); // 15..24 км/год
        int vRiver = 2 + random.nextInt(3);  // 2..4 км/год
        
        int vAlong = vOwn + vRiver;
        int vAgainst = vOwn - vRiver;
        
        int s = vAlong * 2; // Відстань в один бік
        
        // Час проти течії = s / vAgainst
        int gcd = calculateGCD(s, vAgainst);
        int finalNum = s / gcd;
        int finalDen = vAgainst / gcd;
        
        String numStr = String.format("%02d", taskNumber);
        task.textRu = String.format("Собственная скорость катера %d км/ч, а скорость течения реки %d км/ч. Катер прошел за теченим реки расстояние, равное %d км. Сколько времени потратит катер на обратный путь против течения?", vOwn, vRiver, s);
        
        task.solution = String.format("  \"1) Скорость против течения: %d - %d = %d км/ч.\"\n  \"2) Время на обратный путь: %d / %d = %d/%d ч.\"", vOwn, vRiver, vAgainst, s, vAgainst, finalNum, finalDen);

        if (finalDen == 1) {
            task.taskType = "TASK-Z";
            task.answer = String.valueOf(finalNum);
        } else {
            task.taskType = "TASK-Q";
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
}
 
