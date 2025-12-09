package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class Day9 implements DayBase {

    public void init(List<String> lines) {
        
    }

    record Pos(int x, int y) {}

    public void part1(List<String> lines) {
        List<Pos> red = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            red.add(new Pos(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        long max = 0;
        for (Pos p1 : red) {
            for (Pos p2 : red) {
                int dx = Math.abs(p1.x()-p2.x())+1;
                int dy = Math.abs(p1.y()-p2.y())+1;
                if ((long)dx*dy > max) {
                    max = (long)dx*dy;
                }
            }
        }
        System.out.println(max);
    }

    record Edge(Pos p1, Pos p2) {}

    public void part2(List<String> lines) {
        List<Pos> red = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            red.add(new Pos(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }


        long max = 0;
        for (Pos p1 : red) {
            outer:
            for (Pos p2 : red) {
                int left = Math.min(p1.x(), p2.x());
                int right = Math.max(p1.x(), p2.x());
                int top = Math.min(p1.y(), p2.y());
                int bottom = Math.max(p1.y(), p2.y());

                for (int i = 0; i < red.size(); i++) {
                    Pos e1 = red.get(i);
                    Pos e2 = red.get((i + 1) % red.size());
                    if (!(Math.max(e1.x(), e2.x()) <= left || Math.min(e1.x(), e2.x()) >= right ||
                        Math.max(e1.y(), e2.y()) <= top || Math.min(e1.y(), e2.y()) >= bottom)) {
                        continue outer;
                    }
                }

                int dx = Math.abs(p1.x() - p2.x()) + 1;
                int dy = Math.abs(p1.y() - p2.y()) + 1;
                if ((long) dx * dy > max) {
                    max = (long) dx * dy;
                }
            }
        }
        System.out.println(max);
    }

    public static void main(String...args) {
        new Day9().test(1);
        new Day9().test(2);
        new Day9().run();
    }
}
