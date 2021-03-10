package com.ruoyi.asset.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ruoyi.asset.domain.AssetStock;
import com.ruoyi.asset.service.IAssetStockService;
import com.ruoyi.common.utils.DateUtils;

@Component
public class AssetTask {

    @Autowired
    private IAssetStockService assetStockService;
    
	@Scheduled(cron = "30 */10 * * * ?")
    public  void count(){
		AssetStock stock = new AssetStock();
		stock.setYear(DateUtils.dateTimeNow(DateUtils.YYYY));
		List<AssetStock> list = assetStockService.selectAssetStockList(stock);
		if(list == null || list.size() == 0) {
			assetStockService.insertAssetStock(stock);
		}else {
			stock = list.get(0);
		}
		assetStockService.countAssetStock(stock);
    }
}
