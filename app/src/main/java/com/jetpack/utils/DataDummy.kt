package com.jetpack.utils

import com.jetpack.data.source.local.entity.MovieEntity
import com.jetpack.data.source.local.entity.TvShowDetailEntity
import com.jetpack.data.source.local.entity.TvShowEntity
import com.jetpack.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovies() : List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity("1", "Raya and the Last Dragon", "Long ago, in the " +
                    "fantasy world of Kumandra, humans and dragons lived together in harmony. " +
                    "But when an evil force threatened the land, the dragons sacrificed themselves to save " +
                    "humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, " +
                    "Raya, to track down the legendary last dragon to restore the fractured land and its divided people.", "2021",
                "Fantasy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6t7X3LMwVtCubcmk9HWI1M9RtbK.jpg")
        )

        movies.add(
            MovieEntity("2", "Flipped", "When Juli meets Bryce in the second grade, she knows" +
                    "it's true love. After spending six years trying to convince Bryce the same, she's ready to give up - until" +
                    "he starts to reconsider.", "2010",
                "Romance", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zurKuLCZzjn18HMzADW6aRoY4Wj.jpg")
        )

        movies.add(
            MovieEntity("3", "Zootopia", "Determined to prove herself, Officer Judy Hopps, " +
                    "the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means " +
                    "partnering with scam-artist fox Nick Wilde to solve the mystery.", "2016",
                "Adventure", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hlK0e0wAQ3VLuJcsfIYPvb4JVud.jpg")
        )

        movies.add(
            MovieEntity("4", "A Beautiful Mind", "John Nash is a brilliant but asocial mathematician " +
                    "fighting schizophrenia. After he accepts secret work in cryptography, his life takes a turn for the nightmarish.", "2001",
                "Drama", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zwzWCmH72OSC9NA0ipoqw5Zjya8.jpg")
        )

        movies.add(
            MovieEntity("5", "Jumanji: Welcome to the Jungle", "The tables are turned as four " +
                    "teenagers are sucked into Jumanji's world - pitted against rhinos, black mambas and an endless variety of jungle " +
                    "traps and puzzles. To survive, they'll play as characters from the game.", "2017",
                "Adventure", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pSgXKPU5h6U89ipF7HBYajvYt7j.jpg")
        )

        movies.add(
            MovieEntity("6", "Big Hero 6", "The special bond that develops between plus-sized " +
                    "inflatable robot Baymax, and prodigy Hiro Hamada, who team up with a group of friends to form a band of " +
                    "high-tech heroes.", "2014",
                "Action", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2mxS4wUimwlLmI1xp6QW6NSU361.jpg")
        )

        movies.add(
            MovieEntity("7", "Home Alone", "Eight-year-old Kevin McCallister makes the most " +
                    "of the situation after his family unwittingly leaves him behind when they go on Christmas vacation. " +
                    "But when a pair of bungling burglars set their sights on Kevin's house, the plucky kid stands ready " +
                    "to defend his territory. By planting booby traps galore, adorably mischievous Kevin stands his ground " +
                    "as his frantic mother attempts to race home before Christmas Day.", "1990",
                "Comedy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9wSbe4CwObACCQvaUVhWQyLR5Vz.jpg")
        )

        movies.add(
            MovieEntity("8", "Psycho-Pass 3: First Inspector", "Inspector Kei Mikhail Ignatov finds " +
                    "himself involved with an organization named Bifrost with the possibility of freeing his wife if he betrays " +
                    "Unit One. Koichi Azusawa coordinates an assault on the Public Safety Bureau tower using his hacker Obata, " +
                    "locking down the building and kidnapping Inspector Arata Shindo. Azusawa demands that governor Karina Komiya " +
                    "resign from her position.", "2020",
                "Crime", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hNvOilYbemMkaVpDktvwAEeH9sz.jpg")
        )

        movies.add(
            MovieEntity("9", "The Conjuring", "Paranormal investigators Ed and Lorraine Warren " +
                    "work to help a family terrorized by a dark presence in their farmhouse. Forced to confront a powerful entity, " +
                    "the Warrens find themselves caught in the most terrifying case of their lives.", "2013",
                "Horror", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wVYREutTvI2tmxr6ujrHT704wGF.jpg")
        )

        movies.add(
            MovieEntity("10", "Five Feet Apart", "Seventeen-year-old Stella spends most of her " +
                    "time in the hospital as a cystic fibrosis patient. Her life is full of routines, boundaries and self-control — " +
                    "all of which get put to the test when she meets Will, an impossibly charming teen who has the same illness. " +
                    "There's an instant flirtation, though restrictions dictate that they must maintain a safe distance between them. " +
                    "As their connection intensifies, so does the temptation to throw the rules out the window and embrace that attraction.", "2019",
                "Fantasy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/khQolo0hQCQjByVpfUhkyQtOJSq.jpg")
        )

        return movies
    }

    fun generateDummyTvShows() : List<TvShowEntity> {
        val tvshows = ArrayList<TvShowEntity>()

        tvshows.add(
            TvShowEntity("1", "Haikyu!!", "Shōyō Hinata was inspired to play volleyball after " +
                    "seeing Kurasuno High School's \"little giant\" competing in the national tournament on TV. He trains relentlessly " +
                    "to make up for his lack of height, but suffers a crushing defeat in his first and last tournament of middle school at " +
                    "the hands of his rival Tobio Kageyama. Vowing revenge against Kageyama and hoping to follow in the little giant's " +
                    "footsteps, Hinata joins Kurasuno High School's volleyball team. To his initial dismay, Kageyama is also on Kurasuno's " +
                    "team. The former rivals soon overcome their differences though and combine their strengths to form a legendary combo " +
                    "using Hinata's mobility and Kageyama's precision ball-handling. Together with their team, they compete in prefecture " +
                    "tournaments and promise to meet Kurasuno's fated rival school at nationals.", "2014",
                "Comedy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pLpQZHVduTzJTOVNmWfppY9tk3W.jpg")
        )

        tvshows.add(
            TvShowEntity("2", "Anohana: The Flower We Saw That Day", "When Yadomi Jinta was a child, " +
                    "he was a central piece in a group of close friends. In time, however, these childhood friends drifted apart, and when " +
                    "they became high school students, they had long ceased to think of each other as friends.\n" +
                    "\n" +
                    "One of the friends from that group, Honma Meiko, now has a wish she asks Jinta to fulfil. The problem is, she can't " +
                    "remember what her wish is anymore. As Meiko won't cease to bother Jinta about it, he gives in and decides to try to " +
                    "grant this wish he knows nothing of; for that, however, the help of his other former friends, now all very estranged " +
                    "from himself and from each other, may turn out to be necessary. He hasn't spoken to Anjou Naruko, Matsuyuki Atsumu, " +
                    "Tsurumi Chiriko, or Hisakawa Tetsudou in a long time; as he struggles to grant Meiko's wish and gathers his old friends " +
                    "together in the process, all the old feelings that still exist between them and have long been stashed away are bound " +
                    "to come up again.", "2011",
                "Mystery", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2tgOAvOttp7rak3r2U5vIAaeyDt.jpg")
        )

        tvshows.add(
            TvShowEntity("3", "Assassination Classroom", "The students of class 3-E have a mission: kill " +
                    "their teacher before graduation. He has already destroyed the moon, and has promised to destroy the Earth if he can not be " +
                    "killed within a year. But how can this class of misfits kill a tentacled monster, capable of reaching Mach 20 speed, who may " +
                    "be the best teacher any of them have ever had?", "2015",
                "Action", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fIOYLPOorKvP4wut9y0edpnWROz.jpg")
        )

        tvshows.add(
            TvShowEntity("4", "Friends", "The misadventures of a group of friends as they navigate the " +
                    "pitfalls of work, life and love in Manhattan.", "1994",
                "Comedy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdjPjUOT7WFRELJeV1H2mpz2Ppl.jpg")
        )

        tvshows.add(
            TvShowEntity("5", "Grey's Anatomy", "Follows the personal and professional lives of a " +
                    "group of doctors at Seattle’s Grey Sloan Memorial Hospital.", "2005",
                "Drama", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg")
        )

        tvshows.add(
            TvShowEntity("6", "One Piece", "Years ago, the fearsome pirate king Gol D. Roger was executed, " +
                    "leaving a huge pile of treasure and the famous \"One Piece\" behind. Whoever claims the \"One Piece\" will be named the new " +
                    "pirate king. Monkey D. Luffy, a boy who consumed one of the \"Devil's Fruits\", has it in his head that he'll follow in the " +
                    "footsteps of his idol, the pirate Shanks, and find the One Piece. It helps, of course, that his body has the properties of " +
                    "rubber and he's surrounded by a bevy of skilled fighters and thieves to help him along the way. Monkey D. Luffy brings a " +
                    "bunch of his crew followed by, Roronoa Zoro, Nami, Usopp, Sanji, Tony-Tony Chopper, Nico Robin, Franky, and Brook. T" +
                    "hey will do anything to get the One Piece and become King of the Pirates!..", "1999",
                "Action", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9DNCqVyvY2zK1d9jNSsLoqxJw1K.jpg")
        )

        tvshows.add(
            TvShowEntity("7", "MasterChef Australia", "MasterChef Australia is a Logie Award-winning " +
                    "Australian competitive cooking game show based on the original British MasterChef. It is produced by Shine Australia " +
                    "and screens on Network Ten. Restaurateur and chef Gary Mehigan, chef George Calombaris and food critic Matt Preston " +
                    "serve as the show's main judges. Journalist Sarah Wilson hosted the first series, however her role was dropped at the " +
                    "end of the series.", "2009",
                "Reality", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/m5akdtbWznF8KpOewKyKw0C36s1.jpg")
        )

        tvshows.add(
            TvShowEntity("8", "Because This Is My First Life ", "Nam Se-Hee is a single man in his " +
                    "early 30's. He has chosen to not marry. He owns his home, but he owes a lot on his mortgage. Yoon Ji-Ho is a " +
                    "single woman in her early 30's. She does not own a home and envies those that do. She has given up on dating due " +
                    "to her financial struggles. Yoon Ji-Ho begins to live at Nam Se-Hee’s house. They become housemates.", "2017",
                "Comedy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3J0KJ1HIvbxU6tY3aKgjvH3UDYD.jpg")
        )

        tvshows.add(
            TvShowEntity("9", "Case Closed ", "The son of a world famous mystery writer, Jimmy Kudo, " +
                    "has achieved his own notoriety by assisting the local police as a student detective. He has always been able to solve " +
                    "the most difficult of criminal cases using his wits and power of reason.", "1996",
                "Mystery", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uZAmN6TzciPZdhTiUYorIX812Te.jpg")
        )

        tvshows.add(
            TvShowEntity("10", "The Falcon and the Winter Soldier", "Following the events of “Avengers: " +
                    "Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their " +
                    "abilities, and their patience.", "2021",
                "Action", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg")
        )

        return tvshows
    }

    fun generateDummyTvShowsDetail() : List<TvShowDetailEntity> {
        val tvshows = ArrayList<TvShowDetailEntity>()

        tvshows.add(
            TvShowDetailEntity("1", "Haikyu!!", "Shōyō Hinata was inspired to play volleyball after " +
                    "seeing Kurasuno High School's \"little giant\" competing in the national tournament on TV. He trains relentlessly " +
                    "to make up for his lack of height, but suffers a crushing defeat in his first and last tournament of middle school at " +
                    "the hands of his rival Tobio Kageyama. Vowing revenge against Kageyama and hoping to follow in the little giant's " +
                    "footsteps, Hinata joins Kurasuno High School's volleyball team. To his initial dismay, Kageyama is also on Kurasuno's " +
                    "team. The former rivals soon overcome their differences though and combine their strengths to form a legendary combo " +
                    "using Hinata's mobility and Kageyama's precision ball-handling. Together with their team, they compete in prefecture " +
                    "tournaments and promise to meet Kurasuno's fated rival school at nationals.", "2014",
                "Comedy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pLpQZHVduTzJTOVNmWfppY9tk3W.jpg",
                "85")
        )

        tvshows.add(
            TvShowDetailEntity("2", "Anohana: The Flower We Saw That Day", "When Yadomi Jinta was a child, " +
                    "he was a central piece in a group of close friends. In time, however, these childhood friends drifted apart, and when " +
                    "they became high school students, they had long ceased to think of each other as friends.\n" +
                    "\n" +
                    "One of the friends from that group, Honma Meiko, now has a wish she asks Jinta to fulfil. The problem is, she can't " +
                    "remember what her wish is anymore. As Meiko won't cease to bother Jinta about it, he gives in and decides to try to " +
                    "grant this wish he knows nothing of; for that, however, the help of his other former friends, now all very estranged " +
                    "from himself and from each other, may turn out to be necessary. He hasn't spoken to Anjou Naruko, Matsuyuki Atsumu, " +
                    "Tsurumi Chiriko, or Hisakawa Tetsudou in a long time; as he struggles to grant Meiko's wish and gathers his old friends " +
                    "together in the process, all the old feelings that still exist between them and have long been stashed away are bound " +
                    "to come up again.", "2011",
                "Mystery", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2tgOAvOttp7rak3r2U5vIAaeyDt.jpg",
                "11")
        )

        tvshows.add(
            TvShowDetailEntity("3", "Assassination Classroom", "The students of class 3-E have a mission: kill " +
                    "their teacher before graduation. He has already destroyed the moon, and has promised to destroy the Earth if he can not be " +
                    "killed within a year. But how can this class of misfits kill a tentacled monster, capable of reaching Mach 20 speed, who may " +
                    "be the best teacher any of them have ever had?", "2015",
                "Action", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fIOYLPOorKvP4wut9y0edpnWROz.jpg",
                "60")
        )

        tvshows.add(
            TvShowDetailEntity("4", "Friends", "The misadventures of a group of friends as they navigate the " +
                    "pitfalls of work, life and love in Manhattan.", "1994",
                "Comedy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdjPjUOT7WFRELJeV1H2mpz2Ppl.jpg",
                "236")
        )

        tvshows.add(
            TvShowDetailEntity("5", "Grey's Anatomy", "Follows the personal and professional lives of a " +
                    "group of doctors at Seattle’s Grey Sloan Memorial Hospital.", "2005",
                "Drama", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "378")
        )

        tvshows.add(
            TvShowDetailEntity("6", "One Piece", "Years ago, the fearsome pirate king Gol D. Roger was executed, " +
                    "leaving a huge pile of treasure and the famous \"One Piece\" behind. Whoever claims the \"One Piece\" will be named the new " +
                    "pirate king. Monkey D. Luffy, a boy who consumed one of the \"Devil's Fruits\", has it in his head that he'll follow in the " +
                    "footsteps of his idol, the pirate Shanks, and find the One Piece. It helps, of course, that his body has the properties of " +
                    "rubber and he's surrounded by a bevy of skilled fighters and thieves to help him along the way. Monkey D. Luffy brings a " +
                    "bunch of his crew followed by, Roronoa Zoro, Nami, Usopp, Sanji, Tony-Tony Chopper, Nico Robin, Franky, and Brook. T" +
                    "hey will do anything to get the One Piece and become King of the Pirates!..", "1999",
                "Action", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9DNCqVyvY2zK1d9jNSsLoqxJw1K.jpg",
                "974")
        )

        tvshows.add(
            TvShowDetailEntity("7", "MasterChef Australia", "MasterChef Australia is a Logie Award-winning " +
                    "Australian competitive cooking game show based on the original British MasterChef. It is produced by Shine Australia " +
                    "and screens on Network Ten. Restaurateur and chef Gary Mehigan, chef George Calombaris and food critic Matt Preston " +
                    "serve as the show's main judges. Journalist Sarah Wilson hosted the first series, however her role was dropped at the " +
                    "end of the series.", "2009",
                "Reality", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/m5akdtbWznF8KpOewKyKw0C36s1.jpg",
                "747")
        )

        tvshows.add(
            TvShowDetailEntity("8", "Because This Is My First Life ", "Nam Se-Hee is a single man in his " +
                    "early 30's. He has chosen to not marry. He owns his home, but he owes a lot on his mortgage. Yoon Ji-Ho is a " +
                    "single woman in her early 30's. She does not own a home and envies those that do. She has given up on dating due " +
                    "to her financial struggles. Yoon Ji-Ho begins to live at Nam Se-Hee’s house. They become housemates.", "2017",
                "Comedy", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3J0KJ1HIvbxU6tY3aKgjvH3UDYD.jpg",
                "16")
        )

        tvshows.add(
            TvShowDetailEntity("9", "Case Closed ", "The son of a world famous mystery writer, Jimmy Kudo, " +
                    "has achieved his own notoriety by assisting the local police as a student detective. He has always been able to solve " +
                    "the most difficult of criminal cases using his wits and power of reason.", "1996",
                "Mystery", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uZAmN6TzciPZdhTiUYorIX812Te.jpg",
                "1058")
        )

        tvshows.add(
            TvShowDetailEntity("10", "The Falcon and the Winter Soldier", "Following the events of “Avengers: " +
                    "Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their " +
                    "abilities, and their patience.", "2021",
                "Action", "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "6")
        )

        return tvshows
    }

    fun generateRemoteDummyMovies() : List<ResultsItem> {

        val movies = ArrayList<ResultsItem>()

        val genres : List<Int> = listOf(10749)

        movies.add(
            ResultsItem("Long ago, in the \n" +
                    "fantasy world of Kumandra, humans and dragons lived together in harmony. \n" +
                    "But when an evil force threatened the land, the dragons sacrificed themselves to save \n" +
                    "humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, \n" +
                    "Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "Raya and the Last Dragon",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6t7X3LMwVtCubcmk9HWI1M9RtbK.jpg",
                "2021",
                5)
        )

        movies.add(
            ResultsItem("When Juli meets Bryce in the second grade, she knows\" +\n" +
                    "                    \"it's true love. After spending six years trying to convince Bryce the same, she's ready to give up - until\" +\n" +
                    "                    \"he starts to reconsider.",
                "Flipped",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zurKuLCZzjn18HMzADW6aRoY4Wj.jpg",
                "2010",
                21)
        )

        return movies
    }

    fun generateRemoteDummyMovieDetail() : List<MovieDetail> {

        val movies = ArrayList<MovieDetail>()

        val genres : List<GenresItem> = listOf(GenresItem("Romance", 10749))

        movies.add(
            MovieDetail("Long ago, in the \n" +
                    "fantasy world of Kumandra, humans and dragons lived together in harmony. \n" +
                    "But when an evil force threatened the land, the dragons sacrificed themselves to save \n" +
                    "humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, \n" +
                    "Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "Raya and the Last Dragon",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6t7X3LMwVtCubcmk9HWI1M9RtbK.jpg",
                "2021",
                5)
        )

        movies.add(
            MovieDetail("When Juli meets Bryce in the second grade, she knows\" +\n" +
                    "                    \"it's true love. After spending six years trying to convince Bryce the same, she's ready to give up - until\" +\n" +
                    "                    \"he starts to reconsider.",
                "Flipped",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zurKuLCZzjn18HMzADW6aRoY4Wj.jpg",
                "2010",
                21)
        )

        return movies
    }

    fun generateRemoteDummyTvShows() : List<TvResultsItem> {
        val tvshows = ArrayList<TvResultsItem>()
        val genres : List<Int> = listOf(18)

        tvshows.add(
            TvResultsItem("2014",
                "Shōyō Hinata was inspired to play volleyball after " +
                        "seeing Kurasuno High School's \"little giant\" competing in the national tournament on TV. He trains relentlessly " +
                        "to make up for his lack of height, but suffers a crushing defeat in his first and last tournament of middle school at " +
                        "the hands of his rival Tobio Kageyama. Vowing revenge against Kageyama and hoping to follow in the little giant's " +
                        "footsteps, Hinata joins Kurasuno High School's volleyball team. To his initial dismay, Kageyama is also on Kurasuno's " +
                        "team. The former rivals soon overcome their differences though and combine their strengths to form a legendary combo " +
                        "using Hinata's mobility and Kageyama's precision ball-handling. Together with their team, they compete in prefecture " +
                        "tournaments and promise to meet Kurasuno's fated rival school at nationals.",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pLpQZHVduTzJTOVNmWfppY9tk3W.jpg",
                "Haikyuu!",
                2)
        )

        tvshows.add(
            TvResultsItem("2011",
                "When Yadomi Jinta was a child, " +
                        "he was a central piece in a group of close friends. In time, however, these childhood friends drifted apart, and when " +
                        "they became high school students, they had long ceased to think of each other as friends.\n" +
                        "\n" +
                        "One of the friends from that group, Honma Meiko, now has a wish she asks Jinta to fulfil. The problem is, she can't " +
                        "remember what her wish is anymore. As Meiko won't cease to bother Jinta about it, he gives in and decides to try to " +
                        "grant this wish he knows nothing of; for that, however, the help of his other former friends, now all very estranged " +
                        "from himself and from each other, may turn out to be necessary. He hasn't spoken to Anjou Naruko, Matsuyuki Atsumu, " +
                        "Tsurumi Chiriko, or Hisakawa Tetsudou in a long time; as he struggles to grant Meiko's wish and gathers his old friends " +
                        "together in the process, all the old feelings that still exist between them and have long been stashed away are bound " +
                        "to come up again.",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2tgOAvOttp7rak3r2U5vIAaeyDt.jpg",
                "Anohana: The Flower We Saw That Day",
                5)
        )

        return tvshows
    }

    fun generateRemoteDummyTvShowDetail() : List<TvDetail> {
        val tvshows = ArrayList<TvDetail>()

        val genres : List<GenresItem> = listOf(GenresItem("Animation", 18))

        tvshows.add(
            TvDetail("2014",
                6,
                "Shōyō Hinata was inspired to play volleyball after " +
                        "seeing Kurasuno High School's \"little giant\" competing in the national tournament on TV. He trains relentlessly " +
                        "to make up for his lack of height, but suffers a crushing defeat in his first and last tournament of middle school at " +
                        "the hands of his rival Tobio Kageyama. Vowing revenge against Kageyama and hoping to follow in the little giant's " +
                        "footsteps, Hinata joins Kurasuno High School's volleyball team. To his initial dismay, Kageyama is also on Kurasuno's " +
                        "team. The former rivals soon overcome their differences though and combine their strengths to form a legendary combo " +
                        "using Hinata's mobility and Kageyama's precision ball-handling. Together with their team, they compete in prefecture " +
                        "tournaments and promise to meet Kurasuno's fated rival school at nationals.",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pLpQZHVduTzJTOVNmWfppY9tk3W.jpg",
                "Haikyuu!",
                2)
        )

        tvshows.add(
            TvDetail("2011",
                12,
                "When Yadomi Jinta was a child, " +
                        "he was a central piece in a group of close friends. In time, however, these childhood friends drifted apart, and when " +
                        "they became high school students, they had long ceased to think of each other as friends.\n" +
                        "\n" +
                        "One of the friends from that group, Honma Meiko, now has a wish she asks Jinta to fulfil. The problem is, she can't " +
                        "remember what her wish is anymore. As Meiko won't cease to bother Jinta about it, he gives in and decides to try to " +
                        "grant this wish he knows nothing of; for that, however, the help of his other former friends, now all very estranged " +
                        "from himself and from each other, may turn out to be necessary. He hasn't spoken to Anjou Naruko, Matsuyuki Atsumu, " +
                        "Tsurumi Chiriko, or Hisakawa Tetsudou in a long time; as he struggles to grant Meiko's wish and gathers his old friends " +
                        "together in the process, all the old feelings that still exist between them and have long been stashed away are bound " +
                        "to come up again.",
                genres,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2tgOAvOttp7rak3r2U5vIAaeyDt.jpg",
                "Anohana: The Flower We Saw That Day",
                5)
        )

        return tvshows
    }
}