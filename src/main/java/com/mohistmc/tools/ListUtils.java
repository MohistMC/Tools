package com.mohistmc.tools;

import java.util.HashSet;
import java.util.List;

/**
 * @author Mgazul by MohistMC
 * @date 2023/7/27 14:37:28
 */
public class ListUtils {

    public static void isDuplicate(List<String> list, String key) {
        if (!list.contains(key)) {
            list.add(key);
        }
    }

    /**
     * Whether the value in list 1 exists in list 2
     */
    public static boolean is(List<Object> list1, List<Object> list2) {
        if (list1.size() != list2.size()) return false;
        return new HashSet<>(list2).containsAll(list1);
    }
}
