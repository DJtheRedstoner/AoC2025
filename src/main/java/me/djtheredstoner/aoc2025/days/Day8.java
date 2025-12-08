package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Day8 implements DayBase {

    public void init(List<String> lines) {
        
    }

    record Box(int x, int y, int z) {}
    record Dist(long dist, Box b1, Box b2) {}

    public void part1(List<String> lines) {
        List<Box> boxes = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            boxes.add(new Box(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
        Queue<Dist> Q = new PriorityQueue<>(Comparator.comparing(Dist::dist));
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                Box b1 = boxes.get(i);
                Box b2 = boxes.get(j);
                long dx = b1.x() - b2.x();
                long dy = b1.y() - b2.y();
                long dz = b1.z() - b2.z();
                long dist = dx*dx + dy*dy + dz*dz;
                Q.add(new Dist(dist, b1, b2));
            }
        }
        Map<Box, Set<Box>> E = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            Dist d = Q.poll();
            E.computeIfAbsent(d.b1(), _ -> new HashSet<>()).add(d.b2());
            E.computeIfAbsent(d.b2(), _ -> new HashSet<>()).add(d.b1());
        }

        long prod = 1;

        Queue<Long> sizes = new PriorityQueue<>(Comparator.reverseOrder());

        Set<Box> visited = new HashSet<>();
        for (Box b : boxes) {
            Queue<Box> Q2 = new ArrayDeque<>();
            int size = 0;
            Q2.add(b);
            while (!Q2.isEmpty()) {
                Box v = Q2.poll();
                if (visited.contains(v)) continue;
                visited.add(v);
                size++;
                if (E.containsKey(v)) {
                    Q2.addAll(E.get(v));
                }
            }
            if (size != 0)
                sizes.add((long)size);
        }

        System.out.println(sizes.poll()*sizes.poll()*sizes.poll());
    }

    public void part2(List<String> lines) {
        List<Box> boxes = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            boxes.add(new Box(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
        Queue<Dist> Q = new PriorityQueue<>(Comparator.comparing(Dist::dist));
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                Box b1 = boxes.get(i);
                Box b2 = boxes.get(j);
                long dx = b1.x() - b2.x();
                long dy = b1.y() - b2.y();
                long dz = b1.z() - b2.z();
                long dist = dx*dx + dy*dy + dz*dz;
                Q.add(new Dist(dist, b1, b2));
            }
        }
        Map<Box, Set<Box>> E = new HashMap<>();
        while (true) {
            Dist d = Q.poll();
            E.computeIfAbsent(d.b1(), _ -> new HashSet<>()).add(d.b2());
            E.computeIfAbsent(d.b2(), _ -> new HashSet<>()).add(d.b1());

            Queue<Box> Q2 = new ArrayDeque<>();
            Set<Box> visited = new HashSet<>();
            Q2.add(boxes.get(0));
            while (!Q2.isEmpty()) {
                Box v = Q2.poll();
                if (visited.contains(v)) continue;
                visited.add(v);
                if (E.containsKey(v)) {
                    Q2.addAll(E.get(v));
                }
            }
            if (visited.size() == boxes.size()) {
                System.out.println((long)d.b1().x()*d.b2().x());
                break;
            }
        }
    }

    public static void main(String...args) {
//        new Day8().test(1);
        new Day8().test(2);
        new Day8().run();
    }
}
