package ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity

class PriceListResult constructor(
    val startDate: String,
    val endDate: String,
    val region: String,
    val priceItems: List<PriceItemResult>
) {
}