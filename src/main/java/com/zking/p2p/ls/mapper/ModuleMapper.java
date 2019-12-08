package com.zking.p2p.ls.mapper;

import com.zking.p2p.ls.model.Module;

public interface ModuleMapper {
    int insert(Module record);

    int insertSelective(Module record);
}