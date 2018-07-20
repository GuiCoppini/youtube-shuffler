package shuffler.youtubeshuffler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class YoutubeShufflerApplication

fun main(args: Array<String>) {
    runApplication<YoutubeShufflerApplication>(*args)
}
