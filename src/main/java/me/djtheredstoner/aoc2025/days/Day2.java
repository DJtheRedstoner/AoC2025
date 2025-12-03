package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.List;

public class Day2 implements DayBase {

    public void init(List<String> lines) {
        
    }

    public void part1(List<String> lines) {
        long s = 0;
        for (String p : lines.getFirst().split(",")) {
            var parts = p.split("-");
            long min = Long.parseLong(parts[0]);
            long max = Long.parseLong(parts[1]);
            for (long i = min; i <= max; i++) {
                String d = Long.toString(i);
                if (d.substring(0,d.length()/2).equals(d.substring(d.length()/2))) s+=i;
            }
        }
        System.out.println(s);
    }

    public void part2(List<String> lines) {
        long s = 0;
        for (String p : lines.getFirst().split(",")) {
            var parts = p.split("-");
            long min = Long.parseLong(parts[0]);
            long max = Long.parseLong(parts[1]);
            for (long i = min; i <= max; i++) {
                String d = Long.toString(i);
                for (int j = 1; j <= d.length()/2; j++) {
                    String prefix = d.substring(0,j);
                    if (d.equals(prefix.repeat(d.length()/j))) {
                        s+=i;
                        break;
                    }
                }
            }
        }
        System.out.println(s);
    }

    public static void main(String...args) {
        new Day2().test(1);
        new Day2().test(2);
        new Day2().run();
    }
}
