package io.gatling.benchmark.jsonpath;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class Bytes {

	public static final String GOESSNER_PATH1 = "$.store.book[2].author";
	public static final String GOESSNER_PATH2 = "$..author";
	public static final String GOESSNER_PATH3 = "$.store.*";
	public static final String GOESSNER_PATH4 = "$.store..price";
	public static final String GOESSNER_PATH5 = "$..book[2].title";
	public static final String GOESSNER_PATH6 = "$..book[-1:].title";
	public static final String GOESSNER_PATH7 = "$..book[:2].title";
	public static final String GOESSNER_PATH8 = "$..*";
	public static final String GOESSNER_PATH9 = "$.store.book[*].niçôlàs['nico']['foo'][*].bar[1:-2:3]";
	public static final String GOESSNER_PATH10 = "$.store['book'][:2].title";
	public static final String GOESSNER_PATH11 = "$.store.book[?(@.isbn)].title";
	public static final String GOESSNER_PATH12 = "$.store.book[?(@.category == 'fiction')].title";
	public static final String GOESSNER_PATH13 = "$.store.book[?(@.price < 10 && @.price >4)].title";

	public static final String WEBXML_PATH1 = "$.web-app.servlet[0].init-param.dataStoreName";

	public static final String TWITTER_PATH1 = "$.completed_in";
	public static final String TWITTER_PATH2 = "$.results[:3].from_user";
	public static final String TWITTER_PATH3 = "$.results[1:9:-2].from_user";
	public static final String TWITTER_PATH4 = "$.results[*].to_user_name";
	public static final String TWITTER_PATH5 = "$.results[5].metadata.result_type";
	public static final String TWITTER_PATH6 = "$.results[?(@.from_user == 'anna_gatling')]";
	public static final String TWITTER_PATH7 = "$.results[?(@.from_user_id >= 1126180920)]";

	public static final String TWENTY_K_PATH1 = "$..address";
	public static final String TWENTY_K_PATH2 = "$..friends..name";
	public static final String TWENTY_K_PATH3 = "$..friends[?(@.id == 1)].name";

	public static final byte[] GOESSNER_BYTES = readBytes("data/goessner.json");
	public static final byte[] WEBXML_BYTES = readBytes("data/webxml.json");
	public static final byte[] TWITTER_BYTES = readBytes("data/twitter.json");
	public static final byte[] TWENTY_K_BYTES = readBytes("data/20k.json");

	private static byte[] readBytes(String path) {
		try {
			return IOUtils.toByteArray(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
