package domain

import domain.ports.ItemsRepositoryPort
import domain.ports.OrderCompletedNotifierPort
import domain.ports.OrdersRepositoryPort
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OrderTest {
    @Test
    fun `updates the order if it isn't completed when adding an item`() {
        val completed = false
        val item = Item("123")
        val itemsRepository = mockk<ItemsRepositoryPort>()
        val ordersRepository = spyk<OrdersRepositoryPort>()
        val orderCompletedNotifier = mockk<OrderCompletedNotifierPort>()
        val order = Order(
            id = "123",
            completed = completed,
            itemsRepository = itemsRepository,
            ordersRepository = ordersRepository,
            orderCompletedNotifier = orderCompletedNotifier
        )

        order.addItem(item)

        verify(exactly = 1) { ordersRepository.update(order) }
    }

    @Test
    fun `does not update order if it is completed when adding an item`() {
        val completed = true
        val item = Item("123")
        val itemsRepository = mockk<ItemsRepositoryPort>()
        val ordersRepository = spyk<OrdersRepositoryPort>()
        val orderCompletedNotifier = mockk<OrderCompletedNotifierPort>()
        val order = Order(
            id = "123",
            completed = completed,
            itemsRepository = itemsRepository,
            ordersRepository = ordersRepository,
            orderCompletedNotifier = orderCompletedNotifier
        )

        order.addItem(item)

        verify(exactly = 0) { ordersRepository.update(order) }
    }

    @Test
    fun `returns the items found by the order`() {
        val completed = true
        val items = listOf(Item("123"))
        val itemsRepository = mockk<ItemsRepositoryPort>()
        val ordersRepository = spyk<OrdersRepositoryPort>()
        val orderCompletedNotifier = mockk<OrderCompletedNotifierPort>()
        val order = Order(
            id = "123",
            items = items,
            completed = completed,
            itemsRepository = itemsRepository,
            ordersRepository = ordersRepository,
            orderCompletedNotifier = orderCompletedNotifier
        )
        every { itemsRepository.findByOrder(order) } returns items

        Assertions.assertEquals(order.items(), items)
    }

    @Test
    fun `updates an order if it has not been completed and there are items when asking for completion`() {
        val completed = false
        val items = listOf(Item("123"))
        val itemsRepository = mockk<ItemsRepositoryPort>()
        val ordersRepository = spyk<OrdersRepositoryPort>()
        val orderCompletedNotifier = spyk<OrderCompletedNotifierPort>()
        val order = Order(
            id = "123",
            items = items,
            completed = completed,
            itemsRepository = itemsRepository,
            ordersRepository = ordersRepository,
            orderCompletedNotifier = orderCompletedNotifier
        )
        order.complete()

        verify(exactly = 1) { ordersRepository.update(order) }
    }

    @Test
    fun `notifies the order completion if it has not been completed and there are items`() {
        val completed = false
        val items = listOf(Item("123"))
        val itemsRepository = mockk<ItemsRepositoryPort>()
        val ordersRepository = spyk<OrdersRepositoryPort>()
        val orderCompletedNotifier = spyk<OrderCompletedNotifierPort>()
        val order = Order(
            id = "123",
            items = items,
            completed = completed,
            itemsRepository = itemsRepository,
            ordersRepository = ordersRepository,
            orderCompletedNotifier = orderCompletedNotifier
        )

        order.complete()

        verify(exactly = 1) { orderCompletedNotifier.notify(order) }
    }

    @Test
    fun `does not update neither notify if order is already completed`() {
        val completed = true
        val items = listOf(Item("123"))
        val itemsRepository = mockk<ItemsRepositoryPort>()
        val ordersRepository = spyk<OrdersRepositoryPort>()
        val orderCompletedNotifier = mockk<OrderCompletedNotifierPort>()
        val order = Order(
            id = "123",
            items = items,
            completed = completed,
            itemsRepository = itemsRepository,
            ordersRepository = ordersRepository,
            orderCompletedNotifier = orderCompletedNotifier
        )
        order.complete()

        verify(exactly = 0) { ordersRepository.update(order) }
        verify(exactly = 0) { orderCompletedNotifier.notify(order) }
    }

    @Test
    fun `does not update neither notify if there are no items`() {
        val completed = false
        val itemsRepository = mockk<ItemsRepositoryPort>()
        val ordersRepository = spyk<OrdersRepositoryPort>()
        val orderCompletedNotifier = spyk<OrderCompletedNotifierPort>()
        val order = Order(
            id = "123",
            completed = completed,
            itemsRepository = itemsRepository,
            ordersRepository = ordersRepository,
            orderCompletedNotifier = orderCompletedNotifier
        )

        order.complete()

        verify(exactly = 0) { ordersRepository.update(order) }
        verify(exactly = 0) { orderCompletedNotifier.notify(order) }
    }
}