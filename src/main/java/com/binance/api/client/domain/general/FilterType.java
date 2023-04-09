package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringExclude;

/**
 * Filters define trading rules on a symbol or an exchange. Filters come in two forms: symbol filters and exchange filters.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum FilterType {
	// Exchange
	// Symbol
	EXCHANGE_MAX_ALGO_ORDERS,
	EXCHANGE_MAX_NUM_ORDERS,
	ICEBERG_PARTS,
	LOT_SIZE,
	MARKET_LOT_SIZE,
	MAX_ALGO_ORDERS,
	MAX_NUM_ALGO_ORDERS,
	MAX_NUM_ICEBERG_ORDERS,
	MAX_NUM_ORDERS,
	MAX_POSITION,
	MIN_NOTIONAL,
	PERCENT_PRICE,
	PERCENT_PRICE_BY_SIDE,
	PRICE_FILTER,
	TRAILING_DELTA
}