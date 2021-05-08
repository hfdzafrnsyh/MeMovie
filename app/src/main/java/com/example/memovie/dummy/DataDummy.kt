package com.example.memovie.dummy

import com.example.memovie.entity.MovieDetailEntity
import com.example.memovie.entity.MovieEntity
import com.example.memovie.entity.TvShowDetailEntity
import com.example.memovie.entity.TvShowEntity
import com.example.memovie.source.remote.response.DataResponse
import java.util.ArrayList

object DataDummy{

    fun generateDummyMovie() : List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add( MovieEntity(
                "1",
                "Thor: Ragnarok",
                "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of a powerful new threat, the ruthless Hela.",
               "Science Fiction",
                "https://image.tmdb.org/t/p/w185/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg",
                false,
                "2017-10-25"

        ))
        movies.add(MovieEntity(
                "2",
                "Spider-Man: Homecoming",
                "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.",
               "Drama",
                "https://image.tmdb.org/t/p/w185/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg",
                false,
                " 2017-07-05"
        ))
        movies.add(MovieEntity(
                "3",
                "Guardians of the Galaxy Vol. 2",
                "The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage.",
                "Action",
                "https://image.tmdb.org/t/p/w185/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg",
                false,
                "2017-04-19"
        ))
        movies.add(MovieEntity(
                "4",
                "Logan" ,
                "In the near future, a weary Logan cares for an ailing Professor X in a hideout on the Mexican border. But Logan's attempts to hide from the world and his legacy are upended when a young mutant arrives, pursued by dark forces.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/fnbjcRDYn6YviCcePDnGdyAkYsB.jpg",
                false,
                "2018-01-28"
        ))
        movies.add(MovieEntity(
                "5",
                "Doctor Strange",
                "After his career is destroyed, a brilliant but arrogant surgeon gets a new lease on life when a sorcerer takes him under her wing and trains him to defend the world against evil.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/xf8PbyQcR5ucXErmZNzdKR0s8ya.jpg",
                false,
                "2016-10-25"
        ))
        movies.add(MovieEntity(
                "6",
                "X-Men: Apocalypse",
                "After the re-emergence of the world's first mutant, world-destroyer Apocalypse, the X-Men must unite to defeat his extinction level plan." ,
                "Fantasy",
                "https://image.tmdb.org/t/p/w185/2mtQwJKVKQrZgTz49Dizb25eOQQ.jpg",
                false,
                "2016-05-18"
        ))
        movies.add(MovieEntity(
                "7",
                "Captain America: Civil War",
                "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies." ,
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/rAGiXaUfPzY7CDEyNKUofk3Kw2e.jpg",
                false,
                "2016-04-27"
        ))
        movies.add(MovieEntity(
                "8",
                "Deadpool",
                "Deadpool tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life." ,
                "Comedy",
                "https://image.tmdb.org/t/p/w185/yGSxMiF0cYuAiyuve5DA6bnWEOI.jpg",
                false,
                "2016-04-26"
        ))
        movies.add(MovieEntity(
                "9",
                "Fantastic Four",
                "Four young outsiders teleport to a dangerous universe, which alters their physical form in shocking ways. Their lives irrevocably upended, the team must learn to harness their daunting new abilities and work together to save Earth from a former friend turned enemy." ,
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/oeMpEsjmiT9PEJbRM1Fm7TZ1dE0.jpg",
                false,
                "2015-08-05"
        ))
        movies.add(MovieEntity(
                "10",
                "Ant-Man",
                "Armed with the astonishing ability to shrink in scale but increase in strength, master thief Scott Lang must embrace his inner-hero and help his mentor, Doctor Hank Pym, protect the secret behind his spectacular Ant-Man suit from a new generation of towering threats. Against seemingly insurmountable obstacles, Pym and Lang must plan and pull off a heist that will save the world." ,
                "Adventure",
                "https://image.tmdb.org/t/p/w185/rQRnQfUl3kfp78nCWq8Ks04vnq1.jpg",
                false,
                "2015-07-14"
        ))

