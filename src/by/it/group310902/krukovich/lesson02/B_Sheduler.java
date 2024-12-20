package by.it.group310902.krukovich.lesson02;

import java.util.ArrayList;
import java.util.List;
/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        //рассчитаем оптимальное заполнение аудитории
        List<Event> starts = instance.calcStartTimes(events, 0, 10);
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }


    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.


        int start = 0;
        int maxStop = start;


        for (Event event : events)
        {
            if (event.stop > maxStop)
            {
                maxStop = event.stop;
            }
        }

        int end = start + 1;

        while(end <= maxStop)
        {
            boolean isFound = false;
            for (Event event : events)
            {
                if (event.start == start && event.stop == end)
                {
                    isFound = true;
                    break;
                }
            }

            if(isFound)
            {
                result.add(new Event(start, end));
                start = end;
                end = start + 1;
            }else
            {
                if(end == maxStop)
                {
                    start++;
                    end = start+1;
                }
                else
                    end++;
            }
        }

        return result;          //вернем итог
    }
}