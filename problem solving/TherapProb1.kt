sealed class Area(
    val height: Double = 0.0
) {
    // Type: 1
    class Square(val length: Double) : Area(length) {
        fun calculateArea(): Double {
            return (height * height)
        }
    }

    object Linear : Area(){} // Type: 2

    // Type: 3
    class Circle(val diameter: Double) : Area(diameter){
        fun calculateArea():Double{
            return (Pi * ((height * height) / 4.0))
        }
    }

    // Type: 4
    class Rectangle(val width: Double, length: Double) : Area(length){
        fun calculateArea(): Double {
            return (height * width)
        }
    }

    // Type: 5
    class Triangle(val base: Double, height: Double) : Area(height){
        fun calculateArea(): Double {
            return (0.5 * height * base)
        }
    }

    object Spiral : Area() // Type 6

    companion object {
        const val Pi = 3.14159
        const val SQUARE = 1
        const val LINEAR = 2
        const val CIRCLE = 3
        const val RECTANGLE = 4
        const val TRIANGLE = 5
        const val SPIRAL = 6
    }
}

fun main() {
    val scanner = java.util.Scanner(System.`in`)
    var numberOfAreas = scanner.nextInt()
    val areaHeight = scanner.nextDouble()
    var sum = 10.5

    while (numberOfAreas != 0) {
        val areaShape = scanner.nextInt()

        when (areaShape) {
            Area.SQUARE -> {
                sum += Area.Square(areaHeight).calculateArea()
            }
            Area.RECTANGLE ->{
                val width = scanner.nextDouble()
                sum += Area.Rectangle(width= width, length = areaHeight).calculateArea()
            }
            Area.CIRCLE ->{
                sum += Area.Circle(areaHeight).calculateArea()
            }
            Area.TRIANGLE ->{
                val base = scanner.nextDouble()
                sum += Area.Triangle(base = base, height = areaHeight).calculateArea()
            }
        }

        numberOfAreas--
    }

    println(sum)
}