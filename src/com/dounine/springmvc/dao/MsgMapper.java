package com.dounine.springmvc.dao;

import com.dounine.springmvc.entity.Msg;

public interface MsgMapper {

	public int insert(Msg msg);
	
	public boolean delete(Integer id);
	
	public Msg get(Integer id);
}
