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
    Итерационно вычислить расстояние редактирования двух данных непустых строк
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

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int m = one.length();
        int n = two.length();

        int[][] a = new int[m + 1][n + 1];

        // Базовые случаи
        // Если одна из строк пустая, то расстояние редактирования равно длине другой строки. Поэтому заполняется первая строка и первый столбец массива a.
        for (int i = 0; i <= m; i++)
            a[i][0] = i;
        for (int j = 0; j <= n; j++)
            a[0][j] = j;

        // Заполнение матрицы dp
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //Если текущие символы в строках одинаковые, то расстояние редактирования равно расстоянию для предыдущих символов
                if (one.charAt(i - 1) == two.charAt(j - 1))
                    a[i][j] = a[i - 1][j - 1];
                else
                    //Если текущие символы в строках разные, то расстояние редактирования равно 1 + минимум из трех вариантов
                    a[i][j] = 1 + Math.min(Math.min(a[i - 1][j], a[i][j - 1]), a[i - 1][j - 1]);
            }
        }
// возвращаем минимальное расстояние редактирования
        return a[m][n];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_EditDist.class.getResourceAsStream("dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}