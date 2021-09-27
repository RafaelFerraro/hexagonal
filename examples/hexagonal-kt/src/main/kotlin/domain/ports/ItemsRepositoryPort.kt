package domain.ports

import domain.Item
import domain.Order

interface ItemsRepositoryPort {
    fun findByOrder(order: Order): Collection<Item>
}