/*  2. Запуск конвеєра (MainLauncher.java)
Для запуску генерації передайте шлях до цільової директорії, де система розгорне 56 унікальних текстових файлів.
 */
 
package com.mathpartner.generator;

import com.mathpartner.generator.chapters.chapter1.Chapter1Generator;

public class MainLauncherChapter1 {
    public static void main(String[] args) {
        Chapter1Generator ch1Generator = new Chapter1Generator();
        
        // Вказуємо папку для збереження результатів генерації
        String outputFolder = "output/chapter1";
        
        System.out.println("Запуск генерації 56 файлів для Глави 1...");
        ch1Generator.generateAndSaveAll(outputFolder);
        System.out.println("Генерацію успішно завершено!");
    }
   }

