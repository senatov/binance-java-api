package com.binance.api.domain.event;

import com.binance.api.client.domain.event.CandlestickEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests that JSON responses from ta candlestick event are converted to the appropriate {@code CandlestickEvent} object.
 */
public class CandlestickEventDeserializerTest {

	@Test
	public void testCandlestickEventDeserializer() {
		String candlestickEventJson = """
				{
				        "e": "kline",
				        "E": 1,
				        "s": "ETHBTC",
				        "k": {
				        "t": 1499404860000,
				        "T": 1499404919999,
				        "s": "ETHBTC",\s
				        "i": "1m",
				        "f": 77462,\s
				        "L": 77465,\s
				        "o": "0.10278577",\s
				        "c": "0.10278645",\s
				        "h": "0.10278712",\s
				        "l": "0.10278518",\s
				        "v": "17.47929838",\s
				        "n": 4,\s
				        "x": false,\s
				        "q": "1.79662878",\s
				        "V": "2.34879839",\s
				        "Q": "0.24142166",\s
				        "B": "13279784.01349473"
				      }}""";
		ObjectMapper mapper = new ObjectMapper();
		try {
			CandlestickEvent candlestickEvent = mapper.readValue(candlestickEventJson, CandlestickEvent.class);
			assertEquals(candlestickEvent.getEventType(), "kline");
			assertEquals(candlestickEvent.getEventTime(), 1L);
			assertEquals(candlestickEvent.getSymbol(), "ETHBTC");
			assertEquals((long) candlestickEvent.getOpenTime(), 1499404860000L);
			assertEquals(candlestickEvent.getOpen(), "0.10278577");
			assertEquals(candlestickEvent.getHigh(), "0.10278712");
			assertEquals(candlestickEvent.getLow(), "0.10278518");
			assertEquals(candlestickEvent.getClose(), "0.10278645");
			assertEquals(candlestickEvent.getVolume(), "17.47929838");
			assertEquals((long) candlestickEvent.getCloseTime(), 1499404919999L);
			assertEquals(candlestickEvent.getIntervalId(), "1m");
			assertEquals((long) candlestickEvent.getFirstTradeId(), 77462L);
			assertEquals((long) candlestickEvent.getLastTradeId(), 77465L);
			assertEquals(candlestickEvent.getQuoteAssetVolume(), "1.79662878");
			assertEquals((long) candlestickEvent.getNumberOfTrades(), 4L);
			assertEquals(candlestickEvent.getTakerBuyBaseAssetVolume(), "2.34879839");
			assertEquals(candlestickEvent.getTakerBuyQuoteAssetVolume(), "0.24142166");
			assertEquals(candlestickEvent.getBarFinal(), false);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}