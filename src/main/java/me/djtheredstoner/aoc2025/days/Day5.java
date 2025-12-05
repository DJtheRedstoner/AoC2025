package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayList;
import java.util.List;

public class Day5 implements DayBase {

    public void init(List<String> lines) {
        
    }

    record Range(long low, long high) {}

    public void part1(List<String> lines) {
        List<Range> ranges = new ArrayList<>();
        long fresh = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            if (line.contains("-")) {
                String[] parts = line.split("-");
                ranges.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            } else {
                long id = Long.parseLong(line);
                for (Range r : ranges) {
                    if (id >= r.low() && id <= r.high()) {
                        fresh++;
                        break;
                    }
                }
            }
        }
        System.out.println(fresh);
    }

    public void part2(List<String> lines) {

        List<Range> ranges = new ArrayList<>();
        outer:
        for (String line : lines) {
            if (line.isBlank()) continue;
            if (line.contains("-")) {
                String[] parts = line.split("-");
                Range rO = new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                Range r = rO;
                while (true) {
                    boolean changed = false;
                    ranges.removeIf(r2 -> r2.low() >= rO.low() && r2.low() <= rO.high() && r2.high() >= rO.low() && r2.high() <= rO.high());
                    for (Range r2 : ranges) {
                        if (r.low() >= r2.low() && r.low() <= r2.high() && r.high() >= r2.low() && r.high() <= r2.high()) {
                            continue outer;
                        }
                        long low = r.low();
                        if (low >= r2.low() && low <= r2.high()) {
                            low = r2.high() + 1;
                            changed = true;
                        }
                        long high = r.high();
                        if (high >= r2.low() && high <= r2.high()) {
                            high = r2.low() - 1;
                            changed = true;
                        }
                        r = new Range(low, high);
                    }
                    if (!changed) break;
                }
                ranges.add(r);
            }
        }
//        System.out.println(ranges);
        long fresh = 0;
        for (Range r : ranges) {
//            for (Range r2 : ranges) {
//                if (r.equals(r2)) continue;
//                if (r.low() >= r2.low() && r.low() <= r2.high()) {
//                    System.out.println("BAD LOW " + r + r2);
//                }
//                if (r.high() >= r2.low() && r.high() <= r2.high()) {
//                    System.out.println("BAD HIGH " + r + r2);
//                }
//            }
            fresh += r.high() - r.low() + 1;
        }
        System.out.println(fresh);
    }

    public static void main(String...args) {
        new Day5().test(1);
        new Day5().test(2);
        new Day5().run();
    }
}
