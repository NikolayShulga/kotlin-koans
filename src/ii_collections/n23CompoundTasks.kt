package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    return  this.customers.filter { it.orderedProducts.contains(product) }.toSet()
}

fun pickMoreExpensive(p1:Product?, p2:Product): Product {
    if (null == p1 || p1.price < p2.price ) return  p2
    return p1
}

// most expensive product in order. If the order not delivered or empty, null
fun  Order.getMostExpensiveProduct(): Product? {
    if (isDelivered)
        return products.maxBy{it.price}
    return null
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    return orders.filter({it.isDelivered}).flatMap{it.products}.maxBy{it.price}
/*
// TODO: this is Java. Rewrite in a Kotlin way
    var mostExpensive: Product? = null;
    for (order in orders) {
        if (order.isDelivered) {
            val mostExpensiveProductInOrder = order.getMostExpensiveProduct()
            if (null != mostExpensiveProductInOrder) {
                if (null == mostExpensive ||
                        mostExpensiveProductInOrder.price > mostExpensive.price)
                    mostExpensive = mostExpensiveProductInOrder
            }
        }
    }

    return mostExpensive*/
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    return customers.flatMap { it.orders.flatMap{it.products} }.count{it == product}
}
