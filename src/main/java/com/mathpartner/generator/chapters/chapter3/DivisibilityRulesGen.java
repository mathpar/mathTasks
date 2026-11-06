/* 3. Реалізація генератора розділу 3.2: DivisibilityRulesGen.java

Цей клас фокусується на ознаках подільності на 2, 3, 5, 9, 10. Задачі формулюються однозначно: просимо вибрати парні числа, числа, що діляться на 5, або підставити цифру.
/*/

package com.mathpartner.generator.chapters.chapter3;

import com.mathpartner.generator.model.Task;
import java.util.Random;

public class DivisibilityRulesGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(3);
        
        switch (type) {
            case 0: // Ознака подільності на 5 або 10
                int base = 100 + random.nextInt(800);
                int lastDigit = random.nextBoolean() ? 0 : 5;
                int num5 = base * 10 + lastDigit;
                
                Task task0 = new Task(taskId, "TASK-Z", "Чи ділиться число " + num5 + " на 5?\n" +
                        "Якщо ділиться, запишіть у відповідь 1, якщо ні — 0.", "1");
                task0.addTextStep("На 5 діляться всі натуральні числа, які закінчуються цифрою 0 або 5.");
                task0.addTextStep("Число закінчується на " + lastDigit + ", тому воно ділиться на 5.");
                return task0;

            case 1: // Ознака подільності на 3 або 9
                int d1 = 1 + random.nextInt(8);
                int d2 = random.nextInt(9);
                int sum = d1 + d2;
                // Підбираємо третю цифру так, щоб сума ділилася на 3
                int d3 = (3 - (sum % 3)) % 3;
                int num3 = d1 * 100 + d2 * 10 + d3;
                
                Task task1 = new Task(taskId, "TASK-Z", "Обчисліть суму цифр числа " + num3 + "\n" +
                        "та перевірте, чи ділиться воно на 3.", String.valueOf(d1 + d2 + d3));
                task1.addTextStep("Число ділиться на 3, якщо сума його цифр ділиться на 3. Знайдемо суму:");
                task1.addTextStep(d1 + " + " + d2 + " + " + d3 + " = " + (d1 + d2 + d3));
                return task1;

            default: // Задачі на підстановку зірочки
                int start = 450;
                Task task2 = new Task(taskId, "TASK-Z", "Яку найменшу цифру потрібно поставити замість *\n" +
                        "у записі числа 45*, щоб воно ділилося на 2?", "0");
                task2.addTextStep("Щоб число ділилося на 2, воно має закінчуватися парною цифрою (0, 2, 4, 6, 8).");
                task2.addTextStep("Найменша з цих цифр — це 0.");
                return task2;
        }
    }
}
 
