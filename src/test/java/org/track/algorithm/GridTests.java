package org.track.algorithm;

import org.track.DanielTrackApplicationTests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName GridTests
 * @Description TODO
 * @Author lee
 * @Date 2020/10/6 上午12:26
 * @Version 1.0
 **/
public class GridTests extends DanielTrackApplicationTests {

    private static final long MAX_NUM = 1000000000L;

    private Map<Integer, Integer> rowMap = new HashMap<>();
    private Map<Integer, Integer> columnMap = new HashMap<>();
    private Map<Integer, Integer> sumMap = new HashMap<>();
    private Map<Integer, Integer> rowColDiffMap = new HashMap<>();

    private Set<Long> lampSet = new HashSet<>();

    private void setFlagMap(Map<Integer, Integer> flagMap, int key) {
        if (flagMap.containsKey(key)) {
            flagMap.put(key, flagMap.get(key) + 1);
        } else {
            flagMap.put(key, 1);
        }
    }

    private void minusFlagMapCount(Map<Integer, Integer> flagMap, int key) {
        flagMap.put(key, flagMap.get(key) - 1);
    }

    private void closeNeighborLamp(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow < 0 || newCol < 0) {
                    continue;
                }

                long lamp = MAX_NUM * newRow + newCol;
                if (lampSet.contains(lamp)) {
                    lampSet.remove(lamp);
                    minusFlagMapCount(rowMap, newRow);
                    minusFlagMapCount(columnMap, newCol);
                    minusFlagMapCount(sumMap, newRow + newCol);
                    minusFlagMapCount(rowColDiffMap, newRow - newCol);
                }
            }
        }
    }

    public int[] gridIllumination(int[][] lamps, int[][] queries) {
        int queryCount = queries.length;
        if (queryCount == 0) {
            return new int[]{};
        }

        for (int[] lamp : lamps) {
            int row = lamp[0];
            int col = lamp[1];
            int sum = row + col;
            int rowColDiff = row - col;
            setFlagMap(rowMap, row);
            setFlagMap(columnMap, col);
            setFlagMap(sumMap, sum);
            setFlagMap(rowColDiffMap, rowColDiff);
            lampSet.add(MAX_NUM * row + col);
        }

        int[] ansArr = new int[queryCount];
        int count = 0;

        for (int[] query : queries) {
            int row = query[0];
            int col = query[1];
            int sum = row + col;
            int rowColDiff = row - col;

            if (rowMap.containsKey(row) && rowMap.get(row) >= 1 ||
                    columnMap.containsKey(col) && columnMap.get(col) >= 1 ||
                    sumMap.containsKey(sum) && sumMap.get(sum) >= 1 ||
                    rowColDiffMap.containsKey(rowColDiff) && rowColDiffMap.get(rowColDiff) >= 1
            ) {
                ansArr[count++] = 1;
            } else {
                ansArr[count++] = 0;
            }
            closeNeighborLamp(row, col);
        }
        return ansArr;
    }
}
