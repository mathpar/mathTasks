  /* проектування Глави 4: Звичайні дроби (завдання з 129 по 184 / тижні 17–23). Цей розділ є фундаментальним, оскільки в ньому школярі вперше виходять за межі цілих чисел.
Для нашої системи це означає автоматичний перехід багатьох завдань у режим TASK-Q (робота з раціональними числами), де відповідями будуть або правильні дроби числитель/знаменатель, або мішані числа у векторному форматі MathPartner [целая_часть, чисельник/знаменник].
Згідно з конфігурацією плану, Глава 4 містить 56 великих завдань (7 тижнів), розподілених на 4 теми:
• 4.1. Поняття дробу, координатний промінь (8 завдань)
• 4.2. Правильні і неправильні дроби, мішані числа (16 завдань)
• 4.3. Складання та віднімання звичайних дробів (16 завдань)
• 4.4. Текстові задачі на дроби (16 завдань)
Нижче представлено повну структуру підпакета chapters.chapter4, математичну логіку та вихідний код генераторів.

1. Структура підпакета chapters.chapter4

text
com.mathpartner.generator.chapters.chapter4
│
├── Chapter4Generator.java         // Головний диспетчер глави
├── FractionConceptGen.java        // Генератор для розділу 4.1
├── FractionTypesAndMixedGen.java  // Генератор для розділу 4.2
├── FractionArithmeticGen.java     // Генератор для розділу 4.3
└── FractionTextProblemsGen.java   // Генератор для розділу 4.4

6. Головний диспетчер глави: Chapter4Generator.java
Керує автоматичним експортом 56 індивідуальних файлів у відведену папку. Назви файлів формуються за вашим стандартом: без слова PAGE, а пробіли замінюються підкресленнями.
 */
 
package com.mathpartner.generator.chapters.chapter4;

import com.mathpartner.generator.model.Assignment;
import com.mathpartner.generator.engine.MathparRenderer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Chapter4Generator {

    private final FractionConceptGen section41 = new FractionConceptGen();
    private final FractionTypesAndMixedGen section42 = new FractionTypesAndMixedGen();
    private final FractionArithmeticGen section43 = new FractionArithmeticGen();
    private final FractionTextProblemsGen section44 = new FractionTextProblemsGen();
    private final MathparRenderer renderer = new MathparRenderer();

    public void generateAndSaveAll(String outputDirectory) {
        File dir = new File(outputDirectory);
        if (!dir.exists()) dir.mkdirs();

        // Початковий індекс для Глави 4 (після Глави 1 [56] та Глави 2)
        int absoluteIndex = 97; // Якщо Глава 3 була пропущена тимчасово, ставимо за планом тижнів 17..23 (абсолютний індекс 129)
        absoluteIndex = 129; 

        // 4.1. Поняття дробу (8 завдань)
        for (int i = 1; i <= 8; i++) {
            saveToFile(outputDirectory, generateSection41(absoluteIndex++));
        }

        // 4.2. Правильні/неправильні, мішані (16 завдань)
        for (int i = 1; i <= 16; i++) {
            saveToFile(outputDirectory, generateSection42(absoluteIndex++));
        }

        // 4.3. Додавання та віднімання дробів (16 завдань)
        for (int i = 1; i <= 16; i++) {
            saveToFile(outputDirectory, generateSection43(absoluteIndex++));
        }

        // 4.4. Текстові задачі на дроби (16 завдань)
        for (int i = 1; i <= 16; i++) {
            saveToFile(outputDirectory, generateSection44(absoluteIndex++));
        }
    }

    private void saveToFile(String baseDir, Assignment assignment) {
        String header = assignment.getPageHeader();
        String cleanName = header.toUpperCase().startsWith("PAGE ") ? header.substring(5) : header;
        cleanName = cleanName.trim().replaceAll("[\\s\\s]+", "_");
        
        File targetFile = new File(baseDir, cleanName + ".txt");
        try (FileWriter writer = new FileWriter(targetFile)) {
            writer.write(renderer.renderAssignment(assignment));
            System.out.println("Збережено файл Глави 4: " + targetFile.getName());
        } catch (IOException e) {
            System.err.println("Помилка запису файлу дробів: " + e.getMessage());
        }
    }

    private Assignment createHolder(int absIndex, String sectionTitle) {
        int week = ((absIndex - 1) / 8) + 1;
        int taskNum = ((absIndex - 1) % 8) + 1;
        String header = String.format("PAGE UA-SM-05-Week%02d-Task%02d %s", week, taskNum, sectionTitle.toUpperCase());
        return new Assignment(header);
    }

    private Assignment generateSection41(int absIndex) {
        Assignment a = createHolder(absIndex, "Поняття дробу та координатний промінь");
        for (int t = 1; t <= 10; t++) a.addTask(section41.generateTask(String.format("%02d", t)));
        return a;
    }

    private Assignment generateSection42(int absIndex) {
        Assignment a = createHolder(absIndex, "Види дробів та мішані числа");
        for (int t = 1; t <= 10; t++) a.addTask(section42.generateTask(String.format("%02d", t)));
        return a;
    }

    private Assignment generateSection43(int absIndex) {
        Assignment a = createHolder(absIndex, "Додавання та віднімання звичайних дробів");
        for (int t = 1; t <= 10; t++) a.addTask(section43.generateTask(String.format("%02d", t)));
        return a;
    }

    private Assignment generateSection44(int absIndex) {
        Assignment a = createHolder(absIndex, "Текстові задачі на звичайні дроби");
        for (int t = 1; t <= 10; t++) a.addTask(section44.generateTask(String.format("%02d", t)));
        return a;
    }
}

