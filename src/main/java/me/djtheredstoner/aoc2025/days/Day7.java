package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Day7 implements DayBase {

    public void init(List<String> lines) {
        
    }

    record Pos(int x, int y) {}

    public void part1(List<String> lines) {
        Set<Pos> visited = new HashSet<>();
        Queue<Pos> Q = new ArrayDeque<>();
        Q.add(new Pos(lines.getFirst().indexOf('S'), 0));
        int splits = 0;
        while (!Q.isEmpty()) {
            Pos p = Q.poll();
            if (visited.contains(p)) continue;
            visited.add(p);
            int x = p.x();
            int ny = p.y() + 1;
            if (ny > lines.size() - 1) continue;
            if (lines.get(ny).charAt(x) == '^') {
                splits++;
                Q.add(new Pos(x-1, ny));
                Q.add(new Pos(x+1, ny));
            } else {
                Q.add(new Pos(x, ny));
            }
        }
        System.out.println(splits);
    }

    public void part2(List<String> lines) {
        record State(Pos split, Pos p) {}

        int width = lines.getFirst().length();

        Queue<State> Q = new ArrayDeque<>();
        for (int i = 0; i < width; i++) {
            Pos p = new Pos(i, lines.size()-1);
            Q.add(new State(p, p));
        }
        Set<State> visited = new HashSet<>();
        Map<Pos, List<Pos>> splitters = new HashMap<>();
        while (!Q.isEmpty()) {
            State s = Q.poll();
            if (visited.contains(s)) continue;
            visited.add(s);
            int x = s.p().x();
            int ny = s.p().y() - 1;
            if (ny == 0) {
                if (lines.get(ny).charAt(x) == 'S') {
                    splitters.computeIfAbsent(new Pos(x, ny), _ -> new ArrayList<>()).add(s.split());
                }
            } else {
                if (x > 0 && lines.get(ny).charAt(x-1) == '^') {
                    splitters.computeIfAbsent(new Pos(x-1, ny), _ -> new ArrayList<>()).add(s.split());
                    Q.add(new State(new Pos(x-1, ny), new Pos(x-1, ny)));
                }
                if (x < width - 1 && lines.get(ny).charAt(x+1) == '^') {
                    splitters.computeIfAbsent(new Pos(x+1, ny), _ -> new ArrayList<>()).add(s.split());
                    Q.add(new State(new Pos(x+1, ny), new Pos(x+1, ny)));
                }
                if (lines.get(ny).charAt(x) != '^')
                    Q.add(new State(s.split(), new Pos(x, ny)));
            }
        }

        Map<Pos, Long> dp = new HashMap<>();
        System.out.println(dp(dp, splitters, new Pos(lines.getFirst().indexOf('S'), 0)));
    }

    private long dp(Map<Pos, Long> dp, Map<Pos, List<Pos>> splitters, Pos s) {
        if (dp.containsKey(s)) return dp.get(s);
        if (!splitters.containsKey(s)) return 0;
        long paths = 1;
        for (Pos p : splitters.get(s)) {
            paths += dp(dp, splitters, p);
        }
        dp.put(s, paths);
        return paths;
    }

    public static void main(String...args) {
        new Day7().test(1);
        new Day7().test(2);
        new Day7().run();
    }
}
