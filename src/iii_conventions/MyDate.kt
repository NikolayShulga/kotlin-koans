package iii_conventions

import java.math.BigDecimal
import java.math.BigInteger
import java.time.Year
import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        // compareTo must return 0 for same dates, negative or positive number otherwise.
        // There is no requirement to return a consistent "distance" between values, so
        // let's keep things simple.
        if (year != other.year)  return year - other.year
        if (month != other.month) return month - other.month
        if (dayOfMonth != other.dayOfMonth) return dayOfMonth - other.dayOfMonth
        return  0

        // Interestingly enough, a Calendar base implementation doesn't compute exact 'distance' in days either.
        // Performance, I guess, or maybe Calendar just wasn't implemented properly. <insert a comment about Java and toaster ovens>.
        /*
        val c= Calendar.getInstance()
        c.set(year, month, dayOfMonth)
        val c_other = Calendar.getInstance()
        c_other.set(other.year, other.month, other.dayOfMonth)
        val cmp = c.compareTo(c_other)
        return cmp
        */

    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class MultiInterval(val interval: TimeInterval, val count:  Int)

operator fun MyDate.plus(other: TimeInterval): MyDate {
    return addTimeIntervals(other, 1)
}

operator fun MyDate.plus(other: MultiInterval): MyDate {
    return addTimeIntervals(other.interval, other.count)
}

operator fun TimeInterval.times(other: Int): MultiInterval {
    return MultiInterval(this, other)

}

class DateIterator(val range: DateRange, var current:MyDate = range.start): Iterator<MyDate> {
override fun hasNext(): Boolean { return current <= range.endInclusive}
override fun next(): MyDate { val  retval = current; current = current.nextDay(); return retval} // is there an equivalent of postfix?
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> { return DateIterator(this) }
}
