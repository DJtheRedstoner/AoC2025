package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 implements DayBase {

    public void init(List<String> lines) {
        
    }

    public void part1(List<String> lines) {
        var symbols = lines.stream().map(line -> line.trim().split(" +")).toList();
        int width = symbols.getFirst().length;
        long total = 0;
        for (int i = 0; i < width; i++) {
            boolean mul = symbols.getLast()[i].equals("*");
            long acc = mul ? 1 : 0;
            for (int j = 0; j < symbols.size() - 1; j++) {
                long val = Long.parseLong(symbols.get(j)[i]);
                acc = mul ? acc * val : acc + val;
            }
            total += acc;
        }
        System.out.println(total);
    }

    public void part2(List<String> lines) {
        long total = 0;

        String opLine = lines.getLast();
        int maxLength = lines.stream().mapToInt(String::length).max().getAsInt();
        List<Long> numbers = new ArrayList<>();
        for (int x = maxLength - 1; x >= 0; x--) {
            long n = 0;
            boolean seen = false;
            for (int y = 0; y < lines.size() - 1; y++) {
                if (x >= lines.get(y).length()) continue;
                char c = lines.get(y).charAt(x);
                if (Character.isDigit(c)) {
                    seen = true;
                    n *= 10;
                    n += c - '0';
                }
            }
            if (seen) {
                numbers.add(n);
            }
            if (x < opLine.length() && opLine.charAt(x) != ' ') {
                char op = opLine.charAt(x);
                if (op != ' ') {
                    if (op == '+') {
                        total += numbers.stream().mapToLong(Long::longValue).reduce(0L, Long::sum);
                    } else {
                        total += numbers.stream().mapToLong(Long::longValue).reduce(1L, (a, b) -> a * b);
                    }
                }
                // reset
                numbers.clear();
            }
        }

        System.out.println(total);
    }

    public static void main(String...args) {
        new Day6().test(1);
        new Day6().test(2);
        new Day6().run();
    }
}
