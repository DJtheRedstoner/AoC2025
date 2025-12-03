package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.List;

public class Day3 implements DayBase {

    public void init(List<String> lines) {
        
    }

    public void part1(List<String> lines) {
        int s = 0;
        for (String l : lines) {
            int sl = l.length();
            int max1 = 0;
            int imax = 0;
            int max2 = 0;
            int i = 0;
            for (; i < sl-1; i++) {
                if (l.charAt(i)-'0' > max1) {
                    max1 = l.charAt(i)-'0';
                    imax = i;
                }
            }
            for (i = imax+1; i < sl; i++) {
                if (l.charAt(i)-'0' > max2) max2 = l.charAt(i)-'0';
            }
            s += max1*10+max2;
        }
        System.out.println(s);
    }

    public void part2(List<String> lines) {
        long s = 0;
        for (String l : lines) {
            int sl = l.length();

            long acc = 0;

            int imax = -1;
            for (int c = 0; c < 12; c++) {
                int max = 0;
                for (int i = imax+1; i < sl-(11-c); i++) {
                    if (l.charAt(i)-'0' > max) {
                        max = l.charAt(i)-'0';
                        imax = i;
                    }
                }
                acc = 10*acc + max;
            }
            s += acc;
        }
        System.out.println(s);
    }

    public static void main(String...args) {
        new Day3().test(1);
        new Day3().test(2);
        new Day3().run();
    }
}
