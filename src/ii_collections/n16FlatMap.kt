package ii_collections

fun example() {

    val result = listOf("abc", "12").flatMap { it.toList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

val Customer.orderedProducts: Set<Product> get() {
    // Return all products this customer has ordered
    return orders.flatMap { it.products }.toSet()
}

val Shop.allOrderedProducts: Set<Product> get() {
    // Return all products that were ordered by at least one customer
    val allProducts = HashSet<Product> ()

    customers.forEach({allProducts.addAll(it.orderedProducts)})
//  try  to do fold instead
    return allProducts
}
