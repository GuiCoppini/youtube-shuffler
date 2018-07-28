package shuffler.youtubeshuffler.service

import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils


@Service
class SongTimer {
    var isPlaying = false

    var startTimeInMillis : Long = 0

    fun startPlaying() {
        if(startTimeInMillis == 0L)
            startTimeInMillis = System.currentTimeMillis()
    }

    fun currentTime():Double {
        return (System.currentTimeMillis() - startTimeInMillis).div(1000.0)
    }
}