package me.djtheredstoner.aoc2025.days;

import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayList;
import java.util.List;

public class Day4 implements DayBase {

    public void init(List<String> lines) {

    }

    record Pos(int x, int y) {
    }

    public void part1(List<String> lines) {
        lines = new ArrayList<>(lines);
        int w = lines.get(0).length();
        int h = lines.size();
        int c = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (lines.get(j).charAt(i) == '@') {
                    int adj = 0;
                    for (Pos d : new Pos[]{new Pos(0, 1), new Pos(0, -1), new Pos(1, 0), new Pos(-1, 0), new Pos(1, 1), new Pos(1, -1), new Pos(-1, 1), new Pos(-1, -1)}) {
                        int ni = i + d.x();
                        int nj = j + d.y();
                        if (ni >= 0 && ni < w && nj >= 0 && nj < h) {
                            if (lines.get(nj).charAt(ni) == '@') adj++;
                        }
                    }
                    if (adj < 4) {
                        c++;
                    }

                }
            }
        }
        System.out.println(c);
    }

    public void part2(List<String> lines) {
        lines = new ArrayList<>(lines);
        int w = lines.get(0).length();
        int h = lines.size();
        int c = 0;
        while (true) {
            boolean r = false;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (lines.get(j).charAt(i) == '@') {
                        int adj = 0;
                        for (Pos d : new Pos[]{new Pos(0, 1), new Pos(0, -1), new Pos(1, 0), new Pos(-1, 0), new Pos(1, 1), new Pos(1, -1), new Pos(-1, 1), new Pos(-1, -1)}) {
                            int ni = i + d.x();
                            int nj = j + d.y();
                            if (ni >= 0 && ni < w && nj >= 0 && nj < h) {
                                if (lines.get(nj).charAt(ni) == '@' || lines.get(nj).charAt(ni) == 'x') adj++;
                            }
                        }
                        if (adj < 4) {
                            c++;
                            r = true;
                            var sb = new StringBuilder(lines.get(j));
                            sb.setCharAt(i, 'x');
                            lines.set(j, sb.toString());
                        }

                    }
                }
            }
            for (int i = 0; i < h; i++) {
                lines.set(i, lines.get(i).replace("x", "."));
            }
            if (!r) break;
        }
        System.out.println(c);
    }

    public static void main(String... args) {
        new Day4().test(1);
        new Day4().test(2);
        new Day4().run();
    }
}
