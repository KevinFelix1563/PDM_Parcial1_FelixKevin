package com.example.blackjack

object BarajaProvider {

    fun obtenerBarajaMezclada(): MutableList<Carta> {
        val baraja = mutableListOf<Carta>()
        val palos = listOf("Tréboles", "Diamantes", "Corazones", "Espadas")
        val valores = listOf(
            "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7, 
            "8" to 8, "9" to 9, "10" to 10,
            "J" to 10, "Q" to 10, "K" to 10,
            "As" to 11
        )

        for (palo in palos) {
            for ((rango, valor) in valores) {
                val nombre = "$rango de $palo"

                val idImagen = when (nombre) {
                    "2 de Tréboles" -> R.drawable.two_of_clubs_playing_card_25431_512
                    "2 de Diamantes" -> R.drawable.two_of_diamonds_playing_card_25432_512
                    "2 de Corazones" -> R.drawable.two_of_hearts_playing_card_25433_512
                    "2 de Espadas" -> R.drawable.two_of_spades_playing_card_25430_512
                    
                    "3 de Tréboles" -> R.drawable.three_of_clubs_playing_card_25435_512
                    "3 de Diamantes" -> R.drawable.three_of_diamonds_playing_card_25436_512
                    "3 de Corazones" -> R.drawable.three_of_hearts_playing_card_25437_512
                    "3 de Espadas" -> R.drawable.three_of_spades_playing_card_25434_512
                    
                    "4 de Tréboles" -> R.drawable.four_of_clubs_playing_card_25439_512
                    "4 de Diamantes" -> R.drawable.four_of_diamonds_playing_card_25440_512
                    "4 de Corazones" -> R.drawable.four__of_hearts_playing_card_25441_512
                    "4 de Espadas" -> R.drawable.four_of_spades_playing_card_25438_512
                    
                    "5 de Tréboles" -> R.drawable.five_of_clubs_playing_card_25443_512
                    "5 de Diamantes" -> R.drawable.five_of_diamonds_playing_card_25444_512
                    "5 de Corazones" -> R.drawable.five_of_hearts_playing_card_25445_512
                    "5 de Espadas" -> R.drawable.five_of_spades_playing_card_25442_512
                    
                    "6 de Tréboles" -> R.drawable.six_of_clubs_playing_card_25447_512
                    "6 de Diamantes" -> R.drawable.six_of_diamonds_playing_card_25448_512
                    "6 de Corazones" -> R.drawable.six_of_hearts_playing_card_25449_512
                    "6 de Espadas" -> R.drawable.six_of_spades_playing_card_25446_512
                    
                    "7 de Tréboles" -> R.drawable.seven_of_clubs_playing_cards_25451_512
                    "7 de Diamantes" -> R.drawable.seven_of_diamonds_playing_card_25452_512
                    "7 de Corazones" -> R.drawable.seven_of_hearts_playing_card_25453_512
                    "7 de Espadas" -> R.drawable.seven_of_spades_playing_cards_25450_512
                    
                    "8 de Tréboles" -> R.drawable.eight_of_clubs_playing_card_25455_512
                    "8 de Diamantes" -> R.drawable.eight_of_diamonds_playing_card_25456_512
                    "8 de Corazones" -> R.drawable.eight_of_hearts_playing_card_25457_512
                    "8 de Espadas" -> R.drawable.eight_of_spades_playing_card_25454_512
                    
                    "9 de Tréboles" -> R.drawable.nine_of_clubs_playing_card_25459_512
                    "9 de Diamantes" -> R.drawable.nine_of_diamonds_playing_card_25460_512
                    "9 de Corazones" -> R.drawable.nine_of_hearts_playing_card_25461_512
                    "9 de Espadas" -> R.drawable.nine_of_spade_playing_card_25458_512
                    
                    "10 de Tréboles" -> R.drawable.ten_of_clubs_playing_card_25463_512
                    "10 de Diamantes" -> R.drawable.ten_of_diamonds_playing_card_25464_512
                    "10 de Corazones" -> R.drawable.ten_of_hearts_playing_card_25465_512
                    "10 de Espadas" -> R.drawable.ten_of_spades_playing_card_25462_512
                    
                    "J de Tréboles" -> R.drawable.jack_of_clubs_playing_card_25467_512
                    "J de Diamantes" -> R.drawable.jack_of_diamonds_playing_card_25468_512
                    "J de Corazones" -> R.drawable.jack_of_hearts_playing_card_25469_512
                    "J de Espadas" -> R.drawable.jack_of_spades_playing_card_25466_512
                    
                    "Q de Tréboles" -> R.drawable.queen_of_clubs_playing_card_25471_512
                    "Q de Diamantes" -> R.drawable.queen_of_diamonds_playing_card_25472_512
                    "Q de Corazones" -> R.drawable.queen_of_hearts_playing_card_25473_512
                    "Q de Espadas" -> R.drawable.queen_of_spades_playing_card_25470_512
                    
                    "K de Tréboles" -> R.drawable.king_of_clubs_playing_card_25475_512
                    "K de Diamantes" -> R.drawable.king_of_diamonds_playing_card_25476_512
                    "K de Corazones" -> R.drawable.king_of_hearts_playing_card_25477_512
                    "K de Espadas" -> R.drawable.king_of_spades_playing_card_25474_512
                    
                    "As de Tréboles" -> R.drawable.ace_of_clubs_playing_card_25426_512
                    "As de Diamantes" -> R.drawable.ace_of_diamonds_playing_card_25427_512
                    "As de Corazones" -> R.drawable.ace_of_hearts_playing_card_25428_512
                    "As de Espadas" -> R.drawable.ace_of_spades_playing_card_25429_512
                    
                    else -> R.drawable.black_playing_card_back_25478_512
                }
                
                baraja.add(Carta(nombre, valor, idImagen))
            }
        }
        return baraja.shuffled().toMutableList()
    }
}