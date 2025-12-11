package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Day11 implements DayBase {

    public void init(List<String> lines) {
        
    }

    public void part1(List<String> lines) {
        Map<String, List<String>> edges = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(": ");
            edges.put(parts[0], Arrays.asList(parts[1].split(" ")));
        }

        int paths = 0;

        Queue<String> Q = new ArrayDeque<>();
        Q.add("you");
        while (!Q.isEmpty()) {
            String s = Q.poll();
            if (s.equals("out")) {
                paths++;
                continue;
            }
            Q.addAll(edges.get(s));
        }
        System.out.println(paths);
    }

    public void part2(List<String> lines) {
        Map<String, List<String>> edges = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(": ");
            edges.put(parts[0], Arrays.asList(parts[1].split(" ")));
        }

        long svrDac = paths(edges, new HashMap<>(), "svr", "dac");
        long dacFft = paths(edges, new HashMap<>(), "dac", "fft");
        long fftOut = paths(edges, new HashMap<>(), "fft", "out");

        long svrFft = paths(edges, new HashMap<>(), "svr", "fft");
        long fftDac = paths(edges, new HashMap<>(), "fft", "dac");
        long dacOut = paths(edges, new HashMap<>(), "dac", "out");
        System.out.println(svrDac * dacFft * fftOut + svrFft * fftDac * dacOut);
    }

    public long paths(Map<String, List<String>> edges, Map<String, Long> cache, String src, String target) {
        if (src.equals(target)) return 1;
        if (cache.containsKey(src)) return cache.get(src);
        if (!edges.containsKey(src)) return 0;
        long count = 0;
        for (String s : edges.get(src)) {
            count += paths(edges, cache, s, target);
        }
        cache.put(src, count);
        return count;
    }

    public static void main(String...args) {
        new Day11().test(1);
        new Day11().test(2);
        new Day11().run();
    }
}
