package cn.j.sbdemo.sys.dao;

import cn.j.sbdemo.sys.entity.SysDept;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
}