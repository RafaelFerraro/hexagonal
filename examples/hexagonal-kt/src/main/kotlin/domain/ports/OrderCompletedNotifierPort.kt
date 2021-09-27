package domain.ports

import domain.Order

interface OrderCompletedNotifierPort {
    fun notify(order: Order)
}