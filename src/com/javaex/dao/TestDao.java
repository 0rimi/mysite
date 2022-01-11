package com.javaex.dao;

import com.javaex.vo.UserVo;

public class TestDao {

	public static void main(String[] args) {
		
		UserDao userDao = new UserDao();
		UserVo user1 = new UserVo("bg0313","20010313","최범규","male");
		
		userDao.insert(user1);
		
		System.out.println(user1);
		
	}

}
