fun main() {
    val scanner = java.util.Scanner(System.`in`)
    val countryA = scanner.nextLine()
    val countryB = scanner.nextLine()
    val matchedChar = bannerProblem(countryA, countryB)
    println(matchedChar)
}

fun bannerProblem(a: String, b: String): String {
    var hashMapA = HashMap<Char, Int>()
    var hashMapB = HashMap<Char, Int>()
    a.forEach {
        hashMapA.put(it, 0)
    }
    b.forEach {
        hashMapB.put(it, 0)
    }

    for (i in 0 until  hashMapA.size) {
        if (i < a.length && i < b.length) {
            if (hashMapA.containsKey(b.get(i))) {
                return b.get(i).toString()
            }
            if (hashMapB.containsKey(a.get(i)))
                return a.get(i).toString()
        }else{
            return "No match found"
        }
    }
    return "No match found"
}
