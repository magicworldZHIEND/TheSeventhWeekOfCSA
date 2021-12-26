package com.zhiend.service;

import com.zhiend.dao.AccountDao;
import com.zhiend.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Classname : AccountService //类名
 * @Description: 用于载入xml, 以及数据操作 //描述
 * @Author : Administrator //作者
 * @Date : 2021/12/20 22:45//日期
 */

public class AccountService {
    AccountDao accountDao;
    private InputStream inputStream;
    private SqlSession sqlSession;

    //初始化
    public AccountService() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(AccountDao.class);
    }
    //工具方法
    public void Utils(){
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    //释放资源
    private void destroy() {
        try {
            sqlSession.commit();
            sqlSession.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1查询所有记录
    public List<Account> findAll() {
        List<Account> accountList = accountDao.findAll();
        destroy();
        return accountList;
    }

    // 2通过id删除记录
    public int deleteByPrimaryKey(String id) {
        int result = accountDao.deleteByPrimaryKey(id);
        sqlSession.commit();
        destroy();
        return result;
    }

    // 3插入记录
    public int insert(Account record) {

        int result = accountDao.insert(record);

        //提交事务,如果没有提交事务,并且重写的方法不是void而是有返回值的,那么只会有返回值,不会改变数据库
        sqlSession.commit();
        destroy();
        return result;
    }

    // 4通过id查找对象
    public Account selectByPrimaryKey(String id) {
        Account AccountById = accountDao.selectByPrimaryKey(id);
        sqlSession.close();
        return AccountById;
    }

    // 5更新Account
    public int updateByPrimaryKey(Account record) {
        int result = accountDao.updateByPrimaryKey(record);
        //提交事务,如果没有提交事务,并且重写的方法不是void而是有返回值的,那么只会有返回值,不会改变数据库
        sqlSession.commit();
        destroy();
        return result;
    }

    // 6转账功能 输入转出人id，转入人id，转账金额 实现转账
    public void transfer(String remitterId, String remitteeId,int money) {
        //每次完成一个方法之后都需要再次的打开sqlSession以及InputStream
        //得到输入人信息:
        Account in = accountDao.selectByPrimaryKey(remitteeId);
        Utils();
        //得到输出人信息:
        Account out = accountDao.selectByPrimaryKey(remitterId);
        Utils();
        //更新数据:
        in.setMoney(in.getMoney() + money);
        Utils();
        out.setMoney(out.getMoney() - money);
        Utils();
        updateByPrimaryKey(in);
        Utils();
        updateByPrimaryKey(out);
    }


}
