package com.zhiend.dao;

import com.zhiend.domain.Account;
import com.zhiend.service.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Classname : com.zhiend.dao.AccountServiceTest //类名
 * @Description: 测试类 //描述
 * @Author : Administrator //作者
 * @Date : 2021/12/20 22:57//日期
 */
public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void init() throws IOException {
        accountService = new AccountService();
    }

    //查找全部
    @Test
    public void testFindAll() {
        List<Account> list = accountService.findAll();
        for (Account account : list) {
            System.out.println(account);
        }
    }

    //通过id删除
    @Test
    public void testDeleteByPrimaryKey() {
        int i = accountService.deleteByPrimaryKey("4");
        if (i > 0) {
            System.out.println("删除成功");
        }
    }

    //插入
    @Test
    public void testInsert() throws IOException {
        int i = accountService.insert(new Account("4","ZHIEND",10000,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis())));
        if (i > 0) {
            System.out.println("插入成功");
        }
    }

    //根据id进行查找
    @Test
    public void testSelectByPrimaryKey() {
        Account account = accountService.selectByPrimaryKey("3");
        System.out.println(account);
    }

    //根据ID进行更新
    @Test
    public void updateByPrimaryKey() {
        int i = accountService.updateByPrimaryKey(new Account("4", "ZHIEND", 18000, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        if (i > 0) {
            System.out.println("修改成功");
        }
    }

    //转账
    @Test
    public void transfer() {
        int money = 12000;
        accountService.transfer("2", "1",money);
    }

}
