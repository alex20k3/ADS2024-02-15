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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки
    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,
    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,
    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,
    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int m = one.length();
        int n = two.length();
        int[][] a = new int[m + 1][n + 1];//таблица динамического программирования

        for (int i = 0; i <= m; i++) {
            a[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            a[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (one.charAt(i - 1) == two.charAt(j - 1)) {//если символы совпадают, то расстояние равно расстоянию между предыдущими символами
                    a[i][j] = a[i - 1][j - 1];
                } else {//иначе равно 1 + минимум из трех случаев: вставки, удаления или замены
                    a[i][j] = 1 + Math.min(Math.min(a[i - 1][j], a[i][j - 1]), a[i - 1][j - 1]);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        int i = m, j = n;
        // обратный проход для восстановления последовательности операций, необходимых для преобразования одной строки в другую
        while (i > 0 || j > 0) {
            //если элементы равны добавляем смивол #
            if (i > 0 && j > 0 && one.charAt(i - 1) == two.charAt(j - 1)) {
                result.insert(0, "#,");
                i--;
                j--;
            } else {
                // если требуется замена  ~ и заменяемый символ
                if (i > 0 && j > 0 && a[i - 1][j - 1] < a[i - 1][j] && a[i - 1][j - 1] < a[i][j - 1]) {
                    result.insert(0, "~" + two.charAt(j - 1) + ",");
                    i--;
                    j--;
                    // вставка  + и вставляемый символ
                } else if (j > 0 && (i == 0 || a[i][j - 1] <= a[i - 1][j])) {
                    result.insert(0, "+" + two.charAt(j - 1) + ",");
                    j--;
                    // удаление  - и удаляемый символ
                } else {
                    result.insert(0, "-" + one.charAt(i - 1) + ",");
                    i--;
                }
            }
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString(); //строка операций для преобразования первой строки во вторую
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}