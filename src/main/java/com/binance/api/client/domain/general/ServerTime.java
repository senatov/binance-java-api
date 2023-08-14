package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.lang.String.valueOf;

/**
 * Time of the server running Binance's REST API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerTime {
	private Long serverTime;

	public Long getServerTime() {
		return serverTime;
	}

	public void setServerTime(Long serverTime) {
		this.serverTime = serverTime;
	}

	@Override
	public String toString() {
		return valueOf(serverTime);
	}
}