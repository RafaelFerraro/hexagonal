package infra.kafka

import domain.Order
import domain.ports.OrdersRepositoryPort

class OrderCompletedNotifierKafka: OrdersRepositoryPort {
    private val topicName: String = "order-completed"

    override fun update(order: Order) {
        TODO("Not yet implemented")
    }
}