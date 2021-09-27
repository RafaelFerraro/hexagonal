package infra.jparepositories

import domain.Item
import domain.Order
import domain.ports.ItemsRepositoryPort

class ItemsRepository: ItemsRepositoryPort {
    override fun findByOrder(order: Order): Collection<Item> {
        TODO("Not yet implemented")
    }
}