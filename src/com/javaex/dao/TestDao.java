package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class TestDao {

	public static void main(String[] args) {
		
		//UserDao userDao = new UserDao();
		//UserVo user1 = new UserVo("bg0313","20010313","최범규","male");
		
		//userDao.insert(user1);
		
		//System.out.println(user1);
		
		//UserVo userinfo = userDao.getUserinfo(4);
		
		//System.out.println(userinfo);
		
		//유저정보 Vo가져와서 이제 갖다놔.. 
		
		BoardDao boardDao = new BoardDao();
		
		List<BoardVo> bdList = boardDao.list();
				
		UserVo uservo = new UserVo();
		UserDao userDao = new UserDao();
		//bdList의 넘버값 받아오기
		for(int i=0; i<bdList.size(); i++) {
			int no = bdList.get(i).getNo();
			
			String name = userDao.getUserinfo(no).getName();
			
			bdList.get(i).setName(name);					
		}
		
		System.out.println(bdList);
		
	}

}
