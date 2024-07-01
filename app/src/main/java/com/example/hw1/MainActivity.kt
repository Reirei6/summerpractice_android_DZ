package com.example.hw1

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var n: EditText
    private lateinit var carsList: List<Car>
    private lateinit var pairsList: List<Pair<Car, Car>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextText)
        val button = findViewById<Button>(R.id.button)
        val winnerText = findViewById<TextView>(R.id.winnerText)

        button.setOnClickListener {
            val input = editText.text.toString()
            val n = input.toInt()
            carsList = Car.createRandomCars(n)
            pairsList = splitListIntoPairs(carsList)
            winnerText.text = determineWinner(pairsList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

open class Car(
    val id: Int,
    val name: String,
    val maxSpeed: Int,
    val age: Int,
    val wheelDrive: String,
) {
    fun carInfo() {
        println("ID: $id")
        println("Name: $name")
        println("Max Speed: $maxSpeed")
        println("Age: $age")
        println("Wheel Drive: $wheelDrive")
    }

    fun carCompetition(car: Car) {
        if (this.maxSpeed > car.maxSpeed) {
            println("${this.name} wins")
        } else if (this.maxSpeed < car.maxSpeed) {
            println("${car.name} wins")
        } else {
            println("Tie")
        }
    }

    companion object {
        fun createRandomCars(n: Int): List<Car> {
            val cars = mutableListOf<Car>()
            repeat(n) {
                val id = Random.nextInt(1, 100)
                val name = listOf("Lada", "Toyota", "Ford", "BMW").random()
                val maxSpeed = Random.nextInt(70, 200)
                val age = Random.nextInt(1, 10)
                val wheelDrive = listOf("AWD", "FWD", "RWD").random()

                cars.add(Car(id, name, maxSpeed, age, wheelDrive))
            }
            return cars
        }
    }
}

fun splitListIntoPairs(list: List<Car>): MutableList<List<Car>> {
    val shuffledList = list.shuffled()
    val pairsList = mutableListOf<List<Car>>()
    var i = 0
    while (i < shuffledList.size - 1) {
        val pair = listOf(shuffledList[i], shuffledList[i + 1])
        pairsList.add(pair)
        i += 2
    }
    if (shuffledList.size % 2 != 0) {
        pairsList.add(listOf(shuffledList.last()))
    }
    return pairsList.toMutableList()
}


fun determineWinner(pairsList: MutableList<List<Car>>) {
    var round = 1
    while (pairsList.size > 1) {
        println("Round $round:")
        val winners = mutableListOf<Car>()
        for (pair in pairsList) {
            val car1 = pair[0]
            val car2 = pair.getOrNull(1)

            if (car2 != null) {
                println("${car1.name} vs ${car2.name}:")
                car1.carCompetition(car2)

                if (car1.maxSpeed > car2.maxSpeed) {
                    winners.add(car1)
                } else {
                    winners.add(car2)
                }
            } else {
                winners.add(car1)
            }
        }
        pairsList.clear()
        pairsList.addAll(splitListIntoPairs(winners))
        round++
    }
    println("Winner is ${pairsList[0][0].name}")
}




///class Crossover(enjinePower: Int, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)
///class Retro(releaseData: Int, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)
///class Trucks(capacity: Int, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)
///class Branded(tradeMark: String, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)

