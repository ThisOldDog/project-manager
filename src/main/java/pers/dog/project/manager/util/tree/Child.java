package pers.dog.project.manager.util.tree;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 子节点
 *
 * @author 废柴 2021/2/6 19:41
 */
public class Child<T> {

    private List<T> children;

    public boolean getHasChild() {
        return !CollectionUtils.isEmpty(children);
    }

    public List<T> getChildren() {
        return children;
    }

    public Child<T> setChildren(List<T> children) {
        this.children = children;
        return this;
    }
}
