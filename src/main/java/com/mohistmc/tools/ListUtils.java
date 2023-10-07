package com.mohistmc.tools;

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


    public static boolean is(List list1, List list2) {
        if (list1.size() != list2.size()) return false;
        return list2.containsAll(list1);
    }
}