        return movies
    }

    fun generateDummyTvShow() : List<TvShowEntity>{
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add( TvShowEntity(
                "1",
                "Wonder Woman 1984",
                "Natsu Dragneel and his friends travel to the island Kingdom of Stella, where they will reveal dark secrets, fight the new enemies and once again save the world from destruction. ",
                "Animation,Adventure,Fantasy",
                "https://image.tmdb.org/t/p/w185/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                false,
                "2020-12-16"

        ))
        tvShow.add( TvShowEntity(
                "2",
                "Shazam!",
                "A boy is given the ability to become an adult superhero in times of need with a single magic word.",
                "Fantasy",
                "https://image.tmdb.org/t/p/w185/xnopI5Xtky18MPhK40cZAGAOVeV.jpg",
                false,
                "2019-03-29"

        ))
        tvShow.add( TvShowEntity(
                "3",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
               "Fantasy",
                "https://image.tmdb.org/t/p/w185/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                false,
                "2018-12-07"

        ))
        tvShow.add( TvShowEntity(
                "4",
                "Justice League",
                "Fuelled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/eifGNCSDuxJeS1loAXil5bIGgvC.jpg",
                false,
                "2017-11-15"

        ))
        tvShow.add( TvShowEntity(
                "5",
                "Suicide Squad",
                "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/xFw9RXKZDvevAGocgBK0zteto4U.jpg",
                false,
                "2018-08-03"

        ))
        tvShow.add( TvShowEntity(
                "6",
                "Batman v Superman: Dawn of Justice",
                "Fearing the actions of a god-like Super Hero left unchecked, Gotham City’s own formidable, forceful vigilante takes on Metropolis’s most revered, modern-day savior, while the world wrestles with what sort of hero it really needs. And with Batman and Superman at war with one another, a new threat quickly arises, putting mankind in greater danger than it’s ever known before.",
                "Fantasy",
                "https://image.tmdb.org/t/p/w185/5UsK3grJvtQrtzEgqNlDljJW96w.jpg",
                false,
                "2016-03-23"

        ))
        tvShow.add( TvShowEntity(
                "7",
                "Man of Steel",
                "A young boy learns that he has extraordinary powers and is not of this earth. As a young man, he journeys to discover where he came from and what he was sent here to do. But the hero in him must emerge if he is to save the world from annihilation and become the symbol of hope for all mankind.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/7rIPjn5TUK04O25ZkMyHrGNPgLx.jpg",
                false,
                "2013-06-17"

        ))
        tvShow.add( TvShowEntity(
                "8",
                "The Dark Knight Rises",
                "Following the death of District Attorney Harvey Dent, Batman assumes responsibility for Dent's crimes to protect the late attorney's reputation and is subsequently hunted by the Gotham City Police Department. Eight years later, Batman encounters the mysterious Selina Kyle and the villainous Bane, a new terrorist leader who overwhelms Gotham's finest. The Dark Knight resurfaces to protect a city that has branded him an enemy.",
               "Thriller",
                "https://image.tmdb.org/t/p/w185/vzvKcPQ4o7TjWeGIn0aGC9FeVNu.jpg",
                false,
                "2012-07-16"

        ))
        tvShow.add( TvShowEntity(
                "9",
                "Green Lantern",
                "For centuries, a small but powerful force of warriors called the Green Lantern Corps has sworn to keep intergalactic order. Each Green Lantern wears a ring that grants him superpowers. But when a new enemy called Parallax threatens to destroy the balance of power in the Universe, their fate and the fate of Earth lie in the hands of the first human ever recruited.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/fj21HwUprqjjwTdkKC1XZurRSpV.jpg",
                false,
                "2011-06-16"

        ))
        tvShow.add( TvShowEntity(
                "10",
                "Jonah Hex",
                "Gunslinger Jonah Hex is appointed by President Ulysses Grant to track down terrorist Quentin Turnbull, a former Confederate officer determined on unleashing hell on earth. Jonah not only secures freedom by accepting this task, he also gets revenge on the man who slayed his wife and child.",
                "Thriller",
                "https://image.tmdb.org/t/p/w185/1MpWjcCn8M0763QDoxcN0gXrc5q.jpg",
                false,
                "2010-06-16"

        ))



        return tvShow
    }


    fun generateDummyMovieRemote() : List<DataResponse>{
        val movies = ArrayList<DataResponse>()
        movies.add( DataResponse(
                "1",
                "Thor: Ragnarok",
                "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of a powerful new threat, the ruthless Hela.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg",
                "2017-10-25"

        ))
        movies.add(DataResponse(
                "2",
                "Spider-Man: Homecoming",
                "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.",
                "Drama",
                "https://image.tmdb.org/t/p/w185/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg",
                " 2017-07-05"
        ))
        movies.add(DataResponse(
                "3",
                "Guardians of the Galaxy Vol. 2",
                "The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage.",
                "Action",
                "https://image.tmdb.org/t/p/w185/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg",
                "2017-04-19"
        ))
        movies.add(DataResponse(
                "4",
                "Logan" ,
                "In the near future, a weary Logan cares for an ailing Professor X in a hideout on the Mexican border. But Logan's attempts to hide from the world and his legacy are upended when a young mutant arrives, pursued by dark forces.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/fnbjcRDYn6YviCcePDnGdyAkYsB.jpg",
                "2018-01-28"
        ))
        movies.add(DataResponse(
                "5",
                "Doctor Strange",
                "After his career is destroyed, a brilliant but arrogant surgeon gets a new lease on life when a sorcerer takes him under her wing and trains him to defend the world against evil.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/xf8PbyQcR5ucXErmZNzdKR0s8ya.jpg",
                "2016-10-25"
        ))
        movies.add(DataResponse(
                "6",
                "X-Men: Apocalypse",
                "After the re-emergence of the world's first mutant, world-destroyer Apocalypse, the X-Men must unite to defeat his extinction level plan." ,
                "Fantasy",
                "https://image.tmdb.org/t/p/w185/2mtQwJKVKQrZgTz49Dizb25eOQQ.jpg",
                "2016-05-18"
        ))
        movies.add(DataResponse(
                "7",
                "Captain America: Civil War",
                "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies." ,
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/rAGiXaUfPzY7CDEyNKUofk3Kw2e.jpg",
                "2016-04-27"
        ))
        movies.add(DataResponse(
                "8",
                "Deadpool",
                "Deadpool tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life." ,
                "Comedy",
                "https://image.tmdb.org/t/p/w185/yGSxMiF0cYuAiyuve5DA6bnWEOI.jpg",
                "2016-04-26"
        ))
        movies.add(DataResponse(
                "9",
                "Fantastic Four",
                "Four young outsiders teleport to a dangerous universe, which alters their physical form in shocking ways. Their lives irrevocably upended, the team must learn to harness their daunting new abilities and work together to save Earth from a former friend turned enemy." ,
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/oeMpEsjmiT9PEJbRM1Fm7TZ1dE0.jpg",
                "2015-08-05"
        ))
        movies.add(DataResponse(
                "10",
                "Ant-Man",
                "Armed with the astonishing ability to shrink in scale but increase in strength, master thief Scott Lang must embrace his inner-hero and help his mentor, Doctor Hank Pym, protect the secret behind his spectacular Ant-Man suit from a new generation of towering threats. Against seemingly insurmountable obstacles, Pym and Lang must plan and pull off a heist that will save the world." ,
                "Adventure",
                "https://image.tmdb.org/t/p/w185/rQRnQfUl3kfp78nCWq8Ks04vnq1.jpg",
                "2015-07-14"
        ))

        return movies
    }


    fun generateDummyTvshowRemote() : List<DataResponse> {
        val tvShow = ArrayList<DataResponse>()

        tvShow.add( DataResponse(
                "1",
                "Wonder Woman 1984",
                "Natsu Dragneel and his friends travel to the island Kingdom of Stella, where they will reveal dark secrets, fight the new enemies and once again save the world from destruction. ",
                "Animation,Adventure,Fantasy",
                "https://image.tmdb.org/t/p/w185/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020-12-16"

        ))
        tvShow.add( DataResponse(
                "2",
                "Shazam!",
                "A boy is given the ability to become an adult superhero in times of need with a single magic word.",
                "Fantasy",
                "https://image.tmdb.org/t/p/w185/xnopI5Xtky18MPhK40cZAGAOVeV.jpg",
                "2019-03-29"

        ))
        tvShow.add(DataResponse(
                "3",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Fantasy",
                "https://image.tmdb.org/t/p/w185/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                "2018-12-07"

        ))
        tvShow.add( DataResponse(
                "4",
                "Justice League",
                "Fuelled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/eifGNCSDuxJeS1loAXil5bIGgvC.jpg",
                "2017-11-15"

        ))
        tvShow.add( DataResponse(
                "5",
                "Suicide Squad",
                "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/xFw9RXKZDvevAGocgBK0zteto4U.jpg",
                "2018-08-03"

        ))
        tvShow.add( DataResponse(
                "6",
                "Batman v Superman: Dawn of Justice",
                "Fearing the actions of a god-like Super Hero left unchecked, Gotham City’s own formidable, forceful vigilante takes on Metropolis’s most revered, modern-day savior, while the world wrestles with what sort of hero it really needs. And with Batman and Superman at war with one another, a new threat quickly arises, putting mankind in greater danger than it’s ever known before.",
                "Fantasy",
                "https://image.tmdb.org/t/p/w185/5UsK3grJvtQrtzEgqNlDljJW96w.jpg",
                "2016-03-23"

        ))
        tvShow.add( DataResponse(
                "7",
                "Man of Steel",
                "A young boy learns that he has extraordinary powers and is not of this earth. As a young man, he journeys to discover where he came from and what he was sent here to do. But the hero in him must emerge if he is to save the world from annihilation and become the symbol of hope for all mankind.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/7rIPjn5TUK04O25ZkMyHrGNPgLx.jpg",
                "2013-06-17"

        ))
        tvShow.add( DataResponse(
                "8",
                "The Dark Knight Rises",
                "Following the death of District Attorney Harvey Dent, Batman assumes responsibility for Dent's crimes to protect the late attorney's reputation and is subsequently hunted by the Gotham City Police Department. Eight years later, Batman encounters the mysterious Selina Kyle and the villainous Bane, a new terrorist leader who overwhelms Gotham's finest. The Dark Knight resurfaces to protect a city that has branded him an enemy.",
                "Thriller",
                "https://image.tmdb.org/t/p/w185/vzvKcPQ4o7TjWeGIn0aGC9FeVNu.jpg",
                "2012-07-16"

        ))
        tvShow.add( DataResponse(
                "9",
                "Green Lantern",
                "For centuries, a small but powerful force of warriors called the Green Lantern Corps has sworn to keep intergalactic order. Each Green Lantern wears a ring that grants him superpowers. But when a new enemy called Parallax threatens to destroy the balance of power in the Universe, their fate and the fate of Earth lie in the hands of the first human ever recruited.",
                "Science Fiction",
                "https://image.tmdb.org/t/p/w185/fj21HwUprqjjwTdkKC1XZurRSpV.jpg",
                "2011-06-16"

        ))
        tvShow.add( DataResponse(
                "10",
                "Jonah Hex",
                "Gunslinger Jonah Hex is appointed by President Ulysses Grant to track down terrorist Quentin Turnbull, a former Confederate officer determined on unleashing hell on earth. Jonah not only secures freedom by accepting this task, he also gets revenge on the man who slayed his wife and child.",
                "Thriller",
                "https://image.tmdb.org/t/p/w185/1MpWjcCn8M0763QDoxcN0gXrc5q.jpg",
                "2010-06-16"

        ))


        return tvShow
    }

    fun generateDetailDummyMovie(movie : MovieEntity):MovieDetailEntity {
        return MovieDetailEntity(movie)
    }

    fun generateDetailDummyTvShow(tvShow : TvShowEntity) : TvShowDetailEntity{
        return TvShowDetailEntity(tvShow)
    }

}