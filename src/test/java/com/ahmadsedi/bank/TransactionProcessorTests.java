package com.ahmadsedi.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 19/12/2024
 * Time: 10:49
 */

public class TransactionProcessorTests {

    @Test
    void getTotalAmountForEachSource_givenTransactions_returnCorrectValue(){
        int[][] transactions = {{1, 2, 5}, {6, 1, 19}, {1,6,15}, {6,2,5}, {6,1,5}};
        Map<Integer, Integer> totalAmountForEachSource = TransactionProcessor.getTotalAmountForEachSource(transactions);

        Integer totalAmount = totalAmountForEachSource.get(1);
        Assertions.assertEquals(4, totalAmount);

        totalAmount = totalAmountForEachSource.get(7);
        Assertions.assertNull(totalAmount);
    }
    @Test
    void getTotalAmountForEachSource_givenOneTransaction_returnActualValue(){
        int[][] transactions = {{1, 2, 5}};
        Map<Integer, Integer> totalAmountForEachSource = TransactionProcessor.getTotalAmountForEachSource(transactions);
        Integer totalAmount = totalAmountForEachSource.get(1);
        Assertions.assertEquals(-5, totalAmount);
    }

    @Test
    void getTotalAmountForEachSource_givenEmptyArray_returnEmptyMap(){
        int[][] transactions = {};
        Map<Integer, Integer> totalAmountForEachSource = TransactionProcessor.getTotalAmountForEachSource(transactions);
        Assertions.assertEquals(0, totalAmountForEachSource.size());
    }
}
