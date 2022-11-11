package org.itstep;

import java.util.*;

public class Task01 {
    public static void main(String[] args) {
        // Ваш код пишите здесь
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String show = "";
        String canal = "";
        boolean showBoolean = false;
        boolean canalBoolean = false;
        TreeSet<String> showsCanal = null;
        int n;

        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        TreeSet<String> fox = new TreeSet<>(comparator);        // (o1, o2) -> o1.compareTo(o2)
        TreeSet<String> nbc = new TreeSet<>(comparator);
        TreeSet<String> abc = new TreeSet<>(comparator);
        TreeSet<String> cbs = new TreeSet<>(comparator);

        Map<String, TreeSet<String>> networkMap = new HashMap<>();
        networkMap.put("Fox", fox);
        networkMap.put("NBC", nbc);
        networkMap.put("ABC", abc);
        networkMap.put("CBS", cbs);

        fox.add("The Simpsons");
        nbc.add("ER");
        abc.add("20/20");
        cbs.add("Survivor");
        abc.add("Lost");
        fox.add("Family Guy");
        cbs.add("CSI");
        fox.add("American Idol");
        abc.add("Desperate Housewives");
        nbc.add("Law And Order");
        System.out.println(networkMap);

        // Основная программа
        while (true) {
            canalBoolean = false;
            showBoolean = false;
            System.out.print("1-Работа со словарем каналов, 2-Вывод словаря, 3-Вывод словаря с сортировкой по алфавиту, 0-Exit >>> ");
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    System.out.print("Введите название шоу >>> ");
                    show = scanner1.nextLine();
                    System.out.print("Введите название канала >>> ");
                    canal = scanner1.nextLine();
                    label:
                    // Проверка на наличие канала и шоу в словаре (ищем шоу только нужного канала,
                    // шоу с идентичным названием другого канала не интересует)
                    for (Map.Entry<String, TreeSet<String>> node : networkMap.entrySet()) {
                        if (node.getKey().equalsIgnoreCase(canal)) {
                            canalBoolean = true;
                            for (String s : node.getValue()) {
                                if (show.equalsIgnoreCase(s)) {
                                    showBoolean = true;
                                    break label;
                                }
                            }
                        }
                    }

                    if (canalBoolean && showBoolean) {  // В словаре имеются и канал и шоу
                        printShow(networkMap, canal, show);
                    } else if (canalBoolean) {          // В словаре имеются только канал
                        networkMap.get(canal).add(show);
                        printShow(networkMap, canal, show);
                    } else {                            // В словаре нет канала и, соответственно, необходимого шоу
                        showsCanal = null;
                        showsCanal = new TreeSet<>(comparator);
                        showsCanal.add(show);
                        networkMap.put(canal, showsCanal);
                        printShow(networkMap, canal, show);
                    }
                    break;
                case 2:
                    printBase(networkMap);
                    break;
                case 3:
                    printSortBase(networkMap);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Вы неправильно ввели данные");
                    break;
            }
        }

    }

    private static void printShow(Map<String, TreeSet<String>> networkMap, String canal, String show) {
        label:
        for (Map.Entry<String, TreeSet<String>> node : networkMap.entrySet()) {
            if (node.getKey().equalsIgnoreCase(canal)) {
                for (String s : node.getValue()) {
                    if (show.equalsIgnoreCase(s)) {
                        System.out.println(node);
                        break label;
                    }
                }
            }
        }
    }


    private static void printBase(Map<String, TreeSet<String>> networkMap) {
        for (Map.Entry<String, TreeSet<String>> node : networkMap.entrySet()) {
            System.out.println(node);
        }
    }


    private static void printSortBase(Map<String, TreeSet<String>> networkMap) {
//        System.out.println(networkMap.size());
        String[] arr = new String[networkMap.size()];
        int i = 0;
        for (Map.Entry<String, TreeSet<String>> node : networkMap.entrySet()) {
            arr[i] = node.getKey();
            i++;
        }
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr, (o1, o2) -> o1.compareTo(o2));
        System.out.println(Arrays.toString(arr));
        for (int j = 0; j < arr.length; j++) {
            // Как получить элемент (ключ: значение), имея ключ и без перебора в цикле
            //            System.out.println(networkMap.get(arr[j]));
            for (Map.Entry<String, TreeSet<String>> node : networkMap.entrySet()) {
                if (arr[j].equals(node.getKey())) {
                    System.out.println(node);
                    break;
                }
            }
        }

    }

}