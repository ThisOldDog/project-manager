package pers.dog.project.manager.util.tree;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 树 工具
 *
 * @author 废柴 2021/2/6 19:51
 */
public class TreeUtils {


    public static <T extends Child<T>, P> List<T> buildTree(List<T> list, Function<T, P> getParent, Function<T, P> getCurrent) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        Map<P, List<T>> groupWithParent = list.stream().collect(Collectors.groupingBy(getParent));
        List<T> tree = new ArrayList<>();
        Set<P> parents = new HashSet<>(groupWithParent.keySet());
        list.forEach(e -> parents.remove(getCurrent.apply(e)));
        Assert.isTrue(!parents.isEmpty(), "无法构建树，因为数据成环。");
        for (P parent : parents) {
            buildTree(tree, groupWithParent, parent, getCurrent);
        }
        return tree;
    }

    private static <T extends Child<T>, P> void buildTree(List<T> parents, Map<P, List<T>> groupWithParent, P node, Function<T, P> getCurrent) {
        List<T> children = groupWithParent.get(node);
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        parents.addAll(children);
        for (T child : children) {
            List<T> grandson = new ArrayList<>();
            child.setChildren(grandson);
            buildTree(grandson, groupWithParent, getCurrent.apply(child), getCurrent);
        }
    }
}
