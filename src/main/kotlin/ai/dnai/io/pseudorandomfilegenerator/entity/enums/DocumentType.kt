package ai.dnai.io.pseudorandomfilegenerator.entity.enums

enum class DocumentType(val type: String) {
    CONSUMPTION_FILE("consumption"),
    CONTACT_FILE("TODO"),
    ITEM_FILE("items"),
    ORDER_ITEM_FILE("orderItems"),
    PRICE_LIST_FILE("priceList"),
    SUPPLIER_FILE("suppliers"),
    WAREHOUSE_FILE("warehouseState"),
    CURRENCY_FILE("currencies"),
    SUPPLIER_TRANSPORTATION_FILE("suppliersTransportations")
}