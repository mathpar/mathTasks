
/*
4. Реалізація генератора розділу 3.3: PrimeAndCompositeGen.java
Цей клас генерує завдання на визначення типу числа або його розкладання на прості множники.
*/
package com.mathpartner.generator.chapters.chapter3;

import com.mathpartner.generator.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimeAndCompositeGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        
        if (type == 0) {
            // Розкладання на прості множники
            int[] pool = {12, 18, 20, 28, 30, 42};
            int n = pool[random.nextInt(pool.length)];
            
            List<Integer> factors = new ArrayList<>();
            int temp = n;
            for (int i = 2; i <= temp; i++) {
                while (temp % i == 0) {
                    factors.add(i);
                    temp /= i;
                }
            }
            
            // Записуємо у векторний формат для впорядкованого результату множників: [2, 2, 3]
            StringBuilder ans = new StringBuilder("[");
            for (int i = 0; i < factors.size(); i++) {
                ans.append(factors.get(i));
                if (i < factors.size() - 1) ans.append(", ");
            }
            ans.append("]");

            Task task = new Task(taskId, "TASK-Z", "Розкладіть число " + n + " на прості множники.", ans.toString());
            task.addTextStep("Послідовно поділимо число на найменші прості дільники:");
            task.addTextStep("Отримаємо прості множники: " + factors.toString());
            return task;
        } else {
            // Визначення простого чи складеного числа
            int[] primes = {17, 19, 23, 29, 31};
            int p = primes[random.nextInt(primes.length)];
            
            Task task = new Task(taskId, "TASK-Z", "Чи є число " + p + " простим?\n" +
                    "Якщо так, запишіть у відповідь 1, якщо воно складене — 0.", "1");
            task.addTextStep("Число називається простим, якщо воно має рівно два дільники: одиницю і саме себе.");
            task.addTextStep("Число " + p + " не має інших дільників, тому воно є простим.");
            return task;
        }
    }
}

