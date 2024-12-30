package com.ahmadsedi.bank;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ahmad R. Seddighi (ahmadseddighi@yahoo.com)
 * Date: 19/12/2024
 * Time: 10:14
 */

public class TransactionProcessor {

    public static Map<Integer, Integer> getTotalAmountForEachSource(int[][] transactions){
        return Arrays.stream(transactions).map(TransactionProcessor::getTransaction).collect(new TransactionSourceCollector());
    }

    private static Transaction getTransaction(int[] row){
        return new Transaction(row[0], row[1], row[2]);
    }

    private static class TransactionSourceCollector implements Collector<Transaction, Map<Integer, Integer>, Map<Integer, Integer>> {

        @Override
        public Supplier<Map<Integer, Integer>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<Map<Integer, Integer>, Transaction> accumulator() {
            return (map, transaction)->{
                map.merge(transaction.getSource(), (-1)*transaction.getAmount(), Integer::sum);
                map.merge(transaction.getDestination(), transaction.getAmount(), Integer::sum);
            };
        }

        @Override
        public BinaryOperator<Map<Integer, Integer>> combiner() {
            return (map1, map2)-> Stream.of(map1, map2).flatMap(map->map.entrySet().stream()).
                    collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        @Override
        public Function<Map<Integer, Integer>, Map<Integer, Integer>> finisher() {
            return map->map;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
        }
    }



}
