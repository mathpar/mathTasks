/*
2. Фінальний збірний Chapter1Generator.java
Цей клас є серцем першої глави,  він генерує 56 окремих домашніх завдань
(Assignments), кожне з яких містить 10 різнопланових завдань
 (Tasks), та зберігає кожне завдання у свій індивідуальний
 файл assignment_XX.txt.
 */

 
package com.mathpartner.generator.chapters.chapter1;

import com.mathpartner.generator.model.Assignment;
import com.mathpartner.generator.model.Task;
import com.mathpartner.generator.engine.ColumnDivisionGenerator;
import com.mathpartner.generator.engine.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Головний генератор першої глави (Chapter 1).
 * Створює 56 окремих файлів завдань по 10 задач у кожному.
 */
public class Chapter1Generator {

    private final ColumnDivisionGenerator divisionGen = new ColumnDivisionGenerator();
    private final ColumnMultiplicationGenerator multiplicationGen = new ColumnMultiplicationGenerator();
    private final Chapter14ExpressionGenerator expressionGen = new Chapter14ExpressionGenerator();
    private final Chapter16MotionGenerator motionGen = new Chapter16MotionGenerator();
    private final Chapter16WorkGenerator workGen = new Chapter16WorkGenerator();
    
    private final Random random = new Random(42); // Фіксоване зерно для стабільності тестів


/**
 * Генерує всі 56 завдань і зберігає їх у вказану папку (викликається з MainLauncherChapter1).
 */
public void generateAndSaveAll(String outputDirectory) {
    // Вся логіка генерації циклу від 1 до 56 залишається без змін, як у попередньому коді
    java.io.File dir = new java.io.File(outputDirectory);
    if (!dir.exists()) {
        dir.mkdirs();
    }
         for (int i = 1; i <= 56; i++) {
            List<String> taskOutputs = new ArrayList<>();
            
            // Кожне домашнє завдання складається з 10 різнотипних задач
            for (int j = 1; j <= 10; j++) {
                int sectionType = random.nextInt(5); // Випадковий розподіл між підключеними модулями
                String taskId = String.format("T-%02d-%02d", i, j);
                
                switch (sectionType) {
                    case 0: // Умноження в стовпчик
                        int d1 = 2 + random.nextInt(2);
                        int d2 = 2 + random.nextInt(2);
                        ColumnMultiplicationGenerator.MultiplicationTask mTask = multiplicationGen.generateTask(d1, d2);
                        taskOutputs.add("TASK-Z \"Вычислите умножение столбиком.\"\n" + mTask.mathparRender);
                        break;
                        
                    case 1: // Ділення куточком (Сценарний вибір умов)
                        int divIdent = 100 + random.nextInt(900);
                        int divisor = 5 + random.nextInt(20);
                        int formType = random.nextInt(3); // 0-без остачі, 1-частка/остача, 2-мішане число
                        ColumnDivisionGenerator.DivisionTask dTask = divisionGen.generateTask(divIdent, divisor, formType);
                        taskOutputs.add(dTask.toString());
                        break;
                        
                    case 2: // Спрощення буквених виразів (Section 1.4)
                        Chapter14ExpressionGenerator.ExpressionTask eTask = expressionGen.generateExpressionTask(j);
                        taskOutputs.add(eTask.toString());
                        break;
                        
                    case 3: // Задачі на рух (Section 1.6)
                        Chapter16MotionGenerator.MotionTask motionTask = motionGen.generateRandomTask(j);
                        taskOutputs.add(motionTask.toString());
                        break;
                        
                    default: // Задачі на спільну роботу (Section 1.6)
                        Chapter16WorkGenerator.WorkTask workTask = workGen.generateRandomTask(j);
                        taskOutputs.add(workTask.toString());
                        break;
                }
            }

            // Запис сформованого асайменту у файл
            String fileName = String.format("assignment_%02d.txt", i);
            File file = new File(dir, fileName);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("=========================================\n");
                writer.write(String.format("ASSIGNMENT ДЛЯ МЕЖДУНАРОДНОЙ ШКОЛЫ №%02d\n", i));
                writer.write("=========================================\n\n");
                for (String tOut : taskOutputs) {
                    writer.write(tOut);
                    writer.write("\n\n#########################################\n\n");
                }
                System.out.println("Файл успішно збережено: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Помилка запису файлу " + fileName + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Chapter1Generator manager = new Chapter1Generator();
        // Генеруємо файли у папку "math_assignments" у корені проекту
        manager.generateAllAssignments("./math_assignments");
    }
}
 
 

