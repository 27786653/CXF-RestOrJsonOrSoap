package com.sn.service;

import org.springframework.stereotype.Service;

@Service
public class FooBarServiceImpl implements FooBarService{
	@Override
	public String getMessage(String msg) {
 		String output = "FooBar say : " + msg;
 		return output;
 	}
}
