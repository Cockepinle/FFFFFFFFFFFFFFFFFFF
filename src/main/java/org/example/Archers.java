package org.example;

public class Archers extends Army{
    @Override
    void Work() {
        System.out.println("Теперь ваши характеристики такие:");
        System.out.println(this.rank);
        System.out.println(this.count);
        System.out.println(this.bowLevel);
        System.out.println(this.aroowsLevel);

    }


    private int bowLevel;
    private int aroowsLevel;
    public Archers(String rank, int count, int bowLevel, int aroowsLevel) {
        super(rank, count);
        this.bowLevel = bowLevel;
        this.aroowsLevel = aroowsLevel;
    }
}