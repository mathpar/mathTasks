/* Прийнято! Змінюємо черговість та переходимо до проектування Глави 3: Подільність натуральних чисел (завдання з 97 по 128 / тижні 13–16). Це чудовий тактичний хід, оскільки алгоритми подільності, пошуку дільників та розкладання на прості множники є виключно числовими і закладають ідеальну базу перед дробовими темами.
Згідно з нашою загальною конфігурацією (CoursePlanConfig), для Главе 3 виділено 32 великих завдання (4 навчальних тижні), які розподілені так:
• 3.1. Дільники і кратні (8 завдань)
• 3.2. Ознаки подільності (16 завдань)
• 3.3. Прості та складені числа (8 завдань)
Нижче представлено повну структуру підпакета chapters.chapter3, архітектуру та готовий вихідний код базового класу-генератора разом із допоміжними математичними модулями.

1. Структура підпакета chapters.chapter3

Усі файли логічно розміщуються у відповідній директорії проєкту:
text
com.mathpartner.generator.chapters.chapter3
│
├── Chapter3Generator.java        // Головний диспетчер глави
├── DivisorsAndMultiplesGen.java   // Генератор для розділу 3.1
├── DivisibilityRulesGen.java     // Генератор для розділу 3.2
└── PrimeAndCompositeGen.java     // Генератор для розділу 3.3
Будьте обачні, використовуючи код.
*/

/*
2. Реалізація генератора розділу 3.1: DivisorsAndMultiplesGen.java

Цей клас генерує завдання на пошук усіх дільників або спільних кратних. Зверніть увагу: оскільки дільників або кратних може бути декілька, відповіді автоматично пакуються у математичні об'єкти платформи MathPartner: множини \{...\} (порядок неважливий) або вектори [...] (якщо просимо впорядкувати).
 
 */
 
package com.mathpartner.generator.chapters.chapter3;

import com.mathpartner.generator.model.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DivisorsAndMultiplesGen {
    private final Random random = new Random();

    public Task generateTask(String taskId) {
        int type = random.nextInt(2);
        
        if (type == 0) {
            // Знайдіть усі дільники числа N
            int[] pool = {12, 18, 20, 24, 28, 30};
            int n = pool[random.nextInt(pool.length)];
            
            List<Integer> divisors = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) divisors.add(i);
            }
            
            // Формуємо відповідь у вигляді множини \{1, 2, ...\}
            StringBuilder ans = new StringBuilder("\\{");
            for (int i = 0; i < divisors.size(); i++) {
                ans.append(divisors.get(i));
                if (i < divisors.size() - 1) ans.append(", ");
            }
            ans.append("\\}");

            Task task = new Task(taskId, "TASK-Z", "Знайдіть усі дільники числа " + n + ".", ans.toString());
            task.addTextStep("Перевіримо всі натуральні числа, на які задане число ділиться без остачі:");
            task.addTextStep("Дільниками числа " + n + " є: " + divisors.toString());
            return task;
        } else {
            // Знайдіть найменше спільне кратне (НСК) двох чисел
            int a = 4 + random.nextInt(5); // 4..8
            int b = 6 + random.nextInt(6); // 6..11
            if (a == b) b += 2;
            
            int gcd = 1;
            for (int i = 1; i <= Math.min(a, b); i++) {
                if (a % i == 0 && b % i == 0) gcd = i;
            }
            int lcm = (a * b) / gcd;

            Task task = new Task(taskId, "TASK-Z", String.format("Знайдіть найменше спільне кратне (НСК)\nчисел %d та %d.", a, b), String.valueOf(lcm));
            task.addTextStep(String.format("Знайдемо найменше натуральне число, яке ділиться і на %d, і на %d:", a, b));
            task.addTextStep("Шукане найменше спільне кратне дорівнює:");
            task.addTextStep(String.valueOf(lcm));
            return task;
        }
    }
}
 
 

