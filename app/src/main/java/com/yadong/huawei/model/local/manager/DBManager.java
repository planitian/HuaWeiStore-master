package com.yadong.huawei.model.local.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yadong.huawei.model.local.bean.DaoMaster;
import com.yadong.huawei.model.local.bean.DaoSession;
import com.yadong.huawei.model.local.bean.User;
import com.yadong.huawei.model.local.bean.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 数据库的管理
 */

public class DBManager {

    private static DBManager instance;
    private final static String dbName = "test_db";
    private Context mContext;
    private DaoMaster.DevOpenHelper openHelper = null;

    private DBManager(Context context) {
        this.mContext = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName);
    }

    public static DBManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager(context);
                }
            }
        }
        return instance;
    }

    private SQLiteDatabase getWriteableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(mContext, dbName);
        }
        return openHelper.getWritableDatabase();
    }

    private SQLiteDatabase getRedadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(mContext, dbName);
        }
        return openHelper.getReadableDatabase();
    }

    /**
     * 存入User
     */
    public void saveUser(User user) {
        UserDao userDao = getWriteUserDao();
        userDao.save(user);
    }

    /**
     * 存入User集合
     *
     * @param users
     */
    public void saveUsers(List<User> users) {
        UserDao userDao = getWriteUserDao();
        userDao.saveInTx(users);
    }

    /**
     * 删除
     *
     * @param user
     */
    public void delteUser(User user) {
        UserDao userDao = getWriteUserDao();
        userDao.delete(user);
    }


    /**
     * 更新
     *
     * @param user
     */
    public void updateUser(User user) {
        UserDao userDao = getWriteUserDao();
        userDao.update(user);
    }

    /**
     * 查找全部
     *
     * @return
     */
    public List<User> queryUser() {
        UserDao userDao = getReadUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        return qb.list();
    }

    /**
     * 根据姓名查找user
     *
     * @param age
     * @return
     */
    public List<User> queryUser(int age) {
        UserDao userDao = getReadUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        //添加查询条件
        qb.where(UserDao.Properties.Age.eq(age));
        return qb.list();
    }


    private UserDao getWriteUserDao() {
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getUserDao();
    }

    private UserDao getReadUserDao() {
        DaoMaster daoMaster = new DaoMaster(getRedadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getUserDao();
    }

}
