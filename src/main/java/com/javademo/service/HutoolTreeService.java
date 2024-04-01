package com.javademo.service;

import cn.hutool.core.lang.tree.Tree;

import java.util.List;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-04-01 13:45
 **/
public interface HutoolTreeService {

    /**
     * 获取树形结构
     * @return
     */
    List<Tree<Long>> listWithTree();
}
