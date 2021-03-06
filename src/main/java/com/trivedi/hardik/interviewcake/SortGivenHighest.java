package com.trivedi.hardik.interviewcake;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class SortGivenHighest {

	public static int[] sortScores(int[] unorderedScores, int highestPossibleScore) {

		// sort the scores in O(n) time

		int[] scoreCounts = new int[highestPossibleScore + 1];
		for (int score : unorderedScores) {
			scoreCounts[score]++;
		}

		int[] sortedScores = new int[unorderedScores.length];
		int sortedScoreIndex = 0;
		for (int i = highestPossibleScore; i >= 0; i--) {
			if (scoreCounts[i] > 0) {
				for (int j = 0; j < scoreCounts[i]; j++) {
					sortedScores[sortedScoreIndex++] = i;
				}
			}
		}

		return sortedScores;
	}

	// tests

	@Test
	public void noScoresTest() {
		final int[] scores = {};
		final int[] expected = {};
		final int[] actual = sortScores(scores, 100);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void oneScoreTest() {
		final int[] scores = { 55 };
		final int[] expected = { 55 };
		final int[] actual = sortScores(scores, 100);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void twoScoresTest() {
		final int[] scores = { 30, 60 };
		final int[] expected = { 60, 30 };
		final int[] actual = sortScores(scores, 100);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void manyScoresTest() {
		final int[] scores = { 37, 89, 41, 65, 91, 53 };
		final int[] expected = { 91, 89, 65, 53, 41, 37 };
		final int[] actual = sortScores(scores, 100);
		assertArrayEquals(expected, actual);
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(SortGivenHighest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("All tests passed.");
		}
	}

}
