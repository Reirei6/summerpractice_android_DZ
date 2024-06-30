package com.example.hw1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
){
    fun carInfo() {
        println("ID: $id")
        println("Name: $name")
        println("Max Speed: $maxSpeed")
        println("Age: $age")
        println("Wheel Drive: $wheelDrive")
    }
    companion object {
        fun createRandomCar(): Car {
            val id = Random.nextInt(1, 100)
            val name = listOf("Lada", "Toyota", "Ford", "BMW").random()
            val maxSpeed = Random.nextInt(70, 200)
            val age = Random.nextInt(1, 10)
            val wheelDrive = listOf("AWD", "FWD", "RWD").random()

            return Car(id, name, maxSpeed, age, wheelDrive)
        }
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
}


class Crossover(enjinePower: Int, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)
class Retro(releaseData: Int, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)
class Trucks(capacity: Int, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)
class Branded(tradeMark: String, id: Int, name: String, age: Int, maxSpeed: Int, wheelDrive: String) : Car(id, name, age, maxSpeed, wheelDrive)

