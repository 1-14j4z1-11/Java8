package http;

import com.sun.net.httpserver.*;

public interface IResponder
{
	/**
	 * このインスタンスがリクエストに対して応答可能かどうかを判定します
	 * @param method リクエストメソッド
	 * @param path リクエストパス
	 * @return リクエストに対して応答可能であればtrue/応答不可であればfalse
	 */
	boolean canRespond(Method method, String path);
	
	/**
	 * リクエストに対する応答処理を行います
	 * @param exchange リクエストに対する処理を行うHttpExchangeインスタンス
	 */
	void respond(HttpExchange exchange);
}
