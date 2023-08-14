package com.binance.api.client.security;

import com.binance.api.client.constant.BinanceApiConstants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * A request interceptor that injects the API Key Header into requests, and signs messages, whenever required.
 */
public class AuthenticationInterceptor implements Interceptor {

	private final String apiKey;

	private final String secret;

	public AuthenticationInterceptor(String apiKey, String secret) {
		this.apiKey = apiKey;
		this.secret = secret;
	}

	/**
	 * Extracts the request body into a String.
	 *
	 * @return request body as a string
	 */
	@SuppressWarnings("unused")
	private static String bodyToString(RequestBody request) {
		try (Buffer buffer = new Buffer()) {
			RequestBody copy = request;
			if (null != copy) {
				copy.writeTo(buffer);
			} else {
				return "";
			}
			return buffer.readUtf8();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request original = chain.request();
		Request.Builder newRequestBuilder = original.newBuilder();
		boolean isApiKeyRequired = null != original.header(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY);
		boolean isSignatureRequired = null != original.header(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED);
		newRequestBuilder.removeHeader(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY)
				.removeHeader(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED);
		// Endpoint requires sending a valid API-KEY
		if (isApiKeyRequired || isSignatureRequired) {
			newRequestBuilder.addHeader(BinanceApiConstants.API_KEY_HEADER, apiKey);
		}
		// Endpoint requires signing the payload
		if (isSignatureRequired) {
			String payload = original.url().query();
			if (!StringUtils.isEmpty(payload)) {
				String signature = HmacSHA256Signer.sign(payload, secret);
				HttpUrl signedUrl = original.url().newBuilder().addQueryParameter("signature", signature).build();
				newRequestBuilder.url(signedUrl);
			}
		}
		// Build new request after adding the necessary authentication information
		Request newRequest = newRequestBuilder.build();
		return chain.proceed(newRequest);
	}

	@Override
	public int hashCode() {
		return Objects.hash(apiKey, secret);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (null == o || getClass() != o.getClass()) {
			return false;
		}
		AuthenticationInterceptor that = (AuthenticationInterceptor) o;
		return Objects.equals(apiKey, that.apiKey) &&
				Objects.equals(secret, that.secret);
	}
}