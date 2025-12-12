package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayList;
import java.util.List;

public class Day12 implements DayBase {

    public void init(List<String> lines) {
        
    }

    public void part1(List<String> lines) {
        String[] sections = String.join("\n", lines).split("\n\n");

        List<Integer> s = new ArrayList<>();

        for (int i = 0; i < sections.length - 1; i++) {
            int count = 0;
            for (char c : sections[i].toCharArray()) {
                if (c == '#') count++;
            }
            s.add(count);
        }

        int t = 0;

        for (String line : sections[sections.length - 1].split("\n")) {
            String[] parts = line.split(": ");
            String[] dims = parts[0].split("x");
            int w = Integer.parseInt(dims[0]);
            int h = Integer.parseInt(dims[1]);
            parts = parts[1].split(" ");
            int sum = 0;
            for (int i = 0; i < parts.length; i++) {
                sum += Integer.parseInt(parts[i]) * s.get(i);
            }
            if (sum <= w * h) t++;
        }

        System.out.println(t);
    }

    public void part2(List<String> lines) {
        
    }

    public static void main(String...args) {
        new Day12().test(1);
        new Day12().test(2);
        new Day12().run();
    }
}
