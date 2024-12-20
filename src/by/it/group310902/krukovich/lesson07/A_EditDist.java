package by.it.group310902.krukovich.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/
Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.
Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк
    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0
    Sample Input 2:
    short
    ports
    Sample Output 2:
    3
    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5
*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        if (one.length() == 0) { //Если одна из строк пуста, то расстояние редактирования равно длине другой строки, так как необходимо вставить/удалить все символы.
            return two.length();
        }

        if (two.length() == 0) {
            return one.length();
        }

        int result = 0;
        if (one.charAt(0) == two.charAt(0)) {
            result = getDistanceEdinting(one.substring(1), two.substring(1));//если символы равны, пропускаем символы и переходим к сравнению оставшихся частей строк
        } else {//расстояние редактирования равно 1 + минимум из трех вариантов
            int insert = getDistanceEdinting(one, two.substring(1));//вычисляме расстояния редактирования для первой строки и укороченной на один символ второй

            int delete = getDistanceEdinting(one.substring(1), two);
            int replace = getDistanceEdinting(one.substring(1), two.substring(1));
            // выбираем наименьший из трех вариантов
            result = 1 + Math.min(Math.min(insert, delete), replace);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
