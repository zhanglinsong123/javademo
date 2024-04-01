package com.javademo.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.javademo.mapper.TestMapper;
import com.javademo.model.entity.EntAppManageEntity;
import com.javademo.service.HutoolTreeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-04-01 13:49
 **/
@Service
public class HutoolTreeServiceImpl implements HutoolTreeService {

    private final TestMapper testmapper;

    public HutoolTreeServiceImpl(TestMapper testmapper) {
        this.testmapper = testmapper;
    }

    @Override
    public List<Tree<Long>> listWithTree() {
        List<EntAppManageEntity> entityList = testmapper.queryAllEntAppManage();
        if (!CollectionUtils.isEmpty(entityList)) {
            List<TreeNode<Long>> treeNodeList = entityList.stream()
                    .map(this::getLongTreeNode)
                    .collect(Collectors.toList());

            return TreeUtil.build(treeNodeList, 0L);

        }
        return Collections.emptyList();
    }

    private TreeNode<Long> getLongTreeNode(EntAppManageEntity entity) {
        TreeNode<Long> treeNode = new TreeNode<>();
        treeNode.setId(entity.getId());
        treeNode.setParentId(entity.getParent());
        treeNode.setName(entity.getName());
        treeNode.setWeight(entity.getOrder());
        Map<String, Object> extra = new HashMap<>();
        extra.put("appId", entity.getAppId());
        extra.put("appType", entity.getAppType());
        extra.put("link", entity.getLink());
        extra.put("description", entity.getDescription());
        extra.put("iconName", entity.getIconName());
        extra.put("level", entity.getLevel());
        extra.put("order", entity.getOrder());
        extra.put("createdAt", entity.getCreatedAt());
        treeNode.setExtra(extra);
        return treeNode;
    }
}
