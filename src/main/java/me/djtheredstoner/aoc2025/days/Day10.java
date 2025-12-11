package me.djtheredstoner.aoc2025.days;

import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.IntNum;
import me.djtheredstoner.aoc2025.DayBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Day10 implements DayBase {

    public void init(List<String> lines) {
        
    }

    public void part1(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            String[] parts = line.split(" ");
            String lights = parts[0].substring(1,parts[0].length()-1);
            BitSet goal = new BitSet();
            for (int i = 0; i < lights.length(); i++) {
                if (lights.charAt(i) == '#') goal.set(i);
            }

            List<BitSet> buttons = new ArrayList<>();
            for (int i = 1; i < parts.length-1; i++) {
                String sbuttons = parts[i].substring(1,parts[i].length()-1);
                var bs = new BitSet();
                Arrays.stream(sbuttons.split(",")).mapToInt(Integer::parseInt).forEach(bs::set);
                buttons.add(bs);
            }

            record State(BitSet lights, int presses) {}

            Queue<State> Q = new PriorityQueue<>(Comparator.comparing(State::presses));
            Map<BitSet, Integer> dist = new HashMap<>();
            Q.add(new State(new BitSet(), 0));
            dist.put(new BitSet(), 0);
            while (!Q.isEmpty()) {
                var s = Q.poll();
                if (s.lights.equals(goal)) {
                    sum += s.presses;
                    break;
                }
                for (BitSet button : buttons) {
                    var nbs = (BitSet) s.lights.clone();
                    nbs.xor(button);
                    if (!dist.containsKey(nbs) || s.presses + 1 < dist.get(nbs)) {
                        var ns = new State(nbs, s.presses + 1);
                        Q.add(ns);
                        dist.put(nbs, s.presses+1);
                    }
                }
            }
        }
        System.out.println(sum);
    }

    public void part2(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            String[] parts = line.split(" ");
            String joltages = parts[parts.length-1].substring(1,parts[parts.length-1].length()-1);
            List<Integer> goal = Arrays.stream(joltages.split(",")).map(Integer::parseInt).toList();

            List<BitSet> buttons = new ArrayList<>();
            for (int i = 1; i < parts.length-1; i++) {
                String sbuttons = parts[i].substring(1,parts[i].length()-1);
                var bs = new BitSet();
                Arrays.stream(sbuttons.split(",")).mapToInt(Integer::parseInt).forEach(bs::set);
                buttons.add(bs);
            }

            var c = new Context();

            var o = c.mkOptimize();

            IntExpr[] variables = new IntExpr[buttons.size()];
            for (int i = 0; i < buttons.size(); i++) {
                variables[i] = c.mkIntConst("b" + i);
                o.Add(c.mkGe(variables[i], c.mkInt(0)));
            }

            for (int i = 0; i < goal.size(); i++) {
                List<IntExpr> list = new ArrayList<>();
                for (int j = 0; j < buttons.size(); j++) {
                    if (buttons.get(j).get(i)) {
                        list.add(variables[j]);
                    }
                }
                o.Add(c.mkEq(c.mkInt(goal.get(i)), c.mkAdd(list.toArray(new IntExpr[0]))));
            }

            o.MkMinimize(c.mkAdd(variables));

            o.Check();

            var m = o.getModel();

            for (IntExpr variable : variables) {
                sum += ((IntNum)m.eval(variable, false)).getInt();
            }

            c.close();
        }
        System.out.println(sum);
    }

    public static void main(String...args) {
        new Day10().test(1);
        new Day10().test(2);
        new Day10().run();
    }
}
