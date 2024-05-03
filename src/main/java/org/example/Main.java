package org.example;
import java.util.*;
import java.util.Scanner;

public class Main {
    static ArrayList<Army> army = new ArrayList<>();
    static ArrayList<Food> food = new ArrayList<>();
    static Food meat = new Food("Meat", 100);
    static Food water = new Food("Water", 100);
    static boolean isRider = false;

    public static void main(String[] args) {
        food.add(meat); food.add(water);
        System.out.println("Добро пожаловать в средневековый замок!");
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 11) {
            System.out.println("===== Game Menu =====");
            System.out.println("1. Добавить солдат в армию");
            System.out.println("2. Удалить солдата из армии");
            System.out.println("3. Обновить воинское звание");
            System.out.println("4. Накормить армию");
            System.out.println("5. Пополнить запасы продовольствия");
            System.out.println("6. Добавить продукты в хранилище");
            System.out.println("7. Узнать количество солдат в армии");
            System.out.println("8. Удаление запасов еды");
            System.out.println("9. Узнать текущие запасы еды");
            System.out.println("10. Узнать характеристики");
            System.out.println("11. Выход из игрового меню");

            System.out.print("Введите свой выбор: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Введите звание: ");
                    scanner.nextLine();
                    String rank = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    int count = Integer.parseInt(scanner.nextLine());
                    int special1;
                    int special2;
                    if (!isRider) {
                        System.out.print("Введите уровень луков: ");
                        special1 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите уровень стрел: ");
                        special2 = Integer.parseInt(scanner.nextLine());
                    } else {
                        System.out.print("Введите количество лошадей: ");
                        special1 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите крепкость брони: ");
                        special2 = Integer.parseInt(scanner.nextLine());
                    }
                    addArmy(rank, count, special1, special2);
                    System.out.println("Солдаты успешно добавлены в армию!");
                    isRider = !isRider;
                }
                case 2 -> {
                    System.out.print("Введите звание: ");
                    scanner.nextLine();
                    String rank1 = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    int count1 = Integer.parseInt(scanner.nextLine());
                    removeArmy(rank1, count1);
                }
                case 3 -> {
                    System.out.print("Введите звание старое: ");
                    scanner.nextLine();
                    String rankOld = scanner.nextLine();
                    System.out.print("Введите звание новое: ");
                    String rankNew = scanner.nextLine();
                    System.out.print("Введите количество: ");
                    int countNew = Integer.parseInt(scanner.nextLine());
                    updateArmy(rankOld, rankNew, countNew);
                }
                case 4 -> {
                    System.out.print("Введите количество порций еды: ");
                    int portions = scanner.nextInt();
                    scanner.nextLine();
                    feedArmy(portions);
                }
                case 5 -> {
                    System.out.print("Введите количество еды для запаса: ");
                    int foodSupply = scanner.nextInt();
                    scanner.nextLine();
                    addSupply(foodSupply);
                }
                case 6 -> {
                    System.out.print("Введите название продукта, количество которого вы хотите увеличить: ");
                    scanner.nextLine();
                    String nameFood = scanner.nextLine();
                    System.out.print("Введите количество продуктов для добавления: ");
                    int foodCount = Integer.parseInt(scanner.nextLine());
                    boolean isAvailable = isFoodTypeAvailable(nameFood);
                    if (isAvailable) {
                        modifySupply(nameFood, foodCount);
                    } else {
                        System.out.println("Этот вид не входит в состав продуктов питания");
                    }
                }
                case 7 -> {
                    printArmySize();
                }
                case 8 -> {
                    System.out.print("Введите название еды, которую ходите удалить в количестве: ");
                    scanner.nextLine();
                    String nameFood2 = scanner.nextLine();
                    System.out.print("Введите количество еды для удаления: ");
                    int foodCount2 = Integer.parseInt(scanner.nextLine());
                    removeSupply(nameFood2, foodCount2);
                }
                case 9 -> {
                    System.out.print("Текущие запасы еды: ");
                    printFoodSupply();
                }
                case 10 -> {
                    System.out.print("Введите id война");
                    scanner.nextLine();
                    int id = scanner.nextInt();
                    if (id >= army.size())
                    {
                        System.out.print("Id не существует");
                    }
                    else
                    {
                       army.get(id).Work();
                    }
                }
                case 11 -> {
                    System.out.println("Выход из игрового меню");
                }
                default -> {
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            }
        }
        scanner.close();
    }
    public static void addArmy(String rank, int count, int special1, int special2) {
        Army member;
        if (isRider){
            member = new Riders(rank, count, special1, special2);
        }
        else {
            member = new Archers(rank, count, special1, special2);
        }
        army.add(member);
    }
    public static void removeArmy(String rank, int count) {
        for (Army item : army)
        {
            if (Objects.equals(item.getRank(), rank))
            {
                item.setCount(item.getCount()-count);
                if (item.getCount() <= 0)
                {
                    army.remove(item);
                }
            }
        }
    }
    public static void updateArmy(String oldRank, String newRank, int newCount) {
        boolean foundOldRank = false;

        for (Army armyMember : army) {
            if (armyMember.getRank().equals(oldRank)) {
                foundOldRank = true;
                if (newCount > 0) {
                    armyMember.setRank(newRank);
                    armyMember.setCount(newCount);
                } else {
                    System.out.println("Новый подсчет < 0");
                }
                break;
            }
        }

        if (!foundOldRank) {
            System.out.println("Такого звания не существует");
        }
    }

    public static void printArmySize() {
        System.out.println("Солдаты в армии: " + army.size());
    }

    public static void feedArmy(Integer portions) {
        if (portions > 0) {
            int allmembers = 0;
            for(Army member : army){
                allmembers += member.count;
            }
            int allPortions = allmembers*portions;
            boolean isEnough = true;
            for(Food item : food){
                if(!(item.getCount() >= allPortions)) {
                    isEnough = false;
                }
            }
            if(isEnough){
                for(Food item : food){
                    item.setCount(item.getCount()-allPortions);
                }
            }
            else {
                int deficit = allPortions - food.getFirst().getCount();
                for (Army member : army) {
                    member.setCount(member.getCount()-deficit);
                }
                for(Food item : food){
                    item.setCount(item.getCount()-allPortions);
                }
            }
        } else {
            System.out.println("Порции не могут быть меньше 0");
        }
    }
    public static void addSupply(int count){
        for (Food supply : food)
        {
            supply.setCount(count);
        }
    }
    public static void removeSupply(String type, int count) {
        for (Food item : food){
            if(Objects.equals(item.getType(), type)){
                if (item.getCount() >= count) {
                    item.setCount(count);
                } else {
                    System.out.println("Количество не может быть больше текущего количества");
                }
                return;
            }
        }
        System.out.println("Этот тип не входит в состав продуктов питания");
    }
    public static void modifySupply(String type, int count) {
        for(Food item : food){
            if(Objects.equals(item.getType(), type)){
                if (item.getCount() + count >= 0) {
                    item.setCount(item.getCount() + count);
                } else {
                    System.out.println("Сумма меньше 0");
                }
                return;
            }
        }
        System.out.println("Этот тип не входит в состав продуктов питания");
    }
    public static boolean isFoodTypeAvailable(String type) {
        for (Food item : food) {
            if (Objects.equals(item.getType(), type)) {
                return true;
            }
        }
        return false;
    }
    public static void printFoodSupply() {
        System.out.println("Текущие запасы продовольствия:");
        for (Food item : food) {
            System.out.println(item.getType() + ": " + item.getCount());
        }
    }
}