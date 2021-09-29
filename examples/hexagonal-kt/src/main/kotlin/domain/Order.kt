package domain

import domain.ports.ItemsRepositoryPort
import domain.ports.OrderCompletedNotifierPort
import domain.ports.OrdersRepositoryPort

class Order(
    val id: String,
    private var completed: Boolean?,
    private var items: Collection<Item> = emptyList(),
    private val itemsRepository: ItemsRepositoryPort,
    private val ordersRepository: OrdersRepositoryPort,
    private val orderCompletedNotifier: OrderCompletedNotifierPort
    ) {
    fun addItem(item: Item) {
        if (completed == false) {
            items.plus(item)
            ordersRepository.update(this)
        }
    }

    fun items(): Collection<Item> {
        return itemsRepository.findByOrder(this)
    }

    fun complete() {
        if (completed == false && items.any()) {
            completed = true

            ordersRepository.update(this)
            orderCompletedNotifier.notify(this)
        }
    }
}
