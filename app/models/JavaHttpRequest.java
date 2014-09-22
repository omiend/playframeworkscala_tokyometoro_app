package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class JavaHttpRequest {

	public static String execute(String mapCenterLat, String mapCenterLng) {

		// RESTのURL作成
		// String url = "https://api.tokyometroapp.jp/api/v2/places"
		//            + "?rdf:type=ug:Poi"
		// 		   + "&lon=" + mapCenterLng
		// 		   + "&lat=" + mapCenterLat
		// 		   + "&radius=" + "1000"
		//            + "&acl:consumerKey=66d3509375b4fb4cbf90c4b3dfb17976e3951ddd7073ec1613cc81c3fc38f44e";
		String url = "https://api.tokyometroapp.jp/api/v2/datapoints"
		           + "?rdf:type=odpt:TrainInformation"
		           + "&acl:consumerKey=66d3509375b4fb4cbf90c4b3dfb17976e3951ddd7073ec1613cc81c3fc38f44e";
		System.out.println(url);

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

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}
		return null;
	}

}

