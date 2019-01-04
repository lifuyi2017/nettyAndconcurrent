package com.example.concurrent;

import com.example.commonsCLI.CLI;
import com.example.cqct.util.BigEndian;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrentApplicationTests {

	@Test
	public void contextLoads() {
		byte[] bytes ={(byte)123, (byte) 201};
		int bigEndianShort = BigEndian.getBigEndianShort(bytes);
		System.out.println(bigEndianShort);
	}

	@Test
	public void testCli(){
		String argss[]={"-t  1000"};
		try {
			CLI.main(argss);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
