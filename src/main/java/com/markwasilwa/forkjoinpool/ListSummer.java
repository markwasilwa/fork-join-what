package com.markwasilwa.forkjoinpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ListSummer extends RecursiveTask<Integer> {
    private static final Logger log = LoggerFactory.getLogger(ListSummer.class);
    private final List<Integer> listToSum;

    public ListSummer(List<Integer> listToSum) {
        this.listToSum = listToSum;
    }

    @Override
    protected Integer compute() {
        if (listToSum.isEmpty()) {
            log.info("Found empty list, sum is 0");
            return 0;
        }

        int middleIndex = listToSum.size() / 2;
        log.info("List {} middle Index: {}", listToSum, middleIndex);

        List<Integer> leftSublist = listToSum.subList(0, middleIndex);
        List<Integer> rightSublist = listToSum.subList(middleIndex + 1, listToSum.size());

        ListSummer leftSummer = new ListSummer(leftSublist);
        ListSummer rightSummer = new ListSummer(rightSublist);

        leftSummer.fork();
        rightSummer.fork();

        Integer leftSum = leftSummer.join();
        Integer rightSum = rightSummer.join();
        int total = leftSum + listToSum.get(middleIndex) + rightSum;

        log.info("Left sum is {}, right sum is {}, total is {}", leftSum, rightSum, total);

        return total;
    }
}
