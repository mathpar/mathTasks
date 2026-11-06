/*
 Остаточний повний код класу Chapter5Generator.java

Тепер підключаємо новий модуль DecimalAverageGen у метод generateAverageSection, повністю ліквідуючи останній пропуск у конвеєрі Главе 5.
 */
 
package com.mathpartner.generator.chapters.chapter5;

import com.mathpartner.generator.model.Assignment;
import com.mathpartner.generator.model.Task;
import com.mathpartner.generator.engine.MathparRenderer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Chapter5Generator {

    private final DecimalArithmeticGen arithmeticGen = new DecimalArithmeticGen();
    private final DecimalRoundingGen roundingGen = new DecimalRoundingGen();
    private final DecimalAverageGen averageGen = new DecimalAverageGen();
    private final PercentageGen percentageGen = new PercentageGen();
    private final MathparRenderer renderer = new MathparRenderer();

    /**
     * Запускає генерацію та зберігає кожне з 72 завдань в окремий файл .txt без слова PAGE
     */
    public void generateAndSaveAll(String outputDirectory) {
        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Початковий абсолютний індекс для Глави 5 за планом (тижні 24–32)
        int absoluteIndex = 185; 

        // 5.1 та 5.2. Поняття та арифметика десяткових дробів (32 завдання -> 4 тижні)
        for (int i = 1; i <= 32; i++) {
            saveToFile(outputDirectory, generateArithmeticSection(absoluteIndex++));
        }

        // 5.3. Округлення чисел (8 завдань -> 1 тиждень)
        for (int i = 1; i <= 8; i++) {
            saveToFile(outputDirectory, generateRoundingSection(absoluteIndex++));
        }

        // 5.4. Середнє арифметичне (8 завдань -> 1 тиждень)
        for (int i = 1; i <= 8; i++) {
            saveToFile(outputDirectory, generateAverageSection(absoluteIndex++)); 
        }

        // 5.5. Відсотки (24 завдання -> 3 тижні)
        for (int i = 1; i <= 24; i++) {
            saveToFile(outputDirectory, generatePercentageSection(absoluteIndex++));
        }
    }

    private void saveToFile(String baseDir, Assignment assignment) {
        String header = assignment.getPageHeader();
        
        // Очищаємо назву файлу від слова PAGE
        String cleanName = header;
        if (header.toUpperCase().startsWith("PAGE ")) {
            cleanName = header.substring(5);
        }
        
        // Замінюємо пробіли на підкреслення
        cleanName = cleanName.trim().replaceAll("[\\s\\s]+", "_");
        
        File targetFile = new File(baseDir, cleanName + ".txt");
        try (FileWriter writer = new FileWriter(targetFile)) {
            writer.write(renderer.renderAssignment(assignment));
            System.out.println("Збережено файл Глави 5: " + targetFile.getName());
        } catch (IOException e) {
            System.err.println("Помилка запису файлу десяткових дробів: " + e.getMessage());
        }
    }

    private Assignment createHolder(int absIndex, String sectionTitle) {
        int week = ((absIndex - 1) / 8) + 1;
        int taskNum = ((absIndex - 1) % 8) + 1;
        String header = String.format("PAGE UA-SM-05-Week%02d-Task%02d %s", week, taskNum, sectionTitle.toUpperCase());
        return new Assignment(header);
    }

    private Assignment generateArithmeticSection(int absIndex) {
        Assignment a = createHolder(absIndex, "Арифметика десяткових дробів");
        for (int t = 1; t <= 10; t++) {
            a.addTask(arithmeticGen.generateTask(String.format("%02d", t)));
        }
        return a;
    }

    private Assignment generateRoundingSection(int absIndex) {
        Assignment a = createHolder(absIndex, "Округлення десяткових дробів");
        for (int t = 1; t <= 10; t++) {
            a.addTask(roundingGen.generateTask(String.format("%02d", t)));
        }
        return a;
    }

    /**
     * Метод генерації секції 5.4: Викликає реальний математичний модуль середніх величин
     */
    private Assignment generateAverageSection(int absIndex) {
        Assignment a = createHolder(absIndex, "Середнє арифметичне та середня швидкість");
        for (int t = 1; t <= 10; t++) {
            a.addTask(averageGen.generateTask(String.format("%02d", t)));
        }
        return a;
    }

    private Assignment generatePercentageSection(int absIndex) {
        Assignment a = createHolder(absIndex, "Відсотки та їх застосування");
        for (int t = 1; t <= 10; t++) {
            a.addTask(percentageGen.generateTask(String.format("%02d", t)));
        }
        return a;
    }
}
