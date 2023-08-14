package com.binance.api.domain.market;

import com.binance.api.client.domain.market.Candlestick;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the adequate deserialization of candlestick JSON information.
 */
public class CandlestickDeserializerTest {

	@Test
	public void testCandlestickDeserializerTest() {
		final String candlestickJson = """
				[
				    1499040000000,
				        "0.01634790",
				        "0.80000000",
				        "0.01575800",
				        "0.01577100",
				        "148976.11427815",
				        1499644799999,
				        "2434.19055334",
				        308,
				        "1756.87402397",
				        "28.46694368",
				        "17928899.62484339"
				        ]""";
		ObjectMapper mapper = new ObjectMapper();
		try {
			Candlestick candlestick = mapper.readValue(candlestickJson, Candlestick.class);
			assertEquals((long) candlestick.getOpenTime(), 1499040000000L);
			assertEquals(candlestick.getOpen(), "0.01634790");
			assertEquals(candlestick.getHigh(), "0.80000000");
			assertEquals(candlestick.getLow(), "0.01575800");
			assertEquals(candlestick.getClose(), "0.01577100");
			assertEquals(candlestick.getVolume(), "148976.11427815");
			assertEquals((long) candlestick.getCloseTime(), 1499644799999L);
			assertEquals(candlestick.getQuoteAssetVolume(), "2434.19055334");
			assertEquals((long) candlestick.getNumberOfTrades(), 308L);
			assertEquals(candlestick.getTakerBuyBaseAssetVolume(), "1756.87402397");
			assertEquals(candlestick.getTakerBuyQuoteAssetVolume(), "28.46694368");
		} catch (IOException e) {
			fail();
		}
	}
}