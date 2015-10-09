package com.cdthgk.bmp.product.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test {

	public static void main(String[] args) {
		if("1989-10-13".matches("[{[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]}{[0-9][0-9][0-9][0-9]-[0-9]-[0-9]}{[0-9][0-9][0-9][0-9]/[0-9]/[0-9]}{[0-9][0-9][0-9][0-9]/[0-9][0-9]/[0-9][0-9]}]")){
			System.out.println("ok");
		}else{
			System.out.println("error");
		}
	}
}
