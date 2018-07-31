package shuffler.youtubeshuffler.service

import org.springframework.stereotype.Service


@Service
class SongTimer {
    var isPlaying = false

    var startTimeInMillis : Long = 0

    fun startPlaying() {
        if(startTimeInMillis == 0L)
            startTimeInMillis = System.nanoTime()
    }

    fun currentTime():Double {
        return (System.nanoTime() - startTimeInMillis).div(1000000000.0)
    }
}