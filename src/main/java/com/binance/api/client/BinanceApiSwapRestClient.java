package com.binance.api.client;

import com.binance.api.client.domain.SwapRemoveType;
import com.binance.api.client.domain.account.Liquidity;
import com.binance.api.client.domain.account.LiquidityOperationRecord;
import com.binance.api.client.domain.account.Pool;
import com.binance.api.client.domain.account.SwapHistory;
import com.binance.api.client.domain.account.SwapQuote;
import com.binance.api.client.domain.account.SwapRecord;

import java.util.List;

public interface BinanceApiSwapRestClient {

	/**
	 * Get metadata about all swap pools.
	 */
	List<Pool> listAllSwapPools();

	/**
	 * Get liquidity information and user share of a pool.
	 */
	Liquidity getPoolLiquidityInfo(String poolId);

	/**
	 * Add liquidity to a pool.
	 */
	LiquidityOperationRecord addLiquidity(String poolId,
	                                      String asset,
	                                      String quantity);

	/**
	 * Remove liquidity from a pool, type include SINGLE and COMBINATION, asset is mandatory for single asset removal
	 */
	LiquidityOperationRecord removeLiquidity(String poolId, SwapRemoveType type, List<String> asset, String shareAmount);

	/**
	 * Get liquidity operation (add/remove) records of a pool
	 */
	List<LiquidityOperationRecord> getPoolLiquidityOperationRecords(
			String poolId,
			Integer limit);

	/**
	 * Get liquidity operation (add/remove) record.
	 */
	LiquidityOperationRecord getLiquidityOperationRecord(String operationId);

	/**
	 * Request a quote for swap quote asset (selling asset) for base asset (buying asset), essentially price/exchange rates.
	 */
	SwapQuote requestQuote(String quoteAsset,
	                       String baseAsset,
	                       String quoteQty);

	/**
	 * Swap quoteAsset for baseAsset
	 */
	SwapRecord swap(String quoteAsset,
	                String baseAsset,
	                String quoteQty);

	SwapHistory getSwapHistory(String swapId);
}