package com.trivedi.hardik.interviewcake;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * 
 * 
 * 
 * Your quirky boss collects rare, old coins...
 * 
 * They found out you're a programmer and asked you to solve something they've
 * been wondering for a long time.
 * 
 * Write a method that, given:
 * 
 * an amount of money an array of coin denominations
 * 
 * computes the number of ways to make the amount of money with coins of the
 * available denominations.
 * 
 * Example: for amount=444 (444¢) and denominations=[1,2,3][1,2,3][1,2,3] (111¢,
 * 222¢ and 333¢), your program would output 444—the number of ways to make 444¢
 * with those denominations:
 * 
 * 1¢, 1¢, 1¢, 1¢ 1¢, 1¢, 2¢ 1¢, 3¢ 2¢, 2¢
 *
 * 
 * 
 */
public class MakingChange {
	public static int changePossibilities(int amount, int[] denominations) {

		// calculate the number of ways to make change
		int[] waysOfDoingNCents = new int[amount + 1]; // array of zeros from 0..amount
		waysOfDoingNCents[0] = 1;

		for (int coin : denominations) {
			for (int higherAmount = coin; higherAmount <= amount; higherAmount++) {
				int higherAmountRemainder = higherAmount - coin;
				waysOfDoingNCents[higherAmount] += waysOfDoingNCents[higherAmountRemainder];
			}
		}

		return waysOfDoingNCents[amount];
	}

	// tests

	@Test
	public void sampleInputTest() {
		final int actual = changePossibilities(4, new int[] { 1, 2, 3 });
		final int expected = 4;
		assertEquals(expected, actual);
	}

	@Test
	public void oneWayToMakeZeroCentsTest() {
		final int actual = changePossibilities(0, new int[] { 1, 2 });
		final int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void noWaysIfNoCoinsTest() {
		final int actual = changePossibilities(1, new int[] {});
		final int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void bigCoinValueTest() {
		final int actual = changePossibilities(5, new int[] { 25, 50 });
		final int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void bigTargetAmountTest() {
		final int actual = changePossibilities(50, new int[] { 5, 10 });
		final int expected = 6;
		assertEquals(expected, actual);
	}

	@Test
	public void changeForOneDollarTest() {
		final int actual = changePossibilities(100, new int[] { 1, 5, 10, 25, 50 });
		final int expected = 292;
		assertEquals(expected, actual);
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(MakingChange.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("All tests passed.");
		}
	}
}
