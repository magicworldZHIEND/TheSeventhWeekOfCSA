package com.zhiend.dao;

import com.zhiend.domain.Account;

import java.util.List;

public interface AccountDao {
    // 1.查询所有记录
    public List<Account> findAll();

    // 2.通过id删除记录
    public int deleteByPrimaryKey(String id);

    // 3.插入记录
    public int insert(Account record);

    // 4.通过id查找对象
    public Account selectByPrimaryKey(String id);

    // 5.更新Account
    public int updateByPrimaryKey(Account record);
}
