 
package com.mathpartner.generator.engine;
import java.util.Random;
/**
 * Генератор задач на умножение в столбик (Long Multiplication).
 * Формирует промежуточные произведения с выравниванием и финальную сумму.
 */
public class ColumnMultiplicationGenerator {

    private final Random random = new Random();

    public static class MultiplicationTask {
        public int multiplicand;
        public int multiplier;
        public int[] intermediateProducts;
        public int finalResult;
        public String mathparRender;

        @Override
        public String toString() {
            return mathparRender;
        }
    }

    /**
     * Генерирует задачу на умножение двух чисел заданной разрядности.
     * 
     * @param digits1 количество знаков в первом множителе
     * @param digits2 количество знаков во втором множителе
     * @return Объект MultiplicationTask с результатами и Mathpar-строкой
     */
    public MultiplicationTask generateTask(int digits1, int digits2) {
        MultiplicationTask task = new MultiplicationTask();
        
        task.multiplicand = generateRandomNumber(digits1);
        task.multiplier = generateRandomNumber(digits2);
        task.finalResult = task.multiplicand * task.multiplier;
        
        String multiplierStr = String.valueOf(task.multiplier);
        int len = multiplierStr.length();
        task.intermediateProducts = new int[len];
        
        // Вычисляем промежуточные произведения (идем справа налево по множителю)
        for (int i = 0; i < len; i++) {
            int digit = Character.getNumericValue(multiplierStr.charAt(len - 1 - i));
            task.intermediateProducts[i] = task.multiplicand * digit;
        }
        
        task.mathparRender = buildMathparOutput(task);
        return task;
    }

    private int generateRandomNumber(int digits) {
        if (digits <= 0) return 0;
        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;
        return min + random.nextInt(max - min + 1);
    }

    /**
     * Форматирует вывод в синтаксисе Mathpar, заворачивая текстовые элементы 
     * в двойные кавычки согласно требованиям среды.
     */
    private String buildMathparOutput(MultiplicationTask task) {
        StringBuilder sb = new StringBuilder();
        
        String m1 = String.valueOf(task.multiplicand);
        String m2 = String.valueOf(task.multiplier);
        String res = String.valueOf(task.finalResult);
        
        int maxLength = res.length() + 2; // Запас для знаков выравнивания
        
        sb.append("multColumn(\n");
        sb.append(String.format("  \"%" + maxLength + "s\",\n", m1));
        sb.append(String.format("  \"*%" + (maxLength - 1) + "s\",\n", m2));
        
        // Линия подчеркивания
        String line = "-".repeat(Math.max(m1.length(), m2.length()) + 1);
        sb.append(String.format("  \"%" + maxLength + "s\",\n", line));
        
        // Добавление промежуточных произведений со смещением
        for (int i = 0; i < task.intermediateProducts.length; i++) {
            if (task.intermediateProducts.length == 1) break; // Если множитель однозначный, промежуточные не нужны
            
            String prodStr = String.valueOf(task.intermediateProducts[i]);
            // Сдвиг влево на i позиций для каждого шага имитируется пробелами справа в формате,
            // либо пробелами слева для выравнивания в столбик:
            int currentPadding = maxLength - i;
            
            sb.append(String.format("  \"%" + currentPadding + "s\",\n", prodStr));
        }
        
        if (task.intermediateProducts.length > 1) {
            sb.append(String.format("  \"%" + maxLength + "s\",\n", "-".repeat(res.length())));
        }
        
        sb.append(String.format("  \"%" + maxLength + "s\"\n", res));
        sb.append(")");
        
        return sb.toString();
    }

    public static void main(String[] args) {
        ColumnMultiplicationGenerator generator = new ColumnMultiplicationGenerator();
        // Пример генерации: 3 знака на 3 знака (например, 234 * 413)
        MultiplicationTask task = generator.generateTask(3, 3);
        System.out.println("Generated Task Mathpar Format:\n");
        System.out.println(task.mathparRender);
    }
}
 
