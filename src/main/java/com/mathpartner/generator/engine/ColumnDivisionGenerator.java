package com.mathpartner.generator.engine;

import java.util.Random;

/**
 * Генератор ділення куточком (Column Division) із суворим контролем умов та відповідей.
 */
public class ColumnDivisionGenerator {

    private final Random random = new Random();

    public static class DivisionTask {
        public String taskType; // TASK-Z або TASK-Q
        public String textRu;
        public String solution;
        public String answer;

        @Override
        public String toString() {
            return taskType + " \"" + textRu + "\"\n" + answer;
        }
    }

    /**
     * Генерує задачу на ділення згідно із затвердженими типами формулювань.
     * 
     * @param formatType 0 — без остачі (точне ділення), 
     *                   1 — запит на частку та остачу [ціле, остача], 
     *                   2 — запит на цілу та дробову частину [ціле, чисельник/знаменник]
     */
    public DivisionTask generateTask(int dividend, int divisor, int formatType) {
        DivisionTask task = new DivisionTask();
        
        // Перевірка на точне ділення (якщо formatType = 0, підганяємо ділене)
        if (formatType == 0) {
            dividend = (dividend / divisor) * divisor;
        }
        
        int quotient = dividend / divisor;
        int remainder = dividend % divisor;
        
        // Якщо остачі фактично немає, це завжди просте ціле число
        if (remainder == 0) {
            task.taskType = "TASK-Z";
            task.textRu = "Вычислите деление уголком: " + dividend + " / " + divisor + " = ?";
            task.answer = String.valueOf(quotient);
            task.solution = "  \"" + dividend + " / " + divisor + " = " + quotient + "\"";
            return task;
        }

        if (formatType == 1) {
            // Форлювання тип 1
            task.taskType = "TASK-Z";
            task.textRu = "Найдите частное и остаток от деления " + dividend + " / " + divisor + " и запишите их в квадратных скобках через запятую.";
            task.answer = String.format("[%d, %d]", quotient, remainder);
            task.solution = String.format("  \"%d / %d = %d (ост. %d)\"\n  \"=== [%d, %d]\"", 
                    dividend, divisor, quotient, remainder, quotient, remainder);
                    
        } else {
            // Формулювання тип 2
            task.taskType = "TASK-Q";
            task.textRu = "Найдите целую и дробную часть частного от деления " + dividend + " / " + divisor + " и запишите их в квадратных скобках через запятую.";
            
            int gcd = calculateGCD(remainder, divisor);
            int finalNum = remainder / gcd;
            int finalDen = divisor / gcd;
            
            // Якщо ціла частина 0, то за правилом "всі інші випадки" пишемо просто як лінійний дріб
            if (quotient == 0) {
                task.answer = String.format("%d/%d", finalNum, finalDen);
            } else {
                task.answer = String.format("[%d, %d/%d]", quotient, finalNum, finalDen);
            }
            
            task.solution = String.format("  \"%d / %d = %d + %d/%d = [%d, %d/%d]\"", 
                    dividend, divisor, quotient, remainder, divisor, quotient, finalNum, finalDen);
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
