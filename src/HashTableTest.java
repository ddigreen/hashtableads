import java.util.Random;

public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
        Random rnd = new Random();

        // Добавляем 10000 случайных ключей
        for (int i = 0; i < 10_000; i++) {
            MyTestingClass key = new MyTestingClass(rnd.nextInt(5_000));
            table.put(key, i);
        }

        // Печать числа элементов в каждом бакете
        int[] sizes = table.bucketSizes();
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("Bucket %2d : %4d элементов%n", i, sizes[i]);
        }
    }
}
