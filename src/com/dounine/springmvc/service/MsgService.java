package com.dounine.springmvc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dounine.springmvc.dao.MsgMapper;
import com.dounine.springmvc.entity.Msg;

@Component
public class MsgService {

	@Resource
	private MsgMapper msgMapper;
	
	public int add(Msg msg){
		return msgMapper.insert(msg);
	}
	
	public boolean remove(Integer id){
		return msgMapper.delete(id);
	}
	
	public Msg get(Integer id){
		return msgMapper.get(id);
	}
}
