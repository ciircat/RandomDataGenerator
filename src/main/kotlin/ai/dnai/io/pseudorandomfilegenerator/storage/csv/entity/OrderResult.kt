package ai.dnai.io.pseudorandomfilegenerator.storage.csv.entity

class OrderResult constructor(
    val orderId: String,
    val orderDate: String,
    val arrivalDate: String,
    val organisationId: String,
    val orderItems: List<OrderItemResult>
){
}