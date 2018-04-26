package com.hjcrm.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.system.entity.Action;
import com.hjcrm.system.service.IActionService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActionServiceImpl implements IActionService {

	
	public void save(Action action) {
		// TODO Auto-generated method stub
		
	}

}
