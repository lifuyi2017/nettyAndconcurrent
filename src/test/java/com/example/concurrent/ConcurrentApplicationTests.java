package com.example.concurrent;

import com.example.commonsCLI.CLI;
import com.example.cqct.util.BigEndian;
import com.example.entity.Book;
import com.example.utils.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


	@Test
	public void test1() {
		String json = "{\"id\":\"2\",\"NAME\":\"\"三国演义\",\"price\":\"180\",\"author\":\"吴冠中\"}";
		System.out.println(json);
		List<String> tags = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\\\"([a-zA-Z0-9]*)\\\":");
		Matcher m = pattern.matcher(json);
		while (m.find()) {
			tags.add(m.group(1));
		}
		for (int i = 0; i < tags.size(); i++) {
			json = json
					.replaceAll("\\\"" + tags.get(i) + "\\\"",
							"^^" + tags.get(i).toLowerCase() + "^^")
					.replaceAll(":\\\"", ":^^").replaceAll("\\\",", "^^,");
		}
		json = json.replaceAll("\\\"}", "^^}").replaceAll("\\\"]", "^^]")
				.replaceAll("\"", "~~");
		json = json.replace("^^", "\"");
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();

		try {
			Book book = mapper.readValue(json, Book.class);
			System.out.println(book.getName());
			book.setName(book.getName().replace("~~", "\""));
			System.out.println("作者：" + book.getAuthor() + "\n书名："
					+ book.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void  testUtil(){
		String json = "{\"id\":\"2\",\"name\":\"\"三国演/义\",\"price\":\"180\",\"author\":\"吴冠\\中\"}";

		System.out.println(json);

		Book book = JsonUtil.getBeanFromJson(json,Book.class);

		System.out.println(book.getName());
	}
}
