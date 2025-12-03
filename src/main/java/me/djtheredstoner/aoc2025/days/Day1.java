package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.List;

public class Day1 implements DayBase {

    public void init(List<String> lines) {
        
    }

    public void part1(List<String> lines) {
        int d = 50;
        int c = 0;
        for (String l : lines) {
            int dist = Integer.parseInt(l.substring(1));
            if (l.charAt(0) == 'L') dist *= -1;
            d = ((d + 100) + dist) % 100;
            if (d == 0) c++;
        }
        System.out.println(c);
    }

    public void part2(List<String> lines) {
        int d = 50;
        int c = 0;
        for (String l : lines) {
            int dist = Integer.parseInt(l.substring(1));
            c += dist / 100;
            dist %= 100;
            if (l.charAt(0) == 'L') dist *= -1;
            int prevD = d;
            d += dist;
            if (d < 0) {
                if (prevD != 0) c++;
                d+=100;
            }
            else if (d > 99) {
                c++;
                d-=100;
            }
            else if (dist != 0 && d == 0) c++;
        }
        System.out.println(c);
    }

    public static void main(String...args) {
        new Day1().test(1);
        new Day1().test(2);
        new Day1().run();
    }
}
