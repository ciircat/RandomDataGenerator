package ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity

class SupplierResult constructor(
    val organisationId: String,
    val companyId: String,
    val companyName: String,
    val companyDescription: String,
    val vatId: String,
    val webside: String
) {
    val itemsForSaleList = ArrayList<ItemResult>()
    val priceLists = ArrayList<PriceListResult>()
}