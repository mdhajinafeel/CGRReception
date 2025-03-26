package com.codringreen.receptionloading.repository;

import com.codringreen.receptionloading.db.dao.FarmDataEntryDao;
import com.codringreen.receptionloading.db.dao.FarmDetailsDao;
import com.codringreen.receptionloading.db.entity.FarmDataEntry;
import com.codringreen.receptionloading.db.entity.FarmDetails;

import java.util.List;

public class FarmRepository {

    private final FarmDetailsDao farmDetailsDao;
    private final FarmDataEntryDao farmDataEntryDao;

    public FarmRepository(FarmDetailsDao farmDetailsDao, FarmDataEntryDao farmDataEntryDao) {
        this.farmDetailsDao = farmDetailsDao;
        this.farmDataEntryDao = farmDataEntryDao;
    }

    public long saveFarmDetails(FarmDetails farmDetails) {
        return farmDetailsDao.insertOrReplaceFarmDetails(farmDetails);
    }

    public List<FarmDetails> fetchFarmLists() {
        return farmDetailsDao.getFarmDetails();
    }

    public void saveFarmDataEntry(List<FarmDataEntry> farmDataEntryList, String inventoryOrder, int supplierId,
                                  int totalPieces, double totalGrossVolume, double totalNetVolume) {

        //DELETE
        farmDataEntryDao.deleteFarmData(inventoryOrder);
        farmDataEntryDao.insertOrReplaceFarmDataEntry(farmDataEntryList);

        farmDetailsDao.updateFarmDetails(inventoryOrder, supplierId, totalPieces, totalGrossVolume, totalNetVolume);
    }

    public List<FarmDataEntry> getFarmDataEntryLists(String inventoryOrder) {
        return farmDataEntryDao.getFarmDataEntry(inventoryOrder);
    }
}