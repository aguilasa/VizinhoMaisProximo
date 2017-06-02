package com.github.aguilasa;

import java.util.LinkedList;

public class VizinhoMaisProximo {

	private static final String STR = " - ";
	private int[][] matrix;
	private String[] names;
	private LinkedList<LinkedList<Integer>> distances = new LinkedList<>();

	public static void main(String[] args) {
		VizinhoMaisProximo v = new VizinhoMaisProximo();
		v.init();
		v.exec();
	}

	public void init() {
		matrix = new int[][] { { 0, 40, 450, 100, 130, 18 }, { 40, 0, 480, 55, 70, 25 }, { 450, 480, 0, 400, 680, 415 }, { 100, 55, 400, 0, 150, 80 }, { 130, 70, 680, 150, 0, 120 }, { 18, 25, 415, 80, 120, 0 } };
		names = new String[] { "Blumenau", "Itajaí", "Chapecó", "Joinville", "Florianópolis", "Gaspar" };
		for (int i[] : matrix) {
			LinkedList<Integer> list = new LinkedList<>();
			distances.add(list);
			for (int j : i) {
				list.add(j);
			}
		}
	}

	public void exec() {
		LinkedList<LinkedList<Integer>> sequence = new LinkedList<>();
		for (int i = 0; i < names.length; i++) {
			LinkedList<Integer> list = new LinkedList<>();
			sequence.add(list);

			list.add(i);
			int next = i;
			while (list.size() < names.length) {
				next = findNext(next, list);
				list.add(next);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(names[list.getFirst()]);
			sb.append(STR);
			int total = 0;
			for (int j = 1; j < list.size(); j++) {
				int from = list.get(j - 1);
				int to = list.get(j);
				int dist = distances.get(from).get(to);
				total += dist;
				sb.append(dist);
				sb.append(STR);
				sb.append(names[to]);
				sb.append(STR);
			}
			int dist = distances.get(list.getLast()).get(list.getFirst());
			sb.append(dist);
			sb.append(STR);
			total += dist;
			sb.append(names[list.getFirst()]);
			sb.append(" = ");
			sb.append(total);
			
			int tempo = total * 3;
			int hours = tempo / 60; //since both are ints, you get an int
			int minutes = tempo % 60;
			
			sb.append("\r\n");
			sb.append("Tempo = ");
			sb.append(String.format("%d:%02d", hours, minutes));
			sb.append("\r\n");
			
			
			System.out.println(sb.toString());

		}
	}

	private int findNext(int index, LinkedList<Integer> list) {
		LinkedList<Integer> distance = distances.get(index);

		int minIndex = -1;
		int min = Integer.MAX_VALUE;
		minIndex = -1;
		for (int i = 0; i < distance.size(); i++) {
			if (i == index) {
				continue;
			}
			if (!list.contains(i) && distance.get(i) < min) {
				min = distance.get(i);
				minIndex = i;
			}
		}

		return minIndex;
	}

}
