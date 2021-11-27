package module14;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) {

        /*

        Cгенерируйте последовательность из 5 случайных чисел от 0 до 50,
        отфильтруйте последовательность так, чтобы остались только числа меньше 10,
        наконец, оставьте первое число в последовательности.

        Выведите в консоль его или 99, если такого числа нет.

        Попробуйте записать весь ответ одним выражением внутри метода main.

        */

        System.out.println((IntStream.generate(()-> new Random().nextInt(51))
                .limit(5)
                .filter((val) -> val < 10)
                .findFirst()).orElse(99));

    }
}
