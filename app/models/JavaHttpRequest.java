package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class JavaHttpRequest {

	public static String execute(String type, String consumerKey) {

		String url = "https://api.tokyometroapp.jp/api/v2/datapoints"
		           + "?rdf:type=" + type
		           + "&acl:consumerKey=" + consumerKey;
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			
			// HTTPコネクション作成
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			// GETメソッド指定
			connection.setRequestMethod("GET");

			// 文字列として取得(1件のみの決めうち)
			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			/*
			 * 置換内容
			 * "odpt:availableTimeFrom":"05-00" → "odpt-availableTimeFrom":"05-00"
			 * "odpt:carNumber":1 → "odpt-carNumber":1
			 */
			return sb.toString().replaceAll("\":\"", "_CENTER_").replaceAll("\":", "_LEFT_").replaceAll(":\"", "TEST").replaceAll(":", "_").replaceAll("_CENTER_", "\":\"").replaceAll("_LEFT_", "\":");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}
		return null;
	}

}

