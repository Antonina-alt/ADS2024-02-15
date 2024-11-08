package by.it.group310901.sorochuk.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
//класс C_GreedyKnapsack, который содержит вложенный статический класс Item.
// Класс Item представляет элемент, который имеет поля cost (стоимость) и weight (вес).
public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Item other) {
            //тут может быть ваш компаратор
            double specificWeightCurrent = ((double) cost) / weight;
            double specificWeightOther = ((double) other.cost) / other.weight;

            return Double.compare(specificWeightOther, specificWeightCurrent);
        }
    }
    //Метод calc(File source) выполняет решение задачи о рюкзаке.
//Он считывает данные из файла, создает массив предметов,
//выводит информацию о предметах и их общее количество, а затем приступает к алгоритму сборки рюкзака.
    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        //Массив items сортируется по убыванию отношения стоимость/вес, используя лямбда-выражение.
//Затем выполняется цикл по элементам массива items в обратном порядке.
//Если вес текущего предмета <= оставшейся вместимости рюкзака (W),то его стоимость
// добавляется к общей стоимости (result), а его вес вычитается из оставшейся вместимости.
//Если вес текущего предмета > W, то его стоимость делится на вес и
// умножается на оставшуюся вместимость (W), а затем добавляется к общей стоимости.
        Arrays.sort(items);
        double result = 0;
        int totalWeight = 0;
        for (Item item : items) {
            if (totalWeight + item.weight > W) {
                result += ((double) item.cost) / item.weight * (W - totalWeight);
                break;
            }
            result += item.cost;
            totalWeight += item.weight;
        }
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item

        //ваше решение.





        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }
    //В методе main создается экземпляр класса B_Sheduler, создается массив событий events
// и вызывается метод calcStartTimes для расчета оптимального заполнения аудитории.
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }
}