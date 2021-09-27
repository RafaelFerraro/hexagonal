package domain.ports

import domain.Order

interface OrdersRepositoryPort {
    fun update(order: Order)
}