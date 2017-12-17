package com.longzhu.designer.toolbar;

/**
 * 构建者模式：
 * 核心思想：将构建过程和表示过程进行分离。
 * <p>
 * 案例：导航条规范
 */

public interface INavagation {
    /**
     * 导航条布局
     */
    public int bindLayoutId();

    /**
     * 创建和绑定导航条
     *
     * @return
     */
    public void build();
}
